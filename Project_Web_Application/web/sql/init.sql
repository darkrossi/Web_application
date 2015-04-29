CREATE TABLE Spectacle (
    NSP int,
    NomS varchar(30),
    AuteurS varchar(30),
    MESS varchar(30),
    DureeS int,
    Affiche varchar(30),
    InfoS varchar(500),
    constraint pk_spectacle primary key (NSP)
);
INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS, Affiche, InfoS)
SELECT NSP, NomS, AuteurS, MESS, DureeS, Affiche, Infos
FROM Spectacle3;


CREATE TABLE Salle (
    NSA int,
    NomSa varchar(30),
    NbRa int,
    constraint pk_salle primary key (NSA),
    constraint NbRa CHECK(NbRa >= 1)
);
INSERT INTO Salle (NSA, NomSa, NbRa)
SELECT NSA, NomSa, NbRa
FROM Salle3;

CREATE TABLE Representation (
    NR int,
    DateR varchar(30),
    HeureR varchar(30),
    NSP int,
    NSA int,
    NbP int,
    constraint pk_repr primary key (NR),
    constraint fk_repr_nsp_spectacle foreign key (NSP) references Spectacle(NSP),
    constraint fk_repr_nsa_salle foreign key (NSA) references Salle(NSA),
    constraint nn_repr_nsa check (NSA is not null),
    constraint nn_repr_nsp check (NSP is not null),
    constraint nbp_total check (NbP >= 70)
);
INSERT INTO Representation (NR, DateR, HeureR, NSP, NSA, NbP)
SELECT NR, DateR, HeureR, NSP, NSA, NbP
FROM Representation3;

CREATE TABLE Rang (
    NRa int,
    NCT int,
    NSA int,
    NbP int,
    constraint pk_rang primary key (NRa),
    constraint fk_rang_nr_salle foreign key (NSA) references Salle(NSA),
    constraint fk_rang_nr_cattarifs foreign key (NCT) references CatTarifs(NCT),
    constraint nn_rang_nsa check (NSA is not null), 
    constraint NbP CHECK(NbP >= 1)
);
INSERT INTO Rang (NRa, NCT, NSA, NbP)
SELECT NRa, NCT, NSA, NbP
FROM Rang3;

CREATE TABLE Place (
    NP int,
    NRa int,
    NumPl int,
    constraint pk_table primary key (NP),
    constraint fk_place_nra_rang foreign key (NRa) references Rang(NRa),
    constraint nn_place_nra check (NRa is not null)
);
INSERT INTO Place (NP, NRa, NumPl)
SELECT NP, NRa, NumPl
FROM Place3;

CREATE TABLE Users (
    LoginU varchar(30),
    NomU varchar(30),
    PrenomU varchar(30),
    MailU varchar(30),
    MdpU varchar(30),
    RoleU int,
    constraint pk_users primary key (LoginU),
    CONSTRAINT bin_roleu CHECK(RoleU in (0, 1))
);
INSERT INTO Users (LoginU, NomU, PrenomU, MailU, MdpU, RoleU)
SELECT LoginU, NomU, PrenomU, MailU, MdpU, RoleU
FROM Users3;

CREATE TABLE Ticket (
    NT int,
    constraint pk_ticket primary key (NT)
);

CREATE TABLE Dossier (
    ND int, -- numéro dossier
    NR int, -- numéro representation
    LoginU varchar(30), -- login
    NT int, -- numéro ticket
    NbP int, -- nombre de place
    boolResa int, -- 0 si resa, 1 si achat
    constraint pk_dossier primary key (ND),
    constraint fk_dossier_loginu_users foreign key (LoginU) references Users(LoginU),
    constraint fk_dossier_nr_repr foreign key (NR) references Representation(NR),
    constraint nn_dossier_loginu check (LoginU is not null),
    constraint nn_dossier_nr check (NR is not null),
    CONSTRAINT NbPD CHECK(NbP >= 1)
);

CREATE TABLE PlacesRes (
    NPR int,
    NP int,
    ND int,
    constraint pk_placesRes primary key (NPR),
    constraint fk_placesRes_np_place foreign key (NP) references Place(NP),
    constraint fk_placesRes_nd_dossier foreign key (ND) references Dossier(ND),
    constraint nn_placesRes_np check (NP is not null),
    constraint nn_placesRes_nd check (ND is not null)
);

CREATE TABLE CatTarifs (
    NCT int,
    NomCT varchar(30),
    PrixCT int,
    constraint pk_cattarifs primary key (NCT)
);
INSERT INTO CatTarifs (NCT, NomCT, PrixCT)
SELECT NCT, NomCT, PrixCT
FROM CatTarifs3;

INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS, Affiche, InfoS)
    VALUES (0, 'init', 'init', 'init', 1, 'init', 'init');