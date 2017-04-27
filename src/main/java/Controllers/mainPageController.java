package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
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

    @FXML
    private void setDisplayToSearch() throws IOException {
        Stage stage;
        stage = (Stage) searchButton.getScene().getWindow();
        BorderPane root = super.main.getBorderPane();

        URL searchPageURL = getClass().getResource("searchPage.fxml");
        FXMLLoader loader = new FXMLLoader();
        ScrollPane pane = loader.load(searchPageURL);
        root.setTop(main.getMenuBar());
        root.setBottom(pane);

        Scene scene = root.getScene();
        stage.setScene(root.getScene());
        stage.show();
        searchPageController controller = loader.getController();
        controller.init(super.main);

        System.out.println("FUCK ME USE THIS!");
    }


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
