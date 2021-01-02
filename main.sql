CREATE DATABASE enotes;
USE enotes;

CREATE TABLE IF NOT EXISTS user_roles
(
    id   INT AUTO_INCREMENT,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id           INT AUTO_INCREMENT,
    first_name   VARCHAR(25)   NOT NULL,
    last_name    VARCHAR(25)   NOT NULL,
    password     VARCHAR(2000) NOT NULL,
    email        VARCHAR(50)   NOT NULL UNIQUE,
    age          TINYINT       NOT NULL CHECK (age > 3 AND age < 100),
    registration DATE          NOT NULL,
    country      VARCHAR(25)   NOT NULL,
    role_id      INT           NOT NULL DEFAULT 1,
    active       BOOLEAN                DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES user_roles (id)
);

CREATE TABLE IF NOT EXISTS notes
(
    id           INT AUTO_INCREMENT,
    header       VARCHAR(500) NOT NULL,
    body         VARCHAR(5000),
    state        VARCHAR(10)  NOT NULL,
    user_id      INT          NOT NULL,
    is_deleted   BOOLEAN DEFAULT FALSE,
    is_done      BOOLEAN DEFAULT FALSE,
    created      DATE         NOT NULL,
    last_modified DATE,
    archived_at     DATE,
    is_archived  BOOLEAN DEFAULT FALSE,
    version      INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id      INT AUTO_INCREMENT,
    text    VARCHAR(2000) NOT NULL,
    user_id INT           NOT NULL,
    note_id INT           NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (note_id) REFERENCES notes (id)
);

INSERT INTO user_roles
VALUES (1, 'User'),
       (2, 'Admin'),
       (3, 'God');

INSERT INTO users (first_name, last_name, password, email, age, registration, country, role_id)
VALUES ('admin', 'admin', 'A12345', 'admin@mail.com', 21, '2019-01-01', 'Ukraine', 3);

