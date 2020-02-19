package enotes.entity.comment.dao;

import enotes.entity.comment.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaCommentDaoImpl implements CommentDao {
    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void add(Comment entity) {

    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
