package enotes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
public class JdbcConnectionPool implements ConnectionPool {

    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 10;

    public static ConnectionPool create(String url,
                                        String user,
                                        String password) throws SQLException {
        LOGGER.info("Created Connection Pool for user {} and url {}", user, url);
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new JdbcConnectionPool(pool);
    }

    private JdbcConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
}
