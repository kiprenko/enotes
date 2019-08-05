package enotes.entity.comment;

import enotes.entity.user.User;

import java.io.Serializable;

public class Comment implements Serializable {
    private String text;
    private User user;
}
