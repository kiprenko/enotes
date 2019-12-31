package enotes.note.dao;

import enotes.db.ConnectionManager;
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

import static java.lang.String.format;

@Log4j2
@Component
public class JdbcNoteDao implements NoteDao {

    private ConnectionManager connectionManager;

    private static final String ADD_NEW_NOTE_SQL = "INSERT INTO notes (header, body, state, user_id) VALUES ('%s', '%s', '%s', %d);";
    private static final String SELECT_ALL_NOTES_SQL = "SELECT * FROM notes WHERE is_deleted=0;";
    private static final String SELECT_NOTE_BY_ID_SQL = "SELECT * FROM notes WHERE id = %d AND is_deleted=0;";
    private static final String UPDATE_NOTE_SQL = "UPDATE notes SET header = '%s', body = '%s', state = '%s' WHERE id = %s;";
    private static final String DELETE_NOTE_BY_ID_SQL = "UPDATE notes SET is_deleted=1 WHERE id = %s;";

    @Autowired
    public JdbcNoteDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Note> findAll() {
        List<Note> list;
        Note note;
        User user;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_NOTES_SQL)) {

            list = new ArrayList<>();
            while (resultSet.next()) {
                note = new Note();
                user = new User();
                fillNoteFromResultSet(resultSet, note, user);
                list.add(note);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }

        return list;
    }

    private void fillNoteFromResultSet(ResultSet resultSet, Note note, User user) throws SQLException {
        user.setId(resultSet.getLong("user_id"));
        note.setUser(user);
        note.setId(resultSet.getLong("id"));
        note.setState(NoteState.getByStringName(resultSet.getString("state")));
        note.setHeader(resultSet.getString("header"));
        note.setBody(resultSet.getString("body"));
    }

    @Override
    public Note find(Long id) {
        Note note = null;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(format(SELECT_NOTE_BY_ID_SQL, id))) {

            if (resultSet.next()) {
                note = new Note();
                User user = new User();
                fillNoteFromResultSet(resultSet, note, user);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }

        return note;
    }

    @Override
    public boolean add(Note entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Adding new note.");
            statement.execute(format(
                    ADD_NEW_NOTE_SQL,
                    entity.getHeader(), entity.getBody(), entity.getState().getStateAsString(), entity.getUser().getId()
            ));

            LOGGER.info("Note adding query executed successfully.");
        } catch (SQLException e) {
            LOGGER.error("Error during new note creation for user with id={}: ", entity.getUser().getId(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Note entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Updating a note with id={} for a user with id={}", entity.getId(), entity.getUser().getId());
            statement.execute(format(
                    UPDATE_NOTE_SQL,
                    entity.getHeader(), entity.getBody(), entity.getState().getStateAsString(), entity.getId()
            ));

        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(format(DELETE_NOTE_BY_ID_SQL, id));

        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }

        return true;
    }
}
