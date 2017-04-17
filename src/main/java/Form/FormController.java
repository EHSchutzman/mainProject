package Form;

import AgentWorkflow.AgentRecord;
import DBManager.DBManager;
import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

//import com.sun.tools.javac.comp.Check;

// TODO checkboxes and their corresponding lists from Form object and choiceboxes may be the source of errors
public class FormController{
    public int currentPage;

    private Main main;
    private Form form;

    public DBManager DBManager = new DBManager();
    public TableView<AgentRecord> resultsTable;

    public void setDisplay(Main main) {
        this.main = main;
    }

    public void setDisplay2(Main main, Form form) {
        this.main = main;
        this.form = form;
        createAgentForm(form);
    }

    public void ApplyDisplay(Main main) {
        this.main = main;
        //createApplicantForm();
    }

    public void ReviseDisplay(Main main) {
        this.main = main;
        displayApplicantResults();
    }

    public void ReviewDisplay(Main main, Form form) {
        this.main = main;
        this.form = form;
        createReviseForm(form);
    }

    @FXML
    public Button back_button;

    @FXML
    public Button nextButton;

    // Label Info
    @FXML
    public Label ttb_id_label;
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

    // TODO add label image thing
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
    //@FXML
    //public TextField applicant_nameText;

    @FXML
    public TextArea address_text;

    //TTB Use (accept/reject)
    @FXML
    public Button accept_button;
    @FXML
    public Button reject_button;
    //
    @FXML
    public DatePicker approved_date;
    @FXML
    public TextField agent_nameText;
    @FXML
    public DatePicker expiration_date;
    @FXML
    public TextArea approval_comments_text;
    @FXML
    public Label statusLabel; //TODO add to UI
    @FXML
    public TextField submit_date;
    public ImageView label_image;


    public void createApplicantForm() {

        Form form = new Form();

        //statusLabel.setText(form.getStatus());

        // TODO make a random number generator and add a label to display ttbID
        form.setttb_id(form.makeUniqueID());
        form.setrep_id(rep_id_text.getText());
        form.setpermit_no(permit_no_text.getText());
        form.setserial_no(serial_no_text.getText());

        // Initialize Source ChoiceBox and get value
        source_combobox = new ComboBox(FXCollections.observableArrayList("Domestic", "Imported"));
        form.setSource((String) source_combobox.getValue());

        // Initialize Alcohol Type ChoiceBox, get and set value
        alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirit"));
        form.setalcohol_type((String)alcohol_type_combobox.getValue());

        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        option_1_checkbox = new CheckBox("Certificate of Label Approval");
        option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
        option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
        option_4_checkbox = new CheckBox("Resubmission After Rejection");

        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> tempBoolArray = new ArrayList<Boolean>();
        for (int i = 0; i < 4; i++) {
            tempBoolArray.add(false);
        }
        ArrayList<String> tempStrArray = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            tempStrArray.add("");
        }
        if (option_1_checkbox.isSelected()) {//choice 0
            tempBoolArray.set(0, true);
        } else if (option_2_checkbox.isSelected()) {
            tempStrArray.set(0, option_2_text.getText());
            tempBoolArray.set(1, true);
        } else if (option_3_checkbox.isSelected()) {
            tempStrArray.set(1, option_3_text.getText());
            tempBoolArray.set(2, true);
        } else if (option_4_checkbox.isSelected()) {
            tempStrArray.set(2, option_4_text.getText());
            tempBoolArray.set(3, true);
        }
        form.setapplication_type(tempBoolArray);
        form.setapplication_type_text(tempStrArray);

        form.setbrand_name(brand_name_text.getText());
        form.setfanciful_name(fanciful_name_text.getText());
        //form.setalcohol_content(Double.parseDouble(alcohol_content_text.getText()));
        form.setFormula(formula_text.getText());
        form.setlabel_text(label_text.getText());
        // Wines only
        if (alcohol_type_combobox.getValue().equals("Wine")) {
            form.setvintage_year(vintage_year_text.getText());
            form.setpH_level(Integer.parseInt(ph_level_text.getText()));
            form.setgrape_varietals(grape_varietals_text.getText());
            form.setwine_appellation(wine_appellation_text.getText());
        } else {
            form.setvintage_year(null);
            form.setpH_level(-1);
            form.setgrape_varietals(null);
            form.setwine_appellation(null);
        }

