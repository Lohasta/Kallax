import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

/**
 * Seance d'un module avec une date et des horaires, avec un dictionnaire de presence.
 * @author M.Leroux
 * @version 1.0
 */
public class Seance {

    /** Date de la seance */
    private Date date;

    /** Heure de debut */
    private LocalTime heureDeb;

    /** Heure de fin */
    private LocalTime heureFin;

    /** Module de la seance */
    private Module module;

    /** Groupe d'etudiants */
    private GroupeTD groupe;

    /** Dictionnaire de presence */
    private HashMap<Etudiant, EnumPresence> appel;
    

    /**
     * Constructeur de Seance
     * @param date date de la seance
     * @param hDeb Heure de debut
     * @param hFin Heure de fin
     * @param m Module de la seance
     * @param gr Groupe d'etudiants
     * @throws IllegalArgumentException si un des argument est invalide
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
     * Permet d'ajouter des etudiants au dictionnaire de presence
     * @param e etudiant a ajouter
     * @param pres etat de presence de l'etudiant
     */
    public void ajouterPresence(Etudiant e, EnumPresence pres){

        if (e.getGroup().equals(groupe)){
            appel.put(e, pres);
        }
    }


    /**
     * Chaine de caracteres representant la seance
     * @return Chaine de caracteres representant la seance
     */
    public String toString(){
        String res = "La seance du " + date + " commence a " + heureDeb;
        res += " et finit a " + heureFin + ", dans le module " + module;
        res += " avec le groupe " + groupe + ".";

        res += "Le presence au cours est :" + appel.toString();

        return res;

    }
}
