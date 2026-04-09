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
+setEmail(email : String) : void
+setNom(nom : String) : void
+setPrenom(prenom : String) : void
}
note left of Utilisateur{
getMdp utile ?
}


interface IDroitsEnseignant{

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
+getIdEtud() : int
+setIdEtud(idEtud) : void
}
class Enseignant{
-idEns : int
+getIdEns() : int
+setIdEns(idEns : int) : void
}



class DirEtud{

+addNomR(nom : String)
+modifNomR(nom : String,newNom:String)
+removeNomR(nom : String)
+addIntitule(intitule : String)
+modifIntitule(intitule : String,newIntitule : String)
+removeIntitule(intitule : String)
+addEtud(idEtud : int)
+modifEtud()
+removeEtud(idEtud : int)
+addEns(idEns : int)
+modifEns()
+removeEns(idEns : int)
+addEnsR(...)
+removeEnsR(...)
+getListEns() : ArrayList<Enseignant>

}
note right of DirEtud{
Rajouter des méthodes "d'admin"
Intitule = Module ?
}



+class Module{

idModule : String
nomModule : String


getIdModule() : String
getNomModule() : String
setIdModule(newId : String) : void
setNomModule(newName : String) : void
}


+class Controle{

date : int
coefficient : int

getModule() : Module
getDate() : int
setDate(newDate : int) : void
getCoefficient() : int
setCoefficient(newCoeff int) : void
}
note bottom of Controle{
setter comme Note
date = jjmmaaaa
}



+class Note{

-note : double

+getNote() : double
+setNote(newNote : double)
}
note bottom of Note{
si newNote = null -> suppr note
note hérite de controle ?
}


+class Promotion{

nom : String

getNom() : String
setNom(nom : String) : void
getGroupes() : ArrayList<Groupe>
addGroupe(grp: Groupe) : void
removeGroupe(nom: int) : void
}
note bottom of Promotion{
On a besoin de ça ? YAGNI
}


+class Groupe{

nom : String

getNom() : String
setNom(nom : String) : void
getEtudiants() : ArrayList<Etudiant>
addEtudiant(etud : Etudiant) : void
removeEtudiant(idEtud : int) : void
}
note right of Groupe{
on a besoin de ça ? YAGNI
}


IDroitsEnseignant <|.. Enseignant
Note " " <-- Etudiant : -notes
Module "1" <-- Controle : -module
Controle " " <-- Module : -examens
Enseignant " " <-- Module : -enseignants
Etudiant " " <-- Groupe: -etudiants
Groupe "*" <-- Promotion : -groupes
Utilisateur <|-- Etudiant
Utilisateur <|-- Enseignant
Enseignant <|-- DirEtud
@enduml