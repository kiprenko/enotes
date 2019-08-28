package com.enotes.comment;

import com.enotes.note.Note;
import com.enotes.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode(of = {"text", "user"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Comment implements Serializable {
    private Long id;
    @NonNull
    private String text;
    @NonNull
    private User user;
    @NotNull
    private Note note;
}
