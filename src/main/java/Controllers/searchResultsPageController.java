package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import Form.Form;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Status: incomplete.
 * TODO: clean code, make sure there are no WARNINGS, rename Buttons, add Doxygen comments
 */
public class searchResultsPageController extends UIController{

    private DBManager db = new DBManager();
    protected String search;

    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private Button return_to_search;
    @FXML
    private Button generate_csv_button; //TODO: make sure this works! naming convention!
    @FXML
    private TextField search_box;
    @FXML
    private CheckBox malt_beverage_checkbox, wine_checkbox, distilled_spirit_checkbox;

    //@FXML
    //protected void handleInlineSearch() throws SQLException {searchInlineCriteria();}

    //TODO Find out what people want for the goofy wacky and zany COLA search page


    /**
     * This function opens a pop up for the CSV display
     * @throws Exception
     */
    @FXML
    public void displayCSVOptionsPage() throws Exception {
        try {
            Stage stage = new Stage();
            stage.setTitle("CSV Options");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("csvOptions.fxml"));
            AnchorPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 600, 400);
            scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            csvOptionsController controller = loader.getController();
            controller.init(super.main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function handles all the searching functionality
     * @return
     */

     private void displayApprovedLabel(Form form) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("inspectApprovedLabel.fxml"));
            AnchorPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1500, 1000);
            scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            inspectApprovedLabelController controller = loader.getController();
            controller.setForm(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Monitors user double click on results table
     */
    void initApplicationTableView() {
        resultsTable.setItems(null);
        resultsTable.setRowFactory(tv -> {
            TableRow<AppRecord> row = new TableRow<>();
            // Open window if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AppRecord rowData = row.getItem();
                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");
                    // Get form form DB using selected row's ID
                    try {
                        Form viewForm = db.findSingleForm(rowData.getFormID(), fieldList);
                        // Open selected form in new window
                        displayApprovedLabel(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

}