        form.setapplicant_street(applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        form.setapplicant_city(applicant_city_text.getText());
        form.setapplicant_state(applicant_state_text.getText());
        form.setapplicant_zip(applicant_zip_text.getText());
        form.setapplicant_country(applicant_country_text.getText());

        if (sameAsApplicantButton.isSelected()) {
            form.setmailing_address(form.getapplicant_street() + " " +
                    form.getapplicant_city() + " " + form.getapplicant_state() +
                    form.getapplicant_zip() + " " + form.getapplicant_country());
        } else {
            form.setmailing_address(mailing_addressText.getText());
        }

        form.setSignature(signature_text.getText());
        form.setphone_no(phone_no_text.getText());
        form.setEmail(email_text.getText());

        //TODO reference actual applicant name below, if need be

        //main.userData.tempForm.setApplicantID(applicant_nameText.getText());


        /*
        There was previously a setForm function here for the User class
        or Main class but it appears that it does not exist anymore
        */
    }


    public void createAgentForm(Form form){


        // Get Source info and set it to display for the Agent
        //source_combobox = new ComboBox(FXCollections.observableArrayList("Domestic", "Imported"));
        if(form.getSource().equals("Imported")) {
            //source_combobox.getSelectionModel().select(2);
            source_text.setPromptText("Imported");
        } else if (form.getSource().equals("Domestic")) {
            //source_combobox.getSelectionModel().select(1);
            source_text.setPromptText("Domestic");
        }

        // Get Alcohol Type info and set it to display for the Agent
        //alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        if(form.getalcohol_type().equals("Malt Beverages")) {
            //alcohol_type_combobox.getSelectionModel().select(1);
            alcohol_type_text.setPromptText("Malt Beverages");
        } else if (form.getalcohol_type().equals("Wine")) {
            //alcohol_type_combobox.getSelectionModel().select(2);
            alcohol_type_text.setPromptText("Wine");
        } else if (form.getalcohol_type().equals("Distilled Spirit")) {
            //alcohol_type_combobox.getSelectionModel().select(3);
            alcohol_type_text.setPromptText("Distilled Spirit");
        }

        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        option_1_checkbox = new CheckBox("Certificate of Label Approval");
        option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
        option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
        option_4_checkbox = new CheckBox("Resubmission After Rejection");

        ArrayList<Boolean> tempBoolArray = form.getapplication_type();
        ArrayList<String> tempStrArray = form.getapplication_type_text();
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
        if (alcohol_type_text.getText().equals("Wine")) {
            vintage_year_text.setText(form.getvintage_year());
            ph_level_text.setText(String.valueOf(form.getpH_level()));
            grape_varietals_text.setText(form.getgrape_varietals());
            wine_appellation_text.setText(form.getwine_appellation());
        }else {
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

        address_text.setPromptText(form.getapplicant_street() + ", " + form.getapplicant_city() + ", " + form.getapplicant_state() + " " + form.getapplicant_zip() + ", " + form.getapplicant_country());

        //mailing_addressText.setPromptText(form.getmailing_address());

        signature_text.setPromptText(form.getSignature());
        phone_no_text.setPromptText(form.getphone_no());
        email_text.setPromptText(form.getEmail());

        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/Initialization/images/" + form.getlabel_image());
            System.out.println("here");
            String localURL = file.toURI().toURL().toString();
            System.out.println("here now");
            Image image = new Image(localURL);
            System.out.println("here now now");
            label_image.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //@TODO: Put on UI
        //submit_date.setText(form.getsubmit_date().toString());

        //Agent Headers
        //form.setapproved_date(Date.valueOf(approved_date.getValue()));
        //TODO reference agent name if needed

        //form.setexpiration_date(Date.valueOf(expiration_date.getValue()));
        //form.setapproval_comments(approval_comments_text.getText());
    }

