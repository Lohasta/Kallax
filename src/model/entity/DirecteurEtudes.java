package model.entity;

/**
 * DirecteurEtudes who can create teachers, students, promotions, modules and groups,
 * assign teachers to modules and students to groups,
 * calculate the average grade of a student and
 * decide if the student has passed or not.
 *
 * @author TEAM KALLAX
 * @version 1.0
 */
public class DirecteurEtudes extends Enseignant{
    public static void main(String[] args){
        DirecteurEtudes test = new DirecteurEtudes("Taylor", "Corey", "sleepnot@gmail.com", "motdepasse", "CT");
        System.out.println(test);
    }
    /**
     * Constructor of DirecteurEtudes
     * @param nom name
     * @param prenom first name
     * @param email email address
     * @param mdp password
     * @param ident identifiant
     */
    public DirecteurEtudes(String nom, String prenom, String email, String mdp, String ident){
        super(nom, prenom, email, mdp, ident);
    }

    /**
     * Method to create a teacher
     * @param nom name of the teacher
     * @param prenom first name of the teacher
     * @param email email address of the teacher
     * @param mdp password of the teacher
     * @param ident identifiant of the teacher
     * @return the created teacher
     */
    public Enseignant creerEnseignant(String nom, String prenom, String email, String mdp, String ident){
        return new Enseignant(nom, prenom, email, mdp, ident);
    }

    /**
     * Method to create a student
     * @param nom name of the student
     * @param prenom first name of the student
     * @param email email address of the student
     * @param mdp password of the student
     * @param ident identifiant of the student
     * @return the created student
     */
    public Etudiant creerEtudiant(String nom, String prenom, String email, String mdp, String ident){
        return new Etudiant(nom, prenom, email, mdp, Integer.parseInt(ident));
    }


    /**
     * Method to create a promotion
     * @param promotion the type of promotion
     * @return the created promotion
     */
    public Promotion creerPromotion(EnumPromo promotion){
        return new Promotion(promotion);
    }

    /**
     * Method to create a module
     * @param code the code of the module
     * @param intitule the title of the module
     * @param type the type of the module
     * @param promo the promotion to which the module belongs
     * @return the created module
     */
    public Module creerModule(String code, String intitule, EnumModule type, Promotion promo ){
        return new Module(code, intitule, type, promo);
    }

    /**
     * Method to create a group
     * @param nom the name of the group
     * @param promo the promotion to which the group belongs
     * @return the created group
     */
    public GroupeTD creerGroupeTD(String nom, Promotion promo){
        return new GroupeTD(nom, promo);
    }

    /**
     * Method to assign a teacher responsible to a module
     * @param m the module
     * @param e the teacher
     */
    public void definirResponsable(Module m, Enseignant e){
        m.ajouterResponsable(e);
    }

    /**
     * Method to assign a student to a group
     * @param gr the group
     * @param e the student
     */
    public void ajouteEtudiantGroupe(GroupeTD gr, Etudiant e){
        gr.ajouterEtudiant(e);
    }

    /**
     * Method to calculate the general average of a student
     * @param e the student
     * @return the general average of the student
     */
    public double calculerMoyenneEtudiant(Etudiant e){
        return e.calculerMoyenneGenerale();
    }

    /**
     * Method to decide if a student pass or not
     * @param e the student
     * @return true if the student pass, false if not
     */
    public boolean deliberer(Etudiant e){
        boolean rep = false;

        if (calculerMoyenneEtudiant(e) >= 10){
            rep = true;
        }

        return rep;
    }

}
