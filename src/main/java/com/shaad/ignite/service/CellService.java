package com.shaad.ignite.service;

import com.shaad.ignite.dto.ProfileDTO;

import java.util.Collection;

public interface CellService {
    void saveCell(long cellId);

    void saveProfile(long cellId, ProfileDTO profileDTO);

    Collection<ProfileDTO> getProfilesByCellId(long cellId);
}
