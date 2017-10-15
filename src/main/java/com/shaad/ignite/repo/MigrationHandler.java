package com.shaad.ignite.repo;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.shaad.ignite.repo.IgniteCaches.MAIN;
import static com.shaad.ignite.repo.IgniteCaches.MIGRATIONS;

//fixme really dumb migration solution
public class MigrationHandler {
    private static final String LAST_MIGRATION = "lastMigration";
    private final Logger log = LoggerFactory.getLogger(MigrationHandler.class);
    private final Ignite ignite;
    private final Map<Integer, Runnable> migrations = new HashMap<>();

    @Inject
    public MigrationHandler(Ignite ignite) {
        this.ignite = ignite;
        migrations.put(1, this::v1);
    }

    public void handle() {
        IgniteCache<String, Integer> migrationCache = ignite.getOrCreateCache(MIGRATIONS);
        Integer lastMigration = MoreObjects.firstNonNull(migrationCache.get(LAST_MIGRATION), 0);

        migrations
                .entrySet()
                .stream()
                .filter(x -> x.getKey() > lastMigration)
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEachOrdered(x -> {
                    log.info("Executing migration {}", x.getKey());
                    x.getValue().run();
                    migrationCache.put(LAST_MIGRATION, x.getKey());
                });
    }

    private void v1() {
        IgniteCache cache = ignite.getOrCreateCache(MAIN);
        SqlFieldsQuery createProfile = new SqlFieldsQuery(
                "CREATE TABLE IF NOT EXISTS public.Profile (ctn int, name varchar, email varchar, PRIMARY KEY (ctn))");
        cache.query(createProfile);
        SqlFieldsQuery createCell2Ctn = new SqlFieldsQuery(
                "CREATE TABLE IF NOT EXISTS public.Cell2Ctn(cell_id int, ctn int PRIMARY KEY)");
        cache.query(createCell2Ctn);
        SqlFieldsQuery indexQuery = new SqlFieldsQuery(
                "CREATE INDEX idx_ctn_to_cell_id ON public.Cell2Ctn (cell_id)"
        );
        cache.query(indexQuery);

    }
}
