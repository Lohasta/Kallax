## Ce fichier contient le code entier pour le diagramme de classes de l'application. ##
## NE PAS OUBLIER D'ACTUALISER À CHAQUE CHANGEMENT ! ##

@startuml
skinparam classAttributeIconSize 0
abstract class Utilisateur{
-email : String
-mdp : String
-nom : String
-prenom : String
+getEmail() : String
+getNom() : String
+getPrenom() : String
+setEmail(email : String)
+setNom(nom : String)
+setPrenom(prenom : String)
}
note left of Utilisateur{
getMdp utile ?
}
class Etudiant{
-idEtud : int
+getIdEtud() : int
+setIdEtud(idEtud)
}
class Enseignant{
-idEns : int
+getIdEns() : int
+setIdEns(idEns : int)
}
class DirEtud{
--
+addNomR(nom : String)
+modifNomR(nom : String,newNom:String)
+removeNomR(nom : String)
+addIntitule(intitule : String)
+modifIntitule(intitule : String,newIntitule : String)
+removeIntitule(intitule : String)
+addEtud(idEtud : int)
+modifEtud
+removeEtud(idEtud : int)
+addEns(idEns : int)
+modifEns
+removeEns(idEns : int)
+addEnsR(...)
+removeEnsR(...)
+createPromo(nom : String)
+nbGrpTD(nbGrp : int)
+addEtudGrp
+listEns() : Enseignant[]
+listEtud() : Etudiant[]
}
note right of DirEtud{
Rajouter des méthodes "d'admin"
}

+class Module{
- idModule : String
- nomModule : String
+ getIdModule() : String
+ getNomModule() : String
+ setIdModule(newId : String)
+ setNomModule(newName : String)
}

+class Controle{
- date : int
+ getModule() : Module 
+ getDate() : int
+ setNomModule(newMod : Module)
+ setDate(newDate : int)
}
note bottom of Controle{
setter comme Note
date = jjmmaaaa
}
+class Note{
- note : double
+ getNote() : double
+ setNote(newNote : double)
}
note bottom of Note{
si newNote = null -> suppr note
note hérite de controle ?
}

+class Promotion{
- nom : String
+ getNom() : String
+ setNom(nom : String)
+ getGroupes() : ArrayList<Groupe>
+ addGroupe(grp: Groupe)
+ removeGroupe(nom: int)
}

+class Groupe{
- nom : String
+ getNom() : String
+ setNom(nom : String)
+ getEtudiants() : ArrayList<Etudiant>
+ addEtudiant(etud : Etudiant)
+ removeEtudiant(idEtud : int)
}

Note "*" <-- Etudiant : -notes
Module "1" <-- Controle : -module
Controle "*" <-- Module : -examens
Enseignant "*" <-- Module : -enseignants
Etudiant "*" <-- Groupe: -etudiants
Groupe "*" <-- Promotion : -groupes
Utilisateur <|-- Etudiant
Utilisateur <|-- Enseignant
Enseignant <|-- DirEtud
@enduml