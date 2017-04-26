package Controllers;

import AgentWorkflow.AgentRecord;
import DBManager.DBManager;
import Form.Form;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Status: complete, needs review
 */
public class applicationStatusForApplicantController extends UIController{

    @FXML
    private Label errorLabel;
    @FXML
    private TableView resultsTable;

    private DBManager dbManager = new DBManager();

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
            controller.init(super.mainData);
            controller.createRevisionsMenu(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initApplicationStatusTableView(){
        ObservableList<AgentRecord> olAR;
        System.out.println(mainData.userData.getUserInformation().getEmail());
        olAR = dbManager.findForms(mainData.userData.getUserInformation());
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
                        Form viewForm = dbManager.findSingleForm(rowData.getIDNo(), fieldList);
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
