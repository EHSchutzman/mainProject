package DBManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Leo on 4/8/2017.
 */
class QueryBuilderTest {
    @org.junit.jupiter.api.Test
    void createInsertStatement() {
        QueryBuilder qb = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("col1=val1");
        fields.add("col2=val2");
        String qStr = qb.createInsertStatement("USER", fields);
        assertEquals(qStr, "insert into USER values(col1=val1,col2=val2);");
    }

    @org.junit.jupiter.api.Test
    void createUpdateStatement() {
    }

    @org.junit.jupiter.api.Test
    void createSelectStatement() {
    }

}