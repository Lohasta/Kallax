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
interface IDroitsEnseignant{
+getListEtud() : ArrayList
+getListControle() : ArrayList
+getListModule() : ArrayList
+getListNote() : ArrayList
}
note right of IDroitsEnseignant{
faut ajouter le truc de présence et modif Controle/Note
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
+createControle(module : Module, date : int, coefficient : int)
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
+getListEns() : ArrayList
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
setIdModule(newId : String)
setNomModule(newName : String)
}
+class Controle{
date : int
coefficient : int
getModule() : Module
getDate() : int
setDate(newDate : int)
getCoefficient() : int
setCoefficient(newCoeff int)
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
setNom(nom : String)
getGroupes() : ArrayList
addGroupe(grp: Groupe)
removeGroupe(nom: int)
}
note bottom of Promotion{
On a besoin de ça ? YAGNI
}
+class Groupe{
nom : String
getNom() : String
setNom(nom : String)
getEtudiants() : ArrayList
addEtudiant(etud : Etudiant)
removeEtudiant(idEtud : int)
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
Controle "*" <-- Note : -controle
Utilisateur <|-- Etudiant
Utilisateur <|-- Enseignant
Enseignant <|-- DirEtud
@enduml