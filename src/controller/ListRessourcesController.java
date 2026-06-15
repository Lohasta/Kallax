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
import model.entity.Enseignant;
import model.entity.Utilisateur;
import model.entity.Module;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class ListRessourcesController extends EnseignantController{

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


    // --- Zone Centrale ---
    @FXML
    private Button retour;

    @FXML
    private Label titreEval; // Garde l'ID "titreEval" défini dans votre FXML pour le titre principal


    // --- Tableau (TableView des Ressources) ---
    @FXML
    private TableView<Module> tableEval; 

    @FXML
    private TableColumn<Module, String> code; 

    @FXML
    private TableColumn<Module, String> sem;  

    @FXML
    private TableColumn<Module, String> intitule; 

    @FXML
    private TableColumn<Module, String> ens; 
    
    
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

    @FXML 
    public void retour(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantGestion");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}