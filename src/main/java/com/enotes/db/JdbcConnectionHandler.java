package com.enotes.db;

import java.sql.Connection;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public abstract class JdbcConnectionHandler {

    private ConnectionPool connectionPool;

    @Autowired
    public void setPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Connection getConnection() {
        return connectionPool.getConnection();
    }

    public void closeConnection(Connection connection) {
        connectionPool.releaseConnection(connection);
    }
}
