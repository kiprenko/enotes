package enotes.note.dao;

import enotes.db.ConnectionPool;
import enotes.db.JdbcHelper;
import enotes.note.Note;
import enotes.note.NoteState;
import enotes.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class JdbcNoteDao implements NoteDao {

    private ConnectionPool connectionPool;

    @Autowired
    public void setConnectionPool(ConnectionPool pool) {
        this.connectionPool = pool;
    }

    @Override
    public List<Note> findAll() {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        List<Note> list = null;
        Note note;
        User user;
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM notes";
            resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                note = new Note();
                user = new User();
                setupUser(resultSet, note, user);
                list.add(note);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        } finally {
            connectionPool.releaseConnection(connection);
            JdbcHelper.closeResultSet(resultSet);
        }
        return list;
    }

    private void setupUser(ResultSet resultSet, Note note, User user) throws SQLException {
        user.setId(resultSet.getLong("user_id"));
        note.setUser(user);
        note.setId(resultSet.getLong("id"));
        note.setState(NoteState.getByStringName(resultSet.getString("state")));
        note.setHeader(resultSet.getString("header"));
        note.setBody(resultSet.getString("body"));
    }

    @Override
    public Note find(Long id) {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        Note note = null;
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM notes WHERE id = %s", id);
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                note = new Note();
                User user = new User();
                setupUser(resultSet, note, user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return note;
    }

    @Override
    public boolean add(Note note) {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            LOGGER.info("Starting note adding.");
            String sql = String.format(
                    "INSERT INTO notes (header, body, state, user_id) VALUES ('%s', '%s', '%s', %s);",
                    note.getHeader(), note.getBody(), note.getState().getStateAsString(), note.getUser().getId()
            );
            statement.execute(sql);
            LOGGER.info("Note adding query executed successfully.");
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

    @Override
    public boolean update(Note note) {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "UPDATE notes SET header = '%s', body = '%s', state = '%s' WHERE id = %s;",
                    note.getHeader(), note.getBody(), note.getState().getStateAsString(), note.getId()
            );
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("DELETE FROM notes WHERE id = %s;", id);
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }
}
