package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import model.dao.EnseignantDAO;
import model.dao.EtudiantDAO;
import model.dao.UtilisateurDAO;
import model.entity.Etudiant;
import model.entity.Promotion;
import model.entity.Utilisateur;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import model.entity.Utilisateur;
import javafx.scene.control.*;


public class DEAddUserController extends EnseignantController{
    /**Left pannel */
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


    /**The center */
    @FXML
    private Button retour;

    @FXML
    private Label titre;

    
    /**To add the new user */
    @FXML
    private TextField nameNewUser;

    @FXML
    private TextField surnameNewUser;

    @FXML
    private TextField emailNewUser;

    @FXML
    private TextField passwdNewUser;

    @FXML
    private Button ajouter;


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

    /** to return on the precedent page */
    @FXML
    private void handleCancel(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEUsers");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Adds the new User*/
    @FXML
    private void handleAdd(ActionEvent event){
        UtilisateurDAO uDao = new UtilisateurDAO();

        String nom = nameNewUser.getText();
        String prenom = surnameNewUser.getText();
        String email = emailNewUser.getText();
        String mdp = passwdNewUser.getText();

        if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()){
            System.out.println("Un ou plusieurs champs sont vides.");

            setError(nameNewUser);
            setError(surnameNewUser);
            setError(emailNewUser);
            setError(passwdNewUser);

            return;
        } else{
            setSuccess(nameNewUser);
            setSuccess(surnameNewUser);
            setSuccess(emailNewUser);
            setSuccess(passwdNewUser);

            Utilisateur newUser = new Utilisateur(nom, prenom, email, mdp);
            uDao.create(newUser);  // Adds the User to the database
            System.out.println("Utilisateur " + newUser.getEmail() + " ajouté !");
            handleCancel(event);
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
