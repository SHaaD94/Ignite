package com.shaad.ignite.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.shaad.ignite.repo.CellRepository;
import com.shaad.ignite.repo.CellRepositoryImpl;

public class RepositoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CellRepository.class).to(CellRepositoryImpl.class).in(Singleton.class);
    }
}
