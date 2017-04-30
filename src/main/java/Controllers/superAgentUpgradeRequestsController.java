package Controllers;

import AgentWorkflow.AgentRecord;
import DBManager.DBManager;
import DatabaseSearch.UpgradeUserRecord;
import DatabaseSearch.UserRecord;
import Form.Form;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jamescorse on 4/28/17.
 */
public class superAgentUpgradeRequestsController extends UIController {
    @FXML
    public Button backButton;
    @FXML
    public TableView<UpgradeUserRecord> resultsTable;
    private DBManager dbManager = new DBManager();
    private ObservableList<AgentRecord> olAR = FXCollections.observableArrayList();

    public void displayResults() {
        resultsTable.setRowFactory(tv-> {
            TableRow<UpgradeUserRecord> row = new TableRow<>();
            row.setOnMouseClicked(event-> {
                if ((event.getClickCount()==2) && (!row.isEmpty())) {
                    UpgradeUserRecord record = row.getItem();
                    try {
                        String options = "user_id='" + record.getUserID() + "'";
                        User user = dbManager.findUser(options);
                        resultsTable.refresh();
                        displayUpgradeReview(user);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    public void initialize() {
        ObservableList<UpgradeUserRecord> userInfo = dbManager.findUpgradeUsers("");
        displayResults();
        resultsTable.setItems(userInfo);

    }

    public void displayUpgradeReview(User user) throws Exception{
        Stage stage = new Stage();
        stage.setTitle("Upgrade User Approval");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentUpgradeReview.fxml"));
        AnchorPane newWindow = loader.load();
        Scene scene = new Scene(newWindow,1000,500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.getScene().setRoot(newWindow);
        stage.show();
        superAgentUpgradeReviewController controller = loader.getController();
        controller.init(super.main);
        controller.setUser(user);
        controller.setUpgradeReview(user);
    }



}
