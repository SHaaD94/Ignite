package com.shaad.ignite.repo;

import com.google.common.collect.Lists;
import com.shaad.ignite.domain.Profile;

import java.util.Collection;
import java.util.Date;

public class CellRepositoryImpl implements CellRepository {
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
