package com.kuiteul.dbeaver.config;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JdbcConfig {
    @Value("${request.timeout:60000}")
    private int requestTimeoutMillis;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource());

        jdbcTemplate.getJdbcTemplate().setQueryTimeout(requestTimeoutMillis / 1000);
        jdbcTemplate.getJdbcTemplate().setFetchSize(250);
        return jdbcTemplate;
    }

    @Bean
    public StringSubstitutor diParamSubstitutor() {
        return new StringSubstitutor(params().getDi());
    }

    @Bean
    @ConfigurationProperties(prefix = "repository")
    public Params params() {
        return new Params();
    }


    public static class Params {
        private Map<String, String> di = new HashMap<>();


        public Map<String, String> getDi() {
            return di;
        }
    }
}