    public void createReviseForm(Form form) {

        // Get Source info and set it to display for the Agent
        //source_combobox = new ComboBox(FXCollections.observableArrayList("Domestic", "Imported"));
//        if (form.getSource().equals("Imported")) {
//            source_text.setText("Imported");
//            //source_text.setPromptText("Imported");
//        } else if (form.getSource().equals("Domestic")) {
//            source_text.setText("Domestic");
//            //source_text.setPromptText("Domestic");
//        }
//
//        // Get Alcohol Type info and set it to display for the Agent
//        //alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
//        if (form.getalcohol_type().equals("Malt Beverages")) {
//            source_text.setText("Malt Beverages");
//
//            //alcohol_content_text.setPromptText("Beer");
//        } else if (form.getalcohol_type().equals("Wine")) {
//            source_text.setText("Wine");
//            //alcohol_content_text.setPromptText("Wine");
//        } else if (form.getalcohol_type().equals("Distilled Spirit")) {
//            source_text.setText("Distilled Spirit");
//
//            //alcohol_content_text.setPromptText("Distilled Spirit");
//        }
//
//        // Initialize checkboxes
//        // Type of Application Check Boxes and their corresponding TextFields
//        option_1_checkbox = new CheckBox("Certificate of Label Approval");
//        option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
//        option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
//        option_4_checkbox = new CheckBox("Resubmission After Rejection");
//
//        //@TODO: Whatever this shit is supposed to do
//
//        ArrayList<Boolean> tempBoolArray = form.getapplication_type();
//        ArrayList<String> tempStrArray = form.getapplication_type_text();
//        if (tempBoolArray.get(0) == true) {//choice 0
//            option_1_checkbox.setSelected(true);
//        }
//        if (tempBoolArray.get(1) == true) {
//            option_2_text.setPromptText(tempStrArray.get(0));
//            option_2_checkbox.setSelected(true);
//        }
//        if (tempBoolArray.get(2) == true) {
//            option_3_text.setPromptText(tempStrArray.get(1));
//            option_3_checkbox.setSelected(true);
//        }
//        if (tempBoolArray.get(3) == true) {
//            option_4_text.setPromptText(tempStrArray.get(2));
//            option_4_checkbox.setSelected(true);
//        }
//
//        rep_id_text.setText(form.getrep_id());
//        permit_no_text.setText(form.getpermit_no());
//        serial_no_text.setText(form.getserial_no());
//        brand_name_text.setText(form.getbrand_name());
//        fanciful_name_text.setText(form.getfanciful_name());
//        alcohol_content_text.setText(String.valueOf(form.getalcohol_content()));
//        formula_text.setText(form.getFormula());
//        label_text.setText(form.getlabel_text());
//        // Wines only
//        vintage_year_text.setText(form.getvintage_year());
//        ph_level_text.setText(String.valueOf(form.getpH_level()));
//        grape_varietals_text.setText(form.getgrape_varietals());
//        wine_appellation_text.setText(form.getwine_appellation());
//
//        //TODO maybe seperate applicant_street_1_text and applicant_street_2_text because it might be too long
//        /*applicant_street_1_text.setPromptText(form.getapplicant_street());
//        applicant_city_text.setPromptText(form.getapplicant_city());
//        applicant_state_text.setPromptText(form.getapplicant_state());
//        applicant_zip_text.setPromptText(form.getapplicant_zip());
//        applicant_country_text.setPromptText(form.getapplicant_country());*/
//
//        address_text.setPromptText(form.getapplicant_street() + ", " + form.getapplicant_city() + ", " + form.getapplicant_state() + " " + form.getapplicant_zip() + ", " + form.getapplicant_country());
//
//        //mailing_addressText.setPromptText(form.getmailing_address());
//
//        signature_text.setPromptText(form.getSignature());
//        phone_no_text.setPromptText(form.getphone_no());
//        email_text.setPromptText(form.getEmail());


        //@TODO: Put on UI
        //submit_date.setText(form.getsubmit_date().toString());

        //Agent Headers
        //form.setapproved_date(Date.valueOf(approved_date.getValue()));
        //TODO reference agent name if needed

        //form.setexpiration_date(Date.valueOf(expiration_date.getValue()));
        //form.setapproval_comments(approval_comments_text.getText());
    }


