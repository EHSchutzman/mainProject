package DatabaseSearch;

//Maybe these to deal with date?
import java.util.Date;
import java.text.DateFormat;

/**
 * The SearchController will read and store data from the UI search page and pass it to the query builder in order to form an SQL query.
 */
public class SearchController {
    //Date info
    Date from;
    Date to;

    //Name info
    String brand;
    String product; //also known as fanciful name

    //Type info
    String typeFrom;
    String typeTo;

    //location code, also known as origin code
    String origin;

    protected void searchCriteria(){}
}
