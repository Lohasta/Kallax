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
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class DEListEnsController extends EnseignantController{
    
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
    private Label titre;


    /**The table */
    @FXML
    private TableView<Enseignant> tableEval; 

    @FXML
    private TableColumn<Enseignant, String> nom; 

    @FXML
    private TableColumn<Enseignant, String> prenom;

    @FXML
    private TableColumn<Enseignant, String> ress; 

    @FXML
    private TableColumn<Enseignant, String> tuteur; 

    // Liste observable qui notifie le TableView à chaque changement
    private ObservableList<Enseignant> listeEnseignants = FXCollections.observableArrayList();
    private EnseignantDAO enseignantDAO = new EnseignantDAO();

    public void initialize(){
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nomUser = dao.findNomByID(email);
        utilisateur.setText(nomUser);
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());


        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ress.setCellValueFactory(new PropertyValueFactory<>("ress")); 
        tuteur.setCellValueFactory(new PropertyValueFactory<>("tuteur")); 

        tableEval.setItems(listeEnseignants);

        refresh();
    }

    /** to return on the precedent page */
    @FXML 
    public void retour(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEGestion");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addEnseignant() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEAddEnseignant");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void refresh() {
        try {
            List<Enseignant> listeBDD = enseignantDAO.findAll(); 
            
            listeEnseignants.setAll(listeBDD);
        } catch (Exception e) {
            System.out.println("erreur ici");
            e.printStackTrace();
        }
    }

}
