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
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class EnseignantGradesController extends EnseignantController {

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

    /**Center */
    @FXML
    private Button retour;


    @FXML
    private Button addCtrl; 


    /**The table */
    @FXML
    private TableView<Evaluation> tableEval; 

    @FXML
    private TableColumn<Evaluation, String> nomEval;

    @FXML
    private TableColumn<Evaluation, String> ens;

    @FXML
    private TableColumn<Evaluation, String> dateEval;

    @FXML
    private TableColumn<Evaluation, Double> avg;

    @FXML
    private TableColumn<Evaluation, Double> mediane; 

    @FXML
    private TableColumn<Evaluation, Double> coeff; 

    @FXML
    private TableColumn<Evaluation, Button> result; 

    @FXML
    private TableColumn<Evaluation, Button> remplirNote; 

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

}
