package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.dao.UtilisateurDAO;
import javafx.scene.control.Control;

public class EnseignantAddEvalController extends EnseignantController {

    @FXML
    private Label utilisateur;

    @FXML
    private Button notesEns1;

    @FXML
    private Button assiduiteEns1;

    @FXML
    private Button gestionEns1;

    @FXML
    private Button paramEns1;

    @FXML
    private Button deconnexion;

    @FXML
    private TextField idTF;

    @FXML
    private TextField coeffTF;
    
    @FXML
    private TextField moduleTF;

    @FXML
    private Button retour;

    /**
     * Méthode appelée automatiquement après le chargement du fichier FXML.
     * 
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
     * Action déclenchée lors du clic sur le bouton "Ajouter"
     */
    @FXML
    private void handleAdd(ActionEvent event) {
        String idSaisi = idTF.getText().trim();
        String coeffSaisi = coeffTF.getText().trim();
        String moduleSaisi = moduleTF.getText().trim();

        if (idSaisi.isEmpty() || coeffSaisi.isEmpty() || moduleSaisi.isEmpty()) {
            System.out.println("remplir tous les champs avant de soumettre.");

            setError(idTF);
            setError(coeffTF);
            setError(moduleTF);

            return;
        }

        try {
            double coefficient = Double.parseDouble(coeffSaisi.replace(',', '.'));

            // TODO (dao.create(...))

            handleCancel(event);

        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez saisir des nombres valides (ex: 15.5 ou 2).");
        }
    }

     /** to return on the precedent page */
    @FXML
    private void handleCancel(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantGrades");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Sets the border of an element to red, indicating error */
    private void setError(Control field) {
        field.setStyle("-fx-border-color: red;");
    }

    /**Sets the border of an element to green, indicating success */
    private void setSuccess(Control field) {
        field.setStyle("-fx-border-color: green;");
    }
}