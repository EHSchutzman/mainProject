package AgentWorkflow;

import DBManager.DBManager;
import Form.Form;
import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.ResultSet;

/**
 * Created by Chad on 4/8/2017.
 */
public class WorkflowController {

    private Main main = new Main();
    private ObservableList<ObservableList<String>> resultsList = FXCollections.observableArrayList();
    private Form application = new Form();
    public DBManager db = new DBManager();

    // ----- FXML for Workflow results page -----
    @FXML
    private Button closeButton;

    @FXML
    public TableView<ObservableList<String>> resultsTable; // Holds 10 batch-pulled assignments for the Agent

    // ----- FXML for Workflow application page -----



    public WorkflowController() {
    }


    @FXML
    public void displayResults() {

        //@TODO: Get 10 batch assignments from the DB Manager
            // Query for batch
            // Display batch in table
        //resultsTable.setItems(resultsList);
        /*
        try {


            try {

                ObservableList<ObservableList<String>> appList = FXCollections.observableArrayList();
                while(searchResults.previous());
                while (searchResults.next()) {
                    ObservableList<String>  = new FXCollections.observableArrayLisr();
                    String formID = searchResults.getString("TTB_ID");
                    String permitNo = searchResults.getString("PERMIT_NUMBER");
                    String serialNo = searchResults.getString("SERIAL_NUMBER");
                    String completedDate = searchResults.getString("COMPLETED_DATE");
                    String fancifulName = searchResults.getString("FANCIFUL_NAME");
                    String brandName = searchResults.getString("BRAND_NAME");
                    String origin = searchResults.getString("ORIGIN_CODE");
                    String type = searchResults.getString("TYPE_ID");

                    row.setTypeID(formID);
                    row.setPermitNo(permitNo);
                    row.setSerialNo(serialNo);
                    row.setCompletedDate(completedDate);
                    row.setFancifulName(fancifulName);
                    row.setBrandName(brandName);
                    row.setOriginCode(origin);
                    row.setTypeID(type);

                    dataList.add(row);

                    System.out.println("Data "+ dataList);

                }
                System.out.println("Data "+ dataList);

                //FINALLY ADDED TO TableView
                main.displayWorkflowResults(dataList);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error building data!");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error displaying data.");
            return false;
        }


*/
    }

    public void displayResults(ObservableList<ObservableList<String>> rl) {

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

    public void setDisplay(Main main, ObservableList<ObservableList<String>> rl) {
        this.displayResults(rl);  // Display TableView Results
        this.main = main;
    }

}
