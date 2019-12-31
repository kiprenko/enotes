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
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE id=%d;";
    private static final String UPDATE_USER_BY_ID_SQL = "UPDATE users " +
            "SET first_name = '%s', last_name = '%s', email = '%s', password = '%s', age = '%d', registration = '%s', country = '%s', role = '%d' " +
            "WHERE id = '%d';";


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
        User user = null;
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
            statement.execute(getPreparedInsertSql(entity));
            LOGGER.info("User adding query executed successfully.");

        } catch (SQLException e) {
            LOGGER.error("Error during creation of a new user: ", e);
            return false;
        }

        return true;
    }

    private String getPreparedInsertSql(User user) {
        return format(
                ADD_NEW_USER_SQL,
                user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(),
                user.getAge(), convertDate(user.getRegistration()),
                user.getCountry(), user.getRole().getRoleId()
        );
    }

    @Override
    public boolean update(User entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Updating user record with id={}", entity.getId());
            statement.execute(getPreparedUpdateSql(entity));
            LOGGER.info("User with id={} was successfully updated.", entity.getId());

        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    private String getPreparedUpdateSql(User user) {
        return format(
                UPDATE_USER_BY_ID_SQL,
                user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(),
                user.getAge(), convertDate(user.getRegistration()),
                user.getCountry(), user.getRole().getRoleId(),
                user.getId()
        );
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(format(DELETE_USER_BY_ID_SQL, id));

        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
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
