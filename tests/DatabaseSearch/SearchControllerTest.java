package DatabaseSearch;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Leo on 4/4/2017.
 */
class SearchControllerTest {
    SearchController sc = new SearchController();
    ResultSet rs;

    @Test
    void DBConnect() {
    }

    @Test
    void queryDB() {
    }

    @Test
    void searchCriteriaSuccess() {
        assertEquals(true, sc.searchCriteria());
    }

    @Test
    void applicationSearchCriteriaSuccess() {
        assertEquals(true, sc.applicationSearchCriteria());
    }

    @Test
    void displayData() {
        assertEquals(true, sc.displayData(rs));
    }

    @Test
    void saveCSVSuccess() {
        assertEquals(true, sc.saveCSV());
    }

    @Test
    void getQueryBuilder() {
        assertEquals(null, sc.getQueryBuilder());
    }

    @Test
    void getQuery() {
        assertEquals("", sc.getQuery());
    }

}