/**
 * 
 */
public class Enseignant extends Utilisateur{
    
    public String ident;

    public Enseignant(String nom, String prenom, String email, String mdp, String ident){
        super(nom, prenom, email, mdp);

        if(ident != null && ident != ""){
            this.ident = ident;
        } else{
            System.out.println("Constructeur de Enseignant : id incorrect.");
        }
    }

    @Override
    public String toString(){
        return ("\n*** Enseignant ***\n" + super.toString() + "\nID : " + this.getIdent());
    }

    public String getIdent(){
        return this.ident;
    }

    public void setIdent(String nvIdent){
        this.ident = nvIdent;
    }
}
