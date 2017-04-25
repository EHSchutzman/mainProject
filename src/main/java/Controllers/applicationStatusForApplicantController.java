package Controllers;

import AgentWorkflow.AgentRecord;
import DatabaseSearch.AppRecord;
import Form.Form;
import DBManager.DBManager;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by DanielKim on 4/16/2017.
 */
public class applicationStatusForApplicantController extends UIController{

    @FXML
    private Label errorLabel;
    @FXML
    private TableView resultsTable;

    private DBManager db = new DBManager();

    @FXML
    private void setDisplayToRevisionsMenu(Form form) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("revisionsMenu.fxml"));
            AnchorPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1500, 1000);
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            revisionsMenuController controller = loader.getController();
            controller.init(super.main);
            controller.createRevisionsMenu(form,main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initApplicationStatusTableView(){
        ObservableList<AgentRecord> olAR = FXCollections.observableArrayList();
        olAR = db.findForms(main.userData.getUserInformation());
        resultsTable.setItems(olAR);
        resultsTable.refresh();

        resultsTable.setRowFactory(tv -> {
            TableRow<AgentRecord> row = new TableRow<>();
            // Open window if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AgentRecord rowData = row.getItem();
                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");
                    // Get form form DB using selected row's ID
                    try {
                        Form viewForm = db.findSingleForm(rowData.getIDNo(), fieldList);
                        // Open selected form in new window
                        setDisplayToRevisionsMenu(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }
}
