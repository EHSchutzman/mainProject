package Controllers;

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
 * Created by James Corse on 4/24/17.
 */
public class agentMainPageController extends UIController{
    @FXML
    private Button submissionButton, upgradeButton;


    /**
     * Redirects to applicationsForAgent.fxml
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToApplicationsForAgent() throws IOException {
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicationsForAgent.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        applicationsForAgentController controller = loader.getController();
        controller.init(super.main);
        controller.displayResults();
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

