package com.enotes.config;

import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enotes.db.ConnectionPool;
import com.enotes.db.ConnectionPoolImpl;

@Configuration
public class SpringContextConfiguration {
    @Bean
    public ConnectionPool getConnectionPool() throws SQLException
    {
        return ConnectionPoolImpl.create("","", "");
    }
}
