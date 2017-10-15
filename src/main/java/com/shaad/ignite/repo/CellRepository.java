package com.shaad.ignite.repo;

import com.shaad.ignite.domain.Profile;

import java.util.Collection;

public interface CellRepository {
    boolean doesCellExist(long cellId);

    void saveCell(long cellId);

    void saveProfile(long cellId, Profile profile);

    Collection<Profile> getProfilesByCellId(long cellId);
}
