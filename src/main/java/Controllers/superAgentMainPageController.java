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
 * Status: empty, needs work.
 * TODO: finish up functionality for this - need to redo superAgent functionality + UI pages
 * TODO: is this what we really want?
 */
public class superAgentMainPageController extends UIController{
    public slideshowController slideshow = new slideshowController();

    @FXML
    public HBox imagebox;

    @FXML
    public void initialize(){
        super.init(main);
    }

    @FXML
    private Button reviewLabelButton, userSearchButton, createAgentButton;

    @FXML
    public void setDisplayToSuperAgentSearchPending() throws IOException {
        BorderPane pane = main.getBorderPane();
        Stage stage;

        stage=(Stage) reviewLabelButton.getScene().getWindow();



        stage.setScene(pane.getScene());
        pane.setCenter(menuBarSingleton.getInstance().getSuperAgentSearchPendingPane());
        slideshow.stopAnimation();
        stage.show();
        superAgentSearchPendingController controller = menuBarSingleton.getInstance().getSuperAgentSearchPendingController();


    }

    @FXML
    public void setDisplayToSuperAgentSearchUsers() throws IOException {
        Stage stage;
        stage=(Stage) userSearchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentSearchUsers.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        slideshow.stopAnimation();
        stage.show();
        superAgentSearchUsersController controller = loader.getController();
        controller.init(super.main);
        controller.initUserAuthenticationChoiceBox();
        controller.displayResults();
    }

    // TODO: change to createUser page? is this what we really want?
    @FXML
    public void setDisplayToSuperAgentCreateAgent() throws IOException {
        Stage stage;
        stage=(Stage) createAgentButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentCreateAgent.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        slideshow.stopAnimation();
        stage.show();
        superAgentCreateAgentController controller = loader.getController();
        controller.init(super.main);
    }

}