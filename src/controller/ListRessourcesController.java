package controller;

import controller.EnseignantController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.UtilisateurDAO;
import model.entity.Module;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

/**
 * Controller class for the Teacher's Resource List view ("ListRessources").
 * This class handles the display of various academic resources or modules ({@link Module})
 * assigned to the teacher, organized within a structured JavaFX {@link TableView}.
 * * <p>It extends {@link EnseignantController} to reuse shared sidebar panel navigation 
 * workflows and session validation steps.</p>
 * * @author YourName
 * @version 1.0
 */
public class ListRessourcesController extends EnseignantController {

    // ==========================================
    // FXML Fields - Sidebar Navigation Panel
    // ==========================================

    /** Label displaying the profile identity of the currently logged-in user. */
    @FXML
    private Label utilisateur;

    /** Button routing the user to the grading dashboard workspace. */
    @FXML
    private Button notesEns1;

    /** Button routing the user to the student attendance checking workspace. */
    @FXML
    private Button assiduiteEns1;

    /** Button routing the user to the administrative management screen. */
    @FXML
    private Button gestionEns1;

    /** Button routing the user to personal account settings and parameters. */
    @FXML
    private Button paramEns1;

    /** Button managing secure user session destruction and application logout. */
    @FXML
    private Button deconnexion;

    // ==========================================
    // FXML Fields - Center Content Controls
    // ==========================================

    /** Structural navigation button utilized to step back into the previous workspace screen view. */
    @FXML
    private Button retour;

    /** Informational text title placeholder component located directly above the modules data table grid. */
    @FXML
    private Label titreEval; 

    // ==========================================
    // FXML Fields - Resources Table Layout
    // ==========================================

    /** Main layout data grid table mapped to display instances of academic {@link Module} records. */
    @FXML
    private TableView<Module> tableEval; 

    /** Table column capturing and displaying the administrative reference code string of the module. */
    @FXML
    private TableColumn<Module, String> code; 

    /** Table column rendering the specific academic semester tag string associated with the module context. */
    @FXML
    private TableColumn<Module, String> sem;  

    /** Table column detailing the descriptive title or label text of the active course resource. */
    @FXML
    private TableColumn<Module, String> intitule; 

    /** Table column presenting the name of the teacher responsible for instructing the module. */
    @FXML
    private TableColumn<Module, String> ens; 
    
    /**
     * Initializes the controller environment lifecycle. This setup routine triggers automatically 
     * immediately following the completion of structural FXML layout file parsing operations.
     * * <p>It determines identity profiles via the active user context container, displays the parsed name, 
     * and maps user click interactions to concrete operational callbacks.</p>
     */
    @FXML
    public void initialize() {
        String email = model.MailUser.getEmail();
        UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        
        // Application side-panel action bindings configuration
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());
    }

    /**
     * Re-routes the user screen view focus safely back onto the primary teacher management 
     * workspace control module ("EnseignantGestion").
     * * <p>Logs out comprehensive debug trace logs onto the terminal console output stack if 
     * underlying UI layout updates or view loader changes fail to complete correctly.</p>
     */
    @FXML 
    public void retour() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantGestion");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}