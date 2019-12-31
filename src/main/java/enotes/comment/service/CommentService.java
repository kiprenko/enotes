package enotes.comment.service;

import enotes.comment.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    long delete(Comment comment);

    long delete(long id);

    Comment get(Comment comment);

    Comment get(long id);

    Comment update(Comment comment);

    List<Comment> getAllComments();
}
