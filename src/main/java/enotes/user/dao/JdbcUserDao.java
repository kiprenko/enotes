package enotes.user.dao;

import enotes.db.ConnectionManager;
import enotes.user.User;
import enotes.user.role.UserRole;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Log4j2
@Component
public class JdbcUserDao implements UserDao {

    private ConnectionManager connectionManager;

    private static final String ADD_NEW_USER_SQL = "INSERT INTO users (first_name, last_name, email, password, age, registration, country, role)" +
            " VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s', %d);";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE id = %d;";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM users;";


    @Autowired
    public JdbcUserDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        User user;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS_SQL)) {

            users = new ArrayList<>();
            while (resultSet.next()) {
                user = new User();
                fillUserFromResultSet(user, resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error("An error during getting of all users list: ", e);
            return null;
        }

        return users;
    }

    @Override
    public User find(Long id) {
        User  user = null;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(format(SELECT_USER_BY_ID_SQL, id))) {

            if (resultSet.next()) {
                user = new User();
                fillUserFromResultSet(user, resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }

        return user;
    }

    @Override
    public boolean add(User entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Adding new user.");
            statement.execute(format(
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

    private void fillUserFromResultSet(User user, ResultSet resultSet) throws SQLException {
        if (user == null) {
            return;
        }

        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setAge(resultSet.getInt("age"));
        user.setRegistration(resultSet.getDate("registration"));
        user.setCountry(resultSet.getString(("country")));
        user.setRole(UserRole.getRoleById(resultSet.getInt("role")));
    }
}
