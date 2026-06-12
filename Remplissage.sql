-- ========================
-- UTILISATEURS
-- ========================
INSERT INTO Utilisateur (email, motDePasse, nom, prenom) VALUES
('alice@example.com', 'pass123', 'Dupont', 'Alice'),
('bob@example.com', 'pass123', 'Martin', 'Bob'),
('charlie@example.com', 'pass123', 'Durand', 'Charlie'),
('david@example.com', 'pass123', 'Petit', 'David'),
('eva@example.com', 'pass123', 'Moreau', 'Eva'),
('tonin@example.com', 'pass123', 'Tonin', 'P.'),
('naert@example.com', 'pass123', 'Naert', 'N.'),
('tibermacine@example.com', 'pass123', 'Tibermacine', 'C.');

-- ========================
-- ENSEIGNANTS
-- ========================
INSERT INTO Enseignant (identifiant, estDe, userEns) VALUES
('tonin', TRUE, 'tonin@example.com'),
('naert', FALSE, 'naert@example.com'),
('tibermacine', FALSE, 'tibermacine@example.com');

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
(1, NULL, 'alice@example.com', 'A', 'BUT1'),
(2, 'B', 'bob@example.com', 'B', 'BUT2'),
(3, NULL, 'charlie@example.com', 'D', 'BUT1'),
(4, NULL, 'david@example.com', 'B', 'BUT1'),
(5, 'B', 'eva@example.com', 'A', 'BUT2');

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
-- CONTROLES
-- ========================
INSERT INTO Evaluation (coeff, duree, module) VALUES
(20, '01:30:00', 'R2.02'),
(1, '00:10:00', 'R2.02'),
(2, NULL, 'S1.01'),
(1, '00:15:00', 'R3.04');

-- ========================
-- NOTES
-- ========================
INSERT INTO Note (etudiant, eval, valeur, statut) VALUES
('E001', 1, 15.5, 'OK'),
('E001', 2, 12.0, 'OK'),
('E001', 3, NULL, 'EXC'),
('E002', 1, 0, 'ABS'),
('E002', 2, 10.0, 'OK'),
('E002', 3, 8.5, 'OK'),
('E003', 1, NULL, 'ATT'),
('E003', 2, 14.0, 'OK'),
('E004', 1, 9.0, 'OK'),
('E004', 4, 11.5, 'OK'),
('E005', 2, 13.0, 'OK'),
('E005', 3, 0, 'ABS');

-- ========================
-- Seances (présences)
-- ========================
INSERT INTO Seance (etudiant, module, statut, dateSeance, horaireDebut, horaireFin, dateSaisie) VALUES
('E001', 'R2.02', 'PRESENT', '2025-10-01', '08:00:00', '10:00:00', '2025-10-01'),
('E001', 'R2.02', 'RETARD', '2025-10-02', '10:00:00', '12:00:00', '2025-10-02'),
('E002', 'R3.04', 'ABSENT', '2025-10-01', '08:00:00', '10:00:00', '2025-10-01'),
('E002', 'R3.04', 'PRESENT', '2025-10-02', '10:00:00', '12:00:00', '2025-10-02'),
('E003', 'R2.02', 'PRESENT', '2025-10-01', '08:00:00', '10:00:00', '2025-10-01'),
('E004', 'S1.01', 'PRESENT', '2026-02-01', '09:00:00', '11:00:00', '2026-02-01'),
('E005', 'R3.04', 'ABSENT', '2025-10-02', '10:00:00', '12:00:00', '2025-10-02');