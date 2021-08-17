package com.guba.springdata.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("spring.mysql.hibernate")
public class HibernateProperties {
    public static final String SHOW_SQL = "hibernate.show_sql";
    public static final String USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    public static final String USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String H_DIALECT = "hibernate.dialect";

    private boolean showSql;
    private boolean useSqlComments;
    private String ddlAuto;
    private boolean useQueryCache;
    private boolean useSecondLevelCache;
    private String dialect;
}
