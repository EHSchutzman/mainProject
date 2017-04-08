package DBManager;

import DatabaseSearch.TTB_database;
import UserAccounts.User;
import Form.Form;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Leo on 4/8/2017.
 */
public class DBManager {

    /**
     * Persists a user into the database
     * @param user - user to persist
     * @return returns true if persist is successful, false otherwise
     */
    public boolean persistUser(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("'" + user.getUid() + "'");
        fields.add("" + user.getAuthenticationLevel() + "");
        fields.add("'" + user.getUsername() + "'");
        fields.add("'" + user.getPassword() + "'");
        fields.add("'" + user.getEmail() + "'");
        fields.add("'" + user.getPhoneNumber() + "'");
        fields.add("'" + user.getFirstName() + "'");
        fields.add("'" + user.getMiddleInitial() + "'");
        fields.add("'" + user.getLastName() + "'");
        String queryString = queryBuilder.createInsertStatement("USER", fields);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(queryString);
            stmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Persists a form into the database
     * @param form - form to persist
     * @return returns true if persist is successful, false otherwise
     */
    public boolean persistForm(Form form) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> wine = new ArrayList<>();
        fields.add("'" + form.getTTBID() + "'");
        fields.add("'" + form.getREPID() + "'");
        fields.add("'" + form.getPermitNo() + "'");
        fields.add("'" + form.getSource() + "'");
        fields.add("'" + form.getSerialNo() + "'");
        fields.add("'" + form.getAlcoholType() + "'");
        fields.add("'" + form.getBrandName() + "'");
        fields.add("'" + form.getFancifulName() + "'");
        fields.add("" + form.getAlcoholContent() + "");
        fields.add("'" + form.getApplicantAddress() + "'");
        fields.add("'" + form.getMailingAddress() + "'");
        fields.add("'" + form.getFormula() + "'");
        fields.add("'" + form.getPhoneNo() + "'");
        fields.add("'" + form.getEmail() + "'");
        fields.add("'" + form.getLabelText() + "'");
        fields.add("'" + form.getLabelImage() + "'");
        fields.add("'" + form.getSubmitDate() + "'");
        fields.add("'" + form.getSignature() + "'");
        fields.add("'" + form.getApplicantName() + "'");
        fields.add("'" + form.getStatus() + "'");
        fields.add("'" + form.getAgentID() + "'");
        fields.add("'" + form.getApplicantID() + "'");
        fields.add("'" + form.getApprovedDate() + "'");
        fields.add("'" + form.getExpirationDate() + "'");
        if(form.getAlcoholType().equals("Wine")) {
            wine.add("'" + form.getVintageYear() + "'");
            wine.add("'" + form.getPhLevel() + "'");
            wine.add("'" + form.getGrapeVarietals() + "'");
            wine.add("'" + form.getWineAppelation() + "'");
        }
        String queryString = queryBuilder.createInsertStatement("USER", fields);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(queryString);
            if(!wine.isEmpty()) {
                ArrayList<String> wineonly = new ArrayList<>();
                wineonly.add(form.getTTBID());
                wineonly.addAll(wine);
                String wineString = queryBuilder.createInsertStatement("WINEONLY", wineonly);
                stmt.executeUpdate(wineString);
            }
            stmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}