    // TODO delete probably outdated method
    @FXML
    public void startApplication(){
        try{
            main.setDisplayToApply();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO use DBManager functions for applicant to choose form from list
    public void chooseForm () {
        Form review = new Form();

        main.setDisplayToAgentReview();

        //display the form
    }

    @FXML
    public void submitForm() {
        int pH_level;
//        String ttb_id = ttb_id_label.getText();

        String rep_id = rep_id_text.getText();
        String permit_no = permit_no_text.getText();
        String serial_no = serial_no_text.getText();

        String source = (String) source_combobox.getValue();

        String alcohol_type = (String) alcohol_type_combobox.getValue();

        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> application_type = new ArrayList<Boolean>();
        for (int i = 0; i < 4; i++) {
            application_type.add(false);
        }
        ArrayList<String> application_type_text = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            application_type_text.add("");
        }
        if (option_1_checkbox.isSelected()) {//choice 0
            application_type.set(0, true);
        }
        if (option_2_checkbox.isSelected()) {
            application_type_text.set(0, option_2_text.getText());
            application_type.set(1, true);
        }
        if (option_3_checkbox.isSelected()) {
            application_type_text.set(1, option_3_text.getText());
            application_type.set(2, true);
        }
        if (option_4_checkbox.isSelected()) {
            application_type_text.set(2, option_4_text.getText());
            application_type.set(3, true);
        }

        String brand_name = brand_name_text.getText();
        String fanciful_name = (fanciful_name_text.getText());
        Double alcohol_content = (Double.parseDouble(alcohol_content_text.getText()));
        String formula = (formula_text.getText());
        String labeltext = label_text.getText();

        String vintage_year;
        String grape_varietals;
        String wine_appellation;
        if (alcohol_type_combobox.getValue().equals("Wine")){
            vintage_year = (vintage_year_text.getText());
            grape_varietals = (grape_varietals_text.getText());
            pH_level = (Integer.parseInt(ph_level_text.getText()));
            wine_appellation = (wine_appellation_text.getText());
        } else {
            vintage_year = (null);
            grape_varietals = (null);
            pH_level = -1;
            wine_appellation = (null);
        }

        String applicant_street = (applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        String applicant_city = (applicant_city_text.getText());
        String applicant_state = (applicant_state_text.getText());
        String applicant_zip = (applicant_zip_text.getText());
        String applicant_country = (applicant_country_text.getText());

        String mailing_address;
        if (sameAsApplicantButton.isSelected()) {
            mailing_address = (applicant_street + " " +
                    applicant_city + " " + applicant_state +
                    applicant_zip + " " + applicant_country);
        } else {
            mailing_address = (mailing_name_text.getText() + mailing_street_1_text.getText()
                    + mailing_street_2_text.getText() + mailing_city_text.getText() + mailing_zip_text.getText()
                    + mailing_country_text.getText() + mailing_state_text.getText());
        }

        String signature = (signature_text.getText());
        String phone_no = (phone_no_text.getText());
        String email = (email_text.getText());
        Date submitdate = new Date(System.currentTimeMillis());


        //TODO Update image when adding image display things
        Form form = new Form(rep_id, permit_no, source, serial_no, alcohol_type,
                brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state,
                applicant_zip, applicant_country, mailing_address, formula, phone_no, email,
                labeltext, main.userData.form.getlabel_image(), submitdate, signature, "Pending", null, main.userData.getUserInformation().getUid(), null, null,
                vintage_year, pH_level, grape_varietals, wine_appellation, application_type, application_type_text,
                null);

        DBManager.persistForm(form);

        //TODO return to applicant's application list page
    }

    @FXML
    public void submitRevisedForm() {
        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> application_type = new ArrayList<Boolean>();
        for (int i = 0; i < 4; i++) {
            application_type.add(false);
        }
        ArrayList<String> application_type_text = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            application_type_text.add("");
        }
        if (option_1_checkbox.isSelected()) {//choice 0
            application_type.set(0, true);
        }
        if (option_2_checkbox.isSelected()) {
            application_type_text.set(0, option_2_text.getText());
            application_type.set(1, true);
        }
        if (option_3_checkbox.isSelected()) {
            application_type_text.set(1, option_3_text.getText());
            application_type.set(2, true);
        }
        if (option_4_checkbox.isSelected()) {
            application_type_text.set(2, option_4_text.getText());
            application_type.set(3, true);
        }

        String brand_name = brand_name_text.getText();
        String fanciful_name = (fanciful_name_text.getText());
        Double alcohol_content = (Double.parseDouble(alcohol_content_text.getText()));
        String formula = (formula_text.getText());
        String labeltext = label_text.getText();

        // Wines only
        String vintage_year = (vintage_year_text.getText());
        int pH_level = (Integer.parseInt(ph_level_text.getText()));
        String grape_varietals = (grape_varietals_text.getText());
        String wine_appellation = (wine_appellation_text.getText());

        String applicant_street = (applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        String applicant_city = (applicant_city_text.getText());
        String applicant_state = (applicant_state_text.getText());
        String applicant_zip = (applicant_zip_text.getText());
        String applicant_country = (applicant_country_text.getText());

        String mailing_address;
        if (sameAsApplicantButton.isSelected()) {
            mailing_address = (applicant_street + " " +
                    applicant_city + " " + applicant_state +
                    applicant_zip + " " + applicant_country);
        } else {
            mailing_address = (mailing_addressText.getText());
        }

        String signature = (signature_text.getText());
        String phone_no = (phone_no_text.getText());
        String email = (email_text.getText());


        Date date = new Date(0);
        Date submitdate = new Date(System.currentTimeMillis());
        Form form = new Form(main.userData.form.getttb_id(), main.userData.form.getrep_id(), main.userData.form.getpermit_no(), main.userData.form.getSource(), main.userData.form.getserial_no(), main.userData.form.getalcohol_type(),
                brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state,
                applicant_zip, applicant_country, mailing_address, formula, phone_no, email,
                labeltext, main.userData.form.getlabel_image(), submitdate, signature, main.userData.form.getStatus(), "agent_id", "applicant_id", main.userData.form.getapproved_date(), main.userData.form.getexpiration_date(),
                vintage_year, pH_level, grape_varietals, wine_appellation, application_type, application_type_text,
                "approval_comments");

        DBManager.updateForm(form);
    }

    @FXML
    public void returnToMainPage(){
        try{
            if(main.userData.getUserInformation().getAuthenticationLevel() == 1) {
                main.setDisplayToApplicantMain();
            }
            else if(main.userData.getUserInformation().getAuthenticationLevel() >= 2){

                main.setDisplayToAgentMain();
            }
            else{
                main.setDisplayToDefaultMain();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO literally the same as returnToMainPage but to their respective lists of applications
    @FXML
    public void back() {
        try{
            if(main.userData.getUserInformation().getAuthenticationLevel() == 1) {
                //set display to list of Applicant applications
            }
            else if(main.userData.getUserInformation().getAuthenticationLevel() >= 2){
                //set display to list of applications to review
            }
            else{
                //you are not supposed to be here as a guest user, return to main page
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void acceptApplication() {
        // Most of this can probably be taken from FormController

        long currentDate = System.currentTimeMillis();
        long expirationDate = currentDate + 157784630000L;  // Expires in 5 years

        Date accepted = new Date(currentDate);
        Date expiration = new Date(expirationDate);

        form.setStatus("Accepted");
        form.setapproved_date(accepted);
        form.setexpiration_date(expiration);
        form.setapproval_comments(approval_comments_text.getText());
        //application.setAgentID();

        // UPDATE query using DB Manager to change status field to 'Accepted'
        // Agent Name field, expiration date field
        DBManager.updateForm(form);


        // Close application
        this.closeApplication();
    }

    @FXML
    public void rejectApplication() {
        // Most of this can probably be taken from FormController

        form.setStatus("Rejected");
        form.setapproval_comments(approval_comments_text.getText());

        // UPDATE query using DB Manager to change status field to 'Rejected'
        // Agent Name field, expiration date field
        DBManager.updateForm(form);

        // Close application
        this.closeApplication();
    }

    @FXML
    public void closeApplication() {

        // Close the window
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();

        //@TODO: Refresh the TableView's contents
        // Update our resultset???
        //(this.main, resultsList);
        //resultsTable.refresh();

    }

    @FXML
    public void displayApplicantResults() {
        ObservableList<AgentRecord> olAR = FXCollections.observableArrayList();
        System.out.println(main.userData.getUserInformation().getAuthenticationLevel());
        olAR = DBManager.findForms(main.userData.getUserInformation());
        System.out.println(olAR);
        // Query for batch
        // Display batch in table
        resultsTable.setItems(olAR);

        // This block monitors the user's interaction with the tableview,
        //  determining when they double-click a row
        resultsTable.setRowFactory(tv -> {
            TableRow<AgentRecord> row = new TableRow<>();

            // Open application if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AgentRecord rowData = row.getItem();

                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");

                    // Get form form DB using selected row's ID
                    try {
                        Form viewForm = DBManager.findSingleForm(rowData.getIDNo(), fieldList);
                        // Open selected form in new window
                        main.userData.setForm(viewForm);
                        main.setDisplayToReviseForm(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }

    @FXML
    public void browseForFile() {
        FileChooser fc = new FileChooser();
        String currentDir = System.getProperty("user.dir");
//        System.out.println(currentDir);

        fc.setInitialDirectory(new File(currentDir));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files (.jpg .png)", "*.jpg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);


        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                label_image.setImage(image);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid File");
        }

        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

        String newFileName = selectedFile.getName().split("\\.")[0] + dateFormat.format(date) + "." + selectedFile.getName().split("\\.")[1];
        File destInSys = new File(System.getProperty("user.dir") + "/src/main/resources/Initialization/images/" + newFileName);
        try {
            Files.copy(selectedFile.toPath(), destInSys.toPath(), StandardCopyOption.REPLACE_EXISTING, NOFOLLOW_LINKS);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        String path = getClass().getResource("images/").toExternalForm();
//        System.out.println(path);
        main.userData.getForm().setlabel_image(newFileName);
        try {
            System.out.println("here");
            String path = (System.getProperty("user.dir") + "/src/main/resources/Initialization/images/" + newFileName);
            File file = new File(path);
            String localURL = file.toURI().toURL().toString();
            Image image = new Image(localURL);
            System.out.println("Now here");
            System.out.println("down");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //        Image image = new Image(getClass().getResource("images/" + newFileName).toExternalForm());

//        label_image.setImage(image);

    }
}
