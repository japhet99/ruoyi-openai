package com.ruoyi.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class DatabaseService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String fetchDataFromDatabase() {
        return jdbcTemplate.queryForList("SELECT config_value FROM sys_config where config_key = 'milvusHost' limit 1", String.class).get(0);
    }
}