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
import model.dao.EtudiantDAO;
import model.dao.UtilisateurDAO;
import model.entity.Etudiant;
import model.entity.Promotion;
import model.entity.Utilisateur;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class StudentController{

    /**Name of the Student */
    @FXML
    private Label nomEtudiant;

    /**Informations of the student */
    @FXML
    private Label infosEleve;

    /**Clickable button to logout */
    @FXML
    private Button deconnexion;

    /**Button to choose the semester */
    @FXML
    private ComboBox<String> selecteurSemestre;

    /**Button to see more grades */
    @FXML
    private Button voirPlusNotes;

    /**Button to see more absences */
    @FXML
    private Button voirPlusAbsences;

    /**Denomination of each UE */
    @FXML
    private Label ueNb1;
    @FXML
    private Label ueNb2;
    @FXML
    private Label ueNb3;
    @FXML
    private Label ueNb4;
    @FXML
    private Label ueNb5;
    @FXML
    private Label ueNb6;

    /**Averages of each UE */
    @FXML
    private java.awt.Label moyUE1;
    @FXML
    private Label moyUE2;
    @FXML
    private Label moyUE3;
    @FXML
    private Label moyUE4;
    @FXML
    private Label moyUE5;
    @FXML
    private Label moyUE6;

    /**Justified absences */
    @FXML
    private Label absJust;

    /**Unjustified absences */
    @FXML
    private Label absNonJust;

    /**Numbers of delays */
    @FXML
    private Label nbRetards;

    /**
     * Initializes the attributes of the Controller,
     * such as the logout button, the chooser of semester
     */
    @FXML
    public void initialize() {
        String email = model.MailUser.getEmail();
        UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        nomEtudiant.setText(nom);
        EtudiantDAO daoEtud = new EtudiantDAO();
        int nbEtud = daoEtud.findIDByMail(email);
        infosEleve.setText("Étudiant n° "+nbEtud);
        ObservableList<String> opt = FXCollections.observableArrayList("Semestre 1","Semestre 2","Semestre 3","Semestre 4","Semestre 5","Semestre 6");
        selecteurSemestre.setItems(opt);
        selecteurSemestre.getSelectionModel().selectFirst();
        selecteurSemestre.getSelectionModel().selectedItemProperty().addListener((observable,ancienSem,sem) -> {
            if(sem != null){
                affNoteSem(sem);
            }
        });
        deconnexion.setOnAction((ActionEvent e) -> logout());
        voirPlusNotes.setOnAction((ActionEvent e) -> vPNotes());
        voirPlusAbsences.setOnAction((ActionEvent e) -> vPAbs());
    }

    /**
     * Initializes the attributes of the logout button
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
     * Initializes the attributes of the voirPlusNotes button
     */
    @FXML
    public void vPNotes(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("StudentGrades");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the attributes of the voirPlusAbsences button
     */
    @FXML
    public void vPAbs(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("StudentAbs");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the attributes of the selecteurSemestre button
     * @param sem the semester
     */
    public void affNoteSem(String sem){
        if(sem.equals("Semestre 1")){
            ueNb1.setText("UE 1.1");
            ueNb2.setText("UE 1.2");
            ueNb3.setText("UE 1.3");
            ueNb4.setText("UE 1.4");
            ueNb5.setText("UE 1.5");
            ueNb6.setText("UE 1.6");

            moyUE1.setText("Moyenne : ");
            moyUE2.setText("Moyenne : ");
            moyUE3.setText("Moyenne : ");
            moyUE4.setText("Moyenne : ");
            moyUE5.setText("Moyenne : ");
            moyUE6.setText("Moyenne : ");

            absJust.setText("Absences justifiées : ");
            absNonJust.setText("Absences non justifiées : ");
            nbRetards.setText("Nombre de reatrds : ");
        }
        if(sem.equals("Semestre 2")){
            ueNb1.setText("UE 2.1");
            ueNb2.setText("UE 2.2");
            ueNb3.setText("UE 2.3");
            ueNb4.setText("UE 2.4");
            ueNb5.setText("UE 2.5");
            ueNb6.setText("UE 2.6");

            moyUE1.setText("Moyenne : ");
            moyUE2.setText("Moyenne : ");
            moyUE3.setText("Moyenne : ");
            moyUE4.setText("Moyenne : ");
            moyUE5.setText("Moyenne : ");
            moyUE6.setText("Moyenne : ");

            absJust.setText("Absences justifiées : ");
            absNonJust.setText("Absences non justifiées : ");
            nbRetards.setText("Nombre de reatrds : ");
        }
        if(sem.equals("Semestre 3")){
            ueNb1.setText("UE 3.1");
            ueNb2.setText("UE 3.2");
            ueNb3.setText("UE 3.3");
            ueNb4.setText("UE 3.4");
            ueNb5.setText("UE 3.5");
            ueNb6.setText("UE 3.6");

            moyUE1.setText("Moyenne : ");
            moyUE2.setText("Moyenne : ");
            moyUE3.setText("Moyenne : ");
            moyUE4.setText("Moyenne : ");
            moyUE5.setText("Moyenne : ");
            moyUE6.setText("Moyenne : ");

            absJust.setText("Absences justifiées : ");
            absNonJust.setText("Absences non justifiées : ");
            nbRetards.setText("Nombre de reatrds : ");
        }
        if(sem.equals("Semestre 4")){
            ueNb1.setText("UE 4.1");
            ueNb2.setText("UE 4.2");
            ueNb3.setText("UE 4.3");
            ueNb4.setText("UE 4.4");
            ueNb5.setText("UE 4.5");
            ueNb6.setText("UE 4.6");

            moyUE1.setText("Moyenne : ");
            moyUE2.setText("Moyenne : ");
            moyUE3.setText("Moyenne : ");
            moyUE4.setText("Moyenne : ");
            moyUE5.setText("Moyenne : ");
            moyUE6.setText("Moyenne : ");

            absJust.setText("Absences justifiées : ");
            absNonJust.setText("Absences non justifiées : ");
            nbRetards.setText("Nombre de reatrds : ");
        }
        if(sem.equals("Semestre 5")){
            ueNb1.setText("UE 5.1");
            ueNb2.setText("UE 5.2");
            ueNb3.setText("UE 5.3");
            ueNb4.setText("UE 5.4");
            ueNb5.setText("UE 5.5");
            ueNb6.setText("UE 5.6");

            moyUE1.setText("Moyenne : ");
            moyUE2.setText("Moyenne : ");
            moyUE3.setText("Moyenne : ");
            moyUE4.setText("Moyenne : ");
            moyUE5.setText("Moyenne : ");
            moyUE6.setText("Moyenne : ");

            absJust.setText("Absences justifiées : ");
            absNonJust.setText("Absences non justifiées : ");
            nbRetards.setText("Nombre de reatrds : ");
        }
        if(sem.equals("Semestre 6")){
            ueNb1.setText("UE 6.1");
            ueNb2.setText("UE 6.2");
            ueNb3.setText("UE 6.3");
            ueNb4.setText("UE 6.4");
            ueNb5.setText("UE 6.5");
            ueNb6.setText("UE 6.6");

            moyUE1.setText("Moyenne : ");
            moyUE2.setText("Moyenne : ");
            moyUE3.setText("Moyenne : ");
            moyUE4.setText("Moyenne : ");
            moyUE5.setText("Moyenne : ");
            moyUE6.setText("Moyenne : ");

            absJust.setText("Absences justifiées : ");
            absNonJust.setText("Absences non justifiées : ");
            nbRetards.setText("Nombre de reatrds : ");
            
        }
    }
             
}
