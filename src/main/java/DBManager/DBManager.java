package DBManager;

import Form.Form;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Leo on 4/8/2017.
 */
public class DBManager {

    //INSERT FUNCTIONS:

    /**
     * Persists a user into the database
     *
     * @param user - user to persist
     * @return returns true if persist is successful, false otherwise
     */
    public boolean persistUser(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("\'" + user.getUid() + "\'");
        fields.add("" + user.getAuthentication() + "");
        fields.add("\'" + user.getUsername() + "\'");
        fields.add("\'" + user.getPassword() + "\'");
        fields.add("\'" + user.getEmail() + "\'");
        fields.add("\'" + user.getPhoneNo() + "\'");
        fields.add("\'" + user.getFirstName() + "\'");
        fields.add("\'" + user.getMiddleInitial() + "\'");
        fields.add("\'" + user.getLastName() + "\'");
        String queryString = queryBuilder.createInsertStatement("USERS", fields);
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
     *
     * @param form - form to persist
     * @return returns true if persist is successful, false otherwise
     */
    public boolean persistForm(Form form) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> wine = new ArrayList<>();
        fields.add("\'" + form.getttb_id() + "\'");
        fields.add("\'" + form.getrep_id() + "\'");
        fields.add("\'" + form.getpermit_no() + "\'");
        fields.add("\'" + form.getSource() + "\'");
        fields.add("\'" + form.getserial_no() + "\'");
        fields.add("\'" + form.getalcohol_type() + "\'");
        fields.add("\'" + form.getbrand_name() + "\'");
        fields.add("\'" + form.getfanciful_name() + "\'");
        fields.add("" + form.getalcohol_content() + "");
        fields.add("\'" + form.getapplicant_street() + "\'");
        fields.add("\'" + form.getapplicant_city() + "\'");
        fields.add("\'" + form.getapplicant_zip() + "\'");
        fields.add("\'" + form.getapplicant_state() + "\'");
        fields.add("\'" + form.getapplicant_country() + "\'");
        fields.add("\'" + form.getmailing_address() + "\'");
        fields.add("\'" + form.getFormula() + "\'");
        fields.add("\'" + form.getphone_no() + "\'");
        fields.add("\'" + form.getEmail() + "\'");
        fields.add("\'" + form.getlabel_text() + "\'");
        fields.add("\'" + form.getlabel_image() + "\'");
        fields.add("\'" + form.getsubmit_date() + "\'");
        fields.add("\'" + form.getSignature() + "\'");
        fields.add("\'" + form.getStatus() + "\'");
        fields.add("\'" + form.getagent_id() + "\'");
        fields.add("\'" + form.getapplicant_id() + "\'");
        fields.add("\'" + form.getapproved_date() + "\'");
        fields.add("\'" + form.getexpiration_date() + "\'");
        fields.add("\'" + form.getapproval_comments() + "\'");
        if (form.getalcohol_type().equals("Wine")) {
            wine.add("\'" + form.getvintage_year() + "\'");
            wine.add("\'" + form.getpH_level() + "\'");
            wine.add("\'" + form.getgrape_varietals() + "\'");
            wine.add("\'" + form.getwine_appellation() + "\'");
        }
        String queryString = queryBuilder.createInsertStatement("FORMS", fields);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(queryString);
            if (!wine.isEmpty()) {
                ArrayList<String> wineonly = new ArrayList<>();
                wineonly.add(form.getttb_id());
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

    //SELECT FUNCTIONS:

    public ObservableList<ObservableList<String>> findLabels(ArrayList<ArrayList<String>> filters) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String fields = "ttb_id, permit_no, serial_no, approved_date, fanciful_name, brand_name, alcohol_type";
        String query = queryBuilder.createLikeStatement("FORMS", fields, filters);
        ObservableList<ObservableList<String>> ol = FXCollections.observableArrayList();
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                String ttbID = rs.getString("ttb_id");
                String permitNo = rs.getString("permit_no");
                String serialNo = rs.getString("serial_no");
                String approvedDate = rs.getDate("approved_date").toString();
                String fancifulName = rs.getString("fanciful_name");
                String brandName = rs.getString("brand_name");
                String alcoholType = rs.getString("alcohol_type");
                ObservableList<String> observableList = FXCollections.observableArrayList();
                observableList.addAll(ttbID, permitNo, serialNo, approvedDate, fancifulName, brandName, alcoholType);
                ol.add(observableList);
            }
            rs.close();
            stmt.close();
            connection.close();
            return ol;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ObservableList<ObservableList<String>> findForms(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = "";
        ObservableList<ObservableList<String>> ol = FXCollections.observableArrayList();
        //I think that appending these tables is going to get rid of the beer applications but ????
        if (user.getAuthentication() == 1) {
            query = queryBuilder.createSelectStatement("FORMS, WINEONLY", "*", ("applicant_id=" + user.getUid() + "', FORMS.ttb_id = WINEONLY.ttb_id"));
        } else if (user.getAuthentication() == 2 || user.getAuthentication() == 3) {
            query = queryBuilder.createSelectStatement("FORMS, WINEONLY", "*", ("agent_id= '" + user.getUid()));
        }
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                ObservableList<String> dataList = FXCollections.observableArrayList();
                String ttb_id = rs.getString("TTB_ID");
                String rep_id = rs.getString("REP_ID");
                String permit_no = rs.getString("PERMIT_NO");
                String source = rs.getString("SOURCE");
                String serial_no = rs.getString("SERIAL_NO");
                String alcohol_type = rs.getString("ALCOHOL_TYPE");
                String brand_name = rs.getString("BRAND_NAME");
                String fanciful_name = rs.getString("FANCIFUL_NAME");
                String alcohol_content = rs.getString("ALCOHOL_CONTENT");
                String applicant_city = rs.getString("APPLICANT_CITY");
                String applicant_zip = rs.getString("APPLICANT_ZIP");
                String applicant_state = rs.getString("APPLICANT_STATE");
                String applicant_country = rs.getString("APPLICANT_COUNTRY");
                String mailing_address = rs.getString("MAILING_ADDRESS");
                String formula = rs.getString("FORMULA");
                String phone_no = rs.getString("PHONE_NO");
                String email = rs.getString("EMAIL");
                String label_text = rs.getString("LABEL_TEXT");
                String label_image = rs.getString("LABEL_IMAGE");
                String submit_date = rs.getString("SUBMIT_DATE");
                String signature = rs.getString("SIGNATURE");
                String status = rs.getString("STATUS");
                String agent_id = rs.getString("AGENT_ID");
                String applicant_id = rs.getString("APPLICANT_ID");
                String approved_date = rs.getString("APPROVED_DATE");
                String expiration_date = rs.getString("EXPIRATION_DATE");
                /*if(alcohol_type.equals("Wine")){
                    String vintage_year = rs.getString("VINTAGE_YEAR");
                    String ph_level = rs.getString("PH_LEVEL");
                    String grape_varietals = rs.getString("GRAPE_VARIETALS");
                    String wine_appellation = rs.getString("WINE_APPELLATION");
                    dataList.add(vintage_year);
                    dataList.add(ph_level);
                    dataList.add(grape_varietals);
                    dataList.add(wine_appellation);
                }*/
                dataList.add(ttb_id);
                dataList.add(rep_id);
                dataList.add(permit_no);
                dataList.add(source);
                dataList.add(serial_no);
                dataList.add(alcohol_type);
                dataList.add(brand_name);
                dataList.add(fanciful_name);
                dataList.add(alcohol_content);
                dataList.add(applicant_city);
                dataList.add(applicant_zip);
                dataList.add(applicant_state);
                dataList.add(applicant_country);
                dataList.add(mailing_address);
                dataList.add(formula);
                dataList.add(phone_no);
                dataList.add(email);
                dataList.add(label_text);
                dataList.add(label_image);
                dataList.add(submit_date);
                dataList.add(signature);
                dataList.add(status);
                dataList.add(agent_id);
                dataList.add(applicant_id);
                dataList.add(approved_date);
                dataList.add(expiration_date);

                ol.add(dataList);
            }
            rs.close();
            stmt.close();
            connection.close();
            return ol;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Form findSingleForm(String ttbID, ArrayList<String> fields){
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.createSelectStatement("FORMS", "*", "ttb_id=" + ttbID);
        String query2 = queryBuilder.createSelectStatement("TYPEOFAPPLICATION", "*", "ttb_id=" + ttbID);


        try {
            Connection connection = TTB_database.connect();
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);
            ArrayList<Boolean> appType = new ArrayList<Boolean>();
            appType.add(false);
            appType.add(false);
            appType.add(false);
            appType.add(false);
            ArrayList<String> typeText = new ArrayList<String>();

            while(rs2.next()){
                if (rs2.getString("option_no").equals("0")){
                    appType.add(0, true);
                } else if (rs2.getString("option_no").equals("1")){
                    appType.add(1, true);
                } else if (rs2.getString("option_no").equals("2")){
                    appType.add(2, true);
                } else if (rs2.getString("option_no").equals("3")){
                    appType.add(3, true);
                }

                if (!rs2.getString("option").isEmpty()){
                    typeText.add(rs2.getString("option"));
                }
            }

            stmt2.close();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            Form form = null;
            while(rs.next()) {
                String ttb_id = rs.getString("ttb_id");
                String rep_id = rs.getString("rep_id");
                String permit_no = rs.getString("permit_no");
                String source = rs.getString("source");
                String serial_no = rs.getString("serial_no");
                String alcohol_type = rs.getString("alcohol_type");
                String brand_name = rs.getString("brand_name");
                String fanciful_name = rs.getString("fanciful_name");
                double alcohol_content = rs.getDouble("alcohol_content");
                String applicant_street = rs.getString("applicant_street");
                String applicant_city = rs.getString("applicant_city");
                String applicant_state = rs.getString("applicant_state");
                String applicant_zip = rs.getString("applicant_zip");
                String applicant_country = rs.getString("applicant_country");
                String mailing_address = rs.getString("mailing_address");
                String formula = rs.getString("formula");
                String phone_no = rs.getString("phone_no");
                String email = rs.getString("email");
                String label_text = rs.getString("label_text");
                String label_image = rs.getString("label_image");
                Date submit_date = rs.getDate("submit_date");
                String signature = rs.getString("signature");
                String status = rs.getString("status");
                String agent_id = rs.getString("agent_id");
                String applicant_id = rs.getString("applicant_id");
                Date approved_date = rs.getDate("approved_date");
                Date expiration_date = rs.getDate("expiration_date");
                String approval_comments = rs.getString("approval_comments");
                String vintage_year = null;
                int ph_level = -1;
                String grape_varietals = null;
                String wine_appelation = null;
                if(alcohol_type.equals("Wine")) {
                    vintage_year = rs.getString("vintage_year");
                    ph_level = rs.getInt("ph_level");
                    grape_varietals = rs.getString("grape_varietals");
                    wine_appelation = rs.getString("wine_appelation");
                }
                form = new Form(ttb_id, rep_id, permit_no, source, serial_no, alcohol_type, brand_name, fanciful_name,
                        alcohol_content, applicant_street, applicant_city, applicant_state, applicant_zip, applicant_country, mailing_address,
                        formula, phone_no, email, label_text, label_image, submit_date, signature, status, agent_id,
                        applicant_id, approved_date, expiration_date, vintage_year, ph_level, grape_varietals, wine_appelation, appType, typeText, approval_comments);
            }
            return form;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //UPDATE FUNCTIONS:
    /**
     * Updates a user into the database
     * @param user - user object to update
     * @return returns true if update is successful, false otherwise
     */
    public boolean updateUser(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("user_id = "+"\'" + user.getUid() + "\'");
        fields.add("authentication = "+ user.getAuthentication());
        fields.add("username ="+"\'" + user.getUsername() + "\'");
        fields.add("password = "+"\'" + user.getPassword() + "\'");
        fields.add("email = "+"\'" + user.getEmail() + "\'");
        fields.add("phone_no = "+"\'" + user.getPhoneNo() + "\'");
        fields.add("first_name = "+"\'" + user.getFirstName() + "\'");
        fields.add("middle_inital = "+"\'" + user.getMiddleInitial() + "\'");
        fields.add("last_name = "+"\'" + user.getLastName() + "\'");
        String updateString = queryBuilder.createUpdateStatement("USERS", fields, ("user_id = \'"+user.getUid() + "\'"));
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
        String options = "ttb_id=\'" + form.getttb_id() + "\'";
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> wine = new ArrayList<>();
        fields.add("rep_id=" + "\'" + form.getrep_id() + "\'");
        fields.add("permit_no=" + "\'" + form.getpermit_no() + "\'");
        fields.add("source=" + "\'" + form.getSource() + "\'");
        fields.add("serial_no=" + "\'" + form.getserial_no() + "\'");
        fields.add("alcohol_type=" + "\'" + form.getalcohol_type() + "\'");
        fields.add("brand_name=" + "\'" + form.getbrand_name() + "\'");
        fields.add("fanciful_name=" + "\'" + form.getfanciful_name() + "\'");
        fields.add("alcohol_content=" + form.getalcohol_content());
        fields.add("applicant_city=" + "\'" + form.getapplicant_city() + "\'");
        fields.add("applicant_zip=" + "\'" + form.getapplicant_zip() + "\'");
        fields.add("applicant_state=" + "\'" + form.getapplicant_state() + "\'");
        fields.add("applicant_country=" + "\'" + form.getapplicant_country() + "\'");
        fields.add("mailing_address=" + "\'" + form.getmailing_address() + "\'");
        fields.add("formula=" + "\'" + form.getFormula() + "\'");
        fields.add("phone_no=" + "\'" + form.getphone_no() + "\'");
        fields.add("email=" + "\'" + form.getEmail() + "\'");
        fields.add("label_text=" + "\'" + form.getlabel_text() + "\'");
        fields.add("label_image=" + "\'" + form.getlabel_image() + "\'");
        fields.add("submit_date=" + "\'" + form.getsubmit_date() + "\'");
        fields.add("signature=" + "\'" + form.getSignature() + "\'");
        fields.add("status=" + "\'" + form.getStatus() + "\'");
        fields.add("agent_id=" + "\'" + form.getagent_id() + "\'");
        fields.add("applicant_id=" + "\'" + form.getapplicant_id() + "\'");
        fields.add("approved_date=" + "\'" + form.getapproved_date() + "\'");
        fields.add("expiration_date=" + "\'" + form.getexpiration_date() + "\'");
        if(form.getalcohol_type().equals("Wine")) {
            wine.add("vintage_year=" + "\'" + form.getvintage_year() + "\'");
            wine.add("ph_level=" + "\'" + form.getpH_level() + "\'");
            wine.add("grape_varietals=" + "\'" + form.getgrape_varietals() + "\'");
            wine.add("wine_appelation=" + "\'" + form.getwine_appellation() + "\'");
        }
        String queryString = queryBuilder.createUpdateStatement("FORMS", fields, options);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(queryString);
            if(!wine.isEmpty()) {
                String wineString = queryBuilder.createUpdateStatement("WINEONLY", wine, "ttb_id=\'" + form.getttb_id() + "\'");
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

    // Generate CSV file
    public void generateCSV(ResultSet rs) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("labels.txt");
            writer.append("TTB ID");
            writer.append("Permit No.");
            writer.append("Serial No.");
            writer.append("Submitted Date");
            writer.append("Fanciful Name");
            writer.append("Brand Name");
            writer.append("Alcohol Type");
            try {
                while (rs.next()) {
                    writer.append(rs.getString("ttb_id"));
                    writer.append(rs.getString("permit_no"));
                    writer.append(rs.getString("serial_no"));
                    writer.append(rs.getDate("submit_date").toString());
                    writer.append(rs.getString("fanciful_name"));
                    writer.append(rs.getString("brand_name"));
                    writer.append(rs.getString("alcohol_type"));
                    writer.append("\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

