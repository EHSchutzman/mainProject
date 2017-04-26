package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import Form.Form;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * Status: complete, needs review.
 * TODO: clean code, make sure there are no WARNINGS, add Doxygen comments
 */
public class searchResultsPageController extends UIController{

    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private TextField searchBox;
    @FXML
    private CheckBox maltBeverageCheckbox, wineCheckbox, distilledSpiritsCheckbox;

    private DBManager dbManager = new DBManager();
    private ObservableList<AppRecord> observableList;

    // Remove this later?
    //TODO Find out what people want for the goofy wacky and zany COLA search page
    /*public void displaySearchPage() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        searchPageController controller = loader.getController();
        controller.init(super.mainData);
    }*/

    /**
     * This function opens a pop up for the CSV options
     * @throws Exception - throws exception
     */
    @FXML
    public void displayCSVOptions() throws Exception {
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
            controller.init(super.mainData);
            //use passListOfForms to give the list of forms to the csv generator.
            controller.passListOfForms(observableList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function handles all the searching functionality
     * @return - returns an ObservableList of AppRecords to be displayed in the table view
     */
    @FXML
    ObservableList<AppRecord> getSearchResults() {
        try {
            //Set all variables equal to input data
            String search = searchBox.getText();
            boolean isMalt = maltBeverageCheckbox.isSelected();
            boolean isSpirit = distilledSpiritsCheckbox.isSelected();
            boolean isWine = wineCheckbox.isSelected();
            String params = " WHERE STATUS = 'Accepted' AND";
            if (isMalt || isSpirit || isWine) {
                params += " (ALCOHOL_TYPE = ";
                if (isWine) {params += "'Wine'";}
                if (isSpirit && !isWine) {params += "'Distilled Spirits'";}
                if (isSpirit && isWine){params += " OR ALCOHOL_TYPE = 'Distilled Spirits'";}
                if (isMalt && !(isWine || isSpirit)) {params += "'Malt Beverages'";}
                if (isMalt && (isWine || isSpirit)) {params += " OR ALCOHOL_TYPE = 'Malt Beverages'";}
                params += ")";
            }
            if (isMalt || isSpirit || isWine) {
                params += " AND (UPPER(BRAND_NAME) LIKE UPPER('%" + search +
                        "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + search + "%'))";
            } else {
                params += " (UPPER(BRAND_NAME) LIKE UPPER('%" + search +
                        "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + search + "%'))";
            }
            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();
            ObservableList<AppRecord> array = dbManager.findLabels(searchParams, params);
            resultsTable.setItems(array);
            resultsTable.refresh();
            observableList = array;
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            observableList = null;
            return null;
        }
    }

    /**
     * Function displays a new window for viewing an approved form, passed a form object from double click on row.
     * TODO Make sure that the function complies with new UI and things.
     * @param form - form to view
     */
     private void displayInspectApprovedLabel(Form form) {
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
            controller.init(super.mainData);
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
                        Form viewForm = dbManager.findSingleForm(rowData.getFormID(), fieldList);
                        // Open selected form in new window
                        displayInspectApprovedLabel(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

}






