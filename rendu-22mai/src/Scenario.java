import java.lang.Exception;
import java.util.Date;
import java.time.LocalTime;

/**
 * Testing class for each class of the app.
 * Tests Constructors, toString(), getters, setters and
 * any other method.
 *
 * @author Team KALLAX
 *
 * @version 1.1 (NOT FINISHED YET !!)
 */
public class Scenario{
    public static void main(String[] args){
        System.out.println("*** Scénario de tests de l'application Scodoc : ***");

        /*
         * Testing two cases for each class :
         * A first, valid one with every parameter valid
         * A second, faulty one with every parameter set to null
         * The second case causes to throw an Exception, which is caught after.
        */
        System.out.println("\n*** Constructeurs : ***");

        System.out.println("\nEtudiant : ");
        Etudiant u1 = null;
        Etudiant u2 = null;
        try{
            u1 = new Etudiant("Lohan", "Kerrault", "lk@ubs.fr", "1234lol", 1);
            u2 = new Etudiant(null, null, null, null, 0);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nEnseignant : ");
        Enseignant u3, u4;
        try{
            u3 = new Enseignant("Sahur", "Tung", "tsahur@ubs.fr", "5678mdr", "TS");
            u4 = new Enseignant(null, null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nDirecteurEtudes : ");
        DirecteurEtudes u5, u6;
        try{
            u5 = new DirecteurEtudes("Crocodillo", "Bombardillo", "bcrocs@ubs.fr", "9009jsp", "BC");
            u6 = new DirecteurEtudes(null, null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nPromotion : ");
        Promotion p1 = null;
        Promotion p2 = null;
        try{
            p1 = new Promotion(EnumPromo.BUT1);
            p2 = new Promotion(null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nGroupeTD :");
        GroupeTD grA = null;
        GroupeTD grB = null;
        try{
            grA = new GroupeTD("A", p1);
            grB = new GroupeTD(null, p2);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nModule :");
        Module m1 = null;
        Module m2 = null;
        try{
            m1 = new Module("R2.01", "Développement Orienté Objets", EnumModule.RESSOURCE, p1);
            m2 = new Module(null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nSeance :");
        Seance s1 = null;
        Seance s2 = null;
        try{
            s1 = new Seance(new Date(), LocalTime.now(), LocalTime.now(), m1, grA);
            s2 = new Seance(null, null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nEvaluation :");
        Evaluation ev1 = null;
        Evaluation ev2 = null;
        try{
            ev1 = new Evaluation("Test", 1.0, m1);
            ev2 = new Evaluation(null, 0.0, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }



        /*
         * Tests if the toString() prints the attributes normally, if
         * the getters and setters work fine, along with the other
         * methods.
        */
        System.out.println("\n*** Méthodes : ***");

        System.out.println("\nEtudiant : ");

        System.out.println("Méthode toString() :");
        System.out.println(u1);
        System.out.println(u2);

        System.out.println("Getters :");
        System.out.println(u1.getNom());
        System.out.println(u1.getPrenom());
        System.out.println(u1.getEmail());
        System.out.println(u1.getMdp());
        System.out.println(u1.getNum());

        System.out.println("Setters :");
        u1.setNom("Rebêche");
        u1.setPrenom("Gatien");
        u1.setEmail("gr@ubs.fr");
        u1.setMdp("test34");
        u1.setNum(2);
        System.out.println(u1);
    }
}
