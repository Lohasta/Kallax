import java.util.ArrayList;

/**
 * Promotion with an Enumeration of promotions (BUT1, BUT2, BUT3),
 * an ArrayList of GroupeTD and an ArrayList of Module.
 *
 * @author Team KALLAX
 */
public class Promotion{
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
