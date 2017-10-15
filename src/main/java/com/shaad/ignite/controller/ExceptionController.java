package com.shaad.ignite.controller;

import com.shaad.ignite.dto.ExceptionDTO;
import com.shaad.ignite.exception.CTNDoesNotExistException;
import com.shaad.ignite.exception.CellDoesNotExistException;
import org.rapidoid.http.NotFound;
import org.rapidoid.http.Resp;
import org.rapidoid.setup.My;

public class ExceptionController implements Controller {
    @Override
    public void start() {
        My.errorHandler((req, resp, error) -> {
            if (error.getClass().equals(NotFound.class)) {
                return notFound(resp);
            } else if (error.getClass().equals(CellDoesNotExistException.class) ||
                    error.getClass().equals(CTNDoesNotExistException.class)) {
                return entityDoesNotExist(resp, error);
            } else {
                return badRequest(resp, error);
            }
        });
    }

    private ExceptionDTO entityDoesNotExist(Resp resp, Throwable error) {
        resp.code(404);
        return new ExceptionDTO(error.getMessage());
    }

    private ExceptionDTO badRequest(Resp resp, Throwable error) {
        resp.code(400);
        return new ExceptionDTO(error.getMessage());
    }

    private ExceptionDTO notFound(Resp resp) {
        resp.code(404);
        return new ExceptionDTO("Not found");
    }

}
