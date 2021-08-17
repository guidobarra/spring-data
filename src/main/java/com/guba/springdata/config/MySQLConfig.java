package com.guba.springdata.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.guba.springdata.config.HibernateProperties.*;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.guba.springdata.domain")
@EnableJpaRepositories(
        entityManagerFactoryRef = "mySQLEntityManagerFactory",
        transactionManagerRef = "mySQLTransactionManager",
        basePackages = {"com.guba.springdata.repository" })
public class MySQLConfig {
    
    @Bean(name = "mySQLDataSource")
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySQLEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mySQLEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("mySQLDataSource") DataSource dataSource,
                                                                           HibernateProperties hP) {
        Map<String, Object> properties = new HashMap<>();

        properties.put(SHOW_SQL, hP.isShowSql());
        properties.put(USE_SQL_COMMENTS, hP.isUseSqlComments());
        properties.put(HBM2DDL_AUTO, hP.getDdlAuto());
        properties.put(USE_QUERY_CACHE, hP.isUseQueryCache());
        properties.put(USE_SECOND_LEVEL_CACHE, hP.isUseSecondLevelCache());
        properties.put(H_DIALECT, hP.getDialect());
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.guba.springdata.domain")
                .build();
    }

    @Bean(name = "mySQLTransactionManager")
    public PlatformTransactionManager mySQLTransactionManager(
            @Qualifier("mySQLEntityManagerFactory") EntityManagerFactory mySQLEntityManagerFactory) {
        return new JpaTransactionManager(mySQLEntityManagerFactory);
    }
}
