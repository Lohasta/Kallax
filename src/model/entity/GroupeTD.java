package model.entity;
import java.util.ArrayList;

/**
 * GroupeTD with a name, a Promotion, a list of Module and
 * a list of Etudiant.
 * Can add a Student to it's list, a Module to it's list and
 * compare two GroupeTD to check if they are equal.
 * @author TEAM KALLAX
 */
public class GroupeTD {

    private String nom;
    private Promotion promotion;
    private ArrayList<Module> modulesSuivis;
    private ArrayList<Etudiant> etudiants;

    private static ArrayList<GroupeTD> groupes;

    /**
     * The builder
     * @param nom name of TD group
     * @param promo The promotion in which the TD group will be add
     */
    public GroupeTD(String nom,Promotion promo) throws IllegalArgumentException{
        if (nom != null && !nom.equals("")){
            this.nom = nom;
        }
        else{
            throw new IllegalArgumentException("Constructeur de GroupeTD : nom incorrect");
        }
        if(promo != null){
            this.promotion = promo;
        }
        else{
            throw new IllegalArgumentException("Constructeur de GroupeTD : promo incorrecte");
        }
        this.modulesSuivis=new ArrayList<>();

        this.etudiants=new ArrayList<>();
    }

    public static GroupeTD getGroupeByNomAndPromo(String nom, Promotion promo) {
        if (groupes == null) {
            groupes = new ArrayList<>();
        }
        boolean found = false;
        int i = 0;
        GroupeTD grp = null;
        while (!found && i < groupes.size()) {
            grp = groupes.get(i);
            if (grp.getNom().equals(nom) && promo.equals(grp.getPromotion())) {
                found = true;
            }
            i++;
        }

        if (!found) {
            grp = null;
        }
        return grp;
    }

    public static void addGroupe(GroupeTD groupe) {
        if (!groupes.contains(groupe)) {
            groupes.add(groupe);
        }
    }

    public static ArrayList<GroupeTD> getGroupes() {
        return groupes;
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
        if (e != null) {
            if(this.etudiants.size() <= 28 && this.etudiants != null && !this.etudiants.contains(e)){
                this.etudiants.add(e);
                e.setGroupe(this);
            }
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
        if(grp != null){
            if(this.nom.equals(grp.getNom()) && this.promotion == grp.getPromotion()){
                res = true;
            }
        }
        else{
            System.out.println("Groupe incorrect");
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
       if (n!=null && !n.equals("")){
            this.nom = n;
        }
        else{
            System.out.println("Nom incorrect");
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
        else{
            System.out.println("Promotion incorrecte");
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
        else{
            System.out.println("ModulesSuivis incorrect");
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
        else{
            System.out.println("Etudiants incorrect");
        }
    }
}
