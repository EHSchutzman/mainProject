package DBManager;

import AgentWorkflow.AgentRecord;
import DatabaseSearch.AppRecord;
import Form.Form;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
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
            System.out.println(queryString);
            stmt.executeUpdate(queryString);
            stmt.close();
            connection.close();
            return true;
        } catch (SQLIntegrityConstraintViolationException se){
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
        fields.add("null");
        fields.add("\'" + form.getapplicant_id() + "\'");
        fields.add("null");
        fields.add("null");
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

    public ObservableList<AgentRecord> pullFormBatch(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = "";
        ObservableList<AgentRecord> o1 = FXCollections.observableArrayList();
        System.out.println(user.getAuthenticationLevel());
        if (user.getAuthenticationLevel() == 2 || user.getAuthenticationLevel() == 3) {
            query = queryBuilder.createSelectStatement("APP.FORMS", "*", "(AGENT_ID IS NULL)");
            System.out.println(query);
            try {
                Connection connection = TTB_database.connect();
                Statement stmt = connection.createStatement();
                System.out.println("QUERY " + query);
                ResultSet rs = stmt.executeQuery(query);
                int i = 0;
                while (rs.next() && i < 10) {
                    ObservableList<String> dataList = FXCollections.observableArrayList();
                    String ttb_id = rs.getString("TTB_ID");
                    System.out.println("THIS IS THE TTB ID " + ttb_id);
                    ArrayList<String> fields = new ArrayList<String>();
                    fields.add("*");
                    Form form = findSingleForm(ttb_id, fields);
                    System.out.println("FORM TTB ID: " + form.getttb_id());
                    form.setagent_id(user.getUid());
                    System.out.println("Set form agent id to: " + form.getagent_id());
                    updateForm(form);
                    String rep_id = rs.getString("REP_ID");
                    String permit_no = rs.getString("PERMIT_NO");
                    String source = rs.getString("SOURCE");
                    String serial_no = rs.getString("SERIAL_NO");
                    String alcohol_type = rs.getString("ALCOHOL_TYPE");
                    String brand_name = rs.getString("BRAND_NAME");
                    String fanciful_name = rs.getString("FANCIFUL_NAME");
                    String alcohol_content = rs.getString("ALCOHOL_CONTENT");
                    String applicant_street = rs.getString("APPLICANT_STREET");
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
                    AgentRecord agentRecord = new AgentRecord();
                    agentRecord.setIDNo(ttb_id);
                    agentRecord.setName(fanciful_name + ", " + brand_name);
                    agentRecord.setStatus(status);
                    agentRecord.setDate(submit_date);
                    dataList.add(ttb_id);
                    dataList.add(rep_id);
                    dataList.add(permit_no);
                    dataList.add(source);
                    dataList.add(serial_no);
                    dataList.add(alcohol_type);
                    dataList.add(brand_name);
                    dataList.add(fanciful_name);
                    dataList.add(alcohol_content);
                    dataList.add(applicant_street);
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
        String fields = "ttb_id, permit_no, serial_no, approved_date, fanciful_name, brand_name, alcohol_type";
        String query = queryBuilder.createLikeStatement("APP.FORMS", fields, filters);
        if (more != null && !more.isEmpty()) {
            if (!filters.get(0).isEmpty()) {
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
            while(rs.next()) {
                String ttbID = rs.getString("ttb_id");
                String permitNo = rs.getString("permit_no");
                String serialNo = rs.getString("serial_no");
                String approvedDate = rs.getDate("approved_date").toString();
                String fancifulName = rs.getString("fanciful_name");
                String brandName = rs.getString("brand_name");
                String alcoholType = rs.getString("alcohol_type");
                //ObservableList<String> observableList = FXCollections.observableArrayList();
                //observableList.addAll(ttbID, permitNo, serialNo, approvedDate, fancifulName, brandName, alcoholType);
                //ol.add(observableList);
                AppRecord application = new AppRecord();
                application.setFormID(ttbID);
                application.setPermitNo(permitNo);
                application.setSerialNo(serialNo);
                application.setCompletedDate(approvedDate);
                application.setBrandName(brandName);
                application.setFancifulName(fancifulName);
                application.setTypeID(alcoholType);
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


    public ObservableList<AgentRecord> findForms(User user) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = "";
        ObservableList<AgentRecord> ol = FXCollections.observableArrayList();
        //I think that appending these tables is going to get rid of the beer applications but ????

        if (user.getAuthenticationLevel() == 1) {
            query = queryBuilder.createSelectStatement("APP.FORMS", "*", ("applicant_id= \'" + user.getUid() + "\'"));
        } else if (user.getAuthenticationLevel() == 2 || user.getAuthenticationLevel() == 3) {
            query = queryBuilder.createSelectStatement("APP.FORMS", "*", ("agent_id= \'" + user.getUid() + "\'"));

        }
        try {
            Connection connection = TTB_database.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
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
                String wine_appellation = null;
                if(alcohol_type != null && alcohol_type.equals("Wine")) {
                    query = queryBuilder.createSelectStatement("WINEONLY", "*", "ttb_id=\'" + ttb_id + "\'");
                    try {
                        Statement stmt2 = connection.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(query);
                        while(rs2.next()){
                            vintage_year = rs2.getString("vintage_year");
                            ph_level = rs2.getInt("ph_level");
                            grape_varietals = rs2.getString("grape_varietals");
                            wine_appellation = rs2.getString("wine_appellation");
                        }
                        rs2.close();
                        stmt2.close();
                    } catch (SQLException e){
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
        fields.add("authentication = "+ user.getAuthenticationLevel());
        fields.add("username ="+"\'" + user.getUsername() + "\'");
        fields.add("password = "+"\'" + user.getPassword() + "\'");
        fields.add("email = "+"\'" + user.getEmail() + "\'");
        fields.add("phone_no = "+"\'" + user.getPhoneNo() + "\'");
        fields.add("first_name = "+"\'" + user.getFirstName() + "\'");
        fields.add("middle_inital = "+"\'" + user.getMiddleInitial() + "\'");
        fields.add("last_name = "+"\'" + user.getLastName() + "\'");
        String updateString = queryBuilder.createUpdateStatement("USERS", fields, ("user_id = \'" + user.getUid() + "\'"));
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
            fields.add("label_text=" + "\'" + form.getlabel_text() + "\'");
        } else {
            fields.add("label_text= NULL");
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
            if(!wine.isEmpty()) {
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
    public void generateCSV(ResultSet rs) {
        try {
            File file = new File("labels.csv");
            PrintWriter printWriter = new PrintWriter(file.getAbsolutePath());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TTB ID");
            stringBuilder.append(',');
            stringBuilder.append("Permit No.");
            stringBuilder.append(',');
            stringBuilder.append("Serial No.");
            stringBuilder.append(',');
            stringBuilder.append("Submitted Date");
            stringBuilder.append(',');
            stringBuilder.append("Fanciful Name");
            stringBuilder.append(',');
            stringBuilder.append("Brand Name");
            stringBuilder.append(',');
            stringBuilder.append("Alcohol Type");
            stringBuilder.append('\n');
            try {
                while(rs.next()) {
                    stringBuilder.append(rs.getString("ttb_id"));
                    stringBuilder.append(',');
                    stringBuilder.append(rs.getString("permit_no"));
                    stringBuilder.append(',');
                    stringBuilder.append(rs.getString("serial_no"));
                    stringBuilder.append(',');
                    stringBuilder.append(rs.getDate("submit_date").toString());
                    stringBuilder.append(',');
                    stringBuilder.append(rs.getString("fanciful_name"));
                    stringBuilder.append(',');
                    stringBuilder.append(rs.getString("brand_name"));
                    stringBuilder.append(',');
                    stringBuilder.append(rs.getString("alcohol_type"));
                    stringBuilder.append('\n');
                }
                printWriter.write(stringBuilder.toString());
                printWriter.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

