package AgentWorkflow;

import DBManager.DBManager;
import Form.Form;
import Initialization.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Chad on 4/8/2017.
 */
public class WorkflowController {

    private Main main = new Main();
    private ObservableList<AgentRecord> resultsList = FXCollections.observableArrayList();
    private Form application = new Form();
    public DBManager db = new DBManager();

    // ----- FXML for Workflow results page -----
    @FXML
    private Button closeButton;

    @FXML
    public TableView<AgentRecord> resultsTable; // Holds 10 batch-pulled assignments for the Agent

    // ----- FXML for Workflow application page -----



    public WorkflowController() {
    }


    @FXML
    public void displayResults() {

        //@TODO: Get 10 batch assignments from the DB Manager
            // Query for batch
            // Display batch in table

        // This block monitors the user's interaction with the tableview,
        //  determining when they select a row
        resultsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (resultsTable.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = resultsTable.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    System.out.println("Selected Value" + val);

                    AgentRecord viewRecord = resultsTable.getSelectionModel().getSelectedItem();

                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");

                    //
                    try {
                        Form viewForm = db.findSingleForm(viewRecord.getIDNo(), fieldList);
                        // Open selected form in new window
                        main.displayWorkflowApplication(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //setDisplay(this.main, resultsList);

    }

    public void displayResults(ObservableList<AgentRecord> rl) {
        System.out.println("hi");
        // Display batch in table
        //resultsTable.setItems(rl);

    }


    @FXML
    public void displayApplication() {

        //@TODO: Display application in new window with accept/reject options
        // Query for individual application
        // Fill Form object with query information
        // Pass form to new window
        //main.displayWorkflowApplication(form);
        //createAgentForm(form);

    }

    @FXML
    public void acceptApplication() {
        // Most of this can probably be taken from FormController

        long currentDate = System.currentTimeMillis();
        long expirationDate = currentDate + 157784630000L;  // Expires in 5 years

        Date accepted = new Date(currentDate);
        Date expiration = new Date(expirationDate);

        application.setStatus("Accepted");
        application.setapproved_date(accepted);
        application.setexpiration_date(expiration);
        //application.setAgentID();

        // UPDATE query using DB Manager to change status field to 'Accepted'
        // Agent Name field, expiration date field
        db.updateForm(application);


        // Close application
        this.closeApplication();
    }

    @FXML
    public void rejectApplication() {
        // Most of this can probably be taken from FormController

        application.setStatus("Rejected");

        // UPDATE query using DB Manager to change status field to 'Rejected'
        // Agent Name field, expiration date field
        db.updateForm(application);

        // Close application
        this.closeApplication();
    }

    @FXML
    public void closeApplication() {

        // Close the window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

        //@TODO: Refresh the TableView's contents
            // Update our resultset???
        setDisplay(this.main, resultsList);
        //resultsTable.refresh();

    }

    @FXML
    public void returnToMainPage(){
        try{
                main.setDisplayToAgentMain();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setDisplay(Main main) {
        this.displayResults();  // Display TableView Results
        this.main = main;
    }

    public void setDisplay(Main main, ObservableList<AgentRecord> rl) {
        this.displayResults(rl);  // Display TableView Results
        this.main = main;
    }

}
