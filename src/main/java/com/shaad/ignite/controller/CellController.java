package com.shaad.ignite.controller;

import com.google.common.collect.Lists;
import com.shaad.ignite.dto.ProfileDTO;
import org.rapidoid.http.Req;
import org.rapidoid.http.ReqHandler;
import org.rapidoid.setup.On;

import java.util.Collection;
import java.util.Date;

public class CellController implements Controller {
    @Override
    public void start() {
        On.get("/cells/{id}/profiles").json((ReqHandler) (this::getProfilesByCellId));
    }

    private Collection<ProfileDTO> getProfilesByCellId(Req req) {
        Long cellId = Long.parseLong(req.param("id"));
        ProfileDTO profile = new ProfileDTO(cellId, "as", "asdas@email.com", new Date());
        return Lists.newArrayList(profile, profile, profile, profile, profile, profile);
    }
}
