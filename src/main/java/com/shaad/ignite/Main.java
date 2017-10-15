package com.shaad.ignite;

import com.shaad.ignite.configuration.Injector;
import com.shaad.ignite.controller.ControllersStarter;

public class Main {
    public static void main(String[] args) {
        Injector injector = new Injector();

        injector.getInstance(ControllersStarter.class).startAll();
    }
}
