CREATE DATABASE enotes;
USE enotes;
CREATE TABLE IF NOT EXISTS users
(
    id               SMALLINT PRIMARY KEY,
    first_name        VARCHAR(25) NOT NULL,
    last_name       VARCHAR(25) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    age              TINYINT     NOT NULL,
    registration DATE        NOT NULL,
    country          VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS Notes
(
    id SMALLINT PRIMARY KEY,
    header VARCHAR(500) NOT NULL,
    body VARCHAR(5000),
    user_id SMALLINT FOREIGN KEY
)

