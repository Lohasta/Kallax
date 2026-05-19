import java.util.HashMap;

public class Evaluation {
    private String ident;
    private double coeff;
    private HashMap<Etudiant, Double> notes;
    private Module module;
    
    public Evaluation(String type, double coeff, Module m) {
        if (type != null && !type.equals("")) {
            this.ident = type;
        } else {
            this.ident = "Default";
        }

        if (coeff > 0) {
            this.coeff = coeff;
        } else {
            this.coeff = 1;
        }

        if (m != null) {
            this.module = m;
        }

        this.notes = new HashMap<>();
    }

    public String toString() {
        String ret = "";
        ret += "Ident : " + this.ident + "\n";
        ret += "Coeff : " + this.coeff + "\n";
        ret += "Student marks : \n";
        for(Etudiant etu : notes.keySet()) {
            ret += "   \"" + etu.getNum() + "\" : \"" + notes.get(etu) + "\"\n";
        }
        ret += this.module.toString();
        return ret;
    }

    public void ajouterNote(Etudiant e, double valeur) {
        if (e != null) {
            this.notes.put(e, valeur);
        }
    }

    public Double getNote(Etudiant e) {
        double note = 0.0d;
        if (e != null) {
            note = this.notes.get(e);
        }
        return note;
    }

    public double calculerMoyenne() {
        double mean = 0;
        double sum = 0;
        double count = 0;

        for(Etudiant etu : notes.keySet()) {
            sum += this.notes.get(etu);
            count++;
        }
        mean = sum / count;
        
        return mean;
    }

    public String getIdent() {
        return this.ident;
    }

    public double getCoeff() {
        return this.coeff;
    }

    public HashMap<Etudiant, Double> getNotes() {
        HashMap<Etudiant, Double> ret = new HashMap<>();
        ret.putAll(this.notes);
        return ret;
    }

    public Module getModule() {
        return this.module;
    }

    public void setIdent(String type) {
        if (type != null && !type.equals("")) {
            this.ident = type;
        }
    }

    public void setCoeff(double coeff) {
        if (coeff > 0) {
            this.coeff = coeff;
        }
    }

    public void setNotes(HashMap<Etudiant, Double> notes) {
        if (notes != null) {
            this.notes = notes;
        }
    } 

    public void setModule(Module m) {
        if (m != null) {
            this.module = m;
        }
    }
}
