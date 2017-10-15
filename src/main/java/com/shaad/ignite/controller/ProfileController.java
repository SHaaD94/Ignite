package com.shaad.ignite.controller;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.shaad.ignite.dto.ProfileDTO;
import com.shaad.ignite.dto.SuccessDTO;
import com.shaad.ignite.service.CellService;
import org.rapidoid.http.Req;
import org.rapidoid.http.ReqHandler;
import org.rapidoid.setup.On;

import java.util.Date;

import static org.h2.mvstore.DataUtils.checkArgument;

public class ProfileController implements Controller {
    private final CellService cellService;

    @Inject
    public ProfileController(CellService cellService) {
        this.cellService = cellService;
    }

    @Override
    public void start() {
        On.post("/profiles").json((ReqHandler) (this::saveProfile));
    }

    private Object saveProfile(Req req) {
        Object ctn = req.data("ctn");
        String name = req.data("name");
        String email = req.data("email");
        checkArgument(ctn != null, "Ctn should exist");
        checkArgument(!Strings.isNullOrEmpty(name), "Name should exist");
        checkArgument(!Strings.isNullOrEmpty(email), "Email should exist");
        //checkArgument(req.data("date"), "Date should exist");

        cellService.saveProfile(new ProfileDTO(
                Long.parseLong(ctn.toString()),
                req.data(name),
                req.data(email),
                new Date()));

        return new SuccessDTO();
    }
}
