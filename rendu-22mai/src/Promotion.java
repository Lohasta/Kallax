import java.util.ArrayList;

/**
 * Promotion with an Enumeration of promotions (BUT1, BUT2, BUT3),
 * an ArrayList of GroupeTD and an ArrayList of Module.
 *
 * @author Team KALLAX
 */
public class Promotion{
    /**Name of the Promotion (can be BUT1, BUT2, BUT3) */
    private EnumPromo nomPromo;
    /**ArrayList of GroupeTD */
    private ArrayList<GroupeTD> groupes;
    /**ArrayList of Module */
    private ArrayList<Module> modules;

    /**
    * Constructor : Initializes the name of the Promotion and the two
    * ArrayList.
    * @param nom Either BUT1, BUT2, or BUT3
    * @throws IllegalArgumentException if nom is null
 */
    public Promotion(EnumPromo nom) throws IllegalArgumentException{
        if(nom != null){
            this.nomPromo = nom;
        } else{
            throw new IllegalArgumentException("Constructeur de Promotion : nom incorrect.");
        }

        this.groupes = new ArrayList<>();
        this.modules = new ArrayList<>();
    }

    /**
     * Displays the Promotion's infos
     */
    public String toString(){
        return ("\n*** Promotion ***\n" + this.nomPromo);
    }

    /**
    * Adds a GroupeTD to the ArrayList of GroupeTD.
    * @param gr the GroupeTD to add
    */
    public void ajouterGroupe(GroupeTD gr){
        if(gr != null){
            this.groupes.add(gr);
        } else{
            System.out.println("Erreur : le groupe est null.");
        }
    }

    /**
    * Adds a Module to the ArrayList of Module
    * @param m the Module to add
    */
    public void ajouterModule(Module m){
        if(m != null){
            this.modules.add(m);
        } else{
            System.out.println("Erreur : le module est null");
        }
    }

    /**
    * Returns the name of the Promotion
    * @return name of the Promotion
    */
    public EnumPromo getNomPromo(){
        return this.nomPromo;
    }

    /**
    * Changes the name of the Promotion to the one in parameters
    * @param nvNomPromo the new name
    */
    public void setNomPromo(EnumPromo nvNomPromo){
        this.nomPromo = nvNomPromo;
    }

    /**
    * Returns the list of Modules
    * @return an ArrayList of Modules
    */
    public ArrayList<Module> getModules(){
        return this.modules;
    }

    /**
    * Returns the list of GroupeTD
    * @return an ArrayList of GroupeTD
    */
    public ArrayList<GroupeTD> getGroupes(){
        return this.groupes;
    }
}
