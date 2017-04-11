package DatabaseSearch;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import Initialization.Main;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.sql.rowset.CachedRowSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.format.DateTimeFormatter;

/**
 * The SearchController will read and store data from the UI search page and pass it to the query builder in order to form an SQL query.
 * Note: all attributes are stored as strings in the database except for IDs.
 */
public class SearchController {

    private Main main = new Main();
    // Database information
    private ResultSet apprs;
    private ResultSet rs;
    //create QueryBuilder variable to store search info
    private QueryBuilder queryBuilder;
    private String query;
    //VARIABLES FOR SEARCH CRITERIA:
    //Date info
    protected String from;
    protected String to;
    //Name info
    protected String brand;
    protected String product; //also known as fanciful name
    //Type info
    protected String typeFrom;
    protected String typeTo;
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
    private TextField txtBrandName;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtClassRangeStart;
    @FXML
    private TextField txtClassRangeEnd;
    @FXML
    private TextField cbLocationCode;
    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private TextField txtAppID;


    @FXML
    // Handle a search - effectively a "main" function for our program
    protected void handleSearch() throws SQLException {

        System.out.println("Handles search!");

        // Handle search criteria
        searchCriteria();

        System.out.println(getQueryBuilder().getQuery());

        // Set our query
        setQuery(getQueryBuilder().getQuery());

        // Query the DB
        queryDB(getQuery());

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

// Iterate through the data in the result set and display it.

        } catch(SQLException e){
            e.printStackTrace();
        }

        // Display our new data in the TableView
        displayData(rs);

    }

    //@TODO: Let the DB manager handle connection and querying
    // Connect to the DB
    protected Connection DBConnect() throws SQLException {
        return TTB_database.connect();
    }

    // Function will query the DB
    protected ResultSet queryDB(String query) {
        Connection c;
        Statement stmt;
        try {
            c = DBConnect();
            stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            System.out.println("Here");
        } catch (Exception e) {
            System.out.println("HERE");
            e.printStackTrace();
            stmt = null;
        }
        return rs;
    }

    // Function that reads the input entered into the search page and passes it to a QueryBuilder object.
    protected boolean searchCriteria(){
        try {
            //Set all variables equal to input data
            from = (dpDateRangeStart.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            to = (dpDateRangeEnd.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            brand = txtBrandName.getText();
            product = txtProductName.getText();
            typeFrom = txtClassRangeStart.getText();
            typeTo = txtClassRangeEnd.getText();
            origin = cbLocationCode.getText();
            //store search info in a new QueryBuilder object
//            setQueryBuilder(new QueryBuilder(tableName, "TTB_ID,PERMIT_NO,SERIAL_NUMBER,COMPLETED_DATE,FANCIFUL_NAME,BRAND_NAME,ORIGIN_CODE,TYPE_ID",
//                    from, to, brand, product, typeFrom, typeTo, origin)); //For iteration 1 data stored in beverages table
            setQueryBuilder(new QueryBuilder("BEVERAGE", "TTB_ID,PERMIT_NUMBER,SERIAL_NUMBER,COMPLETED_DATE,FANCIFUL_NAME,BRAND_NAME,ORIGIN_CODE,TYPE_ID",
                    from, to, brand, product, typeFrom, typeTo, origin));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return false;
        }
    }

    //Function that reads (currently) an app id entered into a text box and searches for a single application
    protected boolean applicationSearchCriteria(){
        try {
            //Set all variables equal to input data
            appID = txtAppID.getText(); //This is the wrong way to implement it, it should pull from the object clicked on, we'll see how to pull from a tableview when we integrate
            setQueryBuilder(new QueryBuilder("BEVERAGE", "*", appID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return false;
        }
    }

    // Display DB data into a TableView
    protected boolean displayData(ResultSet rs) {

        ResultSet searchResults = rs;

        try {

            try {

                ObservableList<AppRecord> dataList = FXCollections.observableArrayList();
                while(searchResults.previous());
                AppRecord row;
                while (searchResults.next()) {
                    row  = new AppRecord();
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

                main.displaySearchResultsPage(dataList);
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

    }

    // Displays individual application information when user selects an application from the TableView (I don't think this will currently display any additional information, but it should work)
    protected void displayApplication() {
        // Handle search criteria
        applicationSearchCriteria();

        // Set our query
        setQuery(getQueryBuilder().getQuery());

        // Query the DB
        apprs = queryDB(getQuery());

        // Display our new data in the TableView
        //displayData(apprs);
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

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
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
    @FXML
    public void returnToMain(){
        try{
            if(main.userData.getUserInformation().getAuthenticationLevel() == 0) {
                main.setDisplayToDefaultMain();
            }
            else if (main.userData.getUserInformation().getAuthenticationLevel() == 1){
                main.setDisplayToApplicantMain();
            }
            else if (main.userData.getUserInformation().getAuthenticationLevel()>= 2){
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
