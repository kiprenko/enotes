package com.enotes.entity.comment;

import com.enotes.entity.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode(of = {"text", "user"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Comment implements Serializable {
    private long id;
    @NonNull
    private String text;
    @NonNull
    private User user;
}
