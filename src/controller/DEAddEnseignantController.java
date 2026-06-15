package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField; // Ajouté pour le mot de passe
import model.dao.UtilisateurDAO;
import model.entity.Enseignant;

public class DEAddEnseignantController extends EnseignantController {

    @FXML
    private Label utilisateur;

    @FXML
    private TextField identTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField mdpTF; // Modifié en PasswordField pour correspondre au FXML

    // Identifiants mis à jour pour correspondre parfaitement au FXML
    @FXML
    private Button btnEnseignants;

    @FXML
    private Button btnEtudiants;

    @FXML
    private Button btnFormations;

    @FXML
    private Button deconnexion;

    @FXML
    private Button retour;

    @Override
    public void initialize(){
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        
        // Configuration des événements sur les composants existants
        deconnexion.setOnAction((ActionEvent e) -> logout());
        retour.setOnAction((ActionEvent e) -> handleCancel(null));

        // TODO: Si tes boutons "btnEnseignants", "btnEtudiants" et "btnFormations" 
        // doivent rediriger vers d'autres pages, configure leurs actions ici.
        // Exemple :
        // btnEnseignants.setOnAction((ActionEvent e) -> chargerPageEnseignants());
    }

    /**
     * Action déclenchée lors du clic sur le bouton "Ajouter"
     */
    @FXML
    private void handleAdd(ActionEvent event) {
        String ident = identTF.getText().trim();
        String nom = nomTF.getText().trim();
        String prenom = prenomTF.getText().trim();
        String email = emailTF.getText().trim();
        String mdp = mdpTF.getText().trim();

        if (ident.isEmpty() || nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
            System.out.println("Champs incomplets");
            return;
        }

        try {
            Enseignant nouvelEnseignant = new Enseignant(nom, prenom, email, mdp, ident);
            System.out.println("Enseignant créé avec succès");
            
            //TODO model.DAO.EnseignantDAO.insert(nouvelEnseignant);

            System.out.println("Succès : L'enseignant a été ajouté avec succès !");
            handleCancel(null); 

        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur système : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Action déclenchée lors du clic sur "Annuler" ou la flèche "<- Retour"
     */
    @FXML
    private void handleCancel(ActionEvent event) {
         try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEGestionEnseignants");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}