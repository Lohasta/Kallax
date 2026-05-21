import java.lang.Exception;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

/**
 * Testing class for each class of the app.
 * Tests Constructors, toString(), getters, setters and
 * any other method.
 *
 * @author Team KALLAX
 *
 * @version 1.2 (NOT FINISHED YET !!)
 */
public class Scenario{
    public static void main(String[] args){
        // Prerequisites for the tests :
        Etudiant u1 = null;
        Etudiant u2 = null;
        Enseignant u3 = null;
        Enseignant u4 = null;
        DirecteurEtudes u5 = null;
        DirecteurEtudes u6 = null;
        Promotion p1 = null;
        Promotion p2 = null;
        GroupeTD grA = null;
        GroupeTD grB = null;
        Module m1 = null;
        Module m2 = null;
        Seance s1 = null;
        Seance s2 = null;
        Evaluation ev1 = null;
        Evaluation ev2 = null;

        Promotion BUT126 = new Promotion(EnumPromo.BUT1);
        Module R202 = new Module("R2.02", "IHM", EnumModule.RESSOURCE, BUT126);
        Evaluation TPnote = new Evaluation("TP Noté", 0.75, R202);
        Evaluation CT = new Evaluation("Controle terminal", 2.0, R202);
        TPnote.ajouterNote(u1, 14.0);
        CT.ajouterNote(u1, 15.25);
        R202.ajouterEvaluation(TPnote);
        R202.ajouterEvaluation(CT);

        GroupeTD grc = new GroupeTD("Groupe C", BUT126);
        u1.setGroupe(grc);
        Module S201 = new Module("S2.01", "Scodoc", EnumModule.SAE, BUT126);
        BUT126.ajouterModule(R202);
        BUT126.ajouterModule(S201);
        Evaluation Diag = new Evaluation("Diagramme de classes", 1.0, S201);
        Diag.ajouterNote(u1, 13.0);
        S201.ajouterEvaluation(Diag);

        Promotion BUT226 = new Promotion(EnumPromo.BUT2);
        Module R301 = new Module("JSP", "TEST", EnumModule.RESSOURCE, BUT226);

        Seance s3 = new Seance(new Date(), LocalTime.now(), LocalTime.now(), R202, grc);
        s3.ajouterPresence(u1, EnumPresence.PRESENT);
        ArrayList<Seance> seances = new ArrayList<>();
        seances.add(s3);
        R202.setSeance(seances);
        BUT126.ajouterGroupe(grc);
        System.out.println(u1.recupererPresence(R202));


        System.out.println("*** Scénario de tests de l'application Scodoc : ***");

        /*
         * Testing two cases for each class :
         * A first, valid one with every parameter valid
         * A second, faulty one with every parameter set to null
         * The second case causes to throw an Exception, which is caught after.
        */
        System.out.println("\n*** Constructeurs : ***");

        System.out.println("\nEtudiant : ");
        try{
            u1 = new Etudiant("Lohan", "Kerrault", "lk@ubs.fr", "1234lol", 1);
            u2 = new Etudiant(null, null, null, null, 0);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nEnseignant : ");
        try{
            u3 = new Enseignant("Sahur", "Tung", "tsahur@ubs.fr", "5678mdr", "TS");
            u4 = new Enseignant(null, null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nDirecteurEtudes : ");
        try{
            u5 = new DirecteurEtudes("Crocodillo", "Bombardillo", "bcrocs@ubs.fr", "9009jsp", "BC");
            u6 = new DirecteurEtudes(null, null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nPromotion : ");
        try{
            p1 = new Promotion(EnumPromo.BUT1);
            p2 = new Promotion(null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nGroupeTD :");
        try{
            grA = new GroupeTD("A", p1);
            grB = new GroupeTD(null, p2);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nModule :");
        try{
            m1 = new Module("R2.01", "Développement Orienté Objets", EnumModule.RESSOURCE, p1);
            m2 = new Module(null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nSeance :");
        try{
            s1 = new Seance(new Date(), LocalTime.now(), LocalTime.now(), m1, grA);
            s2 = new Seance(null, null, null, null, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nEvaluation :");
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


        System.out.println("\n----- Etudiant : -----");

        System.out.println("\n- Méthode toString() :");
        System.out.println(u1);
        System.out.println(u2);

        System.out.println("\n- Getters :");
        System.out.println(u1.getNom());
        System.out.println(u1.getPrenom());
        System.out.println(u1.getEmail());
        System.out.println(u1.getMdp());
        System.out.println(u1.getNum());

        System.out.println("\n- Setters :");
        u1.setNom("Rebêche");
        u1.setPrenom("Gatien");
        u1.setEmail("gr@ubs.fr");
        u1.setMdp("test34");
        u1.setNum(2);
        System.out.println(u1);

        System.out.println("\n- Méthode calculerMoyenne() :");
        System.out.println(u1.calculerMoyenne(R202)); //Should return 14.625

        System.out.println("\n- Méthode calculerMoyenneGenerale() :");
        System.out.println(u1.calculerMoyenneGenerale()); //Should return 13.8125

        System.out.println("\n- Méthode moduleValide() :");
        System.out.println(u1.moduleValide(R202)); //Should return true
        System.out.println(u1.moduleValide(R301)); //Should return false

        System.out.println("\n- Méthode recupererPresence() :");
        // Prerequisites :

        System.out.println("\n- Méthode seancesSuivies()");
        System.out.println(u1.seancesSuivies());


        System.out.println("\n----- Enseignant : -----");

        System.out.println("Méthode toString()");
        System.out.println(u3);
        System.out.println(u4);
    }

}
