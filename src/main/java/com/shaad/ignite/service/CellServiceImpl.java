package com.shaad.ignite.service;

import com.google.inject.Inject;
import com.shaad.ignite.domain.Profile;
import com.shaad.ignite.dto.ProfileDTO;
import com.shaad.ignite.exception.CellDoesNotExistException;
import com.shaad.ignite.repo.CellRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class CellServiceImpl implements CellService {
    private final CellRepository cellRepository;

    @Inject
    public CellServiceImpl(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    @Override
    public void saveCell(long cellId) {
        cellRepository.saveCell(cellId);
    }

    @Override
    public void saveCtn(long cellId, long ctn) {
        if (!cellRepository.doesCellExist(cellId)) {
            throw new CellDoesNotExistException(cellId);
        }
        cellRepository.saveCtn(cellId, ctn);
    }

    @Override
    public void saveProfile(ProfileDTO profileDTO) {
        cellRepository.saveProfile(profileDTO.getCtn(),
                new Profile(profileDTO.getName(), profileDTO.getEmail(), profileDTO.getActivationDate()));
    }

    @Override
    public Collection<ProfileDTO> getProfilesByCellId(long cellId) {
        if (!cellRepository.doesCellExist(cellId)) {
            throw new CellDoesNotExistException(cellId);
        }

        return cellRepository
                .getProfilesByCellId(cellId)
                .stream()
                .map(x -> mapToDTO(cellId, x))
                .collect(Collectors.toList());
    }

    private ProfileDTO mapToDTO(long cellId, Profile profile) {
        return new ProfileDTO(cellId, profile.getName(), profile.getEmail(), profile.getActivationDate());
    }

}
