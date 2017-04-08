package DBManager;

import java.util.ArrayList;

/**
 * Created by DanielKim on 4/8/2017.
 */
public class QueryBuilder {

    /**
     * Creates an insert statement
     * @param tablename - name of the table to be inserted into
     * @param fields - values of the attributes of the table to be inserted into
     * @return returns the query string
     */
    protected String createInsertStatement(String tablename, ArrayList<String> fields) {
        // Query string
        String query = "";
        // Put the table name into the query string
        query.concat("insert into " + tablename + " values(");
        // Put the fields into the query string
        for(int i = 0; i < fields.size(); i++) {
            if(i == fields.size()-1) {
                query.concat(fields.get(i) + ");");
            } else {
                query.concat(fields.get(i) + ",");
            }
        }
        return query;
    }

    /**
     * Creates an update statement
     * @param tablename - name of the table to update in
     * @param fields - name of fields to update
     * @param options - options to specify which fields to update
     * @return returns the query string
     */
    protected String createUpdateStatement(String tablename, ArrayList<String> fields, String options) {
        // Query string
        String query = "";
        // Put the table name in the query string
        query.concat("update " + tablename + " set ");
        // Put fields into query string
        for(int i = 0; i < fields.size(); i++) {
            if(i == fields.size()-1) {
                query.concat(fields.get(i));
            } else {
                query.concat(fields.get(i) + ",");
            }
        }
        // Put options into query string
        if(!options.isEmpty() && options != null) {
           query.concat(" where " + options + ";");
        } else {
            query.concat(";");
        }
        return query;
    }

    /**
     * Creates a select statement
     * @param tablename - names of the tables to be selected from
     * @param fields - fields to be selected
     * @param options - options for selection
     * @return returns the query string
     */
    protected String createSelectStatement(String tablename, String fields, String options) {
        // Query string
        String query = "";
        // Put the fields and table name in the query string
        query.concat("select " + fields + " from " + tablename);
        // Put options into query string
        if(!options.isEmpty() && options != null) {
           query.concat(options + ";");
        } else {
            query.concat(";");
        }
        return query;
    }
}
