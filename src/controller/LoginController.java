package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import model.dao.UtilisateurDAO;

public class LoginController{
    /**Clickable button to login */
    @FXML
    private Button loginButton;

    /**TextField for the user's username */
    @FXML
    private TextField usernameField;

    /**PasswordField for the user's password*/
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the attributes of the Controller,
     * such as the login button, the username field and the
     * password field.
     */
    @FXML
    public void initialize() {
        System.out.println("[ConnexionController] initialize");
        loginButton.disableProperty().bind(
                usernameField.textProperty().isEmpty()
                        .or(passwordField.textProperty().isEmpty()));
    }

    @FXML
    private void connect() throws IOException, SQLException {
        String name = usernameField.getText();
        String password = passwordField.getText();

        boolean valid = true;

        if(valid && validateField(usernameField) && validateField(passwordField)){
            UtilisateurDAO uDAO = new UtilisateurDAO();
            Connection con = uDAO.isConnected();
            
            if (con != null){
                System.out.println("Tentative de connection...");
                System.out.println("Connexion reussie vers la bdd !");
                
                FXMLLoader fxmlLoader = view.SkodocMain.getFXMLLoader("student_scene");
                view.SkodocMain.loadFXML(fxmlLoader);
            } else{
                //infoLabel.setText("E-mail ou mot de passe incorrect !");
                setError(usernameField);
                setError(passwordField);
            }
        }
    }

    private boolean validateField(TextInputControl field) {
        if (field.getText().isEmpty()) {
            setError(field);
            return false;
        } else {
            setSuccess(field);
            return true;
        }
    }

    private void setError(Control field) {
        field.setStyle("-fx-border-color: red;");
    }

    private void setSuccess(Control field) {
        field.setStyle("-fx-border-color: green;");
    }
}
