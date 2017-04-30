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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Status: complete, needs review.
 * TODO: review this
 */
public class agentMainPageController extends UIController{
    /**
     * Redirects to applicationsForAgent.fxml
     * @throws IOException - throws exception
     */


    @FXML
    public void initialize(){
        super.init(main);
    }

    public slideshowController slideshow = new slideshowController();

    @FXML
    public HBox imagebox;
    @FXML
    private Button submissionButton, upgradeButton;


    /**
     * Redirects to applicationsForAgent.fxml
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToApplicationsForAgent() throws IOException {

        BorderPane pane = main.getBorderPane();
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        stage.setScene(pane.getScene());
        pane.setLeft(menuBarSingleton.getInstance().getApplicationsForAgentsPane());
        applicationsForAgentController controller = menuBarSingleton.getInstance().getApplicationsForAgentController();
        controller.displayResults();
        slideshow.stopAnimation();
        stage.show();
    }

    @FXML
    public void setDisplayToUserUpgradeForm() throws IOException{
        Stage stage;
        stage=(Stage) upgradeButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userUpgradeForm.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        userUpgradeFormController controller = loader.getController();
        controller.init(super.main);
        controller.updateUpgradeInfo();
    }
}


