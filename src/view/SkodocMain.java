package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.dao.EnseignantDAO;
import model.dao.EtudiantDAO;
import model.dao.PromotionDAO;
import model.dao.UtilisateurDAO;
import model.dao.EvaluationDAO;
import model.dao.ModuleDAO;
import model.dao.SeanceDAO;
import model.dao.GroupeTDDAO;
import model.entity.Module;
import model.entity.Enseignant;
import model.entity.EnumPromo;
import model.entity.Etudiant;
import model.entity.Promotion;
import model.entity.Utilisateur;

public class SkodocMain extends Application{
    /**The Scene used to display the App */
    private static Scene scene;

    private Utilisateur user;

    @Override
    public void start(Stage stage) throws Exception {

        PromotionDAO pDao = new PromotionDAO();
        pDao.findAll();
        ModuleDAO mDao = new ModuleDAO();
        mDao.findAll();
        EtudiantDAO etDao = new EtudiantDAO();
        etDao.findAll();
        GroupeTDDAO gtDao = new GroupeTDDAO();
        gtDao.findAll();
        EnseignantDAO ensDao = new EnseignantDAO();
        ensDao.findAll();
        SeanceDAO seaDao = new SeanceDAO();
        seaDao.findAll();
        EvaluationDAO evalDao = new EvaluationDAO();
        evalDao.findAll();

        //UtilisateurDAO testDao = new UtilisateurDAO();
        user = new Utilisateur();
        /*Utilisateur test = new Utilisateur("Avril", "Pierre", "pierre@moi.com", "test");
        testDao.delete(test);
        System.out.println(testDao.findByID("pierre@moi.com"));
        testDao.create(test);
        System.out.println(testDao.findByID("pierre@moi.com"));*/
        //EnseignantDAO testDao = new EnseignantDAO();
        //Enseignant testEns = new Enseignant("Avril", "Pierre", "pierre@moi.com", "test", "test");
        //testDao.create(testEns);
        //testDao.delete(testEns);
        //testEns.setEmail("alice@example.com");
        //testDao.update(testEns);
        //System.out.println(testDao.findByID("test"));
        /*PromotionDAO testDao = new PromotionDAO();
        Promotion testPromo = new Promotion(EnumPromo.BUT1);
        System.out.println(testDao.findAll());
        System.out.println(testDao.findByID(EnumPromo.BUT1.toString()));
        testDao.delete(testPromo);
        System.out.println(testDao.findAll());
        System.out.println(testDao.findByID(EnumPromo.BUT1.toString()));
        testDao.create(testPromo);
        System.out.println(testDao.findAll());
        System.out.println(testDao.findByID(EnumPromo.BUT1.toString()));*/

        /*UtilisateurDAO testDao = new UtilisateurDAO();
        EtudiantDAO testDao2 = new EtudiantDAO();
        Etudiant testEtudiant = new Etudiant("Pape", "Bernard", "billy@monmail.com", "salut", 897);
        Promotion testPromo = new Promotion(EnumPromo.BUT1);
        testPromo.getGroupeByName("A").ajouterEtudiant(testEtudiant);
        testDao.create(testEtudiant);
        testDao2.create(testEtudiant);
        System.out.println(testDao2.findAll());
        System.out.println(testDao2.findByID(testEtudiant.getNum()));
        System.out.println(testDao2.findByID(testEtudiant.getNum()).getGroupe());
        testEtudiant.setGroupe(testPromo.getGroupeByName("B"));
        testDao2.update(testEtudiant);
        System.out.println(testDao2.findByID(testEtudiant.getNum()).getGroupe());
        testDao2.delete(testEtudiant);
        testDao.delete(testEtudiant);
        System.out.println(testDao2.findByID(testEtudiant.getNum()));*/



        // Initiate and add the application icon
        //Image icon = new Image(getClass().getResourceAsStream("assets/logoKallax.png"));
        //stage.getIcons().add(icon);

        // Load the FXML file and create the scene
        FXMLLoader fxmlLoader;
        if (user.getEstConnecte()) {
            fxmlLoader = getFXMLLoader("StudentScene");
        } else {
            fxmlLoader = getFXMLLoader("LoginScene");
            if (fxmlLoader == null) {
                return;
            }
        }
        scene = new Scene(fxmlLoader.load(), 1200, 600);

        // Add CSS
        //scene.getStylesheets().add(getClass().getResource("/assets/style/style.css").toExternalForm());

        stage.setTitle("SKoDoc");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Load the root parent and update the view
     * Inject studentList if needed
     * 
     * @param fxmlLoader a FXMLLoader object
     * @throws IOException
     */
    public static void loadFXML(FXMLLoader fxmlLoader) throws IOException {
        // load the root parent
        Parent root = fxmlLoader.load();
        // retrieve the controller
        Object controller = fxmlLoader.getController();
        setRoot(root);
    }

    /**
     * Update the root parent of the main scene
     * 
     * @param root
     */
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    /**
     * Create a new FXMLLoader object
     * 
     * @param fxml the name of the fxml file (represent the view)
     * @return FXMLLoader object
     */
    public static FXMLLoader getFXMLLoader(String fxml) {
        java.net.URL fxmlLocation = SkodocMain.class.getResource("/view/" + fxml + ".fxml");
    
        return new FXMLLoader(fxmlLocation);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
