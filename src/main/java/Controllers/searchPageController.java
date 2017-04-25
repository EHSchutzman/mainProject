package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by Anthony on 4/20/2017.
 */
public class searchPageController {

    //VARIABLES FOR SEARCH CRITERIA:
    //Date info
    protected String from;
    protected String to;
    //Name info
    protected String brand;
    protected String product; //also known as fanciful search
    //Type info
    protected boolean isMalt;
    protected boolean isSpirit;
    protected boolean isWine;

    //location code, also known as origin code
    protected String origin;

    private DBManager db = new DBManager();

    @FXML
    private DatePicker dpDateRangeStart;
    @FXML
    private DatePicker dpDateRangeEnd;
    @FXML
    private TextField brand_name_text;
    @FXML
    private TextField product_name_text;
    @FXML
    private CheckBox malt_beverage_checkbox;
    @FXML
    private CheckBox distilled_spirit_checkbox;
    @FXML
    private CheckBox wine_checkbox;
    @FXML
    private TextField state_text;
    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private Button search_button;
    @FXML
    private Button return_to_main_button;

    @FXML
    protected void returnToMain() throws IOException{
        Stage stage;
        stage=(Stage) return_to_main_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    // Handle a search - effectively a "main" function for our program
    protected void displaySearchResultsPage() throws IOException {
        Stage stage;
        stage=(Stage) search_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchResultsPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        // Display our new data in the TableView
        displayData(searchCriteria());
    }

    protected void displayData(ObservableList<AppRecord> list) {
        try {
            resultsTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected ObservableList<AppRecord> searchCriteria() {
        try {
            //Set all variables equal to input data
            from = (dpDateRangeStart.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            to = (dpDateRangeEnd.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            brand = brand_name_text.getText();
            product = product_name_text.getText();
            isMalt = malt_beverage_checkbox.isSelected();
            isSpirit = distilled_spirit_checkbox.isSelected();
            isWine = wine_checkbox.isSelected();
            origin = state_text.getText();

            String params = "APPROVED_DATE BETWEEN '" + from + "' AND '" + to + "'";

            boolean firstCheck = false;

            if (isMalt || isSpirit || isWine) {

                params += " AND ALCOHOL_TYPE = ";
            }
            if (isWine) {
                params += "'Wine'";
                firstCheck = true;
            }
            if (isSpirit && !firstCheck) {
                params += "'Distilled Spirit'";
                firstCheck = true;
            } else if (isSpirit && firstCheck) {
                params += " OR ALCOHOL_TYPE = 'Distilled Spirit'";
            }

            if (isMalt && !firstCheck) {
                params += "'Malted Beverages'";
                firstCheck = true;
            } else if (isMalt && firstCheck) {
                params += " OR ALCOHOL_TYPE = 'Malted Beverages'";
            }

            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();
            ArrayList<String> brandArray = new ArrayList<>();
            ArrayList<String> productArray = new ArrayList<>();
            ArrayList<String> typeArray = new ArrayList<>();
            ArrayList<String> originArray = new ArrayList<>();

            brandArray.add("BRAND_NAME");
            brandArray.add(brand);
            productArray.add("FANCIFUL_NAME");
            productArray.add(product);
            originArray.add("SOURCE");
            originArray.add(origin);

            searchParams.add(brandArray);
            searchParams.add(productArray);
            searchParams.add(typeArray);
            searchParams.add(originArray);

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }
}
