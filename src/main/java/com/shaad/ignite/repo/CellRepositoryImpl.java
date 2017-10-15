package com.shaad.ignite.repo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.shaad.ignite.domain.Profile;
import org.apache.ignite.Ignite;

import java.util.Collection;
import java.util.Date;

public class CellRepositoryImpl implements CellRepository {
    private final Ignite ignite;

    @Inject
    public CellRepositoryImpl(Ignite ignite) {
        this.ignite = ignite;
    }

    @Override
    public boolean doesCellExist(long cellId) {
        return true;
    }

    @Override
    public Collection<Profile> getProfilesByCellId(long cellId) {
        Profile profile = new Profile("as", "asdas@email.com", new Date());
        return Lists.newArrayList(profile, profile, profile, profile);
    }
}
