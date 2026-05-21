import java.util.ArrayList;
import java.util.HashMap;
/**
 * Student with an id (numEtudiant) and a group (groupe).
 * Can calculate his average on a Module and on all Modules,
 * check if he participates in a Module, get his presence
 * on a Module and get the Seance he was present in.
 * Inherits from Utilisateur attributes
 *
 * @author Team KALLAX
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
    * Gets Student's ID.
    * @return numEtudiant
    */
    public int getNum(){
        return this.numEtudiant;
    }

    /**
    * Changes the Student's ID.
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
    * Changes the Student's group
    * @param nvGr the new group of the Student
    */
    public void setGroupe(GroupeTD nvGr){
        if(nvGr != null){
            this.groupe = nvGr;
        } else{
            System.out.println("Erreur : GroupeTD est null");
        }

    }

    /**
    * Calculates the Student's average on a Module.
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

    /**
    * Using calcluerMoyenne(), calculates the
    * entire average.
    * (i.e sum of the Modules' average / nb of Modules)
    * @return the average grade of Etudiant
    */
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

    /**
    * Checks if the Module in parameters is valid, i.e
    * if it's inside the list of Modules of the Promotion
    * of the Etudiant.
    * @param m the Module to check
    * @return true if the Module is valid, false else.
    */
    public boolean moduleValide(Module m){
        boolean ret = false;
        if(m != null && this.groupe.getPromotion().getModules().contains(m)){
            ret = true;
        }

        return ret;
    }

    /**
    * Gets the presence of the Etudiant in the form of an HashMap,
    * with the Seance as the Key and the Presence as value.
    * @param m the Module to get the presence from
    * @return an HashMap containing the Seance and the Presence.
    */
    public HashMap<Seance, EnumPresence> recupererPresence(Module m){
        HashMap<Seance, EnumPresence> ret = new HashMap<>();

        if(m != null){
            ArrayList<Seance> seances = m.getSeance();

            for(Seance s : seances){
                HashMap<Etudiant, EnumPresence> appel = s.getAppel();
                if(appel.containsKey(this)){
                    EnumPresence presence = appel.get(this);
                    ret.put(s, presence);
                }
            }
        }
        return ret;
    }

    /**
    * Returns the list of followed Seances of the GroupTD
    * of the Etudiant.
    * @return a list of Seance followed by the Etudiant's GroupTD
    */
    public ArrayList<Seance> seancesSuivies(){
        ArrayList<Seance> ret = new ArrayList<>();
        ArrayList<Module> modules = this.groupe.getPromotion().getModules();

        for(Module m : modules){
            ArrayList<Seance> seances = m.getSeance();

            if(seances != null){
                for(Seance s : seances){
                    if(s.getGroupe() != null && s.getGroupe().equals(this.groupe)){
                        ret.add(s);
                    }
                }
            }
        }
        return ret;
    }
}
