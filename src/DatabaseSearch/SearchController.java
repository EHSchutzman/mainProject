package DatabaseSearch;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

/**
 * The SearchController will read and store data from the UI search page and pass it to the query builder in order to form an SQL query.
 * Note: all attributes are stored as strings in the database except for IDs.
 */
public class SearchController {
    //create QueryBuilder variable to store search info
    QueryBuilder queryBuilder;

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

    //DATEFORMATTING
    DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/yyyy");

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
    private ComboBox cbLocationCode;

    //Function that reads the input entered into the search page and passes it to a QueryBuilder object.
    protected void searchCriteria(){
        //Set all variables equal to input date
        from = (dpDateRangeStart.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        to = (dpDateRangeEnd.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        brand = txtBrandName.getText();
        product = txtProductName.getText();
        typeFrom = txtClassRangeStart.getText();
        typeTo = txtClassRangeEnd.getText();
        origin = (String)cbLocationCode.getValue();
        //store search info in a new QueryBuilder object
        queryBuilder = new QueryBuilder(from, to, brand, product, typeFrom, typeTo, origin);
    }
}
