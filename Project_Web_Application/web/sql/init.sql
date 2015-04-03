DROP Table SPECTACLES;

CREATE TABLE Spectacles (
	Id int primary key,
	Titre varchar(30),
	Auteur varchar(30));

INSERT INTO Spectacles
VALUES (1, 'Hamlet', 'Shakespeare');

COMMIT;