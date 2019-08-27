package com.enotes.db;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractJdbcDao {

    ConnectionPool connectionPool;

    @Autowired
    public void setPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Connection createConnection() {
        //TODO use Apache commons DBCP (Connection pool)
        return null;
    }
}
