package com.shaad.ignite.controller;

import org.rapidoid.setup.On;

public class CellController implements Controller {
    @Override
    public void start() {
        On.get("/").serve("Hello");
    }
}
