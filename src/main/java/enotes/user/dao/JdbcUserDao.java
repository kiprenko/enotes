package enotes.user.dao;

import enotes.db.ConnectionManager;
import enotes.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
public class JdbcUserDao implements UserDao {

    private ConnectionManager connectionManager;

    private static final String ADD_NEW_USER_SQL = "INSERT INTO users (first_name, last_name, email, password, age, registration, country, role)" +
            " VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s', %d);";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE id = %d;";


    @Autowired
    public JdbcUserDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

        } catch (SQLException e) {
            LOGGER.error("An error during getting of all users list: ", e);
        }
        return null;
    }

    @Override
    public User find(Long id) {
//        Connection connection = connectionManager.getConnection();

        return null;
    }

    @Override
    public boolean add(User entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Adding new user.");
            statement.execute(String.format(
                    ADD_NEW_USER_SQL,
                    entity.getFirstName(), entity.getLastName(),
                    entity.getEmail(), entity.getPassword(),
                    entity.getAge(), convertDate(entity.getRegistration()),
                    entity.getCountry(), entity.getRole().getRoleId()
            ));
            LOGGER.info("User adding query executed successfully.");

        } catch (SQLException e) {
            LOGGER.error("Error during creation of a new user: ", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    private String convertDate(Date date) {
        return Optional.ofNullable(date)
                .map((x) -> new SimpleDateFormat("yyyy-MM-dd").format(x))
                .orElseThrow(IllegalArgumentException::new);
    }
}
