-- Script d'initialisation et reinitialisation des donnees

-- Suppression des donnees existantes (ordre important a cause des cles etrangeres)
DELETE FROM ordinateuretat;
DELETE FROM ordinateur;
DELETE FROM modele;
DELETE FROM marque;
DELETE FROM Utilisateur;
DELETE FROM type_panne;

-- Reinitialisation des sequences
ALTER SEQUENCE marque_id_marque_seq RESTART WITH 1;
ALTER SEQUENCE modele_id_modele_seq RESTART WITH 1;
ALTER SEQUENCE ordinateur_id_ordinateur_seq RESTART WITH 1;
ALTER SEQUENCE ordinateuretat_id_seq RESTART WITH 1;
ALTER SEQUENCE utilisateur_id_seq RESTART WITH 1;
ALTER SEQUENCE type_panne_id_seq RESTART WITH 1;

-- Insertion des marques
INSERT INTO marque(libelle) VALUES ('DELL');
INSERT INTO marque(libelle) VALUES ('HP');
INSERT INTO marque(libelle) VALUES ('LENOVO');
INSERT INTO marque(libelle) VALUES ('HP');
INSERT INTO marque(libelle) VALUES ('Dell');
INSERT INTO marque(libelle) VALUES ('Dell');
INSERT INTO marque(libelle) VALUES ('Lenovo');
INSERT INTO marque(libelle) VALUES ('HP');

-- Insertion des modeles
INSERT INTO modele(id_marque, libelle) VALUES (1, 'Optiptex');
INSERT INTO modele(id_marque, libelle) VALUES (2, 'Prodesk');
INSERT INTO modele(id_marque, libelle) VALUES (3, 'ThinkCentre');
INSERT INTO modele(id_marque, libelle) VALUES (4, 'ProBook');
INSERT INTO modele(id_marque, libelle) VALUES (5, 'Latitude');
INSERT INTO modele(id_marque, libelle) VALUES (6, 'Precision');
INSERT INTO modele(id_marque, libelle) VALUES (7, 'ThinkCentre');
INSERT INTO modele(id_marque, libelle) VALUES (8, 'Veritione');

-- Insertion des ordinateurs
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (1, 16, 'Intel Corei7', 512);
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (2, 8, 'Intel Corei7', 256);
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (3, 8, 'Intel Corei5', 512);
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (4, 16, 'Intel Corei9', 512);
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (5, 16, 'Intel Corei5', 512);
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (6, 16, 'Intel Corei7', 256);
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (7, 8, 'Intel Corei9', 256);
INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur) VALUES (7, 8, 'Intel Corei9', 256);

-- Insertion des etats
INSERT INTO Etat (libelle, typeFonctionnel, etatdemarche) VALUES ('Marche', 'Fonctionnel', 1);
INSERT INTO Etat (libelle, typeFonctionnel, etatdemarche) VALUES ('Arret', 'Non fonctionnel', 0);
INSERT INTO Etat (libelle, typeFonctionnel, etatdemarche) VALUES ('En panne', 'Disfonctionnel', 0);

-- Insertion des utilisateurs
INSERT INTO Utilisateur (login, pwd, role) VALUES ('admin', '1234', 'admin');
INSERT INTO Utilisateur (login, pwd, role) VALUES ('user', '1234', 'user');

-- Insertion des types de panne
INSERT INTO type_panne(libelle) VALUES ('Alimentation');
INSERT INTO type_panne(libelle) VALUES ('Disque dur');
INSERT INTO type_panne(libelle) VALUES ('Carte mere');
INSERT INTO type_panne(libelle) VALUES ('Memoire/RAM');
INSERT INTO type_panne(libelle) VALUES ('Processeur');
INSERT INTO type_panne(libelle) VALUES ('Ecran');
INSERT INTO type_panne(libelle) VALUES ('Clavier');
INSERT INTO type_panne(libelle) VALUES ('Souris');
INSERT INTO type_panne(libelle) VALUES ('Batterie');
INSERT INTO type_panne(libelle) VALUES ('Reseau');

-- Insertion des etats d'ordinateurs (ordinateuretat)
-- Date 05/07/2026
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (1, 'Marche', '2026-07-05', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (2, 'En panne', '2026-07-05', 'probleme au niveau alimentation', 1);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (3, 'Marche', '2026-07-05', '', NULL);

-- Date 06/07/2026
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (1, 'Marche', '2026-07-06', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (1, 'Marche', '2026-07-06', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (1, 'Marche', '2026-07-06', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (2, 'En panne', '2026-07-06', 'au niveau disque dur', 2);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (2, 'En panne', '2026-07-06', 'au niveau disque dur', 2);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (2, 'En panne', '2026-07-06', 'au niveau disque dur', 2);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (2, 'En panne', '2026-07-06', 'au niveau disque dur', 2);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (2, 'En panne', '2026-07-06', 'au niveau disque dur', 2);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (3, 'En panne', '2026-07-06', 'carte mere', 3);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (3, 'En panne', '2026-07-06', 'carte mere', 3);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (3, 'En panne', '2026-07-06', 'carte mere', 3);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (4, 'Marche', '2026-07-06', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (5, 'En panne', '2026-07-06', 'probleme au niveau alimentation', 1);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (5, 'En panne', '2026-07-06', 'probleme au niveau alimentation', 1);

-- Insertion d'autres dates pour avoir plus de donnees
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (1, 'Marche', '2026-07-04', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (2, 'Marche', '2026-07-04', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (3, 'En panne', '2026-07-04', 'probleme au niveau alimentation', 1);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (4, 'Marche', '2026-07-04', '', NULL);
INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id) VALUES (5, 'Marche', '2026-07-04', '', NULL);
