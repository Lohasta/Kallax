package controller;

import controller.EnseignantController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.UtilisateurDAO;
import model.entity.*;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.dao.EtudiantDAO;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

public class DEListStudentController extends EnseignantController {
    
    /** Left-bar attributes */
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

    /** Return to previous page */
    @FXML
    private Button retour;

    /** Title above the TableView */
    @FXML
    private Label titre;

    /** Table view and its TableColumns */
    @FXML
    private TableView<Etudiant> tableEval;

    @FXML
    private TableColumn<Etudiant, String> nom;
    
    @FXML
    private TableColumn<Etudiant, String> prenom;

    @FXML
    private TableColumn<Etudiant, String> groupe;


    /** Button under the table */
    @FXML
    private Button addSeance;

    // 1. Liste observable pour mettre à jour automatiquement le TableView
    private ObservableList<Etudiant> listeEtudiants = FXCollections.observableArrayList();
    private EtudiantDAO etudiantDAO = new EtudiantDAO();

    @FXML
    public void initialize() {
        // Infos de connexion et boutons du menu
        String email = model.MailUser.getEmail();
        UtilisateurDAO dao = new UtilisateurDAO();
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
        groupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        groupe.setCellValueFactory(new PropertyValueFactory<>("nomGroupe"));
        
        tableEval.setItems(listeEtudiants);
        refresh();
    
    }

    @FXML 
    public void retour() {
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEGestion");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        try {
            List<Etudiant> listeBDD = etudiantDAO.findAll(); 
            
            System.out.println("nb etudiants : " + listeBDD.size());

            listeEtudiants.setAll(listeBDD);
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des étudiants");
            e.printStackTrace();
        }
    }
}