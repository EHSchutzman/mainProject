package DatabaseSearch;

import AgentWorkflow.AgentRecord;
import DBManager.DBManager;
import Form.Form;
import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The SearchController will read and store data from the UI search page and pass it to the query builder in order to form an SQL query.
 * Note: all attributes are stored as strings in the database except for IDs.
 */
public class SearchController {

    private Main main = new Main();
    // Database information
    public DBManager db = new DBManager();
    private ResultSet apprs;
    private ResultSet rs;
    public ObservableList<AppRecord> aR = FXCollections.observableArrayList();

    private String query;
    //VARIABLES FOR SEARCH CRITERIA:
    //Date info
    protected String from;
    protected String to;
    //Name info
    protected String brand;
    protected String product; //also known as fanciful name
    //Type info
    protected boolean isMalt;
    protected boolean isSpirit;
    protected boolean isWine;

    //location code, also known as origin code
    protected String origin;
    //Application ID
    protected String appID;

    //VARIABLES FOR JAVAFX OBJECTS:
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
    private TextField txtAppID;


    @FXML
    // Handle a search - effectively a "main" function for our program
    protected void handleSearch() throws SQLException {

        System.out.println("Handles search!");

        // Handle search criteria
        System.out.println("in handle search" + searchCriteria());

        // Display our new data in the TableView
        displayData(searchCriteria());
        //displayApplication();

    }

    // Function that reads the input entered into the search page and passes it to DBManager
    protected ObservableList<AppRecord> searchCriteria() {
        try {
            //Set all variables equal to input data
            from = (dpDateRangeStart.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            to = (dpDateRangeEnd.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
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

    // Display DB data into a TableView
    protected void displayData(ObservableList<AppRecord> list) {

        try {
            main.displaySearchResultsPage(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //@TODO: Display application on click
    // Displays individual application information when user selects an application from the TableView (I don't think this will currently display any additional information, but it should work)
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
                        main.userData.setForm(viewForm);
                        main.displayApprovedLabel(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }


    // Save a CSV of the results locally
    @FXML
    protected boolean saveCSV() {

        try {
            generateCSV();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error generating CSV!");
            return false;
        }

    }

    // Generate a CSV file of the current ResultSet
    // http://stackoverflow.com/questions/22439776/how-to-convert-resultset-to-csv
    protected void generateCSV() throws SQLException, FileNotFoundException {

        // Initialize file
        PrintWriter csvWriter = new PrintWriter(new File("TTB_Search_Results.csv"));

        while(rs.previous());

        ResultSet searchResults = rs;

        // Determine CSV size and headers
        ResultSetMetaData meta = rs.getMetaData();
        int numberOfColumns = meta.getColumnCount();
        System.out.println(numberOfColumns);
        String dataHeaders = "\"" + meta.getColumnName(1) + "\"";
        for (int i = 2; i < numberOfColumns + 1; i++) {
            dataHeaders += ",\"" + meta.getColumnName(i).replaceAll("\"", "\\\"") + "\"";
        }

        // Print headers to CSV
        csvWriter.println(dataHeaders);

        // Print data to CSV
        while (searchResults.next()) {
            String row = "\"" + searchResults.getString(1).replaceAll("\"", "\\\"") + "\"";
            for (int i = 2; i < numberOfColumns + 1; i++) {
                row += ",\"" + searchResults.getString(i).replaceAll("\"", "\\\"") + "\"";
            }
            csvWriter.println(row);
        }
        csvWriter.close();

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setDisplay(Main main) {
        this.main = main;
    }
    public void setDisplay2(Main main, ObservableList<AppRecord> list) {
        this.main = main;
        displayApplication(list);
    }

    @FXML
    public void returnToMain(){
        try{
            if (main.userData.getUserInformation().getAuthenticationLevel() == 0) {
                main.setDisplayToDefaultMain();
            } else if (main.userData.getUserInformation().getAuthenticationLevel() == 1) {
                main.setDisplayToApplicantMain();
            } else if (main.userData.getUserInformation().getAuthenticationLevel() >= 2) {
                main.setDisplayToAgentMain();
            }
            else{
                main.setDisplayToMain();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
