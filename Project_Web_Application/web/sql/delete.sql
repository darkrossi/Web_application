-- DELETE FROM Dossier;
-- DELETE FROM Representation;
-- DELETE FROM Place;
-- DELETE FROM Rang;
-- DELETE FROM Ticket;
-- DELETE FROM Spectacle;
-- DELETE FROM Salle;
-- DELETE FROM Users;

DROP TABLE Dossier cascade constraint;
DROP TABLE Representation cascade constraint;
DROP TABLE Place cascade constraint;
DROP TABLE Rang cascade constraint;
DROP TABLE Ticket cascade constraint;
DROP TABLE Spectacle cascade constraint;
DROP TABLE Salle cascade constraint;
DROP TABLE Users cascade constraint;