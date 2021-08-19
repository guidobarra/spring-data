package com.guba.springdata.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "com.guba.springdata.domain")
@EnableJpaRepositories(basePackages= "com.guba.springdata.repository")
@EnableTransactionManagement
public class DatasourceConfig {
}