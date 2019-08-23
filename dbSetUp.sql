CREATE DATABASE enotes;
USE enotes;
CREATE TABLE IF NOT EXISTS users
(
    id           INT         NOT NULL,
    first_name   VARCHAR(25) NOT NULL,
    last_name    VARCHAR(25) NOT NULL,
    email        VARCHAR(50) NOT NULL,
    age          TINYINT     NOT NULL CHECK (age > 3 AND age < 100),
    registration DATE        NOT NULL,
    country      VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS notes
(
    id         INT          NOT NULL,
    header     VARCHAR(500) NOT NULL,
    body       VARCHAR(5000),
    state      VARCHAR(10)  NOT NULL,
    user_id    INT          NOT NULL,
    is_deleted BOOLEAN DEFAULT false,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id      SMALLINT NOT NULL,
    text    VARCHAR(2000),
    user_id INT      NOT NULL,
    note_id INT      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (note_id) REFERENCES notes (id)
);

