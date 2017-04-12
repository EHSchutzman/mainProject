package AgentWorkflow;

import DBManager.DBManager;
import Form.Form;
import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Chad on 4/8/2017.
 */
public class WorkflowController {

    private Main main;
    private ObservableList<AgentRecord> resultsList = FXCollections.observableArrayList();
    private Form application = new Form();
    public DBManager db = new DBManager();

    // ----- FXML for Workflow results page ----- //
    @FXML
    private Button closeButton;

    @FXML
    public TableView<AgentRecord> resultsTable; // Holds 10 batch-pulled assignments for the Agent

    public WorkflowController() {
    }

    @FXML
    public void displayResults() {

        //@TODO: Get 10 batch assignments from the DB Manager
        ObservableList<AgentRecord> olAR = FXCollections.observableArrayList();
        System.out.println(main.userData.getUserInformation().getAuthenticationLevel());
        olAR = db.pullFormBatch(main.userData.getUserInformation());
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
                        Form viewForm = db.findSingleForm(rowData.getIDNo(), fieldList);
                        // Open selected form in new window
                        main.displayWorkflowApplication(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

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
    public void closeApplication() {

        // Close the window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

        //@TODO: Refresh the TableView's contents
            // Update our resultset???
        setDisplay2(this.main, resultsList);
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
        this.main = main;
        this.displayResults();  // Display TableView Results
    }

    public void setDisplay2(Main main, ObservableList<AgentRecord> rl) {
        this.main = main;
        this.displayResults(rl);  // Display TableView Results
    }

}
