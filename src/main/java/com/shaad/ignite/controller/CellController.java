package com.shaad.ignite.controller;

import com.google.inject.Inject;
import com.shaad.ignite.dto.ProfileRequestDTO;
import com.shaad.ignite.dto.SuccessDTO;
import com.shaad.ignite.service.CellService;
import org.rapidoid.http.Req;
import org.rapidoid.http.ReqHandler;
import org.rapidoid.setup.On;

import static com.shaad.ignite.util.NumberUtil.safeCastToLong;
import static org.h2.mvstore.DataUtils.checkArgument;

public class CellController implements Controller {
    private final CellService cellService;

    @Inject
    public CellController(CellService cellService) {
        this.cellService = cellService;
    }

    @Override
    public void start() {
        On.get("/cells/{id}/profiles").json((ReqHandler) (this::getProfilesByCellId));
        On.post("/cells").json((ReqHandler) (this::saveCell));
    }

    private ProfileRequestDTO getProfilesByCellId(Req req) {
        Long cellId = Long.parseLong(req.param("id"));
        return new ProfileRequestDTO(cellService.getProfilesByCellId(cellId));
    }

    private Object saveCell(Req req) {
        Object id = req.data("id");
        checkArgument(id != null, "Id should exist");
        Long cellId = safeCastToLong(id);
        cellService.saveCell(cellId);
        return new SuccessDTO();
    }
}
