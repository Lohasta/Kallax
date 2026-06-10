package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Utilisateur;

public class SkodocMain extends Application{
    /**The Scene used to display the App */
    private static Scene scene;

    private Utilisateur user;

    @Override
    public void start(Stage stage) throws Exception {
        user = new Utilisateur();

        // Initiate and add the application icon
        //Image icon = new Image(getClass().getResourceAsStream("assets/logoKallax.png"));
        //stage.getIcons().add(icon);

        // Load the FXML file and create the scene
        FXMLLoader fxmlLoader;
        if (user.getEstConnecte()) {
            fxmlLoader = getFXMLLoader("StudentScene");
        } else {
            fxmlLoader = getFXMLLoader("LoginScene");
        }
        scene = new Scene(fxmlLoader.load(), 1200, 600);

        // Add CSS
        //scene.getStylesheets().add(getClass().getResource("/assets/style/style.css").toExternalForm());

        stage.setTitle("SKoDoc");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Load the root parent and update the view
     * Inject studentList if needed
     * 
     * @param fxmlLoader a FXMLLoader object
     * @throws IOException
     */
    public static void loadFXML(FXMLLoader fxmlLoader) throws IOException {
        // load the root parent
        Parent root = fxmlLoader.load();
        // retrieve the controller
        Object controller = fxmlLoader.getController();
        setRoot(root);
    }

    /**
     * Update the root parent of the main scene
     * 
     * @param root
     */
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    /**
     * Create a new FXMLLoader object
     * 
     * @param fxml the name of the fxml file (represent the view)
     * @return FXMLLoader object
     */
    public static FXMLLoader getFXMLLoader(String fxml) {
        return new FXMLLoader(SkodocMain.class.getResource(fxml + ".fxml"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
