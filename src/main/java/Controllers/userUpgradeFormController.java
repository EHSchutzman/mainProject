package Controllers;

import DBManager.DBManager;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jamescorse on 4/26/17.
 */
public class userUpgradeFormController extends UIController {
    @FXML
    public void initialize() {
        try {
            refreshToUserUpgradeForm();

        } catch (Exception e) {

        }
    }

    @FXML
    public Button addRequestButton, removeRequestButton, refreshButton;
    @FXML
    public Label requestedAuthentication, saName, statusLabel;

    private DBManager dbManager = new DBManager();

    @FXML
    public void setDisplayToNewUpgradeRequestForm() throws IOException {
        try {
            Stage stage = new Stage();
            stage.setTitle("New Upgrade Request Form");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("newUpgradeRequestForm.fxml"));
            AnchorPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 600, 400);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();

            newUpgradeRequestFormController controller = loader.getController();
            controller.init(super.main);
            controller.initializeRequestedLevel();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refreshToUserUpgradeForm() throws IOException {
        updateUpgradeInfo();
    }

    public void updateUpgradeInfo() {
        ArrayList<String> list = dbManager.findUpgradeRequest(menuBarSingleton.getInstance().getGlobalData().getUserInformation().getUid());
        if (list != null && list.size() == 3) {
            saName.setText(list.get(0));
            requestedAuthentication.setText(list.get(1));
            statusLabel.setText(list.get(2));
            addRequestButton.setDisable(true);
            removeRequestButton.setDisable(false);
        } else {
            addRequestButton.setDisable(false);
            removeRequestButton.setDisable(true);
            saName.setText("No Upgrade Request");
            statusLabel.setText("NA");
            requestedAuthentication.setText("No Upgrade Request");
        }
    }

    @FXML
    public void removeUpgradeRequest() {
        dbManager.dropUpgradeRequest(menuBarSingleton.getInstance().getGlobalData().getUserInformation().getUid());
        updateUpgradeInfo();
    }

}
