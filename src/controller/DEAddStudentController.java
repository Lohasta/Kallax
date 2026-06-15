package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField; // Ajouté pour le mot de passe
import model.dao.UtilisateurDAO;
import model.entity.Etudiant;
import model.entity.Utilisateur;
import model.dao.EtudiantDAO;
import model.entity.GroupeTD;
import model.entity.Promotion;

public class DEAddStudentController extends StudentController {

    @FXML
    private Label utilisateur;

    @FXML
    private TextField numEtudiantTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField mdpTF; 

    @FXML
    private TextField parcoursTF;

    @FXML
    private TextField groupeTDTF;

    @FXML
    private TextField promoTF;

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
    private Button retour;

    @Override
    public void initialize(){
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        retour.setOnAction((ActionEvent e) -> handleCancel(e));
        deconnexion.setOnAction((ActionEvent e) -> logout());
        retour.setOnAction((ActionEvent e) -> handleCancel(null));


    }

    /**
     * Action déclenchée lors du clic sur le bouton "Ajouter"
     */
    @FXML
    private void handleAdd(ActionEvent event) {
        String numEtudStr = numEtudiantTF.getText().trim();
        String nom = nomTF.getText().trim();
        String prenom = prenomTF.getText().trim();
        String email = emailTF.getText().trim();
        String mdp = mdpTF.getText().trim();
        String parcours = parcoursTF.getText().trim();
        String groupeTD = groupeTDTF.getText().trim();
        String promo = promoTF.getText().trim();

        if (numEtudStr.isEmpty() || nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || parcours.isEmpty() || groupeTD.isEmpty() || promo.isEmpty()) {
            System.out.println("Champs incomplets");
            return;
        }

        try {
            int numero = Integer.parseInt(numEtudStr);

            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utilisateur util = new Utilisateur(nom, prenom, email, mdp);
            utilisateurDAO.create(util);

            Etudiant nouvelEtudiant = new Etudiant(nom, prenom, email, mdp, numero);
            // Si tes attributs spécifiques de BD se gèrent par des setters, ajoute-les ici :
            // nouvelEtudiant.setParcours(parcours);

            // 1. Récupération ou création de la promotion via l'Enum
            Promotion promoEntity = Promotion.getPromotionByName(promo);
            
            // 2. Récupération ou création du groupe TD associé à cette promotion
            GroupeTD groupe = GroupeTD.getGroupeByNomAndPromo(groupeTD, promoEntity);
            if (groupe == null) {
                groupe = new GroupeTD(groupeTD, promoEntity);
                GroupeTD.addGroupe(groupe);
                promoEntity.ajouterGroupe(groupe);
            }
            
            // 3. Liaison de l'étudiant et du groupe
            groupe.ajouterEtudiant(nouvelEtudiant);
            
            // Si ta base/entité gère aussi le parcours et la promo par des setters, tu peux décommenter ici :
            // nouvelEtudiant.setParcours(parcours);
            // nouvelEtudiant.setPromo(promoEntity);
            
            EtudiantDAO etudiantDAO= new EtudiantDAO();

            etudiantDAO.create(nouvelEtudiant);

            System.out.println("Succès : L'étudiant a été ajouté avec succès !");
            handleCancel(null); 

        } catch (NumberFormatException e) {
            System.out.println("Erreur de format : Le numéro d'étudiant doit être un entier.");
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
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEListStudent");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}