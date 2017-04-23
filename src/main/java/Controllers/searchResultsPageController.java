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

    // TODO:Replace this with returnToMain???
    @FXML
    public void returnToSearch() throws IOException {
        Stage stage;
        stage = (Stage) return_to_search.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void returnToMainPage() throws IOException{
        super.returnToMainPage();
    }

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

    @FXML
    ObservableList<AppRecord> handleInlineSearch() {
        try {
            //Set all variables equal to input data
            search = search_box.getText();
            boolean isMalt = malt_beverage_checkbox.isSelected();
            boolean isSpirit = distilled_spirit_checkbox.isSelected();
            boolean isWine = wine_checkbox.isSelected();
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
            ObservableList<AppRecord> array = db.findLabels(searchParams, params);
            resultsTable.setItems(array);
            resultsTable.refresh();
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }

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






