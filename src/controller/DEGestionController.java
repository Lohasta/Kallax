package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import model.dao.UtilisateurDAO;

/**
 * Controller class for the Director of Studies (DE) management dashboard view ("DEGestion").
 * It manages the principal control panel options available to a Director of Studies, 
 * including central navigation links to look up student directories, staff registries, 
 * and structural academic resources.
 * This class inherits cross-module menu behaviors from {@link EnseignantController}.
 */
public class DEGestionController extends EnseignantController {
    
    /** Label displaying the name of the currently logged-in Director of Studies. */
    @FXML
    private Label utilisateur;

    /** Sidebar button to navigate to the global grades management module. */
    @FXML
    private Button notesEns1;

    /** Sidebar button to navigate to the global student attendance logs. */
    @FXML
    private Button assiduiteEns1;

    /** Sidebar button to refresh or navigate to the core management dashboard. */
    @FXML
    private Button gestionEns1;

    /** Sidebar button to open the account settings interface. */
    @FXML
    private Button param; 

    /** Sidebar button to securely close the session and log out. */
    @FXML
    private Button deconnexion;

    /** Navigation button to go back to the general teacher dashboard interface. */
    @FXML
    private Button retour;

    /** Main panel button to access the full student management list. */
    @FXML
    private Button listEtud; 

    /** Main panel button to access the full teaching staff directory. */
    @FXML
    private Button listEns;  

    /** Main panel button to access the academic modules and resource catalogs (Ressources/SAÉ). */
    @FXML
    private Button listRess; 

    /**
     * Initializes the controller class. This method is automatically called 
     * once the associated FXML layout markup has been fully parsed.
     * It loads user credentials, resolves the user's name via {@link UtilisateurDAO}, 
     * and maps user interactions on the menu controls to their corresponding view transitions.
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
        listEns.setOnAction((ActionEvent e) -> ens());
        listRess.setOnAction((ActionEvent e) -> ress());
    }

    /**
     * Handles navigation when the back button is clicked.
     * Smoothly returns the user to the foundational teacher interface ("EnseignantScene").
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
     * Handles navigation when the student list button is clicked.
     * Switches the primary scene container to the student directory viewer ("DEListStudent").
     */
    @FXML 
    public void etud() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEListStudent");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles navigation when the resource listing button is clicked.
     * Switches the primary scene container to the academic resource catalog ("DEListRessource").
     */
    @FXML 
    public void ress() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEListRessource");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles navigation when the teacher staff button is clicked.
     * Switches the primary scene container to the faculty directory viewer ("DEListEns").
     */
    @FXML 
    public void ens() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEListEns");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}