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
import model.dao.EnseignantDAO;
import model.dao.EtudiantDAO;
import model.dao.UtilisateurDAO;
import model.entity.Enseignant;
import model.entity.Etudiant;
import model.entity.Promotion;
import model.entity.Utilisateur;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import model.entity.Utilisateur;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class DEUsersController extends EnseignantController {
    
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

    /**The table */
    @FXML
    private TableView<Utilisateur> tableUsers; 

    @FXML
    private TableColumn<Utilisateur, String> nom;

    @FXML
    private TableColumn<Utilisateur, String> prenom;

    @FXML
    private TableColumn<Utilisateur, String> mail;

    @FXML
    private TableColumn<Utilisateur, Button> discard; 

    private ObservableList<Utilisateur> userList = FXCollections.observableArrayList();
    private UtilisateurDAO uDAO = new UtilisateurDAO();

    @FXML
    private Button addCtrl;

    public void initialize(){
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String name = dao.findNomByID(email);
        utilisateur.setText(name);
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());
        addCtrl.setOnAction((ActionEvent e) -> ajouterUser());

        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        tableUsers.setItems(userList);

        refresh();
    }

    /** to return on the precedent page */
    @FXML 
    public void retour(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEParam");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ajouterUser(){
        try{
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEAddUser");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void refresh() {
        try {
            List<Utilisateur> listeBDD = uDAO.findAll(); 
            userList.setAll(listeBDD);
        } catch (Exception e) {
            System.out.println("erreur ici");
            e.printStackTrace();
        }
    }
}
