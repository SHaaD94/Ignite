package com.shaad.ignite.configuration;

import com.google.inject.Guice;

public class Injector {
    private final com.google.inject.Injector injector;

    public Injector() {
        injector = Guice.createInjector(
                new ControllersModule(),
                new RepositoryModule(),
                new ServiceModule()
        );
    }

    public <T> T getInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }
}
