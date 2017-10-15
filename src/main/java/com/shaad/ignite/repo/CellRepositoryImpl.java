package com.shaad.ignite.repo;

import com.google.inject.Inject;
import com.shaad.ignite.domain.Profile;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static com.shaad.ignite.repo.IgniteCaches.CELLS;
import static com.shaad.ignite.repo.IgniteCaches.MAIN;

public class CellRepositoryImpl implements CellRepository {
    private static final String INSERT_CTN_QUERY_TEMPLATE =
            "INSERT INTO public.Cell2Ctn(cell_id,ctn) values(?,?)";
    private static final String INSERT_PROFILE_QUERY_TEMPLATE =
            "INSERT INTO public.Profile(ctn,name,email,activationDate) values(?,?,?,?)";
    private static final String CHECK_CTN_EXISTANCE_QUERY_TEMPLATE =
            "SELECT ctn FROM public.cell2ctn where ctn=?";
    private static final String SELECT_PROFILES_BY_CELL_ID_TEMPLATE =
            "SELECT Profile.name,Profile.email,Profile.activationDate FROM public.profile where ctn " +
                    "in (select ctn from public.cell2ctn where cell_id=?)";

    private final Ignite ignite;

    @Inject
    public CellRepositoryImpl(Ignite ignite) {
        this.ignite = ignite;
        ignite.getOrCreateCache(CELLS);

    }

    @Override
    public boolean doesCellExist(long cellId) {
        return cellsCache().get(cellId) != null;
    }

    @Override
    public void saveCell(long cellId) {
        cellsCache().put(cellId, cellId);
    }

    @Override
    public void saveCtn(long cellId, long ctn) {
        mainCache().query(new SqlFieldsQuery(INSERT_CTN_QUERY_TEMPLATE).setArgs(cellId, ctn));
    }

    @Override
    public boolean doesCtnExist(long ctn) {
        return !mainCache()
                .query(new SqlFieldsQuery(CHECK_CTN_EXISTANCE_QUERY_TEMPLATE).setArgs(ctn))
                .getAll()
                .isEmpty();
    }

    @Override
    public void saveProfile(long ctn, Profile profile) {
        mainCache().query(
                new SqlFieldsQuery(INSERT_PROFILE_QUERY_TEMPLATE)
                        .setArgs(ctn, profile.getName(), profile.getName(), profile.getActivationDate().getTime()));
    }

    @Override
    public Collection<Profile> getProfilesByCellId(long cellId) {
        return mainCache().query(
                new SqlFieldsQuery(SELECT_PROFILES_BY_CELL_ID_TEMPLATE).setArgs(cellId))
                .getAll()
                .stream()
                .map(x -> new Profile((String) x.get(0), (String) x.get(1), new Date((Long) x.get(2))))
                .collect(Collectors.toList());
    }

    private IgniteCache<Object, Object> mainCache() {
        return ignite.cache(MAIN);
    }

    private IgniteCache<Object, Object> cellsCache() {
        return ignite.cache(CELLS);
    }
}
