package com.github.liudi0105.helidon.admin.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Setter;

import javax.sql.DataSource;

@ApplicationScoped
public class DatasourceConfig {

    @Inject
    @Named("test")
    @Setter
    private DataSource ds;
}
