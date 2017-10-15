package com.shaad.ignite.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.shaad.ignite.controller.CellController;
import com.shaad.ignite.controller.Controller;

public class ControllersModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<Controller> controllers = Multibinder.newSetBinder(binder(), Controller.class);
        controllers.addBinding().to(CellController.class);
    }
}
