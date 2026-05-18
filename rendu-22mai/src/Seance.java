import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author M.Leroux
 * @version 1.0
 */
public class Seance {
    

    private Date date;
    private LocalTime heureDeb;
    private LocalTime heureFin;
    private Module module;
    private GroupeTD groupe;
    private HashMap<Etudiant, EnumPresence> appel;
    
    
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
            this.etudiants = new HashMap<>();
        }
    }
}
