package enotes.comment.dao;

import enotes.comment.Comment;
import enotes.db.ConnectionManager;
import enotes.note.Note;
import enotes.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

@Log4j2
@Component
@Profile("jdbc")
public class JdbcCommentDao implements CommentDao {

    private ConnectionManager connectionManager;

    private static final String ADD_NEW_COMMENT_SQL =
            "INSERT INTO comments (text, user_id, note_id) VALUES ('%s', %d, %d);";
    private static final String UPDATE_COMMENT_BY_ID_SQL =
            "UPDATE comments SET text='%s', user_id=%d, note_id=%d WHERE id=%d;";
    private static final String DELETE_COMMENT_BY_ID_SQL =
            "DELETE FROM comments WHERE id=%d;";
    private static final String SELECT_COMMENT_BY_ID_SQL =
            "SELECT * FROM comments WHERE id=%d;";
    private static final String SELECT_ALL_COMMENTS_SQL =
            "SELECT * FROM comments;";

    @Autowired
    public JdbcCommentDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Comment> findAll() {
        Comment comment;
        List<Comment> comments;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_COMMENTS_SQL)) {

            comments = new ArrayList<>();
            while (resultSet.next()) {
                comment = new Comment();
                fillCommentFromResultSet(comment, resultSet);
                comments.add(comment);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }

        return comments;
    }

    @Override
    public Comment find(Long id) {
        Comment comment = null;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(format(SELECT_COMMENT_BY_ID_SQL, id))) {

            if (resultSet.next()) {
                comment = new Comment();
                fillCommentFromResultSet(comment, resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }

        return comment;
    }

    private void fillCommentFromResultSet(Comment comment, ResultSet resultSet) throws SQLException {
        User user = User.builder().id(resultSet.getLong("user_id")).build();
        Note note = Note.builder().id(resultSet.getLong("note_id")).build();
        comment.setId(resultSet.getLong("id"));
        comment.setText(resultSet.getString("text"));
        comment.setUser(user);
        comment.setNote(note);
    }

    @Override
    public void add(Comment entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Adding new comment to note with id = {} from user with id = {}",
                    entity.getNote().getId(), entity.getUser().getId());
            statement.execute(getPreparedInsertSql(entity));
            LOGGER.info("Comment for note with id = {} from user with id = {} was successfully added.",
                    entity.getNote().getId(), entity.getUser().getId());

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private String getPreparedInsertSql(Comment comment) {
        return format(ADD_NEW_COMMENT_SQL,
                comment.getText(), comment.getUser().getId(), comment.getNote().getId());
    }

    @Override
    public void update(Comment entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Updating comment with id = {}", entity.getId());
            statement.execute(getPreparedUpdateSql(entity));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private String getPreparedUpdateSql(Comment comment) {
        return format(UPDATE_COMMENT_BY_ID_SQL,
                comment.getText(), comment.getUser().getId(),
                comment.getNote().getId(), comment.getId());
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Deleting comment with id = {}", id);
            statement.execute(format(DELETE_COMMENT_BY_ID_SQL, id));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
