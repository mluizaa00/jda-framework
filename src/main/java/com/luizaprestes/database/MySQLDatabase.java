package com.luizaprestes.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import javax.sql.DataSource;

public class MySQLDatabase {

    @Getter
    private DataSource dataSource;

    public void connect(String jdbcUrl, String username, String password) {
        final HikariConfig config = new HikariConfig();

        config.setMaximumPoolSize(30);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        config.addDataSourceProperty("autoReconnect", "true");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.dataSource = new HikariDataSource(config);
    }
}
