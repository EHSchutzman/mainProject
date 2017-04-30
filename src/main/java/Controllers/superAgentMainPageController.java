package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: empty, needs work.
 * TODO: finish up functionality for this - need to redo superAgent functionality + UI pages
 */
public class superAgentMainPageController extends UIController{

    @FXML
    private Button reviewLabelButton, userSearchButton, createAgentButton, upgradeRequestsButton;

    @FXML
    public void displayReviewApplication() {} // Stub TODO: remove and replace with actual

    @FXML
    public void displayUserSearchPage() {} // Stub TODO: remove and replace with actual

    @FXML
    public void displayCreateAgentPage() {} // Stub TODO: remove and replace with actual

    @FXML
    public void setDisplayToUpgradeRequests() throws IOException {
        Stage stage;
        stage=(Stage) upgradeRequestsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentUpgradeRequests.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        superAgentUpgradeRequestsController controller = loader.getController();
        controller.init(super.main);
        controller.displayResults();
        controller.initTableView();
    }
}
