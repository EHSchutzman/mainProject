package DatabaseSearch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

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

    // Database information
    private static String url = "Example";
    private static String user = "root";
    private static String pass = "root";
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
    private ComboBox<String> cbLocationCode;
    @FXML
    private TableView<ObservableList<String>> tableview;

    // Handle a search - effectively a "main" function for our program
    protected void handleSearch() {

        // Handle search criteria
        searchCriteria();

        // Set our query
        setQuery(getQueryBuilder().getQuery());

        // Query the DB
        rs = queryDB(getQuery());

        // Display our new data in the TableView
        displayData(rs);

    }

    // Connect to the DB
    protected Connection DBConnect() throws SQLException {
        return TTB_database.connect();
    }

    // Function will query the DB
    protected ResultSet queryDB(String query) {
        Connection c;
        Statement stmt;
        ResultSet rs = null;
        try {
            c = DBConnect();
            stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            stmt = null;
        }
        return rs;
    }

    // Function that reads the input entered into the search page and passes it to a QueryBuilder object.
    protected void searchCriteria(){
        //Set all variables equal to input date
        from = (dpDateRangeStart.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        to = (dpDateRangeEnd.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        brand = txtBrandName.getText();
        product = txtProductName.getText();
        typeFrom = txtClassRangeStart.getText();
        typeTo = txtClassRangeEnd.getText();
        origin = cbLocationCode.getValue();
        //store search info in a new QueryBuilder object
        setQueryBuilder(new QueryBuilder(from, to, brand, product, typeFrom, typeTo, origin));
    }

    // Display DB data into a TableView
    // http://blog.ngopal.com.np/2011/10/19/dyanmic-tableview-data-from-database/comment-page-1/
    protected void displayData(ResultSet rs) {

        // Auto-genericized?
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        try {
            // Add data to ObservableList
            while (rs.next()) {
                // Iterate row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    // Iterate column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            // Add to TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error building data!");
        }

    }

    // Displays individual application information when user selects an application from the TableView
    protected void displayApplication() {

        // Re-find our application in the DB or ResultSet???
        /**
         * Actually, we'll probably end up re-querying the database for a specific application
         * (using an application ID?) and pulling more information than we originally did.
         **/

        // Pull the information we need from that record

        // Display the information on a new page

    }

    // Save a CSV of the results locally
    protected void saveCSV() {

        try {
            generateCSV(rs);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error generating CSV!");
        }

    }

    // Generate a CSV file of the current ResultSet
    // http://stackoverflow.com/questions/22439776/how-to-convert-resultset-to-csv
    protected void generateCSV(ResultSet rs) throws SQLException, FileNotFoundException {

        // Initialize file
        PrintWriter csvWriter = new PrintWriter(new File("TTB_Search_Results.csv"));

        // Determine CSV size and headers
        ResultSetMetaData meta = rs.getMetaData();
        int numberOfColumns = meta.getColumnCount();
        String dataHeaders = "\"" + meta.getColumnName(1) + "\"";
        for (int i = 2; i < numberOfColumns + 1; i++) {
            dataHeaders += ",\"" + meta.getColumnName(i).replaceAll("\"", "\\\"") + "\"";
        }

        // Print headers to CSV
        csvWriter.println(dataHeaders);

        // Print data to CSV
        while (rs.next()) {
            String row = "\"" + rs.getString(1).replaceAll("\"", "\\\"") + "\"";
            for (int i = 2; i < numberOfColumns + 1; i++) {
                row += ",\"" + rs.getString(i).replaceAll("\"", "\\\"") + "\"";
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
}
