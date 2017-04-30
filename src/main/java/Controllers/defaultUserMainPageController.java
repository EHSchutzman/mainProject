package Controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Status: incomplete.
 * TODO: many things
 */
public class defaultUserMainPageController extends UIController{
    public slideshowController slideshow = new slideshowController();

    @FXML
    public HBox imagebox;
    @FXML
    private Button searchButton, upgradeButton;
    @FXML
    private Hyperlink aboutLink;



    @FXML
    public void setDisplayToUserUpgradeForm() throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane newWindow = loader.load(getClass().getResource("userUpgradeForm.fxml"));
            Scene scene = new Scene(newWindow, 600, 400);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            aboutPageController controller = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
