
CREATE DATABASE "ordinateurexam";

-- Table marque
CREATE TABLE marque(
    id_marque SERIAL PRIMARY KEY,
    libelle VARCHAR(100)
);

INSERT INTO marque(libelle)VALUES('Dell');
INSERT INTO marque(libelle)VALUES('HP');
INSERT INTO marque(libelle)VALUES('Lenovo');
INSERT INTO marque(libelle)VALUES('HP');
INSERT INTO marque(libelle)VALUES('Dell');
INSERT INTO marque(libelle)VALUES('Dell');
INSERT INTO marque(libelle)VALUES('Lenovo');
INSERT INTO marque(libelle)VALUES('Acer');

-- Table modele
CREATE TABLE modele(
    id_modele SERIAL PRIMARY KEY,
    id_marque INT,
    libelle VARCHAR(100),
    CONSTRAINT fk_marque FOREIGN KEY (id_marque) REFERENCES marque(id_marque) ON DELETE CASCADE
);

INSERT INTO modele(id_marque,libelle)VALUES(1,'optiptex');
INSERT INTO modele(id_marque,libelle)VALUES(4,'Prodesk');
INSERT INTO modele(id_marque,libelle)VALUES(5,'ThinkCentre');
INSERT INTO modele(id_marque,libelle)VALUES(6,'ProBook');
INSERT INTO modele(id_marque,libelle)VALUES(3,'Latitude');
INSERT INTO modele(id_marque,libelle)VALUES(2,'Precision');
INSERT INTO modele(id_marque,libelle)VALUES(2,'ThinkCentre');
INSERT INTO modele(id_marque,libelle)VALUES(2,'Verition');
INSERT INTO modele(id_marque,libelle)VALUES(2,'Precision');

-- Table ordinateur
CREATE TABLE ordinateur(
    id_ordinateur SERIAL PRIMARY KEY,
    id_modele INT,
    ram INT,
    processeur VARCHAR(100),
    disque_dur INT,
    CONSTRAINT fk_model FOREIGN KEY (id_modele) REFERENCES modele(id_modele) ON DELETE CASCADE
);

-- Table Utilisateur
CREATE TABLE Utilisateur (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    pwd VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Insertion directe d'un utilisateur de test
INSERT INTO Utilisateur (login, pwd, role) VALUES ('admin', '1234', 'admin');

-- Table Etat
CREATE TABLE Etat (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50)
);

-- Insertion d'etats de test
INSERT INTO Etat (id, libelle) VALUES ('1', 'Fonctionnel');
INSERT INTO Etat (id, libelle) VALUES ('2', 'Non fonctionnel');

-- Table ordinateuretat (liaison)
-- Pour une nouvelle base de données:
CREATE TABLE ordinateuretat (
    id SERIAL PRIMARY KEY,
    idordi INT,
    etat_libelle VARCHAR(50),
    date_etat DATE,
    disfonctionnement VARCHAR(100),
    type_panne_id INT
);



-- Table TypePanne
CREATE TABLE type_panne (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

INSERT INTO type_panne(libelle) VALUES ('1', 'Alimentation');
INSERT INTO type_panne(libelle) VALUES ('2', 'Disque dur');
INSERT INTO type_panne(libelle) VALUES ('3', 'Carte mere');


-- Vue pour afficher les modeles avec marque
CREATE OR REPLACE VIEW v_modele AS
SELECT m.id_modele, m.libelle, ma.libelle as libelle_marque
FROM modele m
JOIN marque ma ON m.id_marque = ma.id_marque;


CREATE TABLE ordinateur_panne (
    id SERIAL PRIMARY KEY,
    idOrdinateurEtat INT,
    idTypePanne VARCHAR(50)
);