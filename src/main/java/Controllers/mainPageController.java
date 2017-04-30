package Controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Status: needs review.
 * TODO: change about link to button? - have a function setDisplayToAboutPage in UIController
 */
public class mainPageController extends UIController {

    @FXML
    private Button loginButton; // This is a different loginButton from UIController's? double check
    @FXML
    public HBox imagebox;
    @FXML
    private Hyperlink aboutLink;

    public slideshowController slideshow = new slideshowController();

    @FXML
    private void setDisplayToSearch() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        BorderPane root = super.main.getBorderPane();

        URL searchPageURL = getClass().getResource("searchPage.fxml");
        FXMLLoader loader = new FXMLLoader();
        ScrollPane pane = loader.load(searchPageURL);
        root.setTop(menuBarSingleton.getInstance().getBar());
        root.setBottom(pane);

        Scene scene = root.getScene();
        stage.setScene(root.getScene());
        stage.show();
        searchPageController controller = loader.getController();
        slideshow.stopAnimation();
    }


    /**
     * Redirects to loginPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToLoginPage() throws IOException{
        BorderPane borderPane = main.getBorderPane();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        AnchorPane anchorPane = loader.load();
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(menuBarSingleton.getInstance().getBar());
        borderPane.setBottom(anchorPane);
        slideshow.stopAnimation();
        stage.show();
        loginPageController controller = loader.getController();
        controller.setActionOnEnter();
        slideshow.stopAnimation();

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
