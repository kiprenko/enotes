package enotes.entity.comment;

import enotes.entity.user.User;

import java.io.Serializable;

public class Comment implements Serializable {
    private long id;
    private String text;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Builder getBuilder() {
        return new Comment().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setId(long id) {
            Comment.this.setId(id);
            return this;
        }

        public Builder setText(String text) {
            Comment.this.setText(text);
            return this;
        }

        public Builder setUser(User user) {
            Comment.this.setUser(user);
            return this;
        }

        public Comment build() {
            return Comment.this;
        }
    }
}
