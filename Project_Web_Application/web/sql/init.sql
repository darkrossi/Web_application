CREATE TABLE Spectacle (
    NSP int,
    NomS varchar(30),
    AuteurS varchar(30),
    MESS varchar(30),
    DureeS int,
    Affiche varchar(30),
    constraint pk_spectacle primary key (NSP)
);

CREATE TABLE Salle (
    NSA int,
    constraint pk_salle primary key (NSA)
);

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

CREATE TABLE Rang (
    NR int,
    CatTarif int,
    NSA int,
    constraint pk_rang primary key (NR),
    constraint fk_rang_nr_salle foreign key (NSA) references Salle(NSA),
    constraint nn_rang_nsa check (NSA is not null), 
    constraint CatTarif CHECK(CatTarif >= 0)
);

CREATE TABLE Place (
    NP int,
    NR int,
    NSA int,
    constraint pk_table primary key (NP),
    constraint fk_place_nr_rang foreign key (NR) references Rang(NR),
    constraint fk_place_nr_salle foreign key (NSA) references Salle(NSA),
    constraint nn_place_nr check (NR is not null),
    constraint nn_place_nsa check (NSA is not null)
);

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

CREATE TABLE Ticket (
    NT int,
    constraint pk_ticket primary key (NT)
);

CREATE TABLE Dossier (
    ND int, -- numéro dossier
    NR int, -- numéro reservation
    LoginU varchar(30), -- login
    NT int, -- numéro ticket
    NbP int, -- nombre de place
    constraint pk_dossier primary key (ND),
    constraint fk_dossier_loginu_users foreign key (LoginU) references Users(LoginU),
    constraint fk_dossier_nt_ticket foreign key (NT) references Ticket(NT),
    constraint fk_dossier_nr_repr foreign key (NR) references Representation(NR),
    constraint nn_dossier_loginu check (LoginU is not null),
    constraint nn_dossier_nt check (NT is not null),
    constraint nn_dossier_nr check (NR is not null),
    CONSTRAINT NbP CHECK(NbP >= 1)
);

CREATE TABLE Booking (
    NB int,
    LoginU varchar(30),
    NbP int,
    NSP int,
    NR int,
    NSA int,
    constraint pk_booking primary key (NB),
    constraint fk_booking_loginu_users foreign key (LoginU) references Users(LoginU),
    constraint fk_booking_nsp_spectacle foreign key (NSP) references Spectacle(NSP),
    constraint fk_booking_nsa_salle foreign key (NSA) references Salle(NSA),
    constraint fk_booking_nr_repr foreign key (NR) references Representation(NR),
    constraint nn_booking_loginu check (LoginU is not null),
    constraint nn_booking_nsa check (NSA is not null),
    constraint nn_booking_nsp check (NSP is not null),
    constraint nn_booking_nr check (NR is not null),
    CONSTRAINT NbP CHECK(NbP >= 1)
);

INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS, Affiche)
    VALUES (0, 'init', 'init', 'init', 1, 'init');