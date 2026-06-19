package controller;

import controller.EnseignantController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.UtilisateurDAO;
import model.entity.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import model.dao.ModuleDAO;
import model.entity.Module;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

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


/**
 * Controller class for the Director of Studies' Resource List view ("DEListRessources").
 * This class handles the display of a student roster within a selected educational resource,
 * showing performance metrics like average grades, rank, absences, and group assignments.
 * * <p>It extends {@link EnseignantController} to maintain consistent sidebar navigation 
 * patterns across the dashboard application.</p>
 * * @author Team Kallax
 * @version 1.0
 */
public class DEListRessourcesController extends EnseignantController {

    // ==========================================
    // FXML Fields - Sidebar Navigation Panel
    // ==========================================

    /** Label displaying the identity of the currently authenticated Director of Studies / Instructor. */
    @FXML
    private Label utilisateur;

    /** Button routing the user to the grades overview dashboard. */
    @FXML
    private Button notesEns1;

    /** Button routing the user to the student attendance management tracking panel. */
    @FXML
    private Button assiduiteEns1;

    /** Button routing the user to the primary system administration and management interface. */
    @FXML
    private Button gestionEns1;

    /** Button routing the user to profile configuration and account settings. */
    @FXML
    private Button paramEns1;

    /** Button executing safe session destruction and user logout. */
    @FXML
    private Button deconnexion;

    // ==========================================
    // FXML Fields - Main Content Header Controls
    // ==========================================

    /** Layout button utilized to safely step back to the preceding UI workspace view. */
    @FXML
    private Button retour;

    /** Structural descriptive label displaying the contextual header title above the data table grid. */
    @FXML
    private Label titre;

    // ==========================================
    // FXML Fields - Student Metrics Data Grid Table
    // ==========================================

    /** Primary table view layout element populated with records matching an {@link Etudiant} context. */
    @FXML
    private TableView<Module> tableRes;

    /** Column mapping and displaying the specific target student's family name (last name). */
    @FXML
    private TableColumn<Module, String> code;
    
    /** Column mapping and displaying the specific target student's given name (first name). */

    /** Column displaying the unique TD layout group assignment ({@link GroupeTD}) mapped to the student record. */
    @FXML
    private TableColumn<Module, String> intitule;

    /** Column tracking and visualizing the current statistical ranking score of the student within the module. */


    private ObservableList<Module> listeModules = FXCollections.observableArrayList();
    private ModuleDAO moduleDAO = new ModuleDAO();

    // ==========================================
    // FXML Fields - Footer Action Controls
    // ==========================================

    /** Context action button designated to spawn or append a new session entry to the schedule workspace. */
    @FXML
    private Button addSeance;

    /**
     * Initializes the controller lifecycle environment. This configuration method executes 
     * automatically after structural FXML resource bindings complete.
     * * <p>It determines identity properties from the active user session context, maps profile 
     * text fields, and binds explicit event sequences to interaction routines.</p>
     */
    @FXML
    public void initialize() {
        String email = model.MailUser.getEmail();
        UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        
        // Navigation and operational action event mappings
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> retour());

        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        intitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));

        tableRes.setItems(listeModules);
        refresh();
    }

    /**
     * Safely routes the system display focus backward into the primary Director of Studies 
     * management screen layout workspace environment ("DEGestion").
     * * <p>Intercepts and dumps structural exception trace records if JavaFX resource allocations 
     * or scene transitions fail to fire properly.</p>
     */
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
            List<Module> listeBDD = moduleDAO.findAll(); 
            System.out.println("Nombre de modules récupérés depuis la BDD : " + listeBDD.size());
            listeModules.setAll(listeBDD);
        } catch (Exception e) {
            System.out.println("erreur ici");
            e.printStackTrace();
        }
    }
}