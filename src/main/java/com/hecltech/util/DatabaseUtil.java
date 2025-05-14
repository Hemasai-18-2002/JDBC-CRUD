package com.hecltech.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String HIKARI_CONFIG_FILE = "hikari.properties";
    private static  HikariDataSource HIKARI_DATA_SOURCE=null;
    private static HikariDataSource getDataSource(){
        if(HIKARI_DATA_SOURCE == null) {
            HikariConfig config = new HikariConfig(HIKARI_CONFIG_FILE);
            HIKARI_DATA_SOURCE = new HikariDataSource(config);
        }
        return HIKARI_DATA_SOURCE;
    }
    public static Connection getConnection(){
        try {
            Connection connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            return connection;
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }

    }
}
