package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import AgentWorkflow.AgentRecord;
import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import Form.*;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by James Corse on 4/25/17.
 */
public class applicationsForAgentController extends UIController{
    @FXML
    private TableView resultsTable;
    private ObservableList<AgentRecord> olAR = FXCollections.observableArrayList();
    private Form form = new Form();
    private DBManager dbManager = new DBManager();

    @FXML
    public void displayResults() {

        //@TODO: Get 10 batch assignments from the DB Manager
        System.out.println(main.userData.getUserInformation().getAuthenticationLevel());
        olAR = dbManager.pullFormBatch(main.userData.getUserInformation());
        System.out.println(olAR);
        // Query for batch
        // Display batch in table
        resultsTable.setItems(olAR);

        // This block monitors the user's interaction with the tableview,
        //  determining when they double-click a row
        resultsTable.setRowFactory(tv -> {
            TableRow<AgentRecord> row = new TableRow<>();

            // Open application if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AgentRecord rowData = row.getItem();

                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");

                    // Get form form DB using selected row's ID
                    try {
                        Form viewForm = dbManager.findSingleForm(rowData.getIDNo(), fieldList);
                        olAR.remove(rowData);
                        resultsTable.refresh();
                        // Open selected form in new window
                        displayWorkflowApplication(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }

    // Currently attempting to open in a new page
    public void displayWorkflowApplication(Form application) throws Exception{
        try {

            Stage stage = new Stage();
            stage.setTitle("Workflow Results");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("agentApplicationReview.fxml"));
            AnchorPane newWindow = loader.load();
            //rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            //primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1500, 1000);

            stage.setScene(scene);
            stage.getScene().getStylesheets().add(getClass().getResource("agentApplicationReview.css").toExternalForm());
            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

            agentApplicationReviewController controller = loader.getController();
            controller.init(super.main);
            controller.setReviewForm(application);
            controller.setLabels();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
