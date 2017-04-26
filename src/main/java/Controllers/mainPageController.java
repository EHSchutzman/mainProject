package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Status: needs review.
 * TODO: change about link to button? - have a function setDisplayToAboutPage in UIController
 */
public class mainPageController extends UIController {

    @FXML
    private Button loginButton; // This is a different loginButton from UIController's? double check
    @FXML
    private Hyperlink aboutLink;


    /**
     * Redirects to loginPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToLoginPage() throws IOException{
        BorderPane borderPane = main.getBorderPane();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginPage.fxml"));
        //System.out.println(loader.getLocation().getPath());
        AnchorPane anchorPane = loader.load();
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(main.getMenuBar());
        borderPane.setBottom(anchorPane);
        stage.show();
        loginPageController controller = loader.getController();
        controller.init(super.main);
    }

    /**
     * Redirects to aboutPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToAboutPageTemp() throws IOException{
        Stage stage;
        Parent root;
        stage=(Stage) aboutLink.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("aboutPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
