package com.enotes.note.dao;

import com.enotes.db.ConnectionPool;
import com.enotes.note.Note;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

@Log4j2
@Component
public class JdbcNoteDao implements NoteDao {

    private ConnectionPool connectionPool;

    @Autowired
    public void setConnectionPool(ConnectionPool pool) {
        this.connectionPool = pool;
    }

    @Override
    public Stream<Note> getAll() {
        return null;
    }

    @Override
    public Note find(Long id) {
        return null;
    }

    @Override
    public boolean add(Note note) {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "INSERT INTO notes (header, body, state, user_id) VALUES ('%s', '%s', '%s', %s);",
                    note.getHeader(), note.getBody(), note.getState().getStateAsString(), note.getUser().getId()
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
    public boolean update(Note note) {
        return false;
    }

    @Override
    public boolean delete(Note note) {
        return false;
    }
}
