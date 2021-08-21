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
        entityManagerFactoryRef = "mariaDBEntityManagerFactory",
        transactionManagerRef = "mariaDBTransactionManager",
        basePackages = {"com.guba.springdata.repository.mariadb" })
public class MariaDBConfig {

    @Bean(name = "mariaDBDataSource")
    @ConfigurationProperties(prefix = "spring.mariadb.datasource")
    public DataSource mariaDBDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaDBEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mariaDBEntityManagerFactory(
            @Qualifier("mariaDBDataSource") DataSource dataSource,
            @Qualifier("mariadbHibernateProp") HibernateProp hP) {
        var a = new LocalContainerEntityManagerFactoryBean();
        a.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        a.setDataSource(dataSource);
        a.setPackagesToScan("com.guba.springdata.domain.mariadb");
        a.setJpaPropertyMap(hP.getPropertiesMap());
        return a;
    }

    @Bean(name = "mariaDBTransactionManager")
    public PlatformTransactionManager mariaDBTransactionManager(
            @Qualifier("mariaDBEntityManagerFactory") LocalContainerEntityManagerFactoryBean mariaDBEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(mariaDBEntityManagerFactory.getObject()));
    }

    @Bean("mariadbHibernateProp")
    @ConfigurationProperties("spring.mariadb.hibernate")
    public HibernateProp hibernateProp() {
        return new HibernateProp();
    }
}
