package Controllers;

import DBManager.DBManager;
import Form.Form;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by James Corse on 4/25/17.
 */
public class agentApplicationReviewController extends UIController{
    private Form form = new Form();
    private DBManager dbManager = new DBManager();
    @FXML
    private TextArea approval_comments_text;
    @FXML
    private Button rejectButton, acceptButton;
    @FXML
    public TextField rep_id_text;
    @FXML
    public TextField permit_no_text;
    @FXML
    public TextField serial_no_text;

    // Source and Alcohol Type ComboBoxes + their TextFields for Agent Review
    @FXML
    public ComboBox source_combobox;
    @FXML
    public TextField source_text;
    @FXML
    public ComboBox alcohol_type_combobox;
    @FXML
    public TextField alcohol_type_text;

    @FXML
    public TextField brand_name_text;
    @FXML
    public TextField fanciful_name_text;
    @FXML
    public TextField alcohol_content_text;
    @FXML
    public TextField formula_text;

    @FXML
    public TextField label_text;

    // Wine only
    @FXML
    public TextField vintage_year_text;
    @FXML
    public TextField ph_level_text;
    @FXML
    public TextField grape_varietals_text;
    @FXML
    public TextField wine_appellation_text;


    // CheckBoxes and TextFields for the Type of Application
    //
    @FXML
    public CheckBox option_1_checkbox;
    @FXML
    public CheckBox option_2_checkbox;
    @FXML
    public TextField option_2_text; //For sale in <state> only
    @FXML
    public CheckBox option_3_checkbox;
    @FXML
    public TextField option_3_text; //Bottle capacity before closure
    @FXML
    public CheckBox option_4_checkbox;
    @FXML
    public TextField option_4_text;

    // Applicant Info
    // Addresses
    // street1 and street2 correspond to applicant_street
    @FXML
    public TextField applicant_name_text;
    @FXML
    public TextField applicant_street_1_text;
    @FXML
    public TextField applicant_street_2_text;
    @FXML
    public TextField applicant_city_text;
    @FXML
    public TextField applicant_state_text;
    @FXML
    public TextField applicant_zip_text;
    @FXML
    public TextField applicant_country_text;
    //Mailing Address
    @FXML
    public RadioButton sameAsApplicantButton;
    @FXML
    public TextArea mailing_addressText;
    @FXML
    public TextField mailing_name_text;
    public TextField mailing_street_2_text;
    public TextField mailing_street_1_text;
    public TextField mailing_city_text;
    public TextField mailing_zip_text;
    public TextField mailing_country_text;
    public TextField mailing_state_text;
    @FXML
    public TextField phone_no_text;
    @FXML
    public TextField signature_text;
    @FXML
    public TextField email_text;
    @FXML
    public TextArea address_text;
    public TextField submit_date;
    public ImageView label_image;

    private Form form = new Form();
    private DBManager dbManager = new DBManager();

    void setReviewForm(Form form) {
        this.form = form;
    }

