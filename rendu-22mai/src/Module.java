import java.util.ArrayList;

public class Module{
    private String code;
    private String intitule;
    private EnumModule type;
    private ArrayList<Evaluation> listeEval;
    private ArrayList<Enseignant> responsables;
    private Promotion promo;

    public Module(String code, String intitule, EnumModule type, Promotion promo){
        
        if (code == null || intitule == null || type == null || promo == null){
            throw new IllegalArgumentException("Argument(s) invalide(s) dans le constructeur");
        }

        else{
            this.code = code;
            this.intitule = intitule;
            this.type = type;
            this.promo = promo;
            this.listeEval = new ArrayList<Evaluation>();
            this.responsables = new ArrayList<Enseignant>();
        }
    }


    public String toString(){
        String res = "Le module numero" + code + " " + intitule;
        res += " est une " + type + " dont les responsables sont ";

        for (Enseignant ens : responsables){
            res += ens.getNom() + ",";
        }

        for (Evaluation eval : listeEval){
            res += eval.getIdent() + ",";
        }


        return res;
    }



}
