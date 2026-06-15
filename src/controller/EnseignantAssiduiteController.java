package controller;

import controller.EnseignantController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.UtilisateurDAO;
import model.entity.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class EnseignantAssiduiteController extends EnseignantController {

    /**Left side */
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


    /**center */
    @FXML
    private Button retour;

    /**The table */
    @FXML
    private TableView<Seance> tableEval; 

    @FXML
    private TableColumn<Etudiant, String> nomS; 

    @FXML
    private TableColumn<Etudiant, String> prenomS;

    @FXML
    private TableColumn<Seance, String> groupe;

    @FXML
    private TableColumn<Seance, String> ens;

    @FXML
    private TableColumn<Seance, String> heure; 

    @FXML
    private TableColumn<Seance, String> ressource;

    @FXML
    private TableColumn<Seance, Button> pres; 


    public void initialize(){
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());
    }

    /** to return on the precedent page */
    @FXML 
    public void retour(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantScene");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSeance(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantAddAssiduite");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFaireAppel(ActionEvent event) {
    
        Seance seanceSelectionnee = tableEval.getSelectionModel().getSelectedItem();

        if (seanceSelectionnee == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Sélection requise");
            alert.setHeaderText("Aucune séance sélectionnée");
            alert.setContentText("Veuillez cliquer sur une séance dans le tableau avant de faire l'appel.");
            alert.showAndWait();
            return;
        }

        // 3. Si une séance est sélectionnée, on passe à la page de l'appel
        try {
            System.out.println("Ouverture de l'appel pour la séance de " + seanceSelectionnee.getHeureDeb());
            
            // TODO: Sauvegarder la séance sélectionnée qlq part pour le controller
            
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantFaireAppel");
            view.SkodocMain.loadFXML(fxmlLoader);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}