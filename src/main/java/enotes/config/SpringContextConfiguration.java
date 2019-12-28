package enotes.config;

import java.sql.SQLException;

import enotes.note.dao.JdbcNoteDao;
import enotes.note.dao.NoteDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import enotes.db.ConnectionPool;
import enotes.db.JdbcConnectionPool;

@Configuration
public class SpringContextConfiguration {

    private final static String URL = "jdbc:mysql://localhost:3306/enotes";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    @Bean
    public ConnectionPool getConnectionPool() throws SQLException {
        return JdbcConnectionPool.create(URL, USER, PASSWORD);
    }
}
