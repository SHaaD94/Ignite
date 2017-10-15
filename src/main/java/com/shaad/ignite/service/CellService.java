package com.shaad.ignite.service;

import com.shaad.ignite.dto.ProfileDTO;

import java.util.Collection;

public interface CellService {
    Collection<ProfileDTO> getProfilesByCellId(long cellId);
}
