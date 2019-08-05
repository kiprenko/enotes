package enotes.entity.note;

import enotes.entity.comment.Comment;

import java.io.Serializable;
import java.util.List;

//TODO builder pattern in each bean
public class Note implements Serializable {
    private String header;
    private String Text;
    private List<Comment> comments; //TODO replace Strings by Comment Beans
    private NoteState state;
    private String author;
}
