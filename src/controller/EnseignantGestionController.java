package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.dao.UtilisateurDAO;

/**
 * Controller class for the standard instructor management dashboard view ("EnseignantGestion").
 * It manages the core operational control panel options available to teachers, 
 * including navigation links to review general student rosters and assigned academic resource lists.
 * This class inherits sidebar menu structures and behaviors from {@link EnseignantController}.
 */
public class EnseignantGestionController extends EnseignantController {

    /** Label displaying the name of the currently logged-in teacher in the sidebar. */
    @FXML
    private Label utilisateur;

    /** Sidebar button to navigate to the evaluation and grading module. */
    @FXML
    private Button notesEns1;

    /** Sidebar button to navigate to the student attendance and absence records. */
    @FXML
    private Button assiduiteEns1;

    /** Sidebar button to refresh or navigate back to this management dashboard. */
    @FXML
    private Button gestionEns1;

    /** Sidebar button to open the account profile settings interface. */
    @FXML
    private Button param; 

    /** Sidebar button to safely terminate the current session and log out. */
    @FXML
    private Button deconnexion;


    // --- Central Area ---
    
    /** Navigation button to return to the root teacher home scene. */
    @FXML
    private Button retour;

    /** Dashboard action button to access the standard student directory list. */
    @FXML
    private Button listEtud; 

    /** Dashboard action button to view the list of pedagogical resources or modules. */
    @FXML
    private Button listRess; 

    /**
     * Initializes the controller class. This method is automatically called 
     * once the associated FXML layout markup has been fully parsed.
     * It loads user session credentials, resolves the instructor's display name via {@link UtilisateurDAO}, 
     * and binds click events on menu controls to their respective navigation sequences.
     */
    public void initialize() {
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        param.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());
        listEtud.setOnAction((ActionEvent e) -> etud());
        listRess.setOnAction((ActionEvent e) -> ress());
    }

    /**
     * Handles navigation when the back button is clicked.
     * Smoothly redirects the user back to the primary teacher interface home screen ("EnseignantScene").
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
     * Handles navigation when the student list button is triggered.
     * Displays the standard student directory overview interface ("ListStudent").
     */
    @FXML 
    public void etud() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("ListStudent");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles navigation when the resource list button is triggered.
     * Displays the general academic courses and resource catalogs view ("ListRessources").
     */
    @FXML 
    public void ress() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("ListRessources");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}