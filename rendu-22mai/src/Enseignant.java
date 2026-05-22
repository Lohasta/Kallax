
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * Enseignant who can add and delete modules, create, search, and delete tests,
 * change tests type and coefficient, add grades, calculate averages,
 * add session, add student presence and display it
 *
 * @author TEAM KALLAX
 * @version 1.0
 */
public class Enseignant extends Utilisateur{

    /** Teachers ID */
    private String ident;

    /** List of modules */
    private ArrayList<Module> modules;

    /**
     * Constructor of Enseignant
     * @param nom name of the teacher
     * @param prenom first name of the teacher
     * @param email email address of the teacher
     * @param mdp password of the teacher
     * @param ident identifiant of the teacher
     * @throws IllegalArgumentException if an argument is invalid
     */
    public Enseignant(String nom, String prenom, String email, String mdp, String ident) throws IllegalArgumentException{
        super(nom, prenom, email, mdp);

        if(ident != null && ident != ""){
            this.ident = ident;
            this.modules=new ArrayList<>();
        } else{
            throw new IllegalArgumentException("Constructeur de Enseignant : id incorrect.");
        }

    }

    /**
     * Method to add module in modules
     * @param m module to add
     */
    public void ajouterModule(Module m){
        if(m != null && !this.modules.contains(m)){
            this.modules.add(m);
        }
    }

    /**
     * Method to delete module in modules
     * @param m modules to delete
     */
    public void retirerModule(Module m){
        if(m != null && !this.modules.contains(m)){
            this.modules.remove(m);
        }
    }

    /**
     * Method to create tests
     * @param codeModule module ID
     * @param typeEval tests type
     * @param coeffEval tests coefficient
     * @return a new test
     */
    public Evaluation creerEval(String codeModule, String typeEval, double coeffEval){
        Evaluation eval = null;
        boolean verif = true;
        int i = 0;
        if (codeModule != null && codeModule != ""){
            if(typeEval != null && typeEval != ""){
                if(coeffEval >= 0){
                    while(verif && i<this.modules.size()) {
                        if (this.modules.get(i).getIntitule().equals(codeModule)) {
                        eval = new Evaluation(typeEval, coeffEval, this.modules.get(i));
                        this.modules.get(i).ajouterEvaluation(eval);
                        verif = true;
                }
                        i++;
                    }
                }
                else{
                    System.out.println("Coefficient inférieur ou égale à 0");
                }
            }
            else{
                System.out.println("Type d'évaluation incorrecte");
            }
        }
        else{
            System.out.println("Code module incorrecte");
        }
        return eval;
    }

    /**
     * Search test in a list of tests grouped by module
     * @param codeModule module ID
     * @param identEval test ID
     * @return test of type Evaluation
     */
    public Evaluation chercherEval(String codeModule, String identEval){
        int i = 0;
        boolean verif = true;
        Evaluation eval = null;
        if (codeModule != null && codeModule != ""){
            if(identEval != null && identEval != ""){
                while(verif && i<this.modules.size()){
                    if(this.modules.get(i).getIntitule().equals(codeModule)){
                        int j = 0;
                        while(verif && j<this.modules.get(i).getListeEval().size()){
                            if(this.modules.get(i).getListeEval().get(j).getIdent().equals(identEval)){
                                eval=this.modules.get(i).getListeEval().get(j);
                                verif = false;
                            }
                            j++;
                        }
                    }
                    i++;
                }
            }
            else{
                System.out.println("Identifiant evaluation incorrecte");
            }
        }
        else{
            System.out.println("Code module incorrecte");
        }
        return eval;
    }

    /**
     * Method to delete test from the list
     * @param e test to delete
     */
    public void supprimerEval(Evaluation e){
        int k = 0;
        boolean verif = true;
        if(e != null){
            while(verif && k<this.modules.size()){
                if(this.modules.get(k).getResponsables().contains(this)){
                    int i = 0;
                    while(verif && i<this.modules.get(k).getListeEval().size()){
                        if(this.modules.get(k).getListeEval().get(i)==e){
                            this.modules.get(k).retirerEvaluation(e);
                            verif = false;
                        }
                        i++;
                    }
                }
                k++;
            }
        }else{
            System.out.println("Evaluation incorrecte");
        }

    }

    /**
     * Method to change type of test
     * @param e test to change type
     * @param nvType new type
     */
    public void modifierTypeEval(Evaluation e, String nvType){
        boolean verif = true;
        int i = 0;
        if(e != null){
            while(verif && i<this.modules.size()){
                if(this.modules.get(i).getResponsables().contains(this)){
                    e.setIdent(nvType);
                    verif = false;
                }
                i++;
            }
        }else{
            System.out.println("Evaluation incorrecte");
        }
    }

    /**
     * Method to change coefficient of test
     * @param e test to change coefficient
     * @param nvCoeff new coefficient
     */
    public void modifierCoeffEval(Evaluation e, double nvCoeff){
        boolean verif = true;
        int i = 0;
        if(e != null){
            while(verif && i<this.modules.size()){
                if(this.modules.get(i).getResponsables().contains(this)){
                    e.setCoeff(nvCoeff);
                    verif = false;
                }
                i++;
            }
        }else{
            System.out.println("Evaluation incorrecte");
        }

    }

    /**
     * Method to add garde to a student on a test
     * @param e the test
     * @param etud the Student
     * @param valeur The students grade for the test
     */
    public void saisirNote(Evaluation e, Etudiant etud, double valeur){
        boolean verif = true;
        int i = 0;
        if(e != null){
            while(verif && i<this.modules.size()){
                if(this.modules.get(i).getResponsables().contains(this)){
                    e.ajouterNote(etud, valeur);
                    verif = false;
                }
                i++;
            }
        }else{
            System.out.println("Evaluation incorrecte");
        }
    }

    /**
     * Method to calculate average of the module
     * @param m the module
     * @return modules average
     */
    public double calculerMoyenne(Module m){
       return m.calculerMoyenne();
    }

    /**
     * Method to add session to a module list
     * @param m the module to add session
     * @param date date of the session
     * @param heureDeb start time
     * @param heureFin end time
     * @param gr TD group
     */
    public void ajouterSeance(Module m, Date date,LocalTime heureDeb, LocalTime heureFin, GroupeTD gr){
        boolean verif = true;
        int i = 0;
        while(verif && i<this.modules.size()){
            if(this.modules.get(i).getResponsables().contains(this)){
                new Seance(date, heureDeb, heureFin, m, gr);
                verif = false;
            }
            else{
                System.out.println("L'enseignant n'est pas responsable");
            }
            i++;
        }
    }

    /**
     * Method to mark presences of students in session
     * @param e student to mark
     * @param s session
     * @param pres status of student
     */
    public void marquerPresence(Etudiant e, Seance s, EnumPresence pres){
        if(s != null){
            s.ajouterPresence(e, pres);
        }else{
            System.out.println("Seance incorrecte");
        }
    }

    /**
     * Method to mark all students of a TD group present on a session
     * @param s the session
     */
    public void tousPresents(Seance s){
        if(s != null){
            for (Etudiant appel : s.getAppel().keySet() ) {
                s.ajouterPresence(appel,EnumPresence.PRESENT);
            }
        }else{
            System.out.println("Seance incorrecte");
        }
    }

    /**
     * Method to display student datas and his presence status for each session of a type of module
     * @param codeModule module ID
     */
    public void afficherPresenceModule(String codeModule){
        String msg = "";
        boolean verif = true;
        int i = 0;
        if (codeModule != null && codeModule != ""){
            while(verif && i<this.modules.size()){
                if(this.modules.get(i).getIntitule().equals(codeModule)){
                    for (Seance s : this.modules.get(i).getSeance()) {
                       for (Etudiant appel : s.getAppel().keySet() ) {
                            msg+= "Etudiant : "+appel.getNum()+" EnumPresence : "+s.getAppel().get(appel)+"\n";
                        }
                    }
                }
                i++;
            }
            System.out.println(msg);
        }
        else{
            System.out.println("Code module incorrect");
        }
    }


    /**
     * The toString
     */
    @Override
    public String toString(){
        String ret = "";
        if(this instanceof Enseignant){
            ret += ("\n*** Enseignant ***\n" + super.toString() + "\nID : " + this.getIdent() + "\nModules : "+this.modules+"\n");
        } else if(this instanceof DirecteurEtudes){
            ret += ("\n*** DirecteurEtudes ***\n" + super.toString() + "\nID : " + this.getIdent() + "\nModules : "+this.modules+"\n");
        }

        return ret;
    }

    /**
     * get ID of currant teacher
     * @return teachers ID
     */
    public String getIdent(){
        return this.ident;
    }

    /**
     * set ID of currant teacher
     * @param nvIdent new ID
     */
    public void setIdent(String nvIdent){
        if(nvIdent != null && nvIdent != ""){
            this.ident = nvIdent;
        }
    }

    /**
     * get modules of currant teacher
     * @return list of modules
     */
    public ArrayList<Module> getModules(){
        return new ArrayList<>(this.modules);
    }

    /**
     * set modules of currant teacher
     * @param m new list of modules
     */
    public void setModules(ArrayList<Module> m){
        if(m != null){
            this.modules = m;
        }
    }
}
