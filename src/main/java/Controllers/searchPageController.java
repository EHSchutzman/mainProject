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
    protected String fanciful; //also known as fanciful search
    //Type info
    protected boolean isMalt;
    protected boolean isSpirit;
    protected boolean isWine;

    //variables for storing location info
    protected String stateInfo;
    protected String countryInfo;

    //Create DBManager object to perform database operations
    private DBManager db = new DBManager();

    //Variables for JavaFX buttons
    @FXML
    private DatePicker dpDateRangeStart;
    @FXML
    private DatePicker dpDateRangeEnd;
    @FXML
    private TextField brandName;
    @FXML
    private TextField fancifulName;
    @FXML
    private CheckBox maltBeverageCheckbox;
    @FXML
    private CheckBox otherCheckbox;
    @FXML
    private CheckBox wineCheckbox;
    @FXML
    private TextField state;
    @FXML
    private TextField country;
    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private Button return_to_main_button;

    //Function that returns the user to the main page
    @FXML
    protected void returnToMain() throws IOException{
        Stage stage;
        //@TODO use function from a menu bar controller to access buttons
        stage=(Stage) return_to_main_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    // Handle a search - effectively a "main" function for our program
    protected void displayResults() throws IOException {
        // Display our new data in the TableView
        displayData(searchCriteria());
    }

    //Populates the results table with data from the database
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
            brand = brandName.getText();
            fanciful = fancifulName.getText();
            isMalt = maltBeverageCheckbox.isSelected();
            isSpirit = otherCheckbox.isSelected();
            isWine = wineCheckbox.isSelected();
            stateInfo = state.getText();
            countryInfo = country.getText();

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
            ArrayList<String> countryArray = new ArrayList<>();
            ArrayList<String> stateArray = new ArrayList<>();

            brandArray.add("BRAND_NAME");
            brandArray.add(brand);
            productArray.add("FANCIFUL_NAME");
            productArray.add(fanciful);
            countryArray.add("COUNTRY");
            countryArray.add(countryInfo);
            stateArray.add("STATE");
            stateArray.add(stateInfo);

            searchParams.add(brandArray);
            searchParams.add(productArray);
            searchParams.add(typeArray);
            searchParams.add(countryArray);
            searchParams.add(stateArray);

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }
}
