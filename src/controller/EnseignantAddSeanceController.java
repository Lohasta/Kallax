package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.dao.GroupeTDDAO;
import model.dao.ModuleDAO;
import model.entity.GroupeTD;
import model.entity.Module;
import java.util.ArrayList;
import java.util.List;

import model.dao.UtilisateurDAO;

public class EnseignantAddSeanceController extends EnseignantController {

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

    @FXML
    private TextField groupeTF;

    @FXML
    private TextField moduleTF;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField heureDebTF;

    @FXML
    private TextField coeffTF;

    @FXML
    private Button retour;

    /**
     * Méthode appelée automatiquement après le chargement du fichier FXML.
     * 
     */
    @FXML
    public void initialize() {
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        paramEns1.setOnAction((ActionEvent e) -> parametre(email));
        retour.setOnAction((ActionEvent e) -> handleCancel(e));
    }


    
    /**
     * Action déclenchée lors du clic sur le bouton "Ajouter"
     */
    @FXML
    private void handleAdd(ActionEvent event) {
        String groupeSaisi = groupeTF.getText().trim();
        String coeffSaisi = coeffTF.getText().trim();
        String moduleSaisi = moduleTF.getText().trim();
        String dateSaisi = dateTF.getText().trim();
        String heureDebSaisi = heureDebTF.getText().trim();

       if (groupeSaisi.isEmpty() || coeffSaisi.isEmpty() || moduleSaisi.isEmpty() || dateSaisi.isEmpty() || heureDebSaisi.isEmpty()) {
            System.err.println("Erreur de saisie");
            return;
        }

        GroupeTDDAO groupeDAO = new GroupeTDDAO();
        List<GroupeTD> groupes = groupeDAO.findAll();
        boolean groupeExiste = false;
        
        for (GroupeTD groupe : groupes) {
            if (groupe.getNom().equalsIgnoreCase(groupeSaisi)) {
                groupeExiste = true;
                break;
            }
        }
        
        if (!groupeExiste) {
            System.out.println("groupe incorrect");
            return;
        }
        
        ModuleDAO moduleDAO = new ModuleDAO();
        List<Module> modules = moduleDAO.findAll();
        boolean moduleExiste = false;
        for (Module module : modules) {
            if (module.getCode().equalsIgnoreCase(moduleSaisi)) {
                moduleExiste = true;
                break;
            }
        }
        
        if (!moduleExiste) {
            System.out.println("Module incorrect");
            return;
        }
        try {
            double coefficient = Double.parseDouble(coeffSaisi.replace(',', '.'));
            if (coefficient <= 0) {
                System.out.println("Coefficient invalide");
                return;
            }

            //regex pour les dates
            if (!dateSaisi.matches("\\d{2}/\\d{2}/\\d{4}") && !dateSaisi.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Date invalide");
                return;
            }

            // regex heure : HH:mm ou HHhmm
            if (!heureDebSaisi.matches("\\d{2}[:h]\\d{2}")) {
                System.out.println("Heure invalide");
                return;
            }

            System.out.println("valide :");

            //TODO

            handleCancel(event);

        } catch (NumberFormatException e) {
            System.out.println("Veuillez saisir un nombre valide pour le coefficient.");
        }
    }

     /** to return on the precedent page */
    @FXML
    private void handleCancel(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantAssiduite");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}