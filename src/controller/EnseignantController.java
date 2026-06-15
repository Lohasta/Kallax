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
import model.entity.Utilisateur;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class EnseignantController {
    
    /** user name */
    @FXML
    private Label utilisateur;

    /** differents buttons */
    @FXML
    private Button notesEns1,notesEns2,assiduiteEns1,assiduiteEns2,gestionEns1,gestionEns2,param,deconnexion;


    public void initialize(){
        String email = model.MailUser.getEmail();
        model.dao.UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        utilisateur.setText(nom);
        deconnexion.setOnAction((ActionEvent e) -> logout());
        notesEns1.setOnAction((ActionEvent e) -> notes());
        notesEns2.setOnAction((ActionEvent e) -> notes());
        assiduiteEns1.setOnAction((ActionEvent e) -> assiduite());
        assiduiteEns2.setOnAction((ActionEvent e) -> assiduite());
        gestionEns1.setOnAction((ActionEvent e) -> gestion(email));
        gestionEns2.setOnAction((ActionEvent e) -> gestion(email));
        param.setOnAction((ActionEvent e) -> parametre(email));
    }

    /**
     * Load the grades page
     */
    @FXML
    public void notes(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantGrades");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the login page 
     */
    @FXML
    public void logout(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("LoginScene");
            view.SkodocMain.loadFXML(fxmlLoader);
            model.MailUser.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads Assiduite page
     */
    @FXML
    public void assiduite(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantAssiduite");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads gestion page verify if the user is a DE or a teacher
     */
    @FXML
    public void gestion(String email){
        model.dao.EnseignantDAO dao = new EnseignantDAO();
        if(dao.estDE(email)){
            try {
                FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEGestion");
                view.SkodocMain.loadFXML(fxmlLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantGestion");
                view.SkodocMain.loadFXML(fxmlLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads settings page verify if the user is a DE or a teacher
     */
    @FXML
    public void parametre(String email){
        model.dao.EnseignantDAO dao = new EnseignantDAO();
        if(dao.estDE(email)){
            try {
                FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("DEParam");
                view.SkodocMain.loadFXML(fxmlLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("EnseignantParam");
                view.SkodocMain.loadFXML(fxmlLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    

    

}
