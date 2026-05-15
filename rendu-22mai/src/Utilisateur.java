public abstract class Utilisateur{
    private String nom;
    private String prenom;
    private String email;
    private String mdp;

    public Utilisateur(String nom, String prenom, String email, String mdp){
        if(nom != null && nom != ""){
            this.nom = nom;
        } else{
            System.out.println("Constructeur de Utilisateur : nom incorrect.");
        }

        if(prenom != null && prenom != ""){
            this.prenom = prenom;
        } else{
            System.out.println("Constructeur de Utilisateur : prenom incorrect.");
        }

        if(email != null && email != ""){
            this.email = email;
        } else{
            System.out.println("Constructeur de Utilisateur : email incorrect.");
        }

        if(mdp != null && mdp != ""){
            this.mdp = mdp;
        } else{
            System.out.println("Constructeur de Utilisateur : mdp incorrect.");
        }
    }

    public String toString(){
        return ("Nom : " + this.getNom() + "\nPrenom : " + this.getPrenom() + "\nE-Mail : " + this.getEmail() + "\nMot de Passe : " + this.getMdp());
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nvNom){
        this.nom = nvNom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public void setPrenom(String nvPrenom){
        this.prenom = nvPrenom;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String nvEmail){
        this.email = nvEmail;
    }

    public String getMdp(){
        return this.mdp;
    }

    public void setMdp(String nvMdp){
        this.mdp = nvMdp;
    }
}
