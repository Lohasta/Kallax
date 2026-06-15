package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.dao.UtilisateurDAO;
import javafx.scene.control.Control;

/**
 * Controller class for the evaluation creation view ("Add Evaluation").
 * It allows teachers to create new assessments by specifying details such as 
 * the evaluation name, teacher in charge, date, and coefficient.
 * This class inherits shared navigation behaviors from {@link EnseignantController}.
 */
public class EnseignantAddEvalController extends EnseignantController {

    /** Label displaying the name of the currently logged-in teacher. */
    @FXML
    private Label utilisateur;

    /** Button to navigate to the grades management view. */
    @FXML
    private Button notesEns1;

    /** Button to navigate to the attendance management view. */
    @FXML
    private Button assiduiteEns1;

    /** Button to navigate to the main management/dashboard view. */
    @FXML
    private Button gestionEns1;

    /** Button to navigate to the settings view. */
    @FXML
    private Button paramEns1;

    /** Button to log out of the application. */
    @FXML
    private Button deconnexion;

    /** Button to cancel the operation and go back to the previous screen. */
    @FXML
    private Button retour;

    /** Input field for the evaluation's name (e.g., "DS1"). */
    @FXML
    private TextField nameE;  

    /** Input field for the teacher's name or reference. */
    @FXML
    private TextField ens;     

    /** Input field for the evaluation's date. */
    @FXML
    private TextField dateE;   

    /** Input field for the evaluation's coefficient value. */
    @FXML
    private TextField coeffE;

    /**
     * Initializes the controller class. This method is automatically called 
     * after the FXML file has been loaded.
     * It fetches the logged-in teacher's identity using {@link UtilisateurDAO} 
     * and binds navigation events to the sidebar and action buttons.
     */
    @FXML
    public void initialize() {
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> handleCancel(e));
    }
    
    /**
     * Handles the action when the "Add" button is clicked.
     * It validates input fields, formats numerical inputs, triggers the DAO creation process,
     * and highlights empty fields in red using {@link #setError(Control)}.
     * * @param event The ActionEvent triggered by clicking the add button.
     */
    /*
    @FXML
    private void handleAdd(ActionEvent event) {
        String idSaisi = nameE.getText().trim();
        String coeffSaisi = coeffE.getText().trim();
        String date = dateE.getText().trim();

        if (idSaisi.isEmpty() || coeffSaisi.isEmpty() || date.isEmpty()) {
            System.out.println("remplir tous les champs avant de soumettre.");

            setError(nameE);
            setError(ens);
            setError(dateE);
            setError(coeffE);

            return;
        }

        try {
            double coefficient = Double.parseDouble(coeffSaisi.replace(',', '.'));

            // TODO (dao.create(...))

            handleCancel(event);

        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez saisir des nombres valides (ex: 15.5 ou 2).");
        }
    }*/

    /**
     * Handles the action when the "Cancel" or "Return" button is clicked.
     * It redirects the teacher back to the evaluation overview screen ("EnseignantGrades").
     * * @param event The ActionEvent triggered by clicking the return button.
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantGrades");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Applies a red border to the specified JavaFX control element, 
     * visually indicating a validation error or a missing required field.
     * * @param field The control element (e.g., TextField) to mark with an error style.
     */
    private void setError(Control field) {
        field.setStyle("-fx-border-color: red;");
    }

    /**
     * Applies a green border to the specified JavaFX control element, 
     * visually indicating a successful validation.
     * * @param field The control element (e.g., TextField) to mark with a success style.
     */
    private void setSuccess(Control field) {
        field.setStyle("-fx-border-color: green;");
    }
}