import controller.EnseignantController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.UtilisateurDAO;
import model.entity.*;

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

    @FXML
    private Label titreEval;


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
    private TableColumn<Seance, String> heure; // À changer en String, LocalTime ou Integer selon vos données

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
        param.setOnAction((ActionEvent e) -> parametre(email));
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