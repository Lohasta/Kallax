import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * Module identified by a code, a title and a type.
 * A module contains evaluations, responsible teachers and sessions.
 * A promotion is associated with the module. 
 * @author TEAM KALLAX
 * @version 1.0
 */
public class Module{

    /** Module code */
    private String code;

    /** Module title */
    private String intitule;

    /** Module type */
    private EnumModule type;

    /** List of evaluations for the module */
    private ArrayList<Evaluation> listeEval;

    /** List of responsible teachers for the module */
    private ArrayList<Enseignant> responsables;

    /** Promotion that has courses on the module */
    private Promotion promo;

    /** List of sessions for the module */
    private ArrayList<Seance> seances;



    /**
     * Module constructor
     * @param code Module code
     * @param intitule Module title
     * @param type Module type
     * @param promo Promotion that has courses on the module
     */
    public Module(String code, String intitule, EnumModule type, Promotion promo) throws IllegalArgumentException{

        if (code == null || intitule == null || type == null || promo == null){
            throw new IllegalArgumentException("Argument(s) invalide(s) dans le constructeur de Module");
        }

        else{
            this.code = code;
            this.intitule = intitule;
            this.type = type;
            this.promo = promo;
            this.listeEval = new ArrayList<Evaluation>();
            this.seances = new ArrayList<Seance>();
            this.responsables = new ArrayList<Enseignant>();
        }
    }

    /**
     * String representing the module
     * @return String representing the module
     */
    @Override
    public String toString(){
        String res = "Module code : " + this.code + "\n";
        res += "Module title : " + this.intitule + "\n";
        res += "Module type : " + this.type + "\n";
        res += "Responsible teachers : ";

        for (Enseignant ens : responsables){
            res += ens.getNom() + ", ";
        }

        res += "\nEvaluations : ";

        for (Evaluation eval : listeEval){
            res += eval.getIdent() + ", ";
        }

        res += ".\nPromotion : " + promo;
        return res;
    }


    /**
     * Method to add a session to the module
     * @param date date of the session
     * @param heureDeb start time of the session
     * @param heureFin end time of the session
     * @param gr group of students attending the session
     */ 
    public void ajouterSeance(Date date, LocalTime heureDeb, LocalTime heureFin, GroupeTD gr){

        for (GroupeTD groupe : promo.getGroupes()){
            if (groupe == gr){
                Seance newSeance = new Seance(date, heureDeb, heureFin, this, gr);
                seances.add(newSeance);
            }

            else{
                System.out.println("ce groupe n'a pas cours dans ce module ");
            }
        }
    }

    /**
     * Method to add a responsible teacher to the module
     * @param e teacher to add as responsible for the module
     */
    public void ajouterResponsable(Enseignant e){
        boolean estResponsable = false;

        if (e != null){
            //check if already in the list
            for (Enseignant ens : responsables){

                if (ens == e){
                    estResponsable = true;
                }
            }
            //if not in the list, add it
            if (!estResponsable){
                responsables.add(e);
            }
        }

        else {
            System.out.println("Ens null");
        }
    }


    /**
     * Method to remove a responsible teacher from the module
     * @param e teacher to remove from the list of responsible teachers for the module
     */
    public void retirerResponsable(Enseignant e){
         boolean estResponsable = false;

        if (e != null){
            //check if already in the list
            for (Enseignant ens : responsables){

                if (ens == e){
                    estResponsable = true;
                }
            }
            //if in the list, remove it
            if (estResponsable){
                responsables.remove(e);
            }
        }

        else {
            System.out.println("Ens null");
        }
    }


    /**
     * Method to add an evaluation to the module
     * @param e evaluation to add to the module
     */
    public void ajouterEvaluation(Evaluation e){
        boolean estEval = false;

        //if not null
        if (e != null){
            //check if already in the list
            for (Evaluation eval : listeEval){

                if (eval == e){
                    estEval = true;
                }
            }
            //if not in the list, add it
            if (!estEval){
                listeEval.add(e);
            }
        }

        else {
            System.out.println("Eval null");
        }
    }


    /**
     * Method to remove an evaluation from the module
     * @param e evaluation to remove from the list of evaluations for the module
     */
    public void retirerEvaluation(Evaluation e){
        boolean estEval = false;

        //if not null
        if (e != null){
            //check if already in the list
            for (Evaluation eval : listeEval){

                if (eval == e){
                    estEval = true;
                }
            }
            //if in the list, remove it
            if (estEval){
                listeEval.remove(e);
            }
        }

        else {
            System.out.println("Eval null");
        }
    }


    /**
     * Method to calculate the average mark for the module
     * @return the average mark for the module
     */
    public double calculerMoyenne(){

        double sommeMoyenne = 0;
        double sommeCoeff = 0;

        for (Evaluation e : listeEval){
            sommeMoyenne += e.calculerMoyenne() * e.getCoeff();
            sommeCoeff += e.getCoeff();
        }

        return sommeMoyenne / sommeCoeff;
    }


    /**
     * Static method to search for a module in a list of modules by its code
     * @param code code of the module to search for
     * @param liste list of modules to search in
     * @return the module with the code, or null fi not found
     */
    public static Module chercherModule(String code, ArrayList<Module> liste){
        int i = 0;
        Module moduleCherche = null;

        while (i < liste.size()){

            //if the Module at the index i has the same code has the one in arg
            if (liste.get(i).getCode().equals(code)){
                moduleCherche = liste.get(i);
                i = liste.size();
            }

            i++;
        }

        if (moduleCherche == null){
            System.out.println("Pas de module avec ce code");
        }

        return moduleCherche;
    }


    // --- GETTER / SETTER ---

    /**
     * Code getter
     * @return the code of the module
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Intitule getter 
     * @return the title of the module
     */
    public String getIntitule(){
        return this.intitule;
    }

    /**
     * Type getter
     * @return the type of the module
     */
    public EnumModule getType(){
        return this.type;
    }

    /**
     * ListeEval getter
     * @return a copy of the list of evaluations for the module
     */
    public ArrayList<Evaluation> getListeEval() {
        return new ArrayList<>(this.listeEval);
    }

    /**
     * Responsables getter
     * @return a copy of the list of responsible teachers for the module
     */
    public ArrayList<Enseignant> getResponsables(){
        return new ArrayList<>(this.responsables);
    }

    /**
     * Promotion getter
     * @return the promotion that has courses on the module
     */
    public Promotion getPromo(){
        return this.promo;
    }

    /**
     * Seance getter
     * @return a copy of the list of sessions for the module
     */
    public ArrayList<Seance> getSeance(){
        return new ArrayList<>(this.seances);
    }


    /**
     * Code setter
     * @param newCode the new code for the module
     */
    public void setCode(String newCode){
        if (newCode != null){
            this.code = newCode;
        }
    }


    /**
     * Intitule setter
     * @param newIntitule the new title for the module
     */
    public void setIntitule(String newIntitule){
        if (newIntitule != null){
            this.intitule = newIntitule;
        }
    }

    /**
     * Type setter
     * @param newType the new type for the module
     */
    public void setType(EnumModule newType){
        this.type = newType;
    }

    /**
     * ListeEval setter
     * @param newListeEval the new list of evaluations for the module
     */
    public void setListeEval(ArrayList<Evaluation> newListeEval){
        this.listeEval = newListeEval;
    }

    /**
     * Responsables setter
     * @param newResponsables the new list of responsible teachers for the module
     */
    public void setResponsables(ArrayList<Enseignant> newResponsables){
        this.responsables = newResponsables;
    }

    /**
     * Promotion setter
     * @param newPromo the new promotion that has courses on the module
     */
    public void setPromotion(Promotion newPromo){
        this.promo = newPromo;
    }

    /**
     * Sessions setter
     * @param newSeances the new list of sessions for the module
     */
    public void setSeance(ArrayList<Seance> newSeances){
        this.seances = newSeances;
    }

}
