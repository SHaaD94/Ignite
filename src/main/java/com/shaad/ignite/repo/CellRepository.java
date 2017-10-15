package com.shaad.ignite.repo;

import com.shaad.ignite.domain.Profile;

import java.util.Collection;

public interface CellRepository {
    boolean doesCellExist(long cellId);

    Collection<Profile> getProfilesByCellId(long cellId);
}
