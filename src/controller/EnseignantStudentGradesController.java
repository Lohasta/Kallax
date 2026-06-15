package controller;

import controller.EnseignantController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.UtilisateurDAO;
import model.entity.Evaluation;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

/**
 * Controller class for the Teacher's Grades management view ("EnseignantGrades").
 * This class handles the display of assessments (evaluations) in a table view
 * and manages user interactions for adding evaluations, checking attendance, 
 * viewing grades, and adjusting settings.
 * * <p>It extends {@link EnseignantController} to inherit base teacher navigation functionalities.</p>
 * * @author YourName
 * @version 1.1
 */
public class EnseignantStudentGradesController extends EnseignantController {


    /** Label displaying the currently logged-in user's name. */
    @FXML
    private Label utilisateur;

    /** Button to navigate to the grades management view. */
    @FXML
    private Button notesEns1;

    /** Button to navigate to the attendance management view. */
    @FXML
    private Button assiduiteEns1;

    /** Button to navigate to the general management view. */
    @FXML
    private Button gestionEns1;

    /** Button to navigate to the teacher's profile/settings view. */
    @FXML
    private Button paramEns1;

    /** Button to log out of the application. */
    @FXML
    private Button deconnexion;


    /** Button to return to the previous screen. */
    @FXML
    private Button retour;

    /** Button to trigger the addition of a new control/assessment. */
    @FXML
    private Button addCtrl; 


    /** Table view displaying the list of evaluations. */
    @FXML
    private TableView<Evaluation> tableEval; 

    /** Column displaying the name of the evaluation. */
    @FXML
    private TableColumn<Evaluation, String> nomEval;

    /** Column displaying the teacher responsible for the evaluation. */
    @FXML
    private TableColumn<Evaluation, String> ens;

    /** Column displaying the date of the evaluation. */
    @FXML
    private TableColumn<Evaluation, String> dateEval;

    /** Column displaying the average score of the evaluation. */
    @FXML
    private TableColumn<Evaluation, Double> avg;

    /** Column displaying the median score of the evaluation. */
    @FXML
    private TableColumn<Evaluation, Double> mediane; 

    /** Column displaying the weight/coefficient of the evaluation. */
    @FXML
    private TableColumn<Evaluation, Double> coeff; 

    /** Column containing a button to view the overall results of the evaluation. */
    @FXML
    private TableColumn<Evaluation, Button> result; 

    /** Column containing a button to open the grade input form for the evaluation. */
    @FXML
    private TableColumn<Evaluation, Button> remplirNote; 

    /**
     * Initializes the controller class. This method is automatically called 
     * after the FXML file has been loaded.
     * * <p>It fetches the user's name via their email, updates the UI layout, 
     * and binds action handlers to the interactive UI controls.</p>
     */
    @FXML
    public void initialize() {
        String email = model.MailUser.getEmail();
        UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        
        // Event Handlers Binding
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());
        
        // CORRECTION: Merged the duplicate overwritten handler to explicitly point to the target navigation method
        addCtrl.setOnAction((ActionEvent e) -> ajoutCtrl());
    }

    /**
     * Switches the current scene to the evaluation addition view ("EnseignantAddEval").
     * Prints a stack trace to the console if the FXML file fails to load.
     * * @deprecated Kept for backwards compatibility. Use {@link #ajoutCtrl()} instead.
     */
    @Deprecated
    public void ajouterNotes() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantAddEval");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            System.err.println("Error loading EnseignantAddEval scene inside ajouterNotes");
            e.printStackTrace();
        }
    }

    /**
     * Navigates back to the main teacher dashboard ("EnseignantScene").
     * Triggered by the back button interaction.
     */
    @FXML 
    public void retour() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantScene");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Redirects the user to the add evaluation screen workspace.
     */
    @FXML
    public void ajoutCtrl() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantAddEval");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}