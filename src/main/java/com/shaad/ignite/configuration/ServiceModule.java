package com.shaad.ignite.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.shaad.ignite.service.CellService;
import com.shaad.ignite.service.CellServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CellService.class).to(CellServiceImpl.class).in(Singleton.class);
    }
}
