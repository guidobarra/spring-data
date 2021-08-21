package com.guba.springdata.config;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class HibernateProp {
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

    public Map<String, Object> getPropertiesMap() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(SHOW_SQL, showSql);
        properties.put(USE_SQL_COMMENTS, useSqlComments);
        properties.put(HBM2DDL_AUTO, ddlAuto);
        properties.put(USE_QUERY_CACHE, useQueryCache);
        properties.put(USE_SECOND_LEVEL_CACHE, useSecondLevelCache);
        properties.put(H_DIALECT, dialect);
        return properties;
    }
}
