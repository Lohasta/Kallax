package model.entity;
import java.util.ArrayList;

/**
 * Promotion with an Enumeration of promotions (BUT1, BUT2, BUT3),
 * an ArrayList of GroupeTD and an ArrayList of Module.
 *
 * @author Team KALLAX
 */
public class Promotion{

    private static ArrayList<Promotion> promotions;

    private EnumPromo nomPromo;
    private ArrayList<GroupeTD> groupes;
    private ArrayList<Module> modules;

    public Promotion(EnumPromo nom) throws IllegalArgumentException{
        if(nom != null){
            this.nomPromo = nom;
        } else{
            throw new IllegalArgumentException("Constructeur de Promotion : nom incorrect.");
        }

        this.groupes = new ArrayList<>();
        this.modules = new ArrayList<>();
        if (promotions == null) {
            promotions = new ArrayList<>();
        }
        promotions.add(this);
    }

    public static Promotion getPromotionByName(String name) {
        if (promotions == null) {
            promotions = new ArrayList<>();
        }
        boolean found = false;
        int i = 0;
        Promotion promo = null;
        while (!found && i < promotions.size()) {
            promo = promotions.get(i);

            if (promo.getNomPromo().toString().equals(name)) {
                found = true;
            }
            i++;
        }

        if (!found) {
            promo = new Promotion(EnumPromo.valueOf(name));
            promotions.add(promo);
        }
        return promo;
    }

    public GroupeTD getGroupeByName(String name) {
        boolean found = false;
        int i = 0;
        GroupeTD groupe = null;
        while (!found && i < groupes.size()) {
            groupe = groupes.get(i);

            if (groupe.getNom().equals(name)) {
                found = true;
            }
            i++;
        }

        if (!found) {
            groupe = new GroupeTD(name, this);
            this.ajouterGroupe(groupe);
        }
        return groupe;
    }

    public static ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public String toString(){
        return ("\n*** Promotion ***\n" + this.nomPromo);
    }

    public void ajouterGroupe(GroupeTD gr){
        if(gr != null){
            this.groupes.add(gr);
        } else{
            System.out.println("Erreur : le groupe est null.");
        }
    }

    public void ajouterModule(Module m){
        if(m != null){
            this.modules.add(m);
        } else{
            System.out.println("Erreur : le module est null");
        }
    }

    public EnumPromo getNomPromo(){
        return this.nomPromo;
    }

    public void setNomPromo(EnumPromo nvNomPromo){
        this.nomPromo = nvNomPromo;
    }

    public ArrayList<Module> getModules(){
        return this.modules;
    }

    public ArrayList<GroupeTD> getGroupes(){
        return this.groupes;
    }
}
