package enotes.comment.dao;

import enotes.comment.Comment;
import enotes.db.ConnectionManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static java.lang.String.format;

@Log4j2
@Component
public class JdbcCommentDao implements CommentDao {

    private ConnectionManager connectionManager;

    private static final String ADD_NEW_COMMENT_SQL =
            "INSERT INTO comments (text, user_id, note_id) VALUES ('%s', %d, %d);";
    private static final String UPDATE_COMMENT_BY_ID_SQL =
            "UPDATE comments SET text='%s', user_id=%d, note_id=%d WHERE id=%d;";
    private static final String DELETE_COMMENT_BY_ID_SQL =
            "DELETE FROM comments WHERE id=%d;";

    @Autowired
    public JdbcCommentDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment find(Long id) {
        return null;
    }

    @Override
    public boolean add(Comment entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Adding new comment to note with id = {} from user with id = {}",
                    entity.getNote().getId(), entity.getUser().getId());
            statement.execute(getPreparedInsertSql(entity));
            LOGGER.info("Comment for note with id = {} from user with id = {} was successfully added.",
                    entity.getNote().getId(), entity.getUser().getId());

        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }

        return true;
    }

    private String getPreparedInsertSql(Comment comment) {
        return format(ADD_NEW_COMMENT_SQL,
                comment.getText(), comment.getUser().getId(), comment.getNote().getId());
    }

    @Override
    public boolean update(Comment entity) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Updating comment with id = {}", entity.getId());
            statement.execute(getPreparedUpdateSql(entity));

        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }

        return true;
    }

    private String getPreparedUpdateSql(Comment comment) {
        return format(UPDATE_COMMENT_BY_ID_SQL,
                comment.getText(), comment.getUser().getId(),
                comment.getNote().getId(), comment.getId());
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Deleting comment with id = {}", id);
            statement.execute(format(DELETE_COMMENT_BY_ID_SQL, id));

        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }

        return true;
    }
}
