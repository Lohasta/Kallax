package model.entity;

public class Utilisateur{
    private String nom;
    private String prenom;
    private String email;
    private String mdp;

    private boolean estConnecte;

    public Utilisateur(){
        nom = null;
        prenom = null;
        email = null;
        mdp = null;
        estConnecte = false;
    }

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

        estConnecte = false;
    }

    @Override
    public String toString(){
        return ("Nom : " + this.getNom() + "\nPrenom : " + this.getPrenom() + "\nE-Mail : " + this.getEmail() + "\nMot de Passe : " + this.getMdp());
    }


    /* --- GETTERS / SETTERS */

    /**
     * Name getter  
     * @return name
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Name setter
     * @param nvNom new name
     */
    public void setNom(String nvNom){
        this.nom = nvNom;
    }

    /**
     * First name getter
     * @return first name
     */
    public String getPrenom(){
        return this.prenom;
    }

    /**
     * First name setter
     * @param nvPrenom new first name
     */
    public void setPrenom(String nvPrenom){
        this.prenom = nvPrenom;
    }

    /**
     * Email getter
     * @return email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Email setter
     * @param nvEmail new email
     */
    public void setEmail(String nvEmail){
        this.email = nvEmail;
    }

    /**
     * Password getter
     * @return password
     */
    public String getMdp(){
        return this.mdp;
    }

    /**
     * Password setter
     * @param nvMdp new password
     */
    public void setMdp(String nvMdp){
        this.mdp = nvMdp;
    }

    /**
     * estConnecte getter, tells if the User
     * is currently connected to the App.
     * @return estConnecte
     */
    public boolean getEstConnecte(){
        return this.estConnecte;
    }

    /**
     * estConnecte setter
     * @param estConnecte new state of Connection
     */
    public void setEstConnecte(boolean estConnecte){
        this.estConnecte = estConnecte;
    }
}
