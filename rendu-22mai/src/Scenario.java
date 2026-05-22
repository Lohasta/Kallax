import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
        Etudiant eF = null;
        Etudiant eM = null;
        Enseignant u3 = null;
        Enseignant u4 = null;
        DirecteurEtudes u5 = null;
        DirecteurEtudes u6 = null;
        Promotion p1 = null;
        Promotion p2 = null;
        GroupeTD grA = null;
        GroupeTD grB = null;
        GroupeTD grZ = null;
        Module m1 = null;
        Module m2 = null;
        Module m3 = null;
        Module m4 = null;
        Seance s1 = null;
        Seance s2 = null;
        Seance s3 = null;
        Evaluation ev1 = null;
        Evaluation ev2 = null;
        ArrayList<Module> listM = null;
        ArrayList<Module> listMarchePas = null;
        ArrayList<Etudiant> listE = null;
        ArrayList<Etudiant> listEBenNon = null;
        ArrayList<Evaluation> listEvals = null;
        ArrayList<Enseignant> listResp = null;
        ArrayList<Seance> listSeances = null;
        Date d1 = null;
        Date d2 = null;
        LocalTime hD1 = null;
        LocalTime hD2 = null;
        LocalTime hF1 = null;
        LocalTime hF2 = null;


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
            eF = new Etudiant("Fabien", "Madotto", "fm@ubs.fr", "5678mdr", 67);
            eM = new Etudiant("Maël", "Leroux", "ml@ubs.fr", "LeagueOfLegendsForever", 01141010114);
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
            m3 = new Module("R2.02", "IHM", EnumModule.RESSOURCE, p1);
            m4 = new Module("R2.03", "Qualité de développement", EnumModule.RESSOURCE, p2);
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
            ev1 = new Evaluation("QCM 1", 1.0, m1);
            ev2 = new Evaluation(null, 0.0, null);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }




        System.out.println("*** Création et mise en place de variables préparatoires pour la suite du scénario : ***");

        Promotion BUT126 = new Promotion(EnumPromo.BUT1);
        Module R202 = new Module("R2.02", "IHM", EnumModule.RESSOURCE, BUT126);
        R202.ajouterResponsable(u3);
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
        listM = new ArrayList<>();
        listM.add(m1);
        listM.add(m3);
        listM.add(m4);
        listE = new ArrayList<>();
        listE.add(u1);
        listE.add(eF);
        listE.add(eM);

        Promotion BUT226 = new Promotion(EnumPromo.BUT2);
        Module R301 = new Module("JSP", "TEST", EnumModule.RESSOURCE, BUT226);

        Seance s7 = new Seance(new Date(), LocalTime.now(), LocalTime.now(), R202, grc);
        s7.ajouterPresence(u1, EnumPresence.PRESENT);
        ArrayList<Seance> seances = new ArrayList<>();
        seances.add(s3);
        R202.setSeance(seances);
        BUT126.ajouterGroupe(grc);
        d1 = new Date();
        hD1 = LocalTime.of(15, 00);
        hF1 = LocalTime.of(16, 30);




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
                System.out.println(u1.calculerMoyenne(R202)); // Should return 14.625
                System.out.println(u1.calculerMoyenne(null)); // Should return -1

            System.out.println("\n- Méthode calculerMoyenneGenerale() :");
                System.out.println(u1.calculerMoyenneGenerale()); // Should return 13.8125

            System.out.println("\n- Méthode moduleValide() :");
                System.out.println(u1.moduleValide(R202)); // Should return true
                System.out.println(u1.moduleValide(R301)); // Should return false
                System.out.println(u1.moduleValide(null)); // Should say that the parameter is null

            System.out.println("\n- Méthode recupererPresence() :");
                System.out.println(u1.recupererPresence(R202));
                System.out.println(u1.recupererPresence(R301)); // Should say that R301 isn't a valid Module
                System.out.println(u1.recupererPresence(null)); // Should say that the parameter is null

            System.out.println("\n- Méthode seancesSuivies()");
                System.out.println(u1.seancesSuivies());


        System.out.println("\n----- Enseignant : -----");

            System.out.println("Méthode toString()");
                System.out.println(u3);
                System.out.println(u4);

            System.out.println("\n- Méthode ajouterModule() :");
                u3.ajouterModule(S201);
                u3.ajouterModule(R202);
                System.out.println(u3); // Should have two extra module (S201, R202)

            System.out.println("\n- Méthode retirerModule() :");
                u3.retirerModule(S201);
                System.out.println(u3); // Should have one fewer module (S201)

            System.out.println("\n- Méthode creerEval() :");
                System.out.println(u3.creerEval(R202.getCode(), "QCM 3", 1)); // Should return an Evaluation (if null -> bad)
                System.out.println(u3.creerEval(R202.getCode(), "QCM 4", 1)); // Should return an Evaluation (if null -> bad)
                System.out.println(u3.creerEval(S201.getCode(), "QCM 13", 2)); // Should return null since u3 does not have S201
                System.out.println(u3.creerEval(R202.getCode(), "", 4)); // Should return null since typeEval is invalid
                System.out.println(u3.creerEval(R202.getCode(), "QCM 99", 0)); // Should return null since coeffEval is invalid

            System.out.println("\n- Méthode chercherEval() :");
                System.out.println(u3.chercherEval(R202.getCode(), "QCM 3")); // Should return the correct eval that we created previously
                System.out.println(u3.chercherEval(S201.getCode(), "QCM 3")); // Should return null

            System.out.println("\n- Méthode supprimerEval() :");
                u3.supprimerEval(u3.chercherEval(R202.getCode(), "QCM 4")); // Should remove the QCM from the list of Evaluations for R202
                u3.supprimerEval(u3.chercherEval(R202.getCode(), "QCM 4")); // Should do nothing since it has already been removed

            System.out.println("\n- Méthode modifierTypeEval() :");
                Evaluation eval = u3.chercherEval(R202.getCode(), "QCM 3");
                u3.modifierTypeEval(eval, "TP 3"); // Should change the type (since u3 is a "responsable" of R202)
                u3.modifierTypeEval(u3.chercherEval(R202.getCode(), "QCM 3"), "TP 4"); // Should not change the type since it doesn't exist anymore with this type
                System.out.println(eval); // Should have a type of "TP 3" and not "QCM 3"

            System.out.println("\n- Méthode modifierCoeffEval() :");
                u3.modifierCoeffEval(eval, 10); // Should change the coefficient from 1 to 10
                System.out.println(eval); // Should have the changed coefficient (10 not 1)

            System.out.println("\n- Méthode saisirNote() :");
                u3.saisirNote(eval, u1, 14); // Should add an entry to the notes HashMap attribute of the Evaluation for the student with the specified note


        System.out.println("\n----- Evaluation : -----");

            System.out.println("\n- Méthode toString() :");
                System.out.println(ev1);
                System.out.println(ev2);

            System.out.println("\n- Méthode ajouterNote() :");
                ev1.ajouterNote(u1, 19.0);
                ev1.ajouterNote(new Etudiant("Temp", "Temp", "bonsoir", "salut", 420), 15.0);

            System.out.println("\n- Méthode getNote() :");
                System.out.println(ev1.getNote(u1)); // Should return 19

            System.out.println("\n- Méthode calculerMoyenne() :");
                System.out.println(ev1.calculerMoyenne()); // Should return 17

            System.out.println("\n- Méthode setIdent() :");
                ev1.setIdent("QCM 2"); // Should set ev1's ident attribute to "QCM 2"
                ev1.setIdent(""); // Should not set ev1's ident attribute since it is not valid

            System.out.println("\n- Méthode getIdent() :");
                System.out.println(ev1.getIdent()); // Should return "QCM 2"

            System.out.println("\n- Méthode setCoeff() :");
                ev1.setCoeff(2); // Should set ev1's coeff attribute to 2
                ev1.setCoeff(-2); // Should not set ev1's coeff attribute since it is not valid

            System.out.println("\n- Méthode getCoeff() :");
                System.out.println(ev1.getCoeff()); // Should return 2

            System.out.println("\n- Méthode setNotes() :");
                HashMap<Etudiant, Double> testNotes = new HashMap<>();
                testNotes.put(u1, ev1.getNote(u1));
                ev1.setNotes(testNotes); // Should set ev1's notes attribute to a HashMap with a single mark (only the first one from the old HashMap)
                ev1.setNotes(null); // Should not set ev1's notes attribute since it is not valid

            System.out.println("\n- Méthode getNotes() :");
                System.out.println(ev1.getNotes()); // Should return a HashMap with one entry : {u1, 19.0}

            System.out.println("\n- Méthode setModule() :");
                ev1.setModule(R202); // Should set ev1's module attribute to the R202 module
                ev1.setModule(null); // Should not set ev1's module attribute since it is note valid

            System.out.println("\n- Méthode getModule() :");
                System.out.println(ev1.getModule()); // Should return the R202 module


        System.out.println("\n----- Promotion : -----");

            System.out.println("\n- Méthode toString() :");
                System.out.println(p1);
                System.out.println(p2);

            System.out.println("\n- Getters :");
                System.out.println(p1.getNomPromo());
                System.out.println(p1.getModules());
                System.out.println(p1.getGroupes());

            System.out.println("\n- Setters :");
                p1.setNomPromo(EnumPromo.BUT2);
                System.out.println(p1.getNomPromo()); // Should print BUT2
                p1.setNomPromo(EnumPromo.BUT1); // Putting it back

            System.out.println("\n- Méthode ajouterGroupe() :");
                p1.ajouterGroupe(grc);
                System.out.println(p1.getGroupes()); // Should print grc's infos.
                p1.ajouterGroupe(null); // Prints an error message

            System.out.println("\n- Méthode ajouterModule() :");
                p1.ajouterModule(R202);
                System.out.println(p1.getModules());
                p1.ajouterModule(null); // Prints an error message


        System.out.println("\n----- DirecteurEtudes : -----");

            System.out.println("\n- Méthode toString() :");
                System.out.println(u5);
                System.out.println(u6);

            System.out.println("\n- Méthode creerEnseignant() :");
                Enseignant ln = u5.creerEnseignant("Naert", "Lucie", "lnaert@ubs.fr", "treaN", "LN");
                System.out.println(ln);

            System.out.println("\n- Méthode creerEtudiant() :");
                Etudiant ih = u5.creerEtudiant("Hadjar", "Isack", "ihadjar@rbracing.com", "nooIDestroyedTheCar", "6");
                System.out.println(ih);

            System.out.println("\n- Méthode creerPromotion() :");
                Promotion BUT326 = u5.creerPromotion(EnumPromo.BUT3);
                System.out.println(BUT326);

            System.out.println("\n- Méthode creerModule() :");
                Module S205 = u5.creerModule("S2.05", "Projet Citoyen", EnumModule.SAE, BUT126);
                System.out.println(S205);

            System.out.println("\n- Méthode creerGroupeTD() :");
                GroupeTD grD = u5.creerGroupeTD("Groupe D", BUT126);
                System.out.println(grD);

            System.out.println("\n- Méthode definirResponsable() :");
                u5.definirResponsable(S205, ln);
                System.out.println(S205.getResponsables());  // Should show Lucie Naert as responsible.

            System.out.println("\n- Méthode ajouteEtudiantGroupe() :");
                u5.ajouteEtudiantGroupe(grD, ih);
                System.out.println(ih.getGroupe());

            System.out.println("\n- Méthode calculerMoyenneEtudiant()");
                System.out.println(u5.calculerMoyenneEtudiant(u1)); // Should be the same result Etudiant.calculerMoyenneGenerale, so 13.8125.

            System.out.println("\n- Méthode deliberer()");
                System.out.println(u5.deliberer(u1)); // Should return true, as his average >= 10.
                ih.setGroupe(grD);
                TPnote.ajouterNote(ih, 3);
                CT.ajouterNote(ih, 7);
                Diag.ajouterNote(ih, 6);
                System.out.println(ih.calculerMoyenneGenerale()); // Should be equal to 5.5
                System.out.println(u5.deliberer(ih)); // Should return false, as his average <= 10.


        System.out.println("\n----- GroupeTD : -----");

            System.out.println("\n- Méthode toString() :");
                System.out.println(grA);
                System.out.println(grB);
                System.out.println(grc);

            System.out.println("\n- Méthode ajouterEtudiant() :");
                grA.ajouterEtudiant(u1);//Should add a student
                grc.ajouterEtudiant(u2);//Should not add a student
                System.out.println(grA.getEtudiants());
                System.out.println(grc.getEtudiants());

            System.out.println("\n- Méthode ajouterModule() :");
                grA.ajouterModule(m1);//Should add a module
                grc.ajouterModule(m2);//Should not add a module
                System.out.println(grA.getModulesSuivis());
                System.out.println(grc.getModulesSuivis());

            System.out.println("\n- Méthode equals() :");
                System.out.println(grA.equals(grc));// Should return false
                System.out.println(grc.equals(grc));// Should return true
                System.out.println(grc.equals(grB));// Should return false

            System.out.println("\n- Méthode setNom() :");
                grA.setNom("");//Should not change the name
                grA.setNom("G");//Should change the name to G
                grc.setNom(null);//Should not change the name
                System.out.println(grA.getNom());
                System.out.println(grc.getNom());
                grA.setNom("A");

            System.out.println("\n- Méthode setPromotion() :");
                grA.setPromotion(p2);//Should not change the promotion
                grc.setPromotion(p1);//Should change the promotion to BUT1
                System.out.println(grA.getPromotion());
                System.out.println(grc.getPromotion());
                grc.setPromotion(BUT126);

            System.out.println("\n- Méthode setModulesSuivis() :");
                grA.setModulesSuivis(listM);//Should change list of module
                grc.setModulesSuivis(listMarchePas);//Should not change list of module
                System.out.println(grA.getModulesSuivis());
                System.out.println(grc.getModulesSuivis());


            System.out.println("\n- Méthode setEtudiants() :");
                grA.setEtudiants(listE);//Should change list of student
                grc.setEtudiants(listEBenNon);//Should not change list of student
                System.out.println(grA.getEtudiants());
                System.out.println(grc.getEtudiants());




        System.out.println("\n----- Module : -----");

            System.out.println("\n- Méthode toString() :");
                System.out.println(m1);
                System.out.println(m2);

            System.out.println("\n- Getters :");
                System.out.println(m1.getCode()); // Should return R2.01
                System.out.println(m1.getIntitule()); // Should return Développement Orienté Objets
                System.out.println(m1.getType()); // Should return RESSOURCE
                System.out.println(m1.getListeEval()); // Should return []
                System.out.println(m1.getResponsables()); // Should return []
                System.out.println(m1.getPromo()); // Should print the Promotion's toString()
                System.out.println(m1.getSeance()); // Should print []

            System.out.println("\n- Setters :");
                Seance s11 = new Seance(new Date(), LocalTime.now(), LocalTime.now(), m1, grc);
                listEvals = new ArrayList<>();
                listEvals.add(TPnote);
                listResp = new ArrayList<>();
                listResp.add(u3);
                listSeances = new ArrayList<>();
                listSeances.add(s1);

                m1.setCode("S2.02");
                m1.setCode(null); // Prints an error message
                m1.setIntitule("PRNG");
                m1.setIntitule(null); // Prints an error message
                m1.setType(EnumModule.SAE);
                m1.setType(null); // Prints an error message
                m1.setListeEval(listEvals);
                m1.setListeEval(null); // Prints an error message
                m1.setResponsables(listResp);
                m1.setResponsables(null); // Prints an error message
                m1.setPromotion(BUT126);
                m1.setPromotion(null); // Prints an error message
                m1.setSeance(listSeances);
                m1.setSeance(null); // Prints an error message
                System.out.println(m1); // Checking changes

            System.out.println("\n- Méthode ajouterSeance() :");
                grc.ajouterModule(m1);
                grZ = new GroupeTD("Groupe Z", BUT326);
                m1.ajouterSeance(new Date(), LocalTime.now(), LocalTime.now(), grc); // Should work fine
                m1.ajouterSeance(new Date(), LocalTime.now(), LocalTime.now(), grZ); // Prints an error message
                System.out.println(m1.getSeance()); // Should print s4

            System.out.println("\n- Méthode ajouterResponsable() :");
                m1.ajouterResponsable(ln);
                m1.ajouterResponsable(ln); // Doing it twice won't work
                m1.ajouterResponsable(null); // Prints an error message
                System.out.println(m1.getResponsables());

            System.out.println("\n- Méthode retirerResponsable() :");
                m1.retirerResponsable(ln);
                m1.retirerResponsable(ln); // Won't work, as this teacher isn't in the list anymore
                m1.retirerResponsable(null); // Prints an error message
                System.out.println(m1.getResponsables());

            System.out.println("\n- Méthode ajouterEvaluation() :");
                m1.ajouterEvaluation(CT);
                m1.ajouterEvaluation(CT); // Doing it twice won't work
                m1.ajouterEvaluation(null); // Prints an error message
                System.out.println(m1.getListeEval());

            System.out.println("\n- Méthode retirerEvaluation() :");
                m1.retirerEvaluation(CT);
                m1.retirerEvaluation(CT); // Won't work, as this evaluation isn't in the list anymore
                m1.retirerEvaluation(null); // Prints an error message
                System.out.println(m1.getListeEval());

            System.out.println("\n- Méthode calculerMoyenne() :");
                System.out.println(m1.calculerMoyenne()); // Should print 8.5 (m1's Evaluation(TPnote) has two grades : 3 and 14.)

            System.out.println("\n- Méthode chercherModuleParCode()");
                Module rech1 = m1.chercherModuleParCode("R2.03", listM);
                System.out.println(rech1);
                Module rech2 = m1.chercherModuleParCode("Truc", listM); // Prints an error message
                m1.chercherModuleParCode(null, listM); // Prints an error message
                m1.chercherModuleParCode("S2.02", null); // Prints an error message


        System.out.println("\n----- Seance : -----");

            System.out.println("\n- Méthode toString() :");
                System.out.println(s1);
                System.out.println(s2);

            System.out.println("\n- Méthode ajouterPresence() :");
                s1.ajouterPresence(eM, null);//Should not change presence status for eM
                s3.ajouterPresence(u1, EnumPresence.ABSENT);//Should change presence status for u1
                s1.ajouterPresence(eF, EnumPresence.PRESENT);//Should change presence status for eF
                System.out.println(eM);
                System.out.println(u1);
                System.out.println(eF);

            System.out.println("\n- Méthode setDate() :");
                s3.setDate(d2);//Should not change the date
                System.out.println(s3.getDate());
                s1.setDate(d1);//Should change the date
                System.out.println(s1.getDate());

            System.out.println("\n- Méthode setHeureDeb() :");
                s1.setHeureDeb(hD2);//Should not change the start time of the session
                System.out.println(s1.getHeureDeb());
                s3.setHeureDeb(hD1);//Should change the start time of the session
                System.out.println(s3.getHeureDeb());

            System.out.println("\n- Méthode setHeureFin() :");
                s3.setHeureFin(hF2);//Should not change the end time of the session
                System.out.println(s3.getHeureFin());
                s1.setHeureFin(hF1);//Should change the start time of the session
                System.out.println(s1.getHeureFin());

            System.out.println("\n- Méthode setModule() :");
                s1.setModule(m2);//Should not change the module
                System.out.println(s1.getModule());
                s3.setModule(m4);//Should change the module
                System.out.println(s3.getModule());

            System.out.println("\n- Méthode setGroupe() :");
                s3.setGroupe(grB);//Should not change the group
                System.out.println(s3.getGroupe());
                s1.setGroupe(grc);//Should change the group
                System.out.println(s1.getGroupe());

    }

}
