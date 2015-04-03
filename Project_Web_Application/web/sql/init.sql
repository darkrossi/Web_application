CREATE TABLE Users(
login varchar(100) NOT NULL,
password varchar(100) NOT NULL
);
INSERT INTO Users VALUES ('toto', 'toto');
INSERT INTO Users VALUES ('titi', 'titi');

SELECT * FROM Users;