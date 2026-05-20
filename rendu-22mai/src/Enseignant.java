
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author G.REBECHE, L.KERRAULT
 */
public class Enseignant extends Utilisateur{
    
    private String ident;

    private ArrayList<Module> modules;

    public Enseignant(String nom, String prenom, String email, String mdp, String ident) throws IllegalArgumentException{
        super(nom, prenom, email, mdp);

        if(ident != null && ident != ""){
            this.ident = ident;
            this.modules=new ArrayList<>();
        } else{
            throw new IllegalArgumentException("Constructeur de Enseignant : id incorrect.");
        }

    }


    public void ajouterModule(Module m){
        if(m != null && !this.modules.contains(m)){
            this.modules.add(m);
        }
    }

    public void retirerModule(Module m){
        if(m != null && !this.modules.contains(m)){
            this.modules.remove(m);
        }
    }

    public Evaluation creerEval(String codeModule, String typeEval, double coeffEval){
        Evaluation eval = null;
        boolean verif = true;
        int i = 0;
        if (codeModule != null && codeModule != ""){
            if(typeEval != null && typeEval != ""){
                if(coeffEval >= 0){
                    while(verif) {
                        if(this.modules.get(i).getIntitule().equals(codeModule)){
                            eval=new Evaluation(typeEval, coeffEval, this.modules.get(i));
                            this.modules.get(i).ajouterEvaluation(eval);
                            verif = false;
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

    public Evaluation chercherEval(String codeModule, String identEval){
        int i = 0;
        boolean verif = true;
        Evaluation eval = null;
        if (codeModule != null && codeModule != ""){
            if(identEval != null && identEval != ""){
                while(verif){
                    if(this.modules.get(i).getIntitule().equals(codeModule)){
                        int j = 0;
                        while(verif){
                            if(this.modules.get(i).getListeEval().get(j).getIndent.equals(codeModule)){
                                eval=this.modules.get(i).getListeEval().get(j);
                                verif = false;
                            }
                            j++;
                        }
                        i++;
                    }
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

    public void supprimerEval(Evaluation e){
        int i = 0;
        boolean verif = true;
        if(this.modules.getResponsables().contains(this)){
            while(verif){
                int j = 0;
                while(verif){
                    if(this.modules.get(i).getListeEval().get(j)==e){
                        this.modules.get(i).retirerEvaluation(e);
                        verif = false;
                    }
                }
               i++; 
            }
        }
        else{
            System.out.println("Evaluation incorrecte");
        }
    }

    public void modifierTypeEval(Evaluation e, String nvType){
        if(this.modules.getResponsables().contains(this)){
            e.setIdent(nvType);
        }
        else{
            System.out.println("L'enseignant n'est pas responsable");
        }
    }

    public void modifierCoeffEval(Evaluation e, double nvCoeff){
        if(this.modules.getResponsables().contains(this)){
            e.setCoeff(nvCoeff);
        }
        else{
            System.out.println("L'enseignant n'est pas responsable");
        }
    }

    public void saisirNote(Evaluation e, Etudiant etud, double valeur){
        if(this.modules.getResponsables().contains(this)){
            e.ajouterNote(etud, valeur);
        }
        else{
            System.out.println("L'enseignant n'est pas responsable");
        }
    }

    public double calculerMoyenne(Module m){
       return m.calculerMoyenne();
    }

    public void ajouterSeance(Module m, Date date,LocalTime heureDeb, LocalTime heureFin, GroupeTD gr){
        if(this.modules.getResponsables().contains(this)){
            new Seance(date, heureDeb, heureFin, m, gr);
        }
        else{
            System.out.println("L'enseignant n'est pas responsable");
        }
    }

    public void marquerPresence(Etudiant e, Seance s, EnumPresence pres){
        s.ajouterPresence(e, pres);
    }

    public void tousPresents(Seance s){
        s.ajouterPresence(, EnumPresence.PRESENT);
    }

    public void afficherPresenceModule(String codeModule){

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

    public ArrayList<Module> getModules(){
        return new ArrayList<>(this.modules);
    }

    public void setModules(ArrayList<Module> m){
        if(m != null){
            this.modules = m;
        }
    }
}
