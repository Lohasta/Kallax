/**
 * Non-Instanciable User with a name, first name, email and password.
 * Parent class of Etudiant, Enseignant and DirecteurEtudes.
 *
 * @author Team KALLAX
 * @version v1.0
 */
public abstract class Utilisateur{
    private String nom;
    private String prenom;
    private String email;
    private String mdp;

    /**
    * Constructor : Initializes the User with it's parameters
    * @param nom User's name
    * @param prenom User's first name
    * @param email User's email
    * @param mdp User's password
    */
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

    @Override
    /**
     * Displays the User's infos.
     */
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
}
