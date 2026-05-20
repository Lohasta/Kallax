import java.time.LocalTime;
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

    private ArrayList<Seance> seances;



    /**
     * Constructeur de Module
     * @param code Code du module
     * @param intitule Intitule du module
     * @param type Type du module
     * @param promo Promotion qui ont des cours sur le module
     */
    public Module(String code, String intitule, EnumModule type, Promotion promo) throws IllegalArgumentException{
        
        if (code == null || intitule == null || type == null || promo == null){
            throw new IllegalArgumentException("Argument(s) invalide(s) dans le constructeur de Module");
        }

        else{
            this.code = code;
            this.intitule = intitule;
            this.type = type;
            this.promo = promo;
            this.listeEval = new ArrayList<Evaluation>();
            this.seances = new ArrayList<Seance>();
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


    /**
     * 
     * @param date
     * @param heureDeb
     * @param heureFin
     * @param gr
     */
    public void ajouterSeance(Date date, LocalTime heureDeb, LocalTime heureFin, GroupeTD gr){
        
        for (GroupeTD groupe : promo.getGroupeTD()){
            if (groupe == gr){
                Seance newSeance = new Seance(date, heureDeb, heureFin, this, gr);
                seances.add(newSeance);
            }

            else{
                System.out.println("ce groupe n'a pas cours dans ce module ");
            }
        }
    }

    /**
     * 
     * @param e
     */
    public void ajouterResponsable(Enseignant e){
        boolean estResponsable = false;
        
        if (e != null){
            //check if already in the list
            for (Enseignant ens : responsables){
                
                if (ens == e){
                    estResponsable = true;
                }
            }
            //if not in the list, add it
            if (!estResponsable){
                responsables.add(e);
            }
        }

        else {
            System.out.println("Ens null");
        }  
    }


    /**
     * 
     * @param e
     */
    public void retirerResponsable(Enseignant e){
         boolean estResponsable = false;

        if (e != null){
            //check if already in the list
            for (Enseignant ens : responsables){
                
                if (ens == e){
                    estResponsable = true;
                }
            }
            //if in the list, remove it
            if (estResponsable){
                responsables.remove(e);
            }
        }

        else {
            System.out.println("Ens null");
        }
    }


    /**
     * 
     * @param e
     */
    public void ajouterEvaluation(Evaluation e){
        boolean estEval = false;
        
        //if not null
        if (e != null){
            //check if already in the list
            for (Evaluation eval : listeEval){
                
                if (eval == e){
                    estEval = true;
                }
            }
            //if not in the list, add it
            if (!estEval){
                listeEval.add(e);
            }
        }

        else {
            System.out.println("Eval null");
        }  
    }


    /**
     * 
     * @param e
     */
    public void retirerEvaluation(Evaluation e){
        boolean estEval = false;
        
        //if not null
        if (e != null){
            //check if already in the list
            for (Evaluation eval : listeEval){
                
                if (eval == e){
                    estEval = true;
                }
            }
            //if in the list, remove it
            if (estEval){
                listeEval.remove(e);
            }
        }

        else {
            System.out.println("Eval null");
        }  
    }

    
    /**
     * 
     * @return
     */
    public double calculerMoyenne(){
        
        double sommeMoyenne = 0;
        double sommeCoeff = 0;

        for (Evaluation e : listeEval){
            sommeMoyenne += e.calculerMoyenne() * e.getCoeff();
            sommeCoeff += e.getCoeff();
        }

        return sommeMoyenne / sommeCoeff;
    }



    public static Module chercherModule(String code, ArrayList<Module> liste){
        int i = 0;
        Module moduleCherche = null;

        while (i < liste.size()){

            //if the Module at the index i has the same code has the one in arg
            if (liste.get(i).getCode().equals(code)){
                moduleCherche = liste.get(i);
                i = liste.size();
            }

            i++;
        }

        if (moduleCherche == null){
            System.out.println("Pas de module avec ce code");
        }

        return moduleCherche;
    }


    // --- GETTER / SETTER ---

    /**
     * 
     * @return
     */
    public String getCode(){
        return this.code;
    }

    /**
     * 
     * @return
     */
    public String getIntitule(){
        return this.intitule;
    }

    /**
     * 
     * @return
     */
    public EnumModule getType(){
        return this.type;
    }


    /**
     * 
     * @param newCode
     */
    public void setCode(String newCode){
        if (newCode != null){
            this.code = newCode;
        }
    }


    /**
     * 
     * @param newIntitule
     */
    public void setIntitule(String newIntitule){
        if (newIntitule != null){
            this.intitule = newIntitule;
        }
    }


    public void setType(EnumModule newType){
        this.type = newType;
    }






}
