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
import javafx.event.ActionEvent;


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
        loginButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()));
        loginButton.setOnAction((ActionEvent e) -> {
            try{
                connect();
            }catch(Exception ex){
                ex.printStackTrace();
            }}
        );
    }

    @FXML
    private void connect() throws IOException, SQLException {
        String name = usernameField.getText();
        String password = passwordField.getText();

        boolean valid = true;

        if(valid && validateField(usernameField) && validateField(passwordField)){
            UtilisateurDAO uDAO = new UtilisateurDAO();
            Connection con = uDAO.isConnected();
            
            if (con != null) {
                Utilisateur user = uDAO.findByID(name);
                
                if (user != null && password.equals(user.getMdp())) {
                    System.out.println("Connexion réussie pour : " + user.getEmail());
                    
                    model.MailUser.setEmail(name);

                    String sceneName = "LoginScene";
                    
                    FXMLLoader fxmlLoader = null;

                    if (user instanceof Enseignant) {
                        sceneName = "EnseignantScene";
                        fxmlLoader = view.SkodocMain.getFXMLLoader(sceneName);
                    } else if(user instanceof Etudiant){
                        sceneName = "StudentScene";
                        fxmlLoader = view.SkodocMain.getFXMLLoader(sceneName);
                    }
                   
                    view.SkodocMain.loadFXML(fxmlLoader);
                    
                } else {
                    setError(usernameField);
                    setError(passwordField);
                }
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
