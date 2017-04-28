package Controllers;

import UserAccounts.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Ethan on 4/26/2017.
 */
public class menuBarController extends UIController {

    //Variables to hold the appropriate FXML buttons and fields
    @FXML
    private Button backButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button aboutButton;
    @FXML
    private TextField searchBar;

    @FXML
    private void setDisplayToLoginPage() throws IOException {
        String loginStatus = loginButton.getText();
        if (loginStatus.equals("LOGIN")) {
            Stage stage;
            BorderPane root = main.getBorderPane();
            stage = (Stage) loginButton.getScene().getWindow();
            URL loginPageURL = getClass().getResource("loginPage.fxml");
            FXMLLoader loader = new FXMLLoader();
            AnchorPane pane = loader.load(loginPageURL);
            root.setTop(main.getMenuBar());
            root.setBottom(pane);
            Scene scene = root.getScene();
            stage.setScene(scene);
            stage.show();
            loginPageController controller = loader.getController();
            loginButton.setText("LOGOUT");
        } else {
            main.userData.setUserInformation(new User());
            Stage stage;
            BorderPane root = main.getBorderPane();
            stage=(Stage) loginButton.getScene().getWindow();
            URL mainPageURL = getClass().getResource("mainPage.fxml");
            FXMLLoader loader = new FXMLLoader();
            AnchorPane pane = loader.load(mainPageURL);
            root.setTop(main.getMenuBar());
            root.setBottom(pane);
            Scene scene = root.getScene();
            stage.setScene(scene);
            stage.show();
            mainPageController controller = loader.getController();
            loginButton.setText("LOGIN");
        }
    }


    @FXML
    protected void setDisplayToAboutPage() throws IOException {
        Stage stage;
        BorderPane root = main.getBorderPane();
        stage = (Stage) aboutButton.getScene().getWindow();
        URL aboutPageURL = getClass().getResource("aboutPage.fxml");
        FXMLLoader loader = new FXMLLoader();
        AnchorPane pane = loader.load(aboutPageURL);
        root.setTop(main.getMenuBar());
        root.setBottom(pane);
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();
        loginPageController controller = loader.getController();
    }
}
