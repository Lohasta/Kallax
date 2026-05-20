import java.util.ArrayList;

/**
 * Can define GroupeTD
 * @author G.REBECHE
 */
public class GroupeTD {
    
    private String nom;
    private Promotion promotion;
    private ArrayList<Module> modulesSuivis;
    private ArrayList<Etudiant> etudiants;

    /**
     * The builder
     * @param nom name of TD group
     * @param promo The promotion in which the TD group will be add
     */
    public GroupeTD(String nom,Promotion promo) throws IllegalArgumentException{
        if (nom != null && nom != ""){
            this.nom = nom;
        }
        else{
            throw new IllegalArgumentException("Constructeur de GroupeTD : nom incorrect");
        }
        if(promo != null){
            this.promotion = promo;
        }
        else{
            System.out.println("Constructeur de GroupeTD : promo incorrect");
        }
        this.modulesSuivis=new ArrayList<>();

        this.etudiants=new ArrayList<>();
    }

    /**
     * The toString which displays all the attributes of GroupeTD
     */
    public String toString(){
        String res = "Nom : "+this.nom+"\nPromotion : "+this.promotion+"\nModulesSuivis : "+this.modulesSuivis+"\nEtudiants : "+this.etudiants+"\n";
        return res;
    }

    /**
     * Can add a Student on etudiants
     * @param e the student to add
     */
    public void ajouterEtudiant(Etudiant e){
        if(this.etudiants.size() <= 28 && this.etudiants != null){
            this.etudiants.add(e);
        }
    }

    /**
     * Can add a Module on modulesSuivis
     * @param m the module to add
     */
    public void ajouterModule(Module m){
        if(this.modulesSuivis != null){
            this.modulesSuivis.add(m);
        }
    }

    /**
     * Verify if grp's names and promotion is like the currant TD group
     * @param grp group to verify
     * @return if names and promonotions are the same
     */
    public boolean equals(GroupeTD grp){
        boolean res = false;
        if(this.nom == grp.getNom() && this.promotion == grp.getPromotion()){
            res = true;
        }
        return res;
    }


    /**
     * Get the name of currant GroupeTD 
     * @return the name
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Set the name of currant GroupeTD 
     * @param n the name
     */
    public void setNom(String n){
       if (n!=null && n!=""){
            this.nom = n;
        } 
    }

    /**
     * Get the promotion of currant GroupeTD 
     * @return the promotion
     */
    public Promotion getPromotion(){
        return this.promotion;
    }

    /**
     * Set the promotion of currant GroupeTD 
     * @param promo the promotion
     */
    public void setPromotion(Promotion promo){
        if(promo!=null){
            this.promotion = promo;
        }
    }

    /**
     * Get modulesSuivis of currant GroupeTD 
     * @return modulesSuivis
     */
    public ArrayList<Module> getModulesSuivis(){
        return new ArrayList<>(this.modulesSuivis);
    }

    /**
     * Set modulesSuivis of currant GroupeTD 
     * @param m modulesSuivis
     */
    public void setModulesSuivis(ArrayList<Module> m){
        if(m != null){
            this.modulesSuivis = m;
        }
    }

    /**
     * Get etudiants of currant GroupeTD 
     * @return etudiants
     */
    public ArrayList<Etudiant> getEtudiants(){
        return new ArrayList<>(this.etudiants);
    }

    /**
     * Set etudiants of currant GroupeTD 
     * @param n etudiants
     */
    public void setEtudiants(ArrayList<Etudiant> e){
        if(e != null && e.size() <= 28){
            this.etudiants = e;
        }
    }
}
