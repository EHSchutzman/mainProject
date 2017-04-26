package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: complete, needs review.
 * TODO: review this
 */
public class agentMainPageController extends UIController{

    @FXML
    private Button submissionButton;

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

}

