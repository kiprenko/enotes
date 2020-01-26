package enotes.comment.service;

import enotes.comment.Comment;
import enotes.comment.dao.CommentDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void save(Comment comment) {
        if (comment == null) {
            LOGGER.error("Passed comment is null, comment saving denied.");
            throw new IllegalArgumentException();
        }

        commentDao.add(comment);
    }

    @Override
    public long delete(Comment comment) {
        if (comment == null) {
            LOGGER.error("Passed comment is null, comment deletion denied.");
            throw new IllegalArgumentException();
        }

        Long commentId = comment.getId();
        if (commentId == null || commentId < 1) {
            LOGGER.error("Can't delete comment with id equals null or less than 1. Id = {}", commentId);
            throw new IllegalArgumentException();
        }

        commentDao.delete(commentId);
        return commentId;
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            LOGGER.error("Passed comment id is less than 1, comment deletion denied.");
            throw new IllegalArgumentException();
        }

        commentDao.delete(id);
        return id;
    }

    @Override
    public Optional<Comment> get(Comment comment) {
        if (comment == null) {
            LOGGER.error("Passed comment is null, comment getting denied.");
            throw new IllegalArgumentException();
        }

        Long commentId = comment.getId();
        if (commentId == null || commentId < 1) {
            LOGGER.error("Can't find comment with id equals null or less than 1. Id = {}", commentId);
            throw new IllegalArgumentException();
        }

        return commentDao.find(commentId);
    }

    @Override
    public Optional<Comment> get(long id) {
        if (id < 1) {
            LOGGER.error("Can't find comment with id less than 1. Id = {}", id);
            throw new IllegalArgumentException();
        }

        return commentDao.find(id);
    }

    @Override
    public void update(Comment comment) {
        if (comment == null) {
            LOGGER.error("Passed comment is null, comment updating denied.");
            throw new IllegalArgumentException();
        }

        Long commentId = comment.getId();
        if (commentId == null || commentId < 1) {
            LOGGER.error("Can't find comment with id equals null or less than 1, updating denied. Id = {}", commentId);
            throw new IllegalArgumentException();
        }

        commentDao.update(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }
}
