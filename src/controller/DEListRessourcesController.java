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
    private TableView<Etudiant> tableEval;

    /** Column mapping and displaying the specific target student's family name (last name). */
    @FXML
    private TableColumn<Etudiant, String> nomS;
    
    /** Column mapping and displaying the specific target student's given name (first name). */
    @FXML
    private TableColumn<Etudiant, String> prenomS;

    /** Column displaying the unique TD layout group assignment ({@link GroupeTD}) mapped to the student record. */
    @FXML
    private TableColumn<Etudiant, GroupeTD> groupe;

    /** Column tracking and visualizing the current statistical ranking score of the student within the module. */
    @FXML
    private TableColumn<Etudiant, Integer> rang;

    /** Column rendering the aggregated raw decimal average score achieved by the specific student. */
    @FXML
    private TableColumn<Etudiant, Double> avg;

    /** Column illustrating an explicit linked absence event element instance mapped to a specific {@link Seance}. */
    @FXML
    private TableColumn<Etudiant, Seance> abs;

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
}