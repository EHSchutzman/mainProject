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
 * Status: needs work.
 * TODO: this is currently not used anywhere. May need to move this to searchResultsPageController
 * TODO: - once advanced options are created in UI
 */
public class searchPageController extends UIController{

    //VARIABLES FOR SEARCH CRITERIA:
    //Search bar info
    protected String searchBarContent;
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
    private TextField searchBar;
    @FXML
    private DatePicker dpDateRangeStart;
    @FXML
    private DatePicker dpDateRangeEnd;
    @FXML
    private TextField brandName;
    @FXML
    private TextField fancifulName;
    @FXML
    private CheckBox simpleMaltBeverageCheckbox;
    @FXML
    private CheckBox simpleOtherCheckbox;
    @FXML
    private CheckBox simpleWineCheckbox;
    @FXML
    private CheckBox advMaltBeverageCheckbox;
    @FXML
    private CheckBox advOtherCheckbox;
    @FXML
    private CheckBox advWineCheckbox;
    @FXML
    private TextField state;
    @FXML
    private TextField country;
    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private Button return_to_main_button;

    //Function that returns the user to the mainData page
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
    // Handle a search - effectively a "mainData" function for our program
    protected void displayResults() throws IOException {
        // Display our new data in the TableView
        //displayData(searchCriteria());
    }

    //Populates the results table with data from the database
    protected void displayData(ObservableList<AppRecord> list) {
        try {
            resultsTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    ObservableList<AppRecord> handleInlineSearch() {
        try {
            //Set all variables equal to input data
            searchBarContent = searchBar.getText();
            boolean isMalt = advMaltBeverageCheckbox.isSelected();
            boolean isSpirit = advOtherCheckbox.isSelected();
            boolean isWine = advWineCheckbox.isSelected();
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
                params += " AND (UPPER(BRAND_NAME) LIKE UPPER('%" + searchBarContent +
                        "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + searchBarContent + "%'))";
            } else {
                params += " (UPPER(BRAND_NAME) LIKE UPPER('%" + searchBarContent +
                        "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + searchBarContent + "%'))";
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

    protected ObservableList<AppRecord> simpleSearch() {
        try {
            //Set all variables equal to input data
            brand = brandName.getText();
            fanciful = fancifulName.getText();
            isMalt = simpleMaltBeverageCheckbox.isSelected();
            isSpirit = simpleOtherCheckbox.isSelected();
            isWine = simpleWineCheckbox.isSelected();

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