    void setLabels() {
        // Get Source info and set it to display for the Agent
        //source_combobox = new ComboBox(FXCollections.observableArrayList("Domestic", "Imported"));
        if (form.getSource().equals("Imported")) {
            //source_combobox.getSelectionModel().select(2);
            source_text.setPromptText("Imported");
        } else if (form.getSource().equals("Domestic")) {
            //source_combobox.getSelectionModel().select(1);
            source_text.setPromptText("Domestic");
        }
        // Get Alcohol Type info and set it to display for the Agent
        //alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        System.out.println("creating the form" + form.getalcohol_type());
        if (form.getalcohol_type().equals("Malt Beverages")) {
            //alcohol_type_combobox.getSelectionModel().select(1);
            alcohol_type_text.setText("Malt Beverages");
        } else if (form.getalcohol_type().equals("Wine")) {
            //alcohol_type_combobox.getSelectionModel().select(2);
            alcohol_type_text.setText("Wine");
        } else if (form.getalcohol_type().equals("Distilled Spirits")) {
            //alcohol_type_combobox.getSelectionModel().select(3);
            alcohol_type_text.setText("Distilled Spirits");
        }
        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        option_1_checkbox = new CheckBox("Certificate of Label Approval");
        option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
        option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
        option_4_checkbox = new CheckBox("Resubmission After Rejection");

        ArrayList<Boolean> tempBoolArray = new ArrayList<Boolean>();
        for (int i = 0; i < 4; i++) {
            tempBoolArray.add(false);
        }
        ArrayList<String> tempStrArray = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            tempStrArray.add("");
        }
        if (tempBoolArray.get(0) == true) {//choice 0
            option_1_checkbox.setSelected(true);
        }
        if (tempBoolArray.get(1) == true) {
            option_2_text.setPromptText(tempStrArray.get(0));
            option_2_checkbox.setSelected(true);
        }
        if (tempBoolArray.get(2) == true) {
            option_3_text.setPromptText(tempStrArray.get(1));
            option_3_checkbox.setSelected(true);
        }
        if (tempBoolArray.get(3) == true) {
            option_4_text.setPromptText(tempStrArray.get(2));
            option_4_checkbox.setSelected(true);
        }

        rep_id_text.setText(form.getrep_id());
        permit_no_text.setText(form.getpermit_no());
        serial_no_text.setText(form.getserial_no());
        brand_name_text.setText(form.getbrand_name());
        fanciful_name_text.setText(form.getfanciful_name());
        alcohol_content_text.setText(String.valueOf(form.getalcohol_content()));
        formula_text.setText(form.getFormula());
        label_text.setText(form.getlabel_text());
        // Wines only
        if (form.getalcohol_type().equals("Wine")) {
            vintage_year_text.setText(form.getvintage_year());
            ph_level_text.setText(String.valueOf(form.getpH_level()));
            grape_varietals_text.setText(form.getgrape_varietals());
            wine_appellation_text.setText(form.getwine_appellation());
        } else {
            vintage_year_text.setText(null);
            ph_level_text.setText(null);
            grape_varietals_text.setText(null);
            wine_appellation_text.setText(null);
        }

        //TODO maybe seperate applicant_street_1_text and applicant_street_2_text because it might be too long
        /*applicant_street_1_text.setPromptText(form.getapplicant_street());
        applicant_city_text.setPromptText(form.getapplicant_city());
        applicant_state_text.setPromptText(form.getapplicant_state());
        applicant_zip_text.setPromptText(form.getapplicant_zip());
        applicant_country_text.setPromptText(form.getapplicant_country());*/

        address_text.setText(form.getapplicant_street() + ", " + form.getapplicant_city() + ", " + form.getapplicant_state() + " " + form.getapplicant_zip() + ", " + form.getapplicant_country());

        //mailing_addressText.setPromptText(form.getmailing_address());
        DBManager manager = new DBManager();
        System.out.println("NAME IS THIS");
        System.out.println(manager.findUsersName(form.getapplicant_id()));
        applicant_name_text.setText(null);
        signature_text.setText(form.getSignature());
        phone_no_text.setText(form.getphone_no());
        email_text.setText(form.getEmail());
    }

    //TODO: change approval_comments in DB to approve/reject comments (make up a better name)

    /**
     * Sets form status to APPROVED, adds approval comments and closes window
     * TODO: change Accepted to APPROVED
     *
     * @throws IOException - throws exception
     */
    @FXML
    public void acceptAction() throws IOException{
        form.setapproval_comments(approval_comments_text.getText());
        form.setStatus("Accepted");
        dbManager.updateForm(form);
        System.out.println("accepted form");

        closeWindow();
    }

    /**
     * Sets form status to REJECTED and closes window
     * TODO: change Rejected to REJECTED
     * @throws IOException - throws exception
     */
    @FXML
    public void rejectAction() throws IOException{
        form.setapproval_comments(approval_comments_text.getText());
        form.setStatus("Rejected");
        dbManager.updateForm(form);
        System.out.println("rejected form");

        closeWindow();
    }
}

