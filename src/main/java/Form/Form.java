package Form;


import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

public class Form {
    // Label Info
    private String ttb_id;
    private String rep_id;           // x textfield
    private String permit_no;        // x textfield
    private String serial_no;        // x textfield
    private String source;           // x radio buttons
    private String alcohol_type;     // x radio buttons
    private String brand_name;       // x textfield
    private String fanciful_name;    // x textfield
    private double alcohol_content;  // x textfield
    private String formula;          // x
    private String label_image;      //
    private String label_text;       // x
    // Wines only
    private String vintage_year;     // x
    private int pH_level;            // x
    private String grape_varietals;  // x
    private String wine_appellation; // x
    // Application type
    ArrayList<Boolean> application_type;            // x check boxes
    ArrayList<String> application_type_text;        // x textfields corresponding to their check boxes

    // Applicant Info
    // Addresses
    private String applicant_street; // x two textfields
    private String applicant_city;   // x
    private String applicant_state;  // x
    private String applicant_zip;    // x
    private String applicant_country;// x
    private String mailing_address;  // x has checkbox to see if it should be the same as regular address
    //
    private String signature;        // x
    private String phone_no;         // x
    private String email;            // x

    // Application info
    private Date submit_date;        // x
    private String status;           // x
    private String agent_id;         //
    private String applicant_id;     //
    private Date approved_date;      // x
    private Date expiration_date;    // x
    private String approval_comments;// x




    //constructor
    public Form() {}

    //generate a unique ID for the form
    public String makeUniqueID () {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public Form(String ttb_id, String rep_id, String permit_no, String source, String serial_no, String alcohol_type,
                String brand_name, String fanciful_name, double alcohol_content, String applicant_street, String applicant_city, String applicant_state,
                String applicant_zip, String applicant_country, String mailing_address, String formula, String phone_no,
                String email, String label_text, String label_image, Date submit_date, String signature, String status,
                String agent_id, String applicant_id, Date approved_date, Date expiration_date, String vintage_year,
                int pH_level, String grape_varietals, String wine_appellation, ArrayList<Boolean> application_type, ArrayList<String> application_type_text, String approval_comments) {
        this.ttb_id = ttb_id;
        this.rep_id = rep_id;
        this.permit_no = permit_no;
        this.source = source;
        this.serial_no = serial_no;
        this.alcohol_type = alcohol_type;
        this.brand_name = brand_name;
        this.fanciful_name = fanciful_name;
        this.alcohol_content = alcohol_content;
        this.applicant_street = applicant_street;
        this.applicant_city = applicant_city;
        this.applicant_state = applicant_state;
        this.applicant_zip = applicant_zip;
        this.applicant_country = applicant_country;
        this.mailing_address = mailing_address;
        this.formula = formula;
        this.phone_no = phone_no;
        this.email = email;
        this.label_text = label_text;
        this.label_image = label_image;
        this.submit_date = submit_date;
        this.signature = signature;
        this.status = status;
        this.agent_id = agent_id;
        this.applicant_id = applicant_id;
        this.approved_date = approved_date;
        this.expiration_date = expiration_date;
        this.vintage_year = vintage_year;
        this.pH_level = pH_level;
        this.grape_varietals = grape_varietals;
        this.wine_appellation = wine_appellation;
        this.application_type = application_type;
        this.application_type_text = application_type_text;
        this.approval_comments = approval_comments;
    }

    public String getttb_id() {
        return ttb_id;
    }

    public String getrep_id() {
        return rep_id;
    }

    public String getpermit_no() {
        return permit_no;
    }

    public String getSource() {
        return source;
    }

    public String getserial_no() {
        return serial_no;
    }

    public String getalcohol_type() {
        return alcohol_type;
    }

    public String getbrand_name() {
        return brand_name;
    }

    public String getfanciful_name() {
        return fanciful_name;
    }

    public double getalcohol_content() {
        return alcohol_content;
    }

    public String getapplicant_street() {
        return applicant_street;
    }

    public String getapplicant_city() {
        return applicant_city;
    }

    public String getapplicant_state() {
        return applicant_state;
    }

    public String getapplicant_zip() {
        return applicant_zip;
    }

    public String getapplicant_country() {
        return applicant_country;
    }

    public String getmailing_address() {
        return mailing_address;
    }

    public String getformula() {
        return formula;
    }

    public String getphone_no() {
        return phone_no;
    }

    public String getEmail() {
        return email;
    }

    public String getlabel_text() {
        return label_text;
    }

    public String getlabel_image() {
        return label_image;
    }

    public Date getsubmit_date() {
        return submit_date;
    }

    public String getSignature() {
        return signature;
    }

    public String getStatus() {
        return status;
    }

    public String getagent_id() {
        return agent_id;
    }

    public String getapplicant_id() {
        return applicant_id;
    }

    public Date getapproved_date() {
        return approved_date;
    }

    public String getapproval_comments() {
        return approval_comments;
    }

    public Date getexpiration_date() {
        return expiration_date;
    }

    public String getvintage_year() {
        return vintage_year;
    }

    public int getpH_level() {
        return pH_level;
    }

    public String getgrape_varietals() {
        return grape_varietals;
    }

    public String getwine_appellation() {
        return wine_appellation;
    }

    public ArrayList<Boolean> getapplication_type() {
        return application_type;
    }

    public ArrayList<String> getapplication_type_text() {
        return application_type_text;
    }

    public void setttb_id(String ttb_id) {
        this.ttb_id = ttb_id;
    }

    public void setrep_id(String rep_id) {
        this.rep_id = rep_id;
    }

    public void setpermit_no(String permit_no) {
        this.permit_no = permit_no;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setserial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public void setalcohol_type(String alcohol_type) {
        this.alcohol_type = alcohol_type;
    }

    public void setbrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setfanciful_name(String fanciful_name) {
        this.fanciful_name = fanciful_name;
    }

    public void setalcohol_content(double alcohol_content) {
        this.alcohol_content = alcohol_content;
    }

    public void setapplicant_street(String applicant_street) {
        this.applicant_street = applicant_street;
    }

    public void setapplicant_city(String applicant_city) {
        this.applicant_city = applicant_city;
    }

    public void setapplicant_state(String applicant_state) {
        this.applicant_state = applicant_state;
    }

    public void setapplicant_zip(String applicant_zip) {
        this.applicant_zip = applicant_zip;
    }

    public void setapplicant_country(String applicant_country) {
        this.applicant_country = applicant_country;
    }

    public void setmailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setphone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setlabel_text(String label_text) {
        this.label_text = label_text;
    }

    public void setlabel_image(String label_image) {
        this.label_image = label_image;
    }

    public void setsubmit_date(Date submit_date) {
        this.submit_date = submit_date;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setagent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public void setapplicant_id(String applicant_id) {
        this.applicant_id = applicant_id;
    }

    public void setapproved_date(Date approved_date) {
        this.approved_date = approved_date;
    }

    public void setexpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public void setapproval_comments(String approval_comments) {
        this.approval_comments = approval_comments;
    }

    public void setvintage_year(String vintage_year) {
        this.vintage_year = vintage_year;
    }

    public void setpH_level(int pH_level) {
        this.pH_level = pH_level;
    }

    public void setgrape_varietals(String grape_varietals) {
        this.grape_varietals = grape_varietals;
    }

    public void setwine_appellation(String wine_appellation) {
        this.wine_appellation = wine_appellation;
    }

    public void setapplication_type(ArrayList<Boolean> application_type) {
        this.application_type = application_type;
    }

    public void setapplication_type_text(ArrayList<String> application_type_text) {
        this.application_type_text = application_type_text;
    }
}