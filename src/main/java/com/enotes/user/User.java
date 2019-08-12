package com.enotes.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


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
}
