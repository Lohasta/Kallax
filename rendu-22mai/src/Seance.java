import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author M.Leroux
 * @version 1.0
 */
public class Seance {
    
    /**
     * 
     */
    private Date date;
    private LocalTime heureDeb;
    private LocalTime heureFin;
    private Module module;
    private GroupeTD groupe;
    private HashMap<Etudiant, EnumPresence> appel;
    
    /**
     * 
     * @param date
     * @param hDeb
     * @param hFin
     * @param m
     * @param gr
     * @throws IllegalArgumentException
     */
    public Seance(Date date, LocalTime hDeb, LocalTime hFin, Module m, GroupeTD gr) throws IllegalArgumentException{
        if (date == null || hDeb == null || hFin == null || m == null || gr == null){
            throw new IllegalArgumentException("Argument(s) invalide(s) dans le constructeur");
        }

        else{
            this.date = date;
            this.heureDeb = hDeb;
            this.heureFin = hFin;
            this.module = m;
            this.groupe = gr;
            this.appel = new HashMap<Etudiant, EnumPresence>();
        }
    }

    /**
     * 
     * @param e
     * @param pres
     */
    public void ajouterPresence(Etudiant e, EnumPresence pres){

        if (e.getGroup().equals(groupe)){
            appel.put(e, pres);
        }
    }



    public String toString(){
        String res = "La seance du " + date + " commence a " + heureDeb;
        res += " et finit a " + heureFin + ", dans le module " + module;
        res += " avec le groupe " + groupe + ".";

        return res;

    }
}
