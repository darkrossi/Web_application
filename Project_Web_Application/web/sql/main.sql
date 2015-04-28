-- INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS) VALUES (1, 'Tambours', 'Moulin Dupont', 'Marguerite Madeleine', 240);
-- INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS) VALUES (2, 'Babar', 'Aristide Euphrate', 'Cageot Miranda', 540);

-- SELECT * from Spectacle;
-- SELECT * from Representation3;
-- SELECT * from Salle;
-- SELECT * from Rang;
-- SELECT * from Place;
-- SELECT * from Users;
-- SELECT * from Dossier;
-- SELECT * from Ticket;

-- INSERT INTO Representation (NR, DateR, HeureR, NSP, NSA, NbP)
-- SELECT NR, DateR, HeureR, NSP, NSA, NbP
-- FROM Representation3;

-- select * from place where nd <> -1;

-- DROP TABLE Dossier;

-- DELETE FROM Salle ;
-- DROP TABLE Salle cascade constraint;

-- DELETE FROM Spectacle3;
-- DELETE FROM Users3;
-- DELETE FROM Salle3;

-- DROP TABLE Salle3 cascade constraint;
-- DROP TABLE Users3 cascade constraint;
-- DROP TABLE CatTarifs3 cascade constraint;

-- create table Spectacle3 as select * from Spectacle;
-- create table Users3 as select * from Users;
-- create table CatTarifs3 as select * from CatTarifs;
-- create table Salle3 as select * from Salle;
-- create table Place3 as select * from Place;
-- create table Rang3 as select * from Rang;
-- create table Representation3 as select * from Representation;



-- INSERT INTO Representation (NR, DateR, HeureR, NSP, NSA, NbP)
-- SELECT NR, DateR, HeureR, NSP, NSA, NbP
-- FROM Representation2;

-- select * from Rang r, Place p where r.NSa = 1 and p.NRa = r.NRa and p.isTaken = 0;

-- DELETE FROM Dossier;

-- select * from Spectacle s, Representation rep, Salle sa, Rang rg where rep.NSp = s.NSp and rep.NSa = sa.NSa and sa.NSa = rg.NSa and (s.NomS like '%ro%' or s.AuteurS like '%ro%' or s.MESS like '%ro%' or s.InfoS like '%ro%');
-- 
-- update Spectacle 
-- set Infos = 'Le RIdeau Rouge propose un spectacle interactif, pour les 4-12 ans, à l’opposé des
-- spectacles classiques pour enfants sages. Avec lui, ce sont les enfants
-- qui font le show. Ils participent, chantent, dansent, s’ouvrent les uns
-- aux autres au travers de différents défis qui font aussi découvrir
-- l’esprit d’équipe. ' 
-- where NSP = 1;


-- select distinct * from Salle s, Rang r where r.NSA = s.NSA;

-- INSERT INTO CatTarifs (NCT, NomCT, PrixCT) VALUES (2, 'Orchestre', 60);

select * 
    from Dossier d, Spectacle s, Representation r 
    WHERE d.LoginU ='fournimi' and d.NR = r.NR and r.NSP = s.NSP and d.boolResa = 0;