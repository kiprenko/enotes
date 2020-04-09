package enotes.data.comment;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException();
        }

        commentRepository.save(comment);
    }

    @Override
    public long delete(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException();
        }

        commentRepository.delete(comment);
        return comment.getId();
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Can't delete comment with id equals null or less than 1. Id = %d", id));
        }

        commentRepository.deleteById(id);
        return id;
    }

    @Override
    public Optional<Comment> get(long id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Can't find comment with id less than 1. Id = %d", id));
        }

        return commentRepository.findById(id);
    }

    @Override
    public void update(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException();
        }

        Long commentId = comment.getId();
        if (commentId == null || commentId < 1) {
            throw new IllegalArgumentException(String.format("Can't find comment with id equals null or less than 1, updating denied. Id = %d", commentId));
        }

        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }
}
