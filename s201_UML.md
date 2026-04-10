@startuml
skinparam classAttributeIconSize 0
hide circle

package Model{


abstract class Utilisateur <<abstract>>{
-email : String
-mdp : String
-nom : String
-prenom : String
+Utilisateur(email : String, mdp : String, nom : String, prenom : String)
+getEmail() : String
+getNom() : String
+getPrenom() : String
+setEmail(email : String)
+setNom(nom : String)
+setPrenom(prenom : String)
+toString() : String
}

interface IDroitsEnseignant <<interface>>{
+getListEtud() : ArrayList<Etudiant>
+getListControle() : ArrayList<Controle>
+getListModule() : ArrayList<Module>
+getListNote() : ArrayList<Note>
}
note right of IDroitsEnseignant{
faut ajouter le truc de présence et modif Controle/Note
}

class Etudiant{
-idEtud : int
+Etudiant(idEtud : int, email : String, mdp : String, nom : String, prenom : String)
+getIdEtud() : int
+setIdEtud(idEtud) : void
+getNotes() : Note
+calculMoyenne() : double
+afficherMoyenne()
+toString() : String
}

class Enseignant{
-idEns : int
+Enseignant(idEns : int, email : String, mdp : String, nom : String, prenom : String)
+getIdEns() : int
+setIdEns(idEns : int)
+toString() : String
+createControle(module : Module, date : int, coefficient : int)
}

class DirEtud{
+DirEtud(idEns : int, email : String, mdp : String, nom : String, prenom : String)
+createModule(intituleModule : String, , nomModule : String)
+modifNomModule(module : Module, nom : String,newNom:String)
+modifIntitule(module : Module, intitule : String,newIntitule : String)
+addEtud(idEtud : int)
+modifEtud()
+removeEtud(idEtud : int)
+addEns(idEns : int)
+modifEns()
+removeEns(idEns : int)
+addEnsModule(module : Module, enseignant : Enseignant)
+removeEnsR(module : Module, enseignant : Enseignant)
+getListEns() : ArrayList<Enseignant>
+toString() : String
}

+class Module{
-idModule : String
-nomModule : String
+Module(idModule : String, nomModule : String)
+getIdModule() : String
+getNomModule() : String
+setIdModule(newId : String)
+setNomModule(newName : String)
+toString() : String
}

+class Controle{
-date : int
-coeff : int
+Controle(date : int, coeff : int, module : Module)
+getModule() : Module
+getDate() : int
+setDate(newDate : int)
+getCoefficient() : int
+setCoefficient(newCoeff int)
+toString() : String
}
note right of Controle{
date = jjmmaaaa
}

+class Note{
-note : double
+Note(note : double, date : int, coeff : int, module : Module)
+getNote() : double
+setNote(newNote : double)
+toString() : String
}
note bottom of Note{
si newNote = null -> suppr note
}

+class Promotion{
-nom : String
+Promotion(nom : String)
+getNom() : String
+setNom(nom : String)
+getGroupes() : ArrayList<Groupe>
+addGroupe(grp: Groupe)
+removeGroupe(nom: int)
+toString() : String
}

+class Groupe{
-idGroupe : String
+Groupe(idGroupe : String)
+getNom() : String
+setNom(nom : String) : void
+getEtudiants() : ArrayList<Etudiant>
+addEtudiant(etud : Etudiant)
+removeEtudiant(idEtud : int)
+toString() : String
}


IDroitsEnseignant <|.. Enseignant
Note "*" <-- Etudiant : -notes
Module "1" <-- Controle : -module
Controle "*" <-- Module : -examens
Enseignant "*" <-- Module : -enseignants
Etudiant "*" <-- Groupe: -etudiants
Groupe "*" <-- Promotion : -groupes
Utilisateur <|-- Etudiant
Controle "1" <-- Note : -controle
Utilisateur <|-- Enseignant
Enseignant <|-- DirEtud

}
@enduml
