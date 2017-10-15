package com.shaad.ignite.controller;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.Set;

public class ControllersStarter {
    private final Collection<Controller> controllers;

    @Inject
    public ControllersStarter(Set<Controller> controllers) {
        this.controllers = controllers;
    }

    public void startAll() {
        controllers.forEach(Controller::start);
    }
}
