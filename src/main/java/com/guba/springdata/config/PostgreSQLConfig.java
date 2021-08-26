package com.guba.springdata.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresDBEntityManagerFactory",
        transactionManagerRef = "postgresDBTransactionManager",
        basePackages = {"com.guba.springdata.repository.postgres" })
public class PostgreSQLConfig {

    @Bean(name = "postgresDBDataSource")
    @ConfigurationProperties(prefix = "spring.postgres.datasource")
    public DataSource postgresDBDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresDBEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresDBEntityManagerFactory(
            @Qualifier("postgresDBDataSource") DataSource dataSource,
            @Qualifier("postgresdbHibernateProp") HibernateProp hP) {
        var a = new LocalContainerEntityManagerFactoryBean();
        a.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        a.setDataSource(dataSource);
        a.setPackagesToScan("com.guba.springdata.domain.postgres");
        a.setJpaPropertyMap(hP.getPropertiesMap());
        return a;
    }

    @Bean(name = "postgresDBTransactionManager")
    public PlatformTransactionManager postgresDBTransactionManager(
            @Qualifier("postgresDBEntityManagerFactory") LocalContainerEntityManagerFactoryBean postgresDBEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(postgresDBEntityManagerFactory.getObject()));
    }

    @Bean("postgresdbHibernateProp")
    @ConfigurationProperties("spring.postgres.hibernate")
    public HibernateProp hibernateProp() {
        return new HibernateProp();
    }
}
