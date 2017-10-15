package com.shaad.ignite;

import com.shaad.ignite.configuration.Injector;
import com.shaad.ignite.controller.ControllersStarter;
import com.shaad.ignite.repo.MigrationHandler;

public class Main {
    public static void main(String[] args) {
        Injector injector = new Injector();

        injector.getInstance(MigrationHandler.class).handle();
        injector.getInstance(ControllersStarter.class).startAll();
    }
}
