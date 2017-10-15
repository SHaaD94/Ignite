package com.shaad.ignite.controller;

import com.google.inject.Inject;
import com.shaad.ignite.dto.SuccessDTO;
import com.shaad.ignite.service.CellService;
import org.rapidoid.http.Req;
import org.rapidoid.http.ReqHandler;
import org.rapidoid.setup.On;

import static org.h2.mvstore.DataUtils.checkArgument;

public class CtnController implements Controller {
    private final CellService cellService;

    @Inject
    public CtnController(CellService cellService) {
        this.cellService = cellService;
    }

    @Override
    public void start() {
        On.post("/ctns").json((ReqHandler) (this::saveCtn));
    }

    private Object saveCtn(Req req) {
        Object cellId = req.data("cell_id");
        Object id = req.data("id");
        checkArgument(cellId != null && id != null, "Cell_id and ctn should exist");
        cellService.saveCtn(Long.parseLong(cellId.toString()), Long.parseLong(id.toString()));
        return new SuccessDTO();
    }
}
