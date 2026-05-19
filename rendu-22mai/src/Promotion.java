import java.util.ArrayList;

public class Promotion{
    private EnumPromo nomPromo;
    //private ArrayList<GroupeTD> groupes;

    public Promotion(EnumPromo nom){
        if(nom != null){
            this.nomPromo = nom;
        } else{
            System.out.println("Constructeur de Promotion : nom incorrect.");
        }
    }

    public String toString(){
        return ("\n*** Promotion ***\n");
    }

    /*public void ajouterGroupe(GroupeTD gr){

    }*/

    public void ajouterModule(Module m){

    }

    public EnumPromo getNomPromo(){
        return this.nomPromo;
    }

    public void setNomPromo(EnumPromo nvNomPromo){
        this.nomPromo = nvNomPromo;
    }
}
