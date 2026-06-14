/*
Schéma relationnel

Utilisateur(email(1), motDePasse (NN), nom (NN), prenom (NN))
Enseignant(identifiant(1), estDE, userEns = @Utilisateur.email)

Promotion(code(1))
GroupeTD([sigle, promo = @Promotion.code](1))

Etudiant(numero(1), parcours, userEtud = @Utilisateur.email, groupeTD = @GroupeTD.sigle (NN), promo = @GroupeTD.sigle (NN))

Module(intitule(1), nom (NN), typeModule (NN), promo = @Promotion.code (NN))
Responsable([module = @Module.intitule, ens = @Enseignant.identifiant](1))
Seance([etudiant = @Etudiant.numero, module = @Module.intitule, dateSeance, horaireDebut](1), statut, horaireFin, dateSaisie)
Evaluation(id(1), coeff, module = @Module.intitule)
Note([etudiant = @Etudiant.numero, eval = @Evaluation.numero](1), valeur, statut (NN))

*/
DROP DATABASE IF EXISTS bd_scodoc;
CREATE DATABASE IF NOT EXISTS bd_scodoc;
USE bd_scodoc;

DROP TABLE IF EXISTS Note;
DROP TABLE IF EXISTS Evaluation;
DROP TABLE IF EXISTS Seance;
DROP TABLE IF EXISTS Responsable;
DROP TABLE IF EXISTS Module;
DROP TABLE IF EXISTS Etudiant;
DROP TABLE IF EXISTS GroupeTD;
DROP TABLE IF EXISTS Promotion;
DROP TABLE IF EXISTS Enseignant;
DROP TABLE IF EXISTS Utilisateur;

CREATE TABLE IF NOT EXISTS Utilisateur (
    email VARCHAR(50),
    motDePasse VARCHAR(50) NOT NULL,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    CONSTRAINT pk_Utilisateur PRIMARY KEY (email),
    CONSTRAINT ck_email CHECK (email LIKE '%_@%_.%_')
);

CREATE TABLE Enseignant (
    identifiant VARCHAR(50),
    estDe BOOLEAN DEFAULT FALSE,
    userEns VARCHAR(50),    
    CONSTRAINT pk_Enseignant PRIMARY KEY (identifiant),
    CONSTRAINT fk_EnseignantUtilisateur FOREIGN KEY (userEns)
        REFERENCES Utilisateur (email)
);

CREATE TABLE Promotion (
    code VARCHAR(5),
    CONSTRAINT pk_Promotion PRIMARY KEY (code)
);

CREATE TABLE GroupeTD (
    sigle CHAR(1),
    promo VARCHAR(5),
    CONSTRAINT pk_GroupeTD PRIMARY KEY (sigle, promo),
    CONSTRAINT fk_GroupeTDPromotion FOREIGN KEY (promo)
        REFERENCES Promotion (code)
);

CREATE TABLE Etudiant (
    numero INT,
    parcours CHAR(1) NULL, -- Autorise le NULL par défaut
    userEtud VARCHAR(50) NOT NULL,
    groupeTD CHAR(1) NOT NULL,
    promo VARCHAR(5) NOT NULL,
    CONSTRAINT pk_Etudiant PRIMARY KEY (numero),
    -- Correction du CHECK pour le parcours
    CONSTRAINT ck_parcours CHECK (parcours IN ('A' , 'B')),
    
    -- Contraintes FOREIGN KEY
    CONSTRAINT fk_EtudiantUtilisateur FOREIGN KEY (userEtud)
        REFERENCES Utilisateur (email),
        
    -- CORRECTION : Clé étrangère composite vers GroupeTD (qui lie sigle ET promo)
    CONSTRAINT fk_EtudiantGroupeTD FOREIGN KEY (groupeTD, promo)
        REFERENCES GroupeTD (sigle, promo)
);

CREATE TABLE Module (
    intitule VARCHAR(10),
    nom VARCHAR(50) NOT NULL,
    typeModule ENUM("SAE", "RESSOURCE"),
    promo VARCHAR(5) NOT NULL,
    CONSTRAINT pk_Module PRIMARY KEY (intitule),
    CONSTRAINT fk_ModulePromotion FOREIGN KEY (promo)
        REFERENCES Promotion (code)
);

CREATE TABLE Responsable(
    ens VARCHAR(50),
    module VARCHAR(10),
    CONSTRAINT pk_Responsable PRIMARY KEY (ens , module),
    CONSTRAINT fk_ResponsableEnseignant FOREIGN KEY (ens)
        REFERENCES Enseignant (identifiant),
    CONSTRAINT fk_ResponsableModule FOREIGN KEY (module)
        REFERENCES Module (intitule)
);

CREATE TABLE Seance (
    etudiant INT,
    module VARCHAR(10),
    statut ENUM("PRESENT","RETARD","ABSENT"),
    dateSeance DATE,
    horaireDebut TIME,
    horaireFin TIME,
    groupeTD VARCHAR(10),
    CONSTRAINT pk_Seance PRIMARY KEY (etudiant , module, dateSeance, horaireDebut),
    CONSTRAINT fk_SeanceEtudiant FOREIGN KEY (etudiant)
        REFERENCES Etudiant (numero),
    CONSTRAINT fk_SeanceModule FOREIGN KEY (module)
        REFERENCES Module (intitule)
);

CREATE TABLE Evaluation (
    id VARCHAR(50),
    coeff DOUBLE,
    module VARCHAR(10),
    CONSTRAINT pk_Evaluation PRIMARY KEY (id),
    CONSTRAINT fk_EvaluationModule FOREIGN KEY (module) REFERENCES Module(intitule)
);

CREATE TABLE Note (
    etudiant VARCHAR(50),
    eval VARCHAR(50),
    valeur DECIMAL(4,2),
    statut ENUM('OK', 'ABS', 'EXC', 'ATT') DEFAULT 'OK',
    CONSTRAINT pk_Note PRIMARY KEY (etudiant , eval),
    CONSTRAINT fk_NoteEtudiant FOREIGN KEY (etudiant)
        REFERENCES Etudiant (numero),
    CONSTRAINT fk_NoteModule FOREIGN KEY (eval)
        REFERENCES Evaluation (id)
);