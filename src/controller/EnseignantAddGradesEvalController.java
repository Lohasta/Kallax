package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.dao.UtilisateurDAO;

/**
 * Controller class for the Teacher's Grade Assignment interface ("EnseignantAddGradesEval").
 * This class manages the interface used by instructors to enter or update individual student 
 * grades for a specific evaluation.
 * * <p>It extends {@link EnseignantController} to inherit shared navigation logic 
 * across the teacher panel ecosystem.</p>
 * * @author YourName
 * @version 1.0
 */
public class EnseignantAddGradesEvalController extends EnseignantController {


    /** Label displaying the identity of the currently authenticated teacher. */
    @FXML
    private Label utilisateur;

    /** Button routing the user to the principal grades dashboard. */
    @FXML
    private Button notesEns1;

    /** Button routing the user to the student attendance registry workspace. */
    @FXML
    private Button assiduiteEns1;

    /** Button routing the user to the administrative management screen. */
    @FXML
    private Button gestionEns1;

    /** Button routing the user to personal account preferences and settings. */
    @FXML
    private Button paramEns1;

    /** Button handling session termination and application logout sequences. */
    @FXML
    private Button deconnexion;


    /** Navigation button to return to the previous screen. */
    @FXML
    private Button retour;

    /** Input field or display container for the student's last name. */
    @FXML
    private TextField nameE; 

    /** Input field or display container for the student's first name. */
    @FXML
    private TextField prenomE; 

    /** Input field or display container for the student's assigned group or section. */
    @FXML
    private TextField grpE; 

    /** Numeric input entry field designated for capturing the student's grade. */
    @FXML
    private TextField noteE; 

    /**
     * Initializes the controller environment. This lifecycle method executes automatically 
     * immediately after the corresponding FXML configuration has loaded.
     * * <p>It dynamically updates the UI sidebar with the teacher's profile details 
     * and maps user interaction click sequences to explicit handler routines.</p>
     */
    @FXML
    public void initialize() {
        String email = model.MailUser.getEmail();
        UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        
        // Context-aware action listeners setup
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());
    }

    /**
     * Redirects the user back to the primary evaluation matrix screen ("EnseignantGrades").
     * Prints an error stack trace output to the terminal if JavaFX views experience resource loading failures.
     */
    @FXML 
    public void retour() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantGrades");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}