package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: needs review.
 * TODO: do all TODOs
 */
public class mainPageController extends UIController {

    @FXML
    private Button loginButton, searchButton;
    @FXML
    private Hyperlink aboutLink;

    /**
     * Redirects to loginPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToLoginPage() throws IOException{
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

    /**
     * Redirects to searchResultsPage.fxml TODO: make sure this is correct
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToSearchPage() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchResultsPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        searchResultsPageController controller = loader.getController();
        controller.init(super.main);
        controller.displayApplication();
    }

    /**
     * Redirects to aboutPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToAboutPage() throws IOException{
        Stage stage;
        Parent root;
        stage=(Stage) aboutLink.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("aboutPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
