package Controllers;

import Initialization.Main;
import UserAccounts.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DanielKim on 4/16/2017.
 */
public class defaultUserMainPageController {
    @FXML
    private Button searchButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Hyperlink aboutLink;
    @FXML
    private Label currentUserLabel;

    //public void setCurrentUserLabel(String s){currentUserLabel.setText(s);}

    Main main = new Main();

    @FXML
    public void setDisplayToSearchPage() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setDisplayToAboutPage() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logoutAction() throws IOException{
        //TODO: Logout user first
        main.userData.setUserInformation(new User());
        Stage stage;
        stage=(Stage) logoutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
