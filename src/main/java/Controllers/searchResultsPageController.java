package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import DatabaseSearch.SearchController;
import Form.Form;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Anthony on 4/20/2017. ayyyy lmao xD
 */
public class searchResultsPageController extends UIController{

    /*
    Search Variables
     */
    public DBManager db = new DBManager();
    protected String name;
    private boolean isMalt, isSpirit, isWine;





    private ResultSet rs;






    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private Button return_to_search;
    @FXML
    private Button generate_csv_button;
    @FXML
    private TextField search_box;
    @FXML
    private CheckBox malt_beverage_checkbox, wine_checkbox, distilled_spirit_checkbox;






    /*
    This is a function
    It searches the database for what you want on click
     */
    @FXML
    protected void handleInlineSearch() throws SQLException {searchInlineCriteria();}

    @FXML
    protected void returnToSearch() throws IOException {
        Stage stage;
        stage = (Stage) return_to_search.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void displayCSVOptionsPage() throws Exception {
        try {
            Stage stage = new Stage();
            stage.setTitle("CSV Options");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("csvOptions.fxml"));
            AnchorPane newWindow = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 600, 400);
            scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());

            stage.setScene(scene);

            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();
            csvOptionsController controller = loader.getController();
            controller.init(super.main);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected ObservableList<AppRecord> searchInlineCriteria() {
        try {
            //Set all variables equal to input data
            name = search_box.getText();
            isMalt = malt_beverage_checkbox.isSelected();
            isSpirit = distilled_spirit_checkbox.isSelected();
            isWine = wine_checkbox.isSelected();

            boolean firstCheck = false;

            String params = " WHERE STATUS = 'Accepted' AND";

            if (isMalt || isSpirit || isWine) {

                params += " (ALCOHOL_TYPE = ";

                if (isWine) {
                    params += "'Wine'";
                    firstCheck = true;
                }

                if (isSpirit && !firstCheck) {
                    params += "'Distilled Spirits'";
                    firstCheck = true;
                } else if (isSpirit && firstCheck) {
                    params += " OR ALCOHOL_TYPE = 'Distilled Spirits'";
                }

                if (isMalt && !firstCheck) {
                    params += "'Malt Beverages'";
                    firstCheck = true;
                } else if (isMalt && firstCheck) {
                    params += " OR ALCOHOL_TYPE = 'Malt Beverages'";
                }
                params += ")";
            }

            if (firstCheck) {
                params += " AND (UPPER(BRAND_NAME) LIKE UPPER('%" + name + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + name + "%'))";
            } else {
                params += " (UPPER(BRAND_NAME) LIKE UPPER('%" + name + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + name + "%'))";
            }

            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);

            resultsTable.setItems(arr);
            resultsTable.refresh();

            super.main.userData.setObservableList(arr);

            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;

        }
    }

    protected void displayApplication(ObservableList<AppRecord> list) {

        // This block monitors the user's interaction with the tableview,
        //  determining when they double-click a row
        resultsTable.setItems(list);
        resultsTable.setRowFactory(tv -> {
            TableRow<AppRecord> row = new TableRow<>();

            // Open application if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AppRecord rowData = row.getItem();

                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");

                    // Get form form DB using selected row's ID
                    try {
                        Form viewForm = db.findSingleForm(rowData.getFormID(), fieldList);
                        // Open selected form in new window
                        super.main.userData.setForm(viewForm);
                        super.main.displayApprovedLabel(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

}






