package enotes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionManager implements ConnectionManager {
    //temporary solution
    private final static String URL = "jdbc:mysql://localhost:3306/enotes";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
