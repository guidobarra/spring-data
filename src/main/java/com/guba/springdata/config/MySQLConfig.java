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
        entityManagerFactoryRef = "mySQLEntityManagerFactory",
        transactionManagerRef = "mySQLTransactionManager",
        basePackages = {"com.guba.springdata.repository.mysql" })
public class MySQLConfig {
    
    @Bean(name = "mySQLDataSource")
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource mySQLDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySQLEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mySQLEntityManagerFactory(
            @Qualifier("mySQLDataSource") DataSource dataSource,
            @Qualifier("mysqlHibernateProp") HibernateProp hP) {
        var a = new LocalContainerEntityManagerFactoryBean();
        a.setDataSource(dataSource);
        a.setPackagesToScan("com.guba.springdata.domain.mysql");
        a.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        a.setJpaPropertyMap(hP.getPropertiesMap());
        return a;
    }

    @Bean(name = "mySQLTransactionManager")
    public PlatformTransactionManager mySQLTransactionManager(
            @Qualifier("mySQLEntityManagerFactory") LocalContainerEntityManagerFactoryBean mySQLEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(mySQLEntityManagerFactory.getObject()));
    }

    @Bean("mysqlHibernateProp")
    @ConfigurationProperties("spring.mysql.hibernate")
    public HibernateProp hibernateProp() {
        return new HibernateProp();
    }
}
