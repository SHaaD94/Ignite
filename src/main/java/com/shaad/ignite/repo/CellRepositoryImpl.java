package com.shaad.ignite.repo;

import com.google.inject.Inject;
import com.shaad.ignite.domain.Profile;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static com.shaad.ignite.repo.IgniteCaches.CELLS;
import static com.shaad.ignite.repo.IgniteCaches.MAIN;
import static com.shaad.ignite.util.NumberUtil.safeCastToLong;

public class CellRepositoryImpl implements CellRepository {
    private static final String INSERT_CTN_QUERY_TEMPLATE =
            "INSERT INTO public.Cell2Ctn(cell_id,ctn) values(?,?)";
    private static final String INSERT_PROFILE_QUERY_TEMPLATE =
            "INSERT INTO public.Profile(ctn,name,email,activationDate) values(?,?,?,?)";
    private static final String SELECT_PROFILES_BY_CELL_ID_TEMPLATE =
            "SELECT Profile.ctn, Profile.name, Profile.email, Profile.activationDate FROM public.profile where ctn " +
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
    public void saveProfile(long cellId, Profile profile) {
        long ctn = getCTNGenerator().incrementAndGet();
        mainCache().query(new SqlFieldsQuery(INSERT_CTN_QUERY_TEMPLATE).setArgs(cellId, ctn));
        mainCache().query(
                new SqlFieldsQuery(INSERT_PROFILE_QUERY_TEMPLATE)
                        .setArgs(ctn,
                                profile.getName(),
                                profile.getEmail(),
                                profile.getActivationDate().getTime()));
    }

    @Override
    public Collection<Profile> getProfilesByCellId(long cellId) {
        return mainCache().query(
                new SqlFieldsQuery(SELECT_PROFILES_BY_CELL_ID_TEMPLATE).setArgs(cellId))
                .getAll()
                .stream()
                .map(x -> new Profile(
                        safeCastToLong(x.get(0)),
                        (String) x.get(1),
                        (String) x.get(2),
                        new Date(safeCastToLong(x.get(3)))))
                .collect(Collectors.toList());
    }

    private IgniteAtomicSequence getCTNGenerator() {
        return ignite.atomicSequence("ctnGenerator", 123456789, true);
    }

    private IgniteCache<Object, Object> mainCache() {
        return ignite.cache(MAIN);
    }

    private IgniteCache<Object, Object> cellsCache() {
        return ignite.cache(CELLS);
    }
}
