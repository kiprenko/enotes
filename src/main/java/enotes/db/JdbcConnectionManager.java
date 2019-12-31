package enotes.db;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.Objects.requireNonNull;

@Component
@PropertySource("classpath:configuration/base.properties")
public class JdbcConnectionManager implements ConnectionManager {

    private final static String URL = "db.url";
    private final static String USER = "db.user";
    private final static String PASSWORD = "db.password";

    private Environment env;

    public JdbcConnectionManager(Environment env) {
        this.env = env;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                requireNonNull(env.getProperty(URL)),
                requireNonNull(env.getProperty(USER)),
                requireNonNull(env.getProperty(PASSWORD))
        );
    }
}
