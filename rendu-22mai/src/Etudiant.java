import java.util.ArrayList;
/**
 * Student with an id (numEtudiant) and a group (groupe).
 *
 * @author L. Kerrault
 */
public class Etudiant extends Utilisateur{
    /**The Student's ID */
    private int numEtudiant;
    /**The Student's group */
    private GroupeTD groupe;

    /**
    * Constructor : Initializes a Etudiant object with its parameters.
    * @param nom Student's name
    * @param prenom Student's first name
    * @param email Student's e-mail
    * @param mdp Student's password
    * @param num Student's ID
    */
    public Etudiant(String nom, String prenom, String email, String mdp, int num) throws IllegalArgumentException{
        super(nom, prenom, email, mdp);

        if(num != 0){
            this.numEtudiant = num;
        } else{
            throw new IllegalArgumentException("Argument(s) invalide(s) dans le constructeur de Etudiant");
        }
    }

    /**
     * Displays the Student's infos.
     */
    public String toString(){
        return ("\n*** Etudiant ***\n" + super.toString() + "\nn° Etudiant : " + this.getNum());
    }

    /**
    * Gets Student's ID
    * @return numEtudiant
    */
    public int getNum(){
        return this.numEtudiant;
    }

    /**
    * Changes the Student's ID
    * @param nvNum the new ID of the Student
    */
    public void setNum(int nvNum){
        if(nvNum != 0){
            this.numEtudiant = nvNum;
        } else{
            System.out.println("Erreur : numéro incorrect.");
        }

    }

    /**
    * Gets Student's group
    * @return groupe
    */
    public GroupeTD getGroupe(){
        return this.groupe;
    }

    /**
    * Calculates the Student's average on a Module
    * @param m the Module to calculate the average on
    * @return the average, or -1 if m is null
    */
    public double calculerMoyenne(Module m){
        double sum = 0.0;
        double res = 0.0;

        if(m != null){
            for(Evaluation e : m.getListeEval()){
                sum += e.getNote(this);
            }
            res = sum / m.getListeEval().size();
        } else{
            res = -1;
        }

        return res;
    }

    public double calculerMoyenneGenerale(){
        ArrayList<Module> mList = this.groupe.getPromotion().getModules();
        double sum = 0.0;
        double res = 0.0;

        for(int i = 0; i < mList.size(); i++){
            sum += calculerMoyenne(mList.get(i));
        }
        res = sum / mList.size();

        return res;
    }

    /*public ArrayList<Seance> sceancesSuivies(){
        ArrayList<Seance> ret = new ArrayList<>();
        }*/

}
