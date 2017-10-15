package com.shaad.ignite.controller;

import com.google.inject.Inject;
import com.shaad.ignite.dto.ProfileDTO;
import com.shaad.ignite.service.CellService;
import org.rapidoid.http.Req;
import org.rapidoid.http.ReqHandler;
import org.rapidoid.setup.On;

import java.util.Collection;

public class CellController implements Controller {
    private final CellService cellService;

    @Inject
    public CellController(CellService cellService) {
        this.cellService = cellService;
    }

    @Override
    public void start() {
        On.get("/cells/{id}/profiles").json((ReqHandler) (this::getProfilesByCellId));
    }

    private Collection<ProfileDTO> getProfilesByCellId(Req req) {
        Long cellId = Long.parseLong(req.param("id"));
        return cellService.getProfilesByCellId(cellId);
    }
}
