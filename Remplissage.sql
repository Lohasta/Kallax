use bd_scodoc;

/*-----------------------*/
/* Insertions pour tests */
/*   Et Scénario (Oral)  */
/*-----------------------*/


-- ========================
-- UTILISATEURS
-- ========================
INSERT INTO Utilisateur (email, motDePasse, nom, prenom) VALUES
('lohan@ubs.fr', 'lohanK', 'Kerrault', 'Lohan'),
('fabien@ubs.fr', 'fabienM', 'Madotto', 'Fabien'),
('gatien@ubs.fr', 'gatienR', 'Rebêche', 'Gatien'),
('mael@ubs.fr', 'maelL', 'Leroux', 'Maël'),
('tonin@ubs.fr', 'toninP', 'Tonin', 'P.'),
('naert@ubs.fr', 'naertL', 'Naert', 'N.'),
('tibermacine@ubs.fr', 'TibermacineC', 'Tibermacine', 'C.');

-- ========================
-- ENSEIGNANTS
-- ========================
INSERT INTO Enseignant (identifiant, estDe, userEns) VALUES
('tonin', TRUE, 'tonin@ubs.fr'),
('naert', FALSE, 'naert@ubs.fr'),
('tibermacine', FALSE, 'tibermacine@ubs.fr');

-- ========================
-- ANNEES
-- ========================
INSERT INTO Promotion (code) VALUES
('BUT1'),
('BUT2'),
('BUT3');

-- ========================
-- GROUPES TD
-- ========================
INSERT INTO GroupeTD (sigle, promo) VALUES
('A', 'BUT1'),
('B', 'BUT1'),
('C', 'BUT1'),
('D', 'BUT1'),
('A', 'BUT2'),
('B', 'BUT2'),
('C', 'BUT2'),
('A', 'BUT3'),
('B', 'BUT3');

-- ========================
-- ETUDIANTS
-- ========================
INSERT INTO Etudiant (numero, parcours, userEtud, groupeTD, promo) VALUES
(1, 'A', 'lohan@ubs.fr', 'A', 'BUT2'),
(2, 'B', 'fabien@ubs.fr', 'B', 'BUT2'),
(3, NULL, 'gatien@ubs.fr', 'C', 'BUT1'),
(4, 'B', 'mael@ubs.fr', 'D', 'BUT3'),

-- ========================
-- MODULES
-- ========================
INSERT INTO Module (intitule, nom, typeModule, promo) VALUES
('R2.02', "Développement d'applications avec IHM", 'RESSOURCE', 'BUT1'),
('S1.01', "Implémentation d'un besoin client", 'SAE', 'BUT1'),
('R3.04', 'Qualité de développement', 'RESSOURCE', 'BUT2');

-- ========================
-- RESPONSABLES
-- ========================
INSERT INTO Responsable (ens, module) VALUES
('naert', 'R2.02'),
('naert', 'S1.01'),
('tibermacine', 'R3.04');

-- ========================
-- EVALUATIONS
-- ========================
INSERT INTO Evaluation (id, coeff, module) VALUES
(1, 2, 'R2.02'),
(2, 1.5 , 'R2.02'),
(3, 1, 'S1.01'),
(4, 0.5, 'R3.04');

-- ========================
-- NOTES
-- ========================
INSERT INTO Note (etudiant, eval, valeur, statut) VALUES
(1, 1, 15.5, 'OK'),
(1, 2, 12.0, 'OK'),
(1, 3, NULL, 'EXC'),
(2, 1, 0, 'ABS'),
(2, 2, 10.0, 'OK'),
(2, 3, 8.5, 'OK'),
(3, 1, NULL, 'ATT'),
(3, 2, 14.0, 'OK'),
(4, 1, 9.0, 'OK'),
(4, 4, 11.5, 'OK'),
(5, 2, 13.0, 'OK'),
(5, 3, 0, 'ABS');

-- ========================
-- Seances (présences)
-- ========================
INSERT INTO Seance (etudiant, module, statut, dateSeance, horaireDebut, horaireFin, dateSaisie) VALUES
(1, 'R2.02', 'PRESENT', '2025-10-01', '08:00:00', '10:00:00', '2025-10-01'),
(1, 'R2.02', 'RETARD', '2025-10-02', '10:00:00', '12:00:00', '2025-10-02'),
(2, 'R3.04', 'ABSENT', '2025-10-01', '08:00:00', '10:00:00', '2025-10-01'),
(2, 'R3.04', 'PRESENT', '2025-10-02', '10:00:00', '12:00:00', '2025-10-02'),
(3, 'R2.02', 'PRESENT', '2025-10-01', '08:00:00', '10:00:00', '2025-10-01'),
(4, 'S1.01', 'PRESENT', '2026-02-01', '09:00:00', '11:00:00', '2026-02-01'),
(5, 'R3.04', 'ABSENT', '2025-10-02', '10:00:00', '12:00:00', '2025-10-02');