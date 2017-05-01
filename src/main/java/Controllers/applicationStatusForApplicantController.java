package Controllers;

import AgentWorkflow.AgentRecord;
import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import Form.Form;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Status: complete, needs review
 */
public class applicationStatusForApplicantController extends UIController{

    @FXML
    private Label errorLabel;
    @FXML
    private TableView resultsTable;

    private DBManager db = new DBManager();
    private Form viewForm;
    private Stage primaryStage;

    @FXML
    private void setDisplayToRevisionsMenu(Form form) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("revisionsMenu.fxml"));
            AnchorPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1500, 1000);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            revisionsMenuController controller = loader.getController();
            controller.init(super.main);
            controller.createRevisionsMenu(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initApplicationStatusTableView() {
        ObservableList<AgentRecord> olAR;
        System.out.println(menuBarSingleton.getInstance().getGlobalData().getUserInformation().getEmail());
        olAR = db.findForms(menuBarSingleton.getInstance().getGlobalData().getUserInformation());
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
                        System.out.println("STATUS IS " + viewForm.getStatus());
                        if(!viewForm.getStatus().equalsIgnoreCase("Accepted")) {
                            System.out.println("before displaying incomplete app: " + viewForm.getrep_id());
                            displayIncompleteApplication(viewForm);
                        } else {
                            setDisplayToRevisionsMenu(viewForm);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    public void displayIncompleteApplication(Form application) throws Exception{
        try {

            Stage stage = new Stage();
            stage.setTitle("Incomplete Application");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("viewLabel.fxml"));
            ScrollPane newWindow = loader.load();
            //rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            //primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1010, 1000);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            stage.setScene(scene);
            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

            viewLabelController controller = loader.getController();
            controller.init(super.main);
            controller.setReviewForm(application);
            controller.createIncompleteForm();

        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
