package Controllers;

import Initialization.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DanielKim on 4/13/2017.
 */
public class mainPageController extends UIController {
    @FXML
    private Button loginButton;
    @FXML
    private Button searchButton;
    @FXML
    private Hyperlink aboutLink;
    //@FXML
    //private Label currentUserLabel;

    @FXML
    public void setDisplayToLoginPage() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        loginPageController controller = loader.getController();
        controller.init(super.main);
    }

    @FXML
    public void setDisplayToSearchPage() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchResultsPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        searchResultsPageController controller = loader.getController();
        controller.init(super.main);
        controller.displayApplication(null); //set to null because there is nothing to show xD
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

    public void init(Main main) {
        super.init(main);
    }
}
