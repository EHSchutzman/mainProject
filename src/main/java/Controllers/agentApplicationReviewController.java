package Controllers;

import DBManager.DBManager;
import Form.Form;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by James Corse on 4/25/17.
 */
public class agentApplicationReviewController extends UIController{
    @FXML
    public void initialize() {
        source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
        alcohol_type_combobox.setItems(FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits"));
    }
    @FXML
    private Button back_button;
    @FXML
    public Button browse_button;
    @FXML
    public Button submit_button;
    @FXML
    public Button close_button;
    @FXML
    public TextField repID;
    @FXML
    public TextField permitNO;
    @FXML
    public TextField serialNO;

    // Source and Alcohol Type ComboBoxes + their TextFields for Agent Review
    @FXML
    public ComboBox source_combobox;
    @FXML
    public ComboBox alcohol_type_combobox;

    @FXML
    public TextField brandName;
    @FXML
    public TextField fancifulName;
    @FXML
    public TextField alcoholContent;
    @FXML
    public TextField formula;

    @FXML
    public TextArea extraLabelInfo;

    // Wine only
    @FXML
    public TextField vintageYear;
    @FXML
    public TextField phLevel;
    @FXML
    public TextField grapeVarietals;
    @FXML
    public TextField wineAppellation;


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
    public TextField applicantStreet;
    @FXML
    public TextField applicantCity;
    @FXML
    public TextField applicantState;
    @FXML
    public TextField applicantZip;
    @FXML
    public TextField applicantCountry;
    //Mailing Address
    @FXML
    public CheckBox sameAsApplicantButton;
    @FXML
    public TextArea mailingAddress;

    @FXML
    public TextField phoneNo;
    @FXML
    public TextField signature;
    @FXML
    public TextField email;
    @FXML
    public TextArea address_text;
    public TextField submit_date;
    public ImageView label_image;

    private DBManager db = new DBManager();
    private Form form;

    void setReviewForm(Form form) {
        this.form = form;
    }

    void setLabels() {
        // Get Source info and set it to display for the Agent
        //source_combobox = new ComboBox(FXCollections.observableArrayList("Domestic", "Imported"));

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

        repID.setText(form.getrep_id());
        permitNO.setText(form.getpermit_no());
        serialNO.setText(form.getserial_no());
        brandName.setText(form.getbrand_name());
        fancifulName.setText(form.getfanciful_name());
        alcoholContent.setText(String.valueOf(form.getalcohol_content()));
        formula.setText(form.getFormula());
        extraLabelInfo.setText(form.getlabel_text());
        // Wines only
        if (form.getalcohol_type().equals("Wine")) {
            vintageYear.setText(form.getvintage_year());
            phLevel.setText(String.valueOf(form.getpH_level()));
            grapeVarietals.setText(form.getgrape_varietals());
            wineAppellation.setText(form.getwine_appellation());
        } else {
            vintageYear.setText(null);
            phLevel.setText(null);
            grapeVarietals.setText(null);
            wineAppellation.setText(null);
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
        signature.setText(form.getSignature());
        phoneNo.setText(form.getphone_no());
        email.setText(form.getEmail());
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
        form.setapproval_comments(null);
        form.setStatus("Accepted");
        db.updateForm(form);
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
        form.setapproval_comments(null);
        form.setStatus("Rejected");
        db.updateForm(form);
        System.out.println("rejected form");

        closeWindow();
    }
}

