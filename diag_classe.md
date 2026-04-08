## Ce fichier contient le code entier pour le diagramme de classes de l'application.
## NE PAS OUBLIER D'ACTUALISER À CHAQUE CHANGEMENT !

@startuml
skinparam classAttributeIconSize 0

+ interface Utilisateur{
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

+ class Etudiant{
   -idEtud : int

   +getIdEtud() : int
   +setIdEtud(idEtud)
}

+ class Enseignant{
   -idEns : int

   +getIdEns() : int
   +setIdEns(idEns : int)
}

+ class DirEtud{
}
note right of DirEtud{
Rajouter des méthodes "d'admin"
}

Utilisateur <|.. Etudiant
Utilisateur <|.. Enseignant
Enseignant <|-- DirEtud


@enduml
