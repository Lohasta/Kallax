import java.util.ArrayList;

/**
 * Module qui a un code, un intitule et un type. Ce module a des evaluations et des enseignants responsables. Une promotion lui est attribue.
 * @author M.Leroux
 * @version 1.0
 */
public class Module{

    /** Code du module */
    private String code;

    /** Intitule du module */
    private String intitule;

    /** Type du module */
    private EnumModule type;

    /** Liste d'evaluations du module */
    private ArrayList<Evaluation> listeEval;

    /** Liste d'enseignants responsables du module */
    private ArrayList<Enseignant> responsables;

    /** Promotion qui ont des cours sur le module */
    private Promotion promo;



    /**
     * Constructeur de Module
     * @param code Code du module
     * @param intitule Intitule du module
     * @param type Type du module
     * @param promo Promotion qui ont des cours sur le module
     */
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

    /**
     * Chaine de caracteres representant le module
     * @return Chaine de caracteres representant le module
     */
    @Override
    public String toString(){
        String res = "Le module numero" + code + " " + intitule;
        res += " est une " + type + " dont les responsables sont ";

        for (Enseignant ens : responsables){
            res += ens.getNom() + ", ";
        }

        res += "\nLes evaluations du modules sont : ";

        for (Evaluation eval : listeEval){
            res += eval.getIdent() + ", ";
        }

        res += ".\nLa promotion qui assiste au module est " + promo;
        return res;
    }



}
