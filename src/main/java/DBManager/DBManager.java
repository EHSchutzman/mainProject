package DBManager;

import AgentWorkflow.AgentRecord;
import DatabaseSearch.AppRecord;
import DatabaseSearch.UserRecord;
import Form.Form;
import Initialization.Main;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Leo on 4/8/2017.
 */
public class DBManager {

    private Main main;
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
        fields.add("" + user.getAuthenticationLevel() + "");
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
        } catch (SQLIntegrityConstraintViolationException se) {
            return false;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String submit_date = formatter.format(form.getsubmit_date());

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
        fields.add("\'" + form.getformula() + "\'");
        fields.add("\'" + form.getphone_no() + "\'");
        fields.add("\'" + form.getEmail() + "\'");
        fields.add("\'" + form.getlabel_text() + "\'");
        fields.add("\'" + form.getlabel_image() + "\'");
        fields.add("\'" + submit_date + "\'");
        fields.add("\'" + form.getSignature() + "\'");
        fields.add("\'" + form.getStatus() + "\'");
        fields.add("NULL");
        fields.add("\'" + form.getapplicant_id() + "\'");
        fields.add("NULL");
        fields.add("NULL");
        fields.add("NULL");
        if (form.getalcohol_type().equals("Wine")) {
            wine.add("\'" + form.getttb_id() + "\'");
            wine.add("\'" + form.getvintage_year() + "\'");
            wine.add("" + form.getpH_level() + "");
            wine.add("\'" + form.getgrape_varietals() + "\'");
            wine.add("\'" + form.getwine_appellation() + "\'");
        }
        //Array to hold boolean type values
        ArrayList<Boolean> types;
        types = form.getapplication_type();
        //Storing info for each type of application
        ArrayList<String> typeofapplication0 = new ArrayList<String>();
        ArrayList<String> typeofapplication1 = new ArrayList<String>();
        ArrayList<String> typeofapplication2 = new ArrayList<String>();
        ArrayList<String> typeofapplication3 = new ArrayList<String>();

        if(types.get(0) == true){
            typeofapplication0.add("\'" + form.getttb_id() + "\'");
            typeofapplication0.add("" + 0 + "");
            typeofapplication0.add("NULL");
        }
        if(types.get(1) == true){
            typeofapplication1.add("\'" + form.getttb_id() + "\'");
            typeofapplication1.add("" + 1 + "");
            typeofapplication1.add("\'" + form.getapplication_type_text().get(0) + "\'");
        }
        if(types.get(2) == true){
            typeofapplication2.add("\'" + form.getttb_id() + "\'");
            typeofapplication2.add("" + 2 + "");
            typeofapplication2.add("\'" + form.getapplication_type_text().get(1) + "\'");
        }
        if(types.get(3) == true){
            typeofapplication3.add("\'" + form.getttb_id() + "\'");
            typeofapplication3.add("" + 3 + "");
            typeofapplication3.add("\'" + form.getapplication_type_text().get(2) + "\'");
        }
        String queryString = queryBuilder.createInsertStatement("FORMS", fields);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(queryString);
            if (!wine.isEmpty()) {
                String wineString = queryBuilder.createInsertStatement("WINEONLY", wine);
                stmt.executeUpdate(wineString);
            }
            if(!typeofapplication0.isEmpty()){
                String queryStringType = queryBuilder.createInsertStatement("TYPEOFAPPLICATION", typeofapplication0);
                stmt.executeUpdate(queryStringType);
            }
            if(!typeofapplication1.isEmpty()){
                String queryStringType1 = queryBuilder.createInsertStatement("TYPEOFAPPLICATION", typeofapplication1);
                stmt.executeUpdate(queryStringType1);
            }
            if(!typeofapplication2.isEmpty()){
                String queryStringType2 = queryBuilder.createInsertStatement("TYPEOFAPPLICATION", typeofapplication2);
                stmt.executeUpdate(queryStringType2);
            }
            if(!typeofapplication3.isEmpty()){
                String queryStringType3 = queryBuilder.createInsertStatement("TYPEOFAPPLICATION", typeofapplication3);
                stmt.executeUpdate(queryStringType3);
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

    public ObservableList<AgentRecord> pullFormBatch(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = "";
        ObservableList<AgentRecord> o1 = FXCollections.observableArrayList();
        System.out.println(user.getAuthenticationLevel());
        if (user.getAuthenticationLevel() == 2 || user.getAuthenticationLevel() == 3) {

            // Search for pre-assigned, un-reviewed applications first
            String query0 = queryBuilder.createSelectStatement("APP.FORMS", "*", "AGENT_ID = \'" + user.getUid() +
                    "\' AND STATUS = \'Pending\'");

            query = queryBuilder.createSelectStatement("APP.FORMS", "*", "(AGENT_ID IS NULL)");
            System.out.println(query0);
            System.out.println(query);
            try {
                Connection connection = TTB_database.connect();
                Statement stmt0 = connection.createStatement();
                Statement stmt = connection.createStatement();

                ResultSet rs0 = stmt0.executeQuery(query0);
                ResultSet rs = stmt.executeQuery(query);

                int i = 0;

                // Iterate through pre-assigned, un-reviewed applications
                while (rs0.next() && i < 10) {
                    String ttb_id = rs0.getString("TTB_ID");
                    String brand_name = rs0.getString("BRAND_NAME");
                    String fanciful_name = rs0.getString("FANCIFUL_NAME");
                    String submit_date = rs0.getString("SUBMIT_DATE");
                    String status = rs0.getString("STATUS");
                    AgentRecord agentRecord = new AgentRecord();
                    agentRecord.setIDNo(ttb_id);
                    agentRecord.setName(fanciful_name + ", " + brand_name);
                    agentRecord.setStatus(status);
                    agentRecord.setDate(submit_date);
                    o1.add(agentRecord);
                    i++;
                }

                // Then get to the unassigned, un-reviewed applications
                while (rs.next() && i < 10) {
                    // Update form to confirm agent assignment
                    String ttb_id = rs.getString("TTB_ID");
                    ArrayList<String> fields = new ArrayList<>();
                    fields.add("*");
                    Form form = findSingleForm(ttb_id, fields);
                    form.setagent_id(user.getUid());
                    updateForm(form);

                    String brand_name = rs.getString("BRAND_NAME");
                    String fanciful_name = rs.getString("FANCIFUL_NAME");
                    String submit_date = rs.getString("SUBMIT_DATE");
                    String status = rs.getString("STATUS");
                    AgentRecord agentRecord = new AgentRecord();
                    agentRecord.setIDNo(ttb_id);
                    agentRecord.setName(fanciful_name + ", " + brand_name);
                    agentRecord.setStatus(status);
                    agentRecord.setDate(submit_date);
                    o1.add(agentRecord);
                    i++;
                }
                stmt.close();
                connection.close();
                return o1;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ten applications were not able to be pulled and assigned to the specified agent.");
            }
        } else {
            System.out.println("The specified user does not have permission to perform that task.");
        }
        return null;
    }

    public ObservableList<AppRecord> findLabels(ArrayList<ArrayList<String>> filters, String more) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String fields = "ttb_id, permit_no, serial_no, fanciful_name, brand_name, alcohol_type, approved_date";
        String query = queryBuilder.createLikeStatement("APP.FORMS", fields, filters);
        if (more != null && !more.isEmpty()) {
            if (filters.isEmpty()) {
                query = query.concat(more);
            } else if (!filters.get(0).isEmpty()) {
                query = query.concat(more);
            } else {
                query = query.concat(" and " + more);
            }
        }
        System.out.println(query);

        ObservableList<AppRecord> ol = FXCollections.observableArrayList();
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String ttbID = rs.getString("ttb_id");
                String permitNo = rs.getString("permit_no");
                String serialNo = rs.getString("serial_no");
                String fancifulName = rs.getString("fanciful_name");
                String brandName = rs.getString("brand_name");
                String alcoholType = rs.getString("alcohol_type");
                String approvedDate = rs.getDate("approved_date").toString();
                //ObservableList<String> observableList = FXCollections.observableArrayList();
                //observableList.addAll(ttbID, permitNo, serialNo, approvedDate, fancifulName, brandName, alcoholType);
                //ol.add(observableList);
                AppRecord application = new AppRecord();
                application.setFormID(ttbID);
                application.setPermitNo(permitNo);
                application.setSerialNo(serialNo);
                application.setBrandName(brandName);
                application.setFancifulName(fancifulName);
                application.setTypeID(alcoholType);
                application.setTtbID(ttbID);
                ol.add(application);
                application.setCompletedDate(approvedDate);
                application.setTtbID(ttbID);
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


    public ObservableList<AgentRecord> findForms(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = "";
        ObservableList<AgentRecord> ol = FXCollections.observableArrayList();
        //I think that appending these tables is going to get rid of the beer applications but ????

        if (user.getAuthenticationLevel() == 1) {
            query = queryBuilder.createSelectStatement("APP.FORMS", "*", ("applicant_id= \'" + user.getUid() + "\'"));
            System.out.println(query);
        } else if (user.getAuthenticationLevel() == 2 || user.getAuthenticationLevel() == 3) {
            query = queryBuilder.createSelectStatement("APP.FORMS", "*", ("agent_id= \'" + user.getUid() + "\'"));
            System.out.println(query);

        }
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                AgentRecord application = new AgentRecord();
                String status = rs.getString("STATUS");
                String date = rs.getString("SUBMIT_DATE");
                String idno = rs.getString("TTB_ID");
                String name = rs.getString("BRAND_NAME") + " " + rs.getString("FANCIFUL_NAME");
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

                application.setStatus(status);
                application.setDate(date);
                application.setIDNo(idno);
                application.setName(name);

                ol.add(application);
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

    public Form findSingleForm(String ttbID, ArrayList<String> fields) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.createSelectStatement("FORMS", "*", "ttb_id='" + ttbID + "'");
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Form form = null;
            while (rs.next()) {
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
                String label_text = "";//rs.getString("extraLabelInfo");
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
                String wine_appellation = null;
                if (alcohol_type != null && alcohol_type.equals("Wine")) {
                    query = queryBuilder.createSelectStatement("WINEONLY", "*", "ttb_id=\'" + ttb_id + "\'");
                    try {
                        Statement stmt2 = connection.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(query);
                        while (rs2.next()) {
                            vintage_year = rs2.getString("vintage_year");
                            ph_level = rs2.getInt("ph_level");
                            grape_varietals = rs2.getString("grape_varietals");
                            wine_appellation = rs2.getString("wine_appellation");
                        }
                        rs2.close();
                        stmt2.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                ArrayList<Boolean> application_type = new ArrayList<>();
                ArrayList<String> application_type_text = new ArrayList<>();
                form = new Form(ttb_id, rep_id, permit_no, source, serial_no, alcohol_type,
                        brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state,
                        applicant_zip, applicant_country, mailing_address, formula, phone_no,
                        email, label_text, label_image, submit_date, signature, status,
                        agent_id, applicant_id, approved_date, expiration_date, vintage_year,
                        ph_level, grape_varietals, wine_appellation, application_type, application_type_text, approval_comments);
            }
            rs.close();
            stmt.close();
            connection.close();
            return form;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUser(String options) {
        System.out.println(options);
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.createSelectStatement("USERS", "*", options);
        System.out.println(query);
        try {
            Connection connection = TTB_database.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            User user = new User();
            while (rs.next()) {
                String user_id = rs.getString("user_id");
                int authentication = rs.getInt("authentication");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone_no = rs.getString("phone_no");
                String first_name = rs.getString("first_name");
                String middle_initial = rs.getString("middle_initial");
                String last_name = rs.getString("last_name");
                user = new User(user_id, username, password, first_name, middle_initial, last_name, email, phone_no, authentication);
            }
            rs.close();
            statement.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findUsersName(String user_id) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.createSelectStatement("USERS", "first_name, middle_initial, last_name", "user_id = \'" + user_id + "\'");
        System.out.println(query);
        try {
            Connection connection = TTB_database.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String usersname = "";
            while (rs.next()) {
                String first_name = rs.getString("first_name");
                String middle_initial = rs.getString("middle_initial");
                String last_name = rs.getString("last_name");

                usersname = "" + first_name + " " + middle_initial + ". " + last_name;
            }
            rs.close();
            statement.close();
            connection.close();
            return usersname;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //UPDATE FUNCTIONS:

    /**
     * Updates a user into the database
     *
     * @param user - user object to update
     * @return returns true if update is successful, false otherwise
     */
    public boolean updateUser(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("user_id = " + "\'" + user.getUid() + "\'");
        fields.add("authentication = " + user.getAuthenticationLevel());
        fields.add("username =" + "\'" + user.getUsername() + "\'");
        fields.add("password = " + "\'" + user.getPassword() + "\'");
        fields.add("email = " + "\'" + user.getEmail() + "\'");
        fields.add("phone_no = " + "\'" + user.getPhoneNo() + "\'");
        fields.add("first_name = " + "\'" + user.getFirstName() + "\'");
        fields.add("middle_initial = " + "\'" + user.getMiddleInitial() + "\'");
        fields.add("last_name = " + "\'" + user.getLastName() + "\'");
        String updateString = queryBuilder.createUpdateStatement("USERS", fields, ("user_id = \'" + user.getUid() + "\'"));
        System.out.println(updateString);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(updateString);
            stmt.close();
            connection.close();
            System.out.println("Update success");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update failed");
            return false;
        }
    }

    /**
     * Updates a form in the database
     *
     * @param form - form to update
     * @return returns true if the update is successful, false otherwise
     */
    public boolean updateForm(Form form) {
        System.out.println(form.getagent_id());
        QueryBuilder queryBuilder = new QueryBuilder();
        String options = "ttb_id=\'" + form.getttb_id() + "\'";
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> wine = new ArrayList<>();
        if (form.getrep_id() != null) {
            fields.add("rep_id=" + "\'" + form.getrep_id() + "\'");
        } else {
            fields.add("rep_id=" + "NULL");
        }
        fields.add("permit_no=" + "\'" + form.getpermit_no() + "\'");
        fields.add("source=" + "\'" + form.getSource() + "\'");
        fields.add("serial_no=" + "\'" + form.getserial_no() + "\'");
        fields.add("alcohol_type=" + "\'" + form.getalcohol_type() + "\'");
        fields.add("brand_name=" + "\'" + form.getbrand_name() + "\'");

        if (form.getfanciful_name() != null) {
            fields.add("fanciful_name=" + "\'" + form.getfanciful_name() + "\'");
        } else {
            fields.add("fanciful_name=" + "NULL");
        }

        fields.add("alcohol_content=" + form.getalcohol_content());

        fields.add("applicant_city=" + "\'" + form.getapplicant_city() + "\'");
        fields.add("applicant_zip=" + "\'" + form.getapplicant_zip() + "\'");
        fields.add("applicant_state=" + "\'" + form.getapplicant_state() + "\'");
        fields.add("applicant_country=" + "\'" + form.getapplicant_country() + "\'");

        if (form.getapplicant_street() != null) {
            fields.add("applicant_street=" + "\'" + form.getapplicant_street() + "\'");
        } else {
            fields.add("applicant_street = NULL");
        }

        if (form.getmailing_address() != null) {
            fields.add("mailing_address=" + "\'" + form.getmailing_address() + "\'");
        } else {
            fields.add("mailing_address= NULL");
        }

        if (form.getformula() != null) {
            fields.add("formula=" + "\'" + form.getformula() + "\'");
        } else {
            fields.add("formula= NULL");
        }

        if (form.getphone_no() != null) {
            fields.add("phone_no=" + "\'" + form.getphone_no() + "\'");
        } else {
            fields.add("phone_no= NULL");
        }

        if (form.getEmail() != null) {
            fields.add("email=" + "\'" + form.getEmail() + "\'");
        } else {
            fields.add("email= NULL");
        }

        if (form.getlabel_text() != null) {
            fields.add("extraLabelInfo=" + "\'" + form.getlabel_text() + "\'");
        } else {
            fields.add("extraLabelInfo= NULL");
        }
        if (form.getlabel_image() != null) {
            fields.add("label_image=" + "\'" + form.getlabel_image() + "\'");
        } else {
            fields.add("label_image= NULL");
        }
        fields.add("submit_date=" + "\'" + form.getsubmit_date() + "-00.00.00" + "\'");
        fields.add("signature=" + "\'" + form.getSignature() + "\'");
        fields.add("status=" + "\'" + form.getStatus() + "\'");
        if (form.getagent_id() != null) {
            fields.add("agent_id=" + "\'" + form.getagent_id() + "\'");
        } else {
            fields.add("agent_id= NULL");
        }
        fields.add("applicant_id=" + "\'" + form.getapplicant_id() + "\'");
        if (form.getapproved_date() != null) {
            fields.add("approved_date=" + "\'" + form.getapproved_date() + "-00.00.00" + "\'");
        } else {
            fields.add("approved_date = NULL");
        }
        if (form.getexpiration_date() != null) {
            fields.add("expiration_date=" + "\'" + form.getexpiration_date() + "-00.00.00" + "\'");
        } else {
            fields.add("expiration_date = NULL");
        }
        if (form.getapproval_comments() != null) {
            fields.add("approval_comments=" + "\'" + form.getapproval_comments() + "\'");
        } else {
            fields.add("approval_comments = NULL");
        }

        if (form.getalcohol_type() != null && form.getalcohol_type().equals("Wine")) {
            wine.add("vintage_year=" + "\'" + form.getvintage_year() + "\'");
            wine.add("ph_level=" + form.getpH_level());
            wine.add("grape_varietals=" + "\'" + form.getgrape_varietals() + "\'");
            wine.add("wine_appellation=" + "\'" + form.getwine_appellation() + "\'");
        }
        String queryString = queryBuilder.createUpdateStatement("FORMS", fields, options);
        System.out.println(queryString);
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(queryString);
            if (!wine.isEmpty()) {
                System.out.println("Updating wine");
                String wineString = queryBuilder.createUpdateStatement("WINEONLY", wine, "ttb_id=\'" + form.getttb_id() + "\'");
                System.out.println(wineString);
                Statement innerWine = connection.createStatement();
                innerWine.executeUpdate(wineString);
                innerWine.close();
            }
            stmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // TODO: the commented line can be placed after the if - will fix the warning where the if is always true
    // TODO: this throws an error to csvOptionsController where if you don't select a file path
    // TODO: - and close the file chooser, the confirmation message still displays
    public void generateCSV(ObservableList<AppRecord> list, String separator, String extension) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.dir")));
        File selectedFile = dc.showDialog(null);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        File file = new File(selectedFile.getPath() + "/" + "labelResults" + dateFormat.format(new Date(System.currentTimeMillis())) + extension);
        if (selectedFile != null) {
            //File file = new File(selectedFile.getPath() + "/" + "labelResults" + dateFormat.format(new Date(System.currentTimeMillis())) + extension);
            try {
                FileWriter fileWriter = new FileWriter(file, false);
                System.out.println(file.getAbsolutePath());
                fileWriter.write("TTB ID");
                fileWriter.write(separator);
                fileWriter.write("Completed Date");
                fileWriter.write(separator);
                fileWriter.write("Alcohol Type");
                fileWriter.write(separator);
                fileWriter.write("Brand Name");
                fileWriter.write(separator);
                fileWriter.write("Fanciful Name");
                fileWriter.write(separator);
                fileWriter.write("Permit Number");
                fileWriter.write(separator);
                fileWriter.write("Serial Number");
                fileWriter.write("\n");
                for (AppRecord ar : list) {
                    fileWriter.write(ar.getTtbID());
                    fileWriter.write(separator);
                    fileWriter.write(ar.getCompletedDate());
                    fileWriter.write(separator);
                    fileWriter.write(ar.getTypeID());
                    fileWriter.write(separator);
                    fileWriter.write(ar.getBrandName());
                    fileWriter.write(separator);
                    try {
                        fileWriter.write(ar.getFancifulName());
                    } catch (NullPointerException e) {
                        fileWriter.write("");
                    }
                    fileWriter.write(separator);
                    fileWriter.write(ar.getPermitNo());
                    fileWriter.write(separator);
                    fileWriter.write(ar.getSerialNo());
                    fileWriter.write("\n");
                }
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // For the search users page for super agents
    public ObservableList<UserRecord> searchUsers(String options) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.createSelectStatement("USERS", "*", options);
        System.out.println(query);
        try {
            Connection connection = TTB_database.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ObservableList<UserRecord> userRecordList = FXCollections.observableArrayList();
            while (rs.next()) {
                String user_id = rs.getString("user_id");
                String username = rs.getString("username");
                String first_name = rs.getString("first_name");
                String middle_initial = rs.getString("middle_initial");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                String phone_no = rs.getString("phone_no");
                int authentication = rs.getInt("authentication");
                UserRecord userRecord = new UserRecord(user_id, username, first_name, middle_initial, last_name, email, phone_no, Integer.toString(authentication));
                userRecordList.add(userRecord);
            }
            rs.close();
            statement.close();
            connection.close();
            return userRecordList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

