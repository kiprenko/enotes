package com.enotes.config;

import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enotes.db.ConnectionPool;
import com.enotes.db.JdbcConnectionPool;

@Configuration
public class SpringContextConfiguration {

    private final static String URL = "jdbc:mysql://localhost:3306/enotes";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    @Bean
    public ConnectionPool getJdbcConnectionPool() throws SQLException {
        return JdbcConnectionPool.create(URL, USER, PASSWORD);
    }
}
