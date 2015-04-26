-- INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS) VALUES (1, 'Tambours', 'Moulin Dupont', 'Marguerite Madeleine', 240);
-- INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS) VALUES (2, 'Babar', 'Aristide Euphrate', 'Cageot Miranda', 540);

-- SELECT * from Spectacle;
-- SELECT * from Representation;
-- SELECT * from Salle;
-- SELECT * from Rang;
-- SELECT * from Place;
-- SELECT * from Users;
-- SELECT * from Dossier;
-- SELECT * from Ticket;

-- DROP TABLE Dossier;
-- DROP TABLE Representation;

-- DELETE FROM Salle;

-- create table Spectacle as select * from Spectacle2;
-- create table Users as select * from Users2;
-- create table Representation as select * from Representation2;

-- INSERT INTO Representation (NR, DateR, HeureR, NSP, NSA, NbP)
-- SELECT NR, DateR, HeureR, NSP, NSA, NbP
-- FROM Representation2;

-- select * from Rang r, Place p where r.NSa = 1 and p.NRa = r.NRa and p.isTaken = 0;

-- DELETE FROM Dossier;

-- select * from Spectacle s, Representation rep, Salle sa, Rang rg where rep.NSp = s.NSp and rep.NSa = sa.NSa and sa.NSa = rg.NSa and (s.NomS like '%ro%' or s.AuteurS like '%ro%' or s.MESS like '%ro%' or s.InfoS like '%ro%');

UPDATE Spectacle
SET NoteS = 4;
























