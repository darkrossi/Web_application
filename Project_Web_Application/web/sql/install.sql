DROP TABLE comptes;

--
-- PARTIE 1 --
--

-- DEBUT EXERCICE 1

CREATE TABLE comptes (
	NC int primary key,
	Nom varchar(30),
	Soldes float check (Soldes > 0));

COMMIT;

INSERT INTO comptes
VALUES (1, 'Paul', 3000);

INSERT INTO comptes
VALUES (2, 'Paul', 5000);

SELECT * FROM comptes;
SELECT SUM(Soldes) FROM comptes;

ROLLBACK;

SELECT * FROM comptes;
SELECT SUM(Soldes) FROM comptes;

-- FIN EXERCICE 1

-- DEBUT EXERCICE 2

INSERT INTO comptes
VALUES (1, 'Pierre', 3000);

INSERT INTO comptes
VALUES (2, 'Pierre', 5000);

commit;

INSERT INTO comptes
VALUES (3, 'Paul', 3000);

INSERT INTO comptes
VALUES (4, 'Paul', 5000);

SELECT Nom, SUM(Soldes) FROM comptes
group by Nom;

rollback;

SELECT Nom, SUM(Soldes) FROM comptes
group by Nom;

-- FIN EXERCICE 2

-- DEBUT EXERCICE 3

set autocommit on;

INSERT INTO comptes
VALUES (5, 'Jacques', 3000);

INSERT INTO comptes
VALUES (6, 'Jacques', 5000);

SELECT Nom, SUM(Soldes) FROM comptes
group by Nom;

rollback;

SELECT Nom, SUM(Soldes) FROM comptes
group by Nom;

set autocommit off;

-- FIN EXERCICE 3

-- DEBUT EXERCICE 4

INSERT INTO comptes
VALUES (7, 'Jean', 3000);

INSERT INTO comptes
VALUES (8, 'Jean', 5000);

savepoint DeuxInserts;

INSERT INTO comptes
VALUES (9, 'Jean', 3000);

INSERT INTO comptes
VALUES (10, 'Jean', 5000);

SELECT Nom, SUM(Soldes) FROM comptes
group by Nom;

rollback to DeuxInserts;

SELECT Nom, SUM(Soldes) FROM comptes
group by Nom;

rollback;

SELECT Nom, SUM(Soldes) FROM comptes
group by Nom;

--
-- PARTIE 2 --
--

-- DEBUT EXERCICE 1

INSERT INTO comptes
VALUES (11, 'Claude', 100);

INSERT INTO comptes
VALUES (12, 'Henri', 200);

update comptes
SET Soldes = Soldes + 50
where NC = 12;

update comptes
SET Soldes = Soldes - 150
where NC = 11;











