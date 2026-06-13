package controller;

import java.io.IOException;
import java.sql.Connection;
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
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;


public class StudentGradesController extends StudentController{
    
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
    private ComboBox<String> semesterSelector;

    /**Button to go to the precedent page */
    @FXML
    private Button reduceButton;

    /**Denomination of each UE */
    @FXML 
    private Label ue1, ue2, ue3, ue4, ue5, ue6;

    /**Denomination of each resources */
    @FXML 
    private Label r1C1, r2C1, r1C2, r2C2, r1C3, r2C3, r1C4, r2C4, r1C5, r2C5, r1C6, r2C6;

    /**
     * Initializes the attributes of the Controller,
     * such as the logout button, the chooser of semester
     */
    @FXML
    public void initialize() {
        String email=model.MailUser.getEmail();
        UtilisateurDAO dao = new UtilisateurDAO();
        String nom = dao.findNomByID(email);
        nomEtudiant.setText(nom);
        List<String> listeSemestres = new LinkedList<>();
        listeSemestres.add("Semestre 1");
        listeSemestres.add("Semestre 2");
        listeSemestres.add("Semestre 3");
        listeSemestres.add("Semestre 4");
        listeSemestres.add("Semestre 5");
        listeSemestres.add("Semestre 6");
        ObservableList<String> opt = FXCollections.observableList(listeSemestres);
        selecteurSemestre.setItems(opt);
        selecteurSemestre.requestLayout();
        selecteurSemestre.getSelectionModel().selectFirst();
        selecteurSemestre.getSelectionModel().selectedItemProperty().addListener((observable,ancienSem,sem) -> {
            if(sem != null){
                affNoteSem(sem);
            }
        });
        deconnexion.setOnAction((ActionEvent e) -> logout());
        reduceButton.setOnAction((ActionEvent e) -> retour());

    }

    /**
     * Initializes the attributes of the semsterSelector button
     * @param sem the semester
     */
    @FXML
    public void affNoteSemRess(String sem){
        if(sem.equals("Semestre 1")){
            ue1.setText("UE 1.1");
            ue2.setText("UE 1.2");
            ue3.setText("UE 1.3");
            ue4.setText("UE 1.4");
            ue5.setText("UE 1.5");
            ue6.setText("UE 1.6");

            r1C1.setText(" Moyenne :");
            r2C1.setText(" Moyenne :");
            r1C2.setText(" Moyenne :");
            r2C2.setText(" Moyenne :");
            r1C3.setText(" Moyenne :");
            r2C3.setText(" Moyenne :");
            r1C4.setText(" Moyenne :");
            r2C4.setText(" Moyenne :");
            r1C5.setText(" Moyenne :");
            r2C5.setText(" Moyenne :");
            r1C6.setText(" Moyenne :");
            r2C6.setText(" Moyenne :");
        }
        if (sem.equals("Semestre 2")) {
            ue1.setText("UE 2.1"); 
            ue2.setText("UE 2.2"); 
            ue3.setText("UE 2.3");
            ue4.setText("UE 2.4"); 
            ue5.setText("UE 2.5"); 
            ue6.setText("UE 2.6");
        }
        if (sem.equals("Semestre 3")) {
            ue1.setText("UE 3.1"); 
            ue2.setText("UE 3.2"); 
            ue3.setText("UE 3.3");
            ue4.setText("UE 3.4"); 
            ue5.setText("UE 3.5"); 
            ue6.setText("UE 3.6");
        }
        if (sem.equals("Semestre 4")) {
            ue1.setText("UE 4.1"); 
            ue2.setText("UE 4.2"); 
            ue3.setText("UE 4.3");
            ue4.setText("UE 4.4"); 
            ue5.setText("UE 4.5"); 
            ue6.setText("UE 4.6");
        }
        if (sem.equals("Semestre 5")) {
            ue1.setText("UE 5.1"); 
            ue2.setText("UE 5.2");
            ue3.setText("UE 5.3");
            ue4.setText("UE 5.4"); 
            ue5.setText("UE 5.5"); 
            ue6.setText("UE 5.6");
        }
        if (sem.equals("Semestre 6")) {
            ue1.setText("UE 6.1"); 
            ue2.setText("UE 6.2"); 
            ue3.setText("UE 6.3");
            ue4.setText("UE 6.4"); 
            ue5.setText("UE 6.5"); 
            ue6.setText("UE 6.6");
        }
    }

    /**To go to the precedent page */
    @FXML
    public void retour(){
        try {
            FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("StudentScene");
            view.SkodocMain.loadFXML(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
