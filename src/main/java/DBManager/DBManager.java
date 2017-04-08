package DBManager;

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
        fields.add("\'" + user.getUid() + "\'");
        fields.add("" + user.getAuthenticationLevel() + "");
        fields.add("\'" + user.getUsername() + "\'");
        fields.add("\'" + user.getPassword() + "\'");
        fields.add("\'" + user.getEmail() + "\'");
        fields.add("\'" + user.getPhoneNumber() + "\'");
        fields.add("\'" + user.getFirstName() + "\'");
        fields.add("\'" + user.getMiddleInitial() + "\'");
        fields.add("\'" + user.getLastName() + "\'");
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
        fields.add("\'" + form.getTtbID() + "\'");
        fields.add("\'" + form.getRepID() + "\'");
        fields.add("\'" + form.getPermitNo() + "\'");
        fields.add("\'" + form.getSource() + "\'");
        fields.add("\'" + form.getSerialNo() + "\'");
        fields.add("\'" + form.getAlcoholType() + "\'");
        fields.add("\'" + form.getBrandName() + "\'");
        fields.add("\'" + form.getFancifulName() + "\'");
        fields.add("" + form.getAlcoholContent() + "");
        fields.add("\'" + form.getApplicantAddress() + "\'");
        fields.add("\'" + form.getMailingAddress() + "\'");
        fields.add("\'" + form.getFormula() + "\'");
        fields.add("\'" + form.getPhoneNo() + "\'");
        fields.add("\'" + form.getEmail() + "\'");
        fields.add("\'" + form.getLabelText() + "\'");
        fields.add("\'" + form.getLabelImage() + "\'");
        fields.add("\'" + form.getSubmitDate() + "\'");
        fields.add("\'" + form.getSignature() + "\'");
        fields.add("\'" + form.getApplicantName() + "\'");
        fields.add("\'" + form.getStatus() + "\'");
        fields.add("\'" + form.getAgentID() + "\'");
        fields.add("\'" + form.getApplicantID() + "\'");
        fields.add("\'" + form.getApprovedDate() + "\'");
        fields.add("\'" + form.getExpirationDate() + "\'");
        if(form.getAlcoholType().equals("Wine")) {
            wine.add("\'" + form.getVintageYear() + "\'");
            wine.add("\'" + form.getPhLevel() + "\'");
            wine.add("\'" + form.getGrapeVarietals() + "\'");
            wine.add("\'" + form.getWineAppelation() + "\'");
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

    /**
     * Updates a user into the database
     * @param user - user object to update
     * @return returns true if update is successful, false otherwise
     */
    public boolean updateUser(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("user_id = "+"\'" + user.getUid() + "\'");
        fields.add("authentication = "+ user.getAuthenticationLevel());
        fields.add("username ="+"\'" + user.getUsername() + "\'");
        fields.add("password = "+"\'" + user.getPassword() + "\'");
        fields.add("email = "+"\'" + user.getEmail() + "\'");
        fields.add("phone_no = "+"\'" + user.getPhoneNumber() + "\'");
        fields.add("first_name = "+"\'" + user.getFirstName() + "\'");
        fields.add("middle_inital = "+"\'" + user.getMiddleInitial() + "\'");
        fields.add("last_name = "+"\'" + user.getLastName() + "\'");
        String updateString = queryBuilder.createUpdateStatement("USER", fields, ("user_id = \'"+user.getUid() + "\'"));
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(updateString);
            stmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a form in the database
     * @param form - form to update
     * @return returns true if the update is successful, false otherwise
     */
    public boolean updateForm(Form form) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String options = "ttb_id=\'" + form.getTtbID() + "\'";
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> wine = new ArrayList<>();
        fields.add("rep_id=" + "\'" + form.getRepID() + "\'");
        fields.add("permit_no=" + "\'" + form.getPermitNo() + "\'");
        fields.add("source=" + "\'" + form.getSource() + "\'");
        fields.add("serial_no=" + "\'" + form.getSerialNo() + "\'");
        fields.add("alcohol_type=" + "\'" + form.getAlcoholType() + "\'");
        fields.add("brand_name=" + "\'" + form.getBrandName() + "\'");
        fields.add("fanciful_name=" + "\'" + form.getFancifulName() + "\'");
        fields.add("alcohol_content=" + form.getAlcoholContent());
        fields.add("applicant_address=" + "\'" + form.getApplicantAddress() + "\'");
        fields.add("mailing_address=" + "\'" + form.getMailingAddress() + "\'");
        fields.add("formula=" + "\'" + form.getFormula() + "\'");
        fields.add("phone_no=" + "\'" + form.getPhoneNo() + "\'");
        fields.add("email=" + "\'" + form.getEmail() + "\'");
        fields.add("label_text=" + "\'" + form.getLabelText() + "\'");
        fields.add("label_image=" + "\'" + form.getLabelImage() + "\'");
        fields.add("submit_date=" + "\'" + form.getSubmitDate() + "\'");
        fields.add("signature=" + "\'" + form.getSignature() + "\'");
        fields.add("applicant_name=" + "\'" + form.getApplicantName() + "\'");
        fields.add("status=" + "\'" + form.getStatus() + "\'");
        fields.add("agent_id=" + "\'" + form.getAgentID() + "\'");
        fields.add("applicant_id=" + "\'" + form.getApplicantID() + "\'");
        fields.add("approved_date=" + "\'" + form.getApprovedDate() + "\'");
        fields.add("expiration_date=" + "\'" + form.getExpirationDate() + "\'");
        if(form.getAlcoholType().equals("Wine")) {
            wine.add("vintage_year=" + "\'" + form.getVintageYear() + "\'");
            wine.add("ph_level=" + "\'" + form.getPhLevel() + "\'");
            wine.add("grape_varietals=" + "\'" + form.getGrapeVarietals() + "\'");
            wine.add("wine_appelation=" + "\'" + form.getWineAppelation() + "\'");
        }
        String queryString = queryBuilder.createUpdateStatement("FORM", fields, options);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(queryString);
            if(!wine.isEmpty()) {
                String wineString = queryBuilder.createUpdateStatement("WINEONLY", wine, "ttb_id=\'" + form.getTtbID() + "\'");
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
