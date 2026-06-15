package model.entity;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Evaluation with an type, a coefficient,
 * a HashMap linking student Objects with marks
 * and a Module
 *
 * @author Team KALLAX
 */
public class Evaluation {

    private String ident;
    private double coeff;
    private HashMap<Etudiant, Double> notes;
    private Module module;

    private static ArrayList<Evaluation> evaluations;

    /**
     * Constructor : Initializes a Evaluation object with its parameters.
     * @param type the Evaluation's type
     * @param coeff the Evaluation's coefficient
     * @param m the Evaluation's module 
     * @throws IllegalArgumentException ff one of the arguments is invalid
     */
    public Evaluation(String type, double coeff, Module m) throws IllegalArgumentException {
        if (type != null && !type.equals("")) {
            this.ident = type;
        } else {
            throw new IllegalArgumentException("Constructeur de Evaluation : type incorrect.");
        }

        if (coeff > 0) {
            this.coeff = coeff;
        } else {
            throw new IllegalArgumentException("Constructeur de Evaluation : coeff incorrect.");
        }

        if (m != null) {
            this.module = m;
        } else {
            throw new IllegalArgumentException("Constructeur de Evaluation : module incorrect.");
        }

        this.notes = new HashMap<>();
    }

    /** 
     * Static getter of Evaluation by their ident
     * @param ident the id to look for
     * @return the evaluation that was found or null
     */
    public static Evaluation getEvaluationByIdent(String ident) {
        if (evaluations == null) {
            evaluations = new ArrayList<>();
        }
        boolean found = false;
        int i = 0;
        Evaluation eval = null;
        while (!found && i < evaluations.size()) {
            eval = evaluations.get(i);

            if (eval.getIdent().equals(ident)) {
                found = true;
            }
            i++;
        }

        if (!found) {
            eval = null;
        }
        return eval;
    }

    /**
     * Public method to add an Evaluation to the static list
     * @param eval the evaluation to add
     */
    public static void addEvaluation(Evaluation eval) {
        if (evaluations.contains(eval)) {
            evaluations.add(eval);
        }
    }

    /**
     * Static getter to get the Evaluation static list
     * @return the ArrayList
     */
    public static ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }

    /**
     * Displays the Evaluation's infos.
     */
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

    /**
     * Adds a new mark for this Evaluation to a student
     * @param e the student to link the mark with
     * @param valeur the score the student got
     */
    public void ajouterNote(Etudiant e, double valeur) {
        if (e != null) {
            this.notes.put(e, valeur);
        }
    }

    /**
     * Gets the mark a specific student got
     * @param e the student
     * @return the score the student got (-1 if he hasn't got one)
     */
    public Double getNote(Etudiant e) {
        double note = -1.0d;
        if (e != null) {
            if (this.notes.get(e) != null) {
                note = this.notes.get(e);
            }
        }
        return note;
    }

    /**
     * Compute the average score of this Evaluation
     * @return the computed average
     */
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

    /**
     * Getter for the ident attribute
     * @return ident attribute
     */
    public String getIdent() {
        return this.ident;
    }

    /**
     * Getter for the coeff attribute
     * @return coeff attribute
     */
    public double getCoeff() {
        return this.coeff;
    }

    /**
     * Getter for the notes attribute
     * @return shallow copy of the notes attribute
     */
    public HashMap<Etudiant, Double> getNotes() {
        HashMap<Etudiant, Double> ret = new HashMap<>();
        ret.putAll(this.notes);
        return ret;
    }

    /**
     * Getter for the module attribute
     * @return module attribute
     */
    public Module getModule() {
        return this.module;
    }

    /**
     * Setter for the ident attribute
     * @param type the type to set
     */
    public void setIdent(String type) {
        if (type != null && !type.equals("")) {
            this.ident = type;
        }
        else{
            System.out.println("Type de l'évaluation incorrect");
        }
    }

    /**
     * Setter for the coeff attribute
     * @param coeff the coeff to set
     */
    public void setCoeff(double coeff) {
        if (coeff > 0) {
            this.coeff = coeff;
        }
        else{
            System.out.println("Coefficient incorrect");
        }
    }

    /**
     * Setter for the notes attribute
     * @param notes the notes hashmap to set
     */
    public void setNotes(HashMap<Etudiant, Double> notes) {
        if (notes != null) {
            this.notes = notes;
        }
        else{
            System.out.println("Liste de couple (étudiant,note) incorrecte");
        }
    }

    /**
     * Setter for the module attribute
     * @param m the module to set
     */
    public void setModule(Module m) {
        if (m != null) {
            this.module = m;
        }
        else{
            System.out.println("Module incorrecte");
        }
    }
}
