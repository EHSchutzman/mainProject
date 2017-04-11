package Form;
import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;

// TODO checkboxes and their corresponding lists from Form object and choiceboxes may be the source of errors
public class FormController{
    public int currentPage;

    private Main main;

    public void setDisplay(Main main) {
        this.main = main;
    }

    @FXML
    public Button mainPageButton;

    @FXML
    public Button nextButton;

    // Label Info
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

    // TODO add label image thing (?)
    @FXML
    public TextField label_text;

    // Wine only
    @FXML
    public TextField vintage_year_text;
    @FXML
    public TextField pH_level_text;
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
    public CheckBox mailing_addressCBox;
    @FXML
    public TextArea mailing_addressText;

    @FXML
    public TextField phone_no_text;
    @FXML
    public TextField signature_text;
    @FXML
    public TextField email_text;
    //@FXML
    //public TextField applicant_nameText;


    //TTB Use (accept/reject)
    @FXML
    public Button accept_button;
    @FXML
    public Button reject_button;
    //
    @FXML
    public DatePicker submit_date;
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

    public void createApplicantForm(){

        statusLabel.setText(main.userData.form.getStatus());

        // TODO make a random number generator
        main.userData.form.setttb_id(main.userData.form.makeUniqueID());
        main.userData.form.setrep_id(rep_id_text.getText());
        main.userData.form.setpermit_no(permit_no_text.getText());
        main.userData.form.setserial_no(serial_no_text.getText());

        // Initialize Source ChoiceBox and get value
        source_combobox = new ComboBox(FXCollections.observableArrayList("Domestic", "Imported"));
        main.userData.form.setSource((String)source_combobox.getValue());

        // Initialize Alcohol Type ChoiceBox, get and set value
        alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        main.userData.form.setalcohol_type((String)alcohol_type_combobox.getValue());

        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        option_1_checkbox = new CheckBox("Certificate of Label Approval");
        option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
        option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
        option_4_checkbox = new CheckBox("Resubmission After Rejection");

        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> tempBoolArray = main.userData.form.getapplication_type();
        ArrayList<String> tempStrArray = main.userData.form.getapplication_type_text();
        if (option_1_checkbox.isSelected()) {//choice 0
            tempBoolArray.set(0, true);
        } else if (option_2_checkbox.isSelected()) {
            tempStrArray.set(1, option_2_text.getText());
            tempBoolArray.set(1, true);
        } else if (option_3_checkbox.isSelected()) {
            tempStrArray.set(2, option_3_text.getText());
            tempBoolArray.set(2, true);
        } else if (option_4_checkbox.isSelected()) {
            tempStrArray.set(3, option_4_text.getText());
            tempBoolArray.set(3, true);
        }
        main.userData.form.setapplication_type(tempBoolArray);
        main.userData.form.setapplication_type_text(tempStrArray);

        main.userData.form.setbrand_name(brand_name_text.getText());
        main.userData.form.setfanciful_name(fanciful_name_text.getText());
        main.userData.form.setalcohol_content(Double.parseDouble(alcohol_content_text.getText()));
        main.userData.form.setFormula(formula_text.getText());
        main.userData.form.setlabel_text(label_text.getText());
        // Wines only
        main.userData.form.setvintage_year(vintage_year_text.getText());
        main.userData.form.setpH_level(Integer.parseInt(pH_level_text.getText()));
        main.userData.form.setgrape_varietals(grape_varietals_text.getText());
        main.userData.form.setwine_appellation(wine_appellation_text.getText());

        main.userData.form.setapplicant_street(applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        main.userData.form.setapplicant_city(applicant_city_text.getText());
        main.userData.form.setapplicant_state(applicant_state_text.getText());
        main.userData.form.setapplicant_zip(applicant_zip_text.getText());
        main.userData.form.setapplicant_country(applicant_country_text.getText());

        if (mailing_addressCBox.isSelected()) {
            main.userData.form.setmailing_address(main.userData.form.getapplicant_street() + " " +
                    main.userData.form.getapplicant_city() + " " + main.userData.form.getapplicant_state() +
                    main.userData.form.getapplicant_zip() + " " + main.userData.form.getapplicant_country());
        } else {
            main.userData.form.setmailing_address(mailing_addressText.getText());
        }

        main.userData.form.setSignature(signature_text.getText());
        main.userData.form.setphone_no(phone_no_text.getText());
        main.userData.form.setEmail(email_text.getText());

        main.userData.form.setsubmit_date(Date.valueOf(submit_date.getValue()));
        //TODO reference actual applicant name below, if need be

        //main.userData.tempForm.setApplicantID(applicant_nameText.getText());


        /*
        There was previously a setForm function here for the User class
        or Main class but it appears that it does not exist anymore
        */
    }

    // THIS FUNCTION HAS BEEN MOVED TO THE WORKFLOW CONTROLLER
    /*
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
        alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        if(form.getalcohol_type().equals("Beer")) {
            //alcohol_type_combobox.getSelectionModel().select(1);
            alcohol_content_text.setPromptText("Beer");
        } else if (form.getalcohol_type().equals("Wine")) {
            //alcohol_type_combobox.getSelectionModel().select(2);
            alcohol_content_text.setPromptText("Wine");
        } else if (form.getalcohol_type().equals("Distilled Spirit")) {
            //alcohol_type_combobox.getSelectionModel().select(3);
            alcohol_content_text.setPromptText("Distilled Spirit");
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
        } else if (tempBoolArray.get(1) == true) {
            option_2_text.setPromptText(tempStrArray.get(1));
            option_2_checkbox.setSelected(true);
        } else if (tempBoolArray.get(2) == true) {
            option_3_text.setPromptText(tempStrArray.get(2));
            option_3_checkbox.setSelected(true);
        } else if (tempBoolArray.get(3) == true) {
            option_4_text.setPromptText(tempStrArray.get(3));
            option_4_checkbox.setSelected(true);
        }

        rep_id_text.setPromptText(main.userData.form.getrep_id());
        permit_no_text.setPromptText(main.userData.form.getpermit_no());
        serial_no_text.setPromptText(main.userData.form.getserial_no());
        brand_name_text.setPromptText(main.userData.form.getbrand_name());
        fanciful_name_text.setPromptText(main.userData.form.getfanciful_name());
        alcohol_content_text.setPromptText(String.valueOf(main.userData.form.getalcohol_content()));
        formula_text.setPromptText(main.userData.form.getformula());
        label_text.setPromptText(main.userData.form.getlabel_text());

        // Wines only
        vintage_year_text.setPromptText(form.getvintage_year());
        pH_level_text.setPromptText(String.valueOf(form.getpH_level()));
        grape_varietals_text.setPromptText(form.getgrape_varietals());
        wine_appellation_text.setPromptText(form.getwine_appellation());

        //TODO maybe seperate applicant_street_1_text and applicant_street_2_text because it might be too long
        applicant_street_1_text.setPromptText(form.getapplicant_street());
        applicant_city_text.setPromptText(form.getapplicant_city());
        applicant_state_text.setPromptText(form.getapplicant_state());
        applicant_zip_text.setPromptText(form.getapplicant_zip());
        applicant_country_text.setPromptText(form.getapplicant_country());

        mailing_addressText.setPromptText(form.getmailing_address());

        signature_text.setPromptText(form.getSignature());
        phone_no_text.setPromptText(form.getphone_no());
        email_text.setPromptText(form.getEmail());

        submit_date.setValue(form.getsubmit_date().toLocalDate());

        //Agent Headers
        form.setapproved_date(Date.valueOf(approved_date.getValue()));
        //TODO reference agent name if needed

        form.setexpiration_date(Date.valueOf(expiration_date.getValue()));
        form.setapproval_comments(approval_comments_text.getText());
    }
    */

    // Probably outdated method
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
        Form submit = new Form();
        //TODO return to applicant's application list page
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
    public void Back(){
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


}
