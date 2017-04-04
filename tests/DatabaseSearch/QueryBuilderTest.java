package DatabaseSearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Chad on 4/3/2017.
 */
class QueryBuilderTest {

    QueryBuilder individualAppQuery = new QueryBuilder("APPLICATION", "*", "1");
    QueryBuilder searchAppQuery = new QueryBuilder("APPLICATION", "*", "01/01/2000", "01/01/2017",
            "Coldsnap", "Super Freeze", "000", "999", "MA");
    QueryBuilder userQuery = new QueryBuilder("USERS", "*", "testUser", "password");

    static String iaSQL = "SELECT * FROM APPLICATION WHERE APP_ID=1";
    static String saSQL = "SELECT * FROM APPLICATION WHERE DATE BETWEEN #01/01/2000# AND #01/01/2017#" +
            " AND BRAND_NAME='Coldsnap' AND PRODUCT_NAME='Super Freeze' AND TYPE BETWEEN 000 AND 999 AND ORIGIN_CODE='MA'";
    static String uSQL = "SELECT * FROM USERS WHERE LOGIN_NAME='testUser' AND PASSWORD='password'";

    @Test
    void getIndividualAppQueryTest() {
        assertEquals(individualAppQuery.getQuery(), iaSQL);
    }

    @Test
    void getSearchAppQueryTest() {
        assertEquals(searchAppQuery.getQuery(), saSQL);
    }

    @Test
    void getUserQueryTest() {
        assertEquals(userQuery.getQuery(), uSQL);
    }

}