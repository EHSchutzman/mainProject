package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


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

    private Button agentReviewLabelButton;

    /**
     * Redirects to applicationsForAgent.fxml
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToApplicationsForAgent() throws IOException {
        BorderPane pane = main.getBorderPane();
        Stage stage;
        stage = (Stage) submissionButton.getScene().getWindow();
        stage.setScene(pane.getScene());
        pane.setLeft(menuBarSingleton.getInstance().getApplicationsForAgentsPane());
        applicationsForAgentController controller = menuBarSingleton.getInstance().getApplicationsForAgentController();
        menuBarSingleton.getInstance().getMenuBarController().menuTitle.setText("Review Pending Applications");
        System.out.println(controller);
        controller.initApplicationTableView();
        controller.displayResults();
        slideshow.stopAnimation();
        stage.show();
        menuBarSingleton.getInstance().getMenuBarController().setOnSearchPage(true);
        menuBarSingleton.getInstance().getMenuBarController().setSearchType(3);
    }

    @FXML
    public void setDisplayToUserUpgradeForm() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane newWindow = loader.load(getClass().getResource("userUpgradeForm.fxml"));
        Scene scene = new Scene(newWindow, 600, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.getScene().setRoot(newWindow);
        stage.show();
        userUpgradeFormController controller = loader.getController();
    }
}
