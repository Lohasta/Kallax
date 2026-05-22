import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

/**
 * Session of a module, with a date, a start time, an end time, a module, a group of students and a dictionary of presence
 * @author TEAM KALLAX
 * @version 1.0
 */
public class Seance {

    /** Date of the session */
    private Date date;

    /** Start time of the session */
    private LocalTime heureDeb;

    /** End time of the session */
    private LocalTime heureFin;

    /** Module of the session */
    private Module module;

    /** Group of students */
    private GroupeTD groupe;

    /** Dictionary of presence */
    private HashMap<Etudiant, EnumPresence> appel;


    /**
     * Session constructor
     * @param date date of the session
     * @param hDeb start time of the session
     * @param hFin end time of the session
     * @param m Module of the session
     * @param gr Group of students
     * @throws IllegalArgumentException if any of the arguments is invalid
     */
    public Seance(Date date, LocalTime hDeb, LocalTime hFin, Module m, GroupeTD gr) throws IllegalArgumentException{
        if (date == null ){
            throw new IllegalArgumentException("Constructeur de Seance : date incorrecte.");
        }

        if (hDeb == null){
            throw new IllegalArgumentException("Constructeur de Seance : heure de debut incorrecte.");
        }

        if (hFin == null){
            throw new IllegalArgumentException("Constructeur de Seance : heure de fin incorrecte.");
        }

        if (m == null){
            throw new IllegalArgumentException("Constructeur de Seance : module incorrect.");
        }

        if (gr == null){
            throw new IllegalArgumentException("Constructeur de Seance : groupe incorrect.");
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
     * Add a presence for a student in the session
     * @param e the student to add
     * @param pres presence status of the student
     */
    public void ajouterPresence(Etudiant e, EnumPresence pres){
        if(e != null && pres != null && e.getGroupe() != null){
            if (e.getGroupe().equals(groupe)){
                appel.put(e, pres);
            }
        }
    }


    /**
     * String representing the session
     * @return String representing the session
     */
    @Override
    public String toString(){
        String res = "Session date : " + date + "\n";
        res += "Start time : " + heureDeb + "\n";
        res += "End time : " + heureFin + "\n";
        res+= "Module : " + module + "\n";
        res += " with group " + groupe + "\n";

        res += "Presence " + appel.toString();

        return res;

    }



    /* --- GETTERS / SETTERS --- */

    /**
     * Date getter
     * @return date of the session
     */
    public Date getDate(){
        return this.date;
    }


    /**
     * Start time getter
     * @return start time of the session
     */
    public LocalTime getHeureDeb(){
        return this.heureDeb;
    }
    

    /**
     * End time getter
     * @return end time of the session
     */
    public LocalTime getHeureFin(){
        return this.heureFin;
    }

    /**
     * Module getter
     * @return module of the session
     */
    public Module getModule(){
        return this.module;
    }

    /**
     * Group getter
     * @return group of the session
     */
    public GroupeTD getGroupe(){
        return this.groupe;
    }

    /**
     * Presence dictionary getter
     * @return presence dictionary of the session
     */
    public HashMap<Etudiant, EnumPresence> getAppel(){
        return new HashMap<Etudiant, EnumPresence>(this.appel);
    }


    /**
     * Date setter
     * @param newDate the new date of the session
     */
    public void setDate(Date newDate){
        if (newDate != null){
            this.date = newDate;
        }
    }

    /**
     * Start time setter
     * @param newHeureDeb the new start time of the session
     */
    public void setHeureDeb(LocalTime newHeureDeb){
        if (newHeureDeb != null){
            this.heureDeb = newHeureDeb;
        }
    }
    


    /**
     * End time setter
     * @param newHeureFin the new end time of the session
     */
    public void setHeureFin(LocalTime newHeureFin){
        if (newHeureFin != null){
            this.heureFin = newHeureFin;
        }
    }


    /**
     * Module setter
     * @param newModule the new module of the session
     */
    public void setModule(Module newModule){
        if (newModule != null){
            this.module = newModule;
        }
    }

    /**
     * Group setter
     * @param newGroupe the new group of the session
     */
    public void setGroupe(GroupeTD newGroupe){
        if (newGroupe != null){
            this.groupe = newGroupe;
        }
    }

    /**
     * Presence dictionary setter
     * @param newAppel the new presence dictionary of the session
     */
    public void setAppel(HashMap<Etudiant, EnumPresence> newAppel){
        if (newAppel != null){
            this.appel = new HashMap<Etudiant, EnumPresence>(newAppel);
        }
    }
}
