CREATE DATABASE enotesDB;
USE enotesDB;
CREATE TABLE IF NOT EXISTS Notes
(
    id        SMALLINT PRIMARY KEY ,
    FirstName VARCHAR(25)       NOT NULL,
    LastName  VARCHAR(25)       NOT NULL,
    Email     VARCHAR(50)       NOT NULL,
    Age       TINYINT           NOT NULL,
    RegistrationDate DATE       NOT NULL,
    Country   VARCHAR(25)       NOT NULL
)

