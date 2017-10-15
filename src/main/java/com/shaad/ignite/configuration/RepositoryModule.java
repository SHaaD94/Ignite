package com.shaad.ignite.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.shaad.ignite.repo.CellRepository;
import com.shaad.ignite.repo.CellRepositoryImpl;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.ConnectorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

public class RepositoryModule extends AbstractModule {
    @Override
    protected void configure() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        ConnectorConfiguration connCfg = new ConnectorConfiguration();
        connCfg.setHost("127.0.0.1");
        connCfg.setPort(11211);
        connCfg.setThreadPoolSize(10);
        cfg.setConnectorConfiguration(connCfg);

        bind(Ignite.class).toInstance(Ignition.start(cfg));
        bind(CellRepository.class).to(CellRepositoryImpl.class).in(Singleton.class);
    }
}
