## Ce fichier contient le code entier pour le diagramme de classes de l'application.
## NE PAS OUBLIER D'ACTUALISER À CHAQUE CHANGEMENT !

@startuml
skinparam classAttributeIconSize 0
class UtilisateurFactory{
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
note left of UtilisateurFactory{
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

UtilisateurFactory <|.. Etudiant
UtilisateurFactory <|.. Enseignant
Enseignant <|-- DirEtud
@enduml