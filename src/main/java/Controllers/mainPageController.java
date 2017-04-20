package Controllers;

import UserAccounts.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DanielKim on 4/13/2017.
 */
public class mainPageController extends UIController {
    @FXML
    private Button logInButton;
    @FXML
    private Button searchButton;
    @FXML
    private Hyperlink aboutLink;
    @FXML
    private Label currentUserLabel;

    @FXML
    public void setDisplayToLoginPage() throws IOException{
        Stage stage;
        stage=(Stage) logInButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setDisplayToSearchPage() throws IOException{
        Stage stage;
        Parent root;
        stage=(Stage) searchButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("searchPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setDisplayToAboutPage() throws IOException{
        Stage stage;
        Parent root;
        stage=(Stage) aboutLink.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("aboutPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //TODO: Set this using the Data class? Or keep sending a user object across pages to update currentUserLabel(s)
    @FXML
    public void initializeCurrentUserLabel(User user) {
        currentUserLabel.setText(user.getUsername());
    }
}
