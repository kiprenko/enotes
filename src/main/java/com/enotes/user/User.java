package com.enotes.user;

import java.io.Serializable;
import java.util.List;

import com.enotes.note.Note;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(of = {"firstName", "lastName", "email"})
@NoArgsConstructor
@ToString
public class User implements Serializable {
    long id;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String email;
    String password;
    @Singular
    private List<Note> notes;
}
