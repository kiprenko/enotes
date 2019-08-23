package com.enotes.note;

import com.enotes.comment.Comment;
import com.enotes.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode(of = {"header", "text", "comments", "state", "user"})
@NoArgsConstructor
@ToString
public class Note implements Serializable {
    private long id;
    @NonNull
    private String header;
    private String text;
    @Singular
    private List<Comment> comments;
    @NonNull
    private NoteState state;
    @NonNull
    private User user;
    boolean isDeleted;
}
