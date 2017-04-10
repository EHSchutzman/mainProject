package Form;
import DatabaseSearch.QueryBuilder;
import DatabaseSearch.TTB_database;
import Initialization.Main;
import com.sun.tools.javac.comp.Check;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.soap.Text;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;

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

    @FXML
    public TextField rep_idText;

    @FXML
    public TextField label_textText;

    @FXML
    public TextField permit_noText;

    @FXML
    public TextField serial_noText;

    @FXML
    public TextField formulaText;

    //TODO change radio buttons to choiceboxes, this includes applicant form and agent form versions (one gets values the other is set)
    @FXML
    public ChoiceBox sourceChoice;
    @FXML
    public ChoiceBox alcohol_typeChoice;

    //CheckBoxes and TextFields for the Type of Application
    //
    @FXML
    public CheckBox labelApprovalCBox;
    @FXML
    public CheckBox labelExemptionCBox;
    @FXML
    public TextField labelExemptionText; //For sale in <state> only
    @FXML
    public CheckBox distinctBottleCBox;
    @FXML
    public TextField distinctBottleText; //Bottle capacity before closure
    @FXML
    public CheckBox resubmissionCBox;
    @FXML
    public TextField resubmissionTTBIDText;


    @FXML
    public TextField brand_nameText;

    @FXML
    public TextField fanciful_nameText;

    //Address
    //street1 and street2 correspond to applicant_street
    @FXML
    public TextField street1Text;
    @FXML
    public TextField street2Text;
    @FXML
    public TextField applicant_cityText;
    @FXML
    public TextField applicant_stateText;
    @FXML
    public TextField applicant_zipText;
    @FXML
    public TextField applicant_countryText;

    //Mailing Address
    @FXML
    public CheckBox mailing_addressCBox;
    @FXML
    public TextArea mailing_addressText;

    @FXML
    public TextField phone_noText;

    @FXML
    public TextField emailText;

    @FXML
    public TextField alcohol_contentText;

    //Wine only
    @FXML
    public TextField vintage_yearText;
    @FXML
    public TextField pH_levelText;
    @FXML
    public TextField grape_varietalsText;
    @FXML
    public TextField wine_appellationText;

    //Applicant's Certification
    @FXML
    public DatePicker submit_date;
    @FXML
    public TextField applicant_nameText;
    @FXML
    public TextField signatureText;

    //TTB Use (accept/reject)
    @FXML
    public Button acceptButton;
    @FXML
    public Button rejectButton;
    //
    @FXML
    public DatePicker approved_date;
    @FXML
    public TextField agent_nameText;
    @FXML
    public DatePicker expiration_date;
    @FXML
    public TextArea approval_commentsText;
    @FXML
    public Label statusLabel; //TODO add to UI and ApplicantForm

    public void createApplicantForm(){
        Form form = new Form();

        statusLabel.setText(main.userData.tempForm.getStatus());

        // TODO make a random number generator
        main.userData.tempForm.setttb_id(main.userData.tempForm.makeUniqueID());
        main.userData.tempForm.setrep_id(rep_idText.getText());
        main.userData.tempForm.setpermit_no(permit_noText.getText());
        main.userData.tempForm.setserial_no(serial_noText.getText());

        /*
        // Initializes radio buttons
        // Source
        // Make radio buttons and group them
        domesticRadio = new RadioButton("Domestic");
        importedRadio = new RadioButton("Imported");
        // Set selected
        importedRadio.setSelected(true);
        // Create group for radio buttons
        sourceGroup = new ToggleGroup();
        domesticRadio.setToggleGroup(sourceGroup);
        importedRadio.setToggleGroup(sourceGroup);
        */

        // Initialize Source ChoiceBox and get value
        sourceChoice = new ChoiceBox(FXCollections.observableArrayList("Domestic", "Imported"));
        main.userData.tempForm.setSource((String)sourceChoice.getValue());

        /*
        // Alcohol Type
        // Make radio buttons and group them
        beerRadio = new RadioButton("Beer");
        wineRadio = new RadioButton("Wine");
        spiritsRadio = new RadioButton("Distilled Spirit");
        // Set selected
        beerRadio.setSelected(true);
        // Create group for radio buttons
        typeGroup = new ToggleGroup();
        beerRadio.setToggleGroup(typeGroup);
        wineRadio.setToggleGroup(typeGroup);
        spiritsRadio.setToggleGroup(typeGroup);
        */

        // Initialize Alcohol Type ChoiceBox, get and set value
        alcohol_typeChoice = new ChoiceBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        main.userData.tempForm.setalcohol_type((String)alcohol_typeChoice.getValue());

        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        labelApprovalCBox = new CheckBox("Certificate of Label Approval");
        labelExemptionCBox = new CheckBox("Certificate of Exemption from Label Approval");
        distinctBottleCBox = new CheckBox("Distinctive Liquor Bottle Approval");
        resubmissionCBox = new CheckBox("Resubmission After Rejection");

        /*
        // Determine inputted source
        if (domesticRadio.isSelected()) {
            main.userData.tempForm.setSource("Domestic");
        } else if (importedRadio.isSelected()) {
            main.userData.tempForm.setSource("Imported");
        }
        //determine inputted type
        if (beerRadio.isSelected()) {
            main.userData.tempForm.setalcohol_type("Beer");
        } else if (wineRadio.isSelected()) {
            main.userData.tempForm.setalcohol_type("Wine");
        } else if (spiritsRadio.isSelected()) {
            main.userData.tempForm.setalcohol_type("Distilled Spirit");
        }
        */

        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> tempBoolArray = main.userData.tempForm.getapplication_type();
        ArrayList<String> tempStrArray = main.userData.tempForm.getapplication_type_text();
        if (labelApprovalCBox.isSelected()) {//choice 0
            tempBoolArray.set(0, true);
        } else if (labelExemptionCBox.isSelected()) {
            tempStrArray.set(1, labelExemptionText.getText());
            tempBoolArray.set(1, true);
        } else if (distinctBottleCBox.isSelected()) {
            tempStrArray.set(2, distinctBottleText.getText());
            tempBoolArray.set(2, true);
        } else if (resubmissionCBox.isSelected()) {
            tempStrArray.set(3, resubmissionTTBIDText.getText());
            tempBoolArray.set(3, true);
        }
        main.userData.tempForm.setapplication_type(tempBoolArray);
        main.userData.tempForm.setapplication_type_text(tempStrArray);

        main.userData.tempForm.setbrand_name(brand_nameText.getText());
        main.userData.tempForm.setfanciful_name(fanciful_nameText.getText());
        main.userData.tempForm.setalcohol_content(Double.parseDouble(alcohol_contentText.getText()));
        main.userData.tempForm.setFormula(formulaText.getText());
        main.userData.tempForm.setlabel_text(label_textText.getText());
        // Wines only
        main.userData.tempForm.setvintage_year(vintage_yearText.getText());
        main.userData.tempForm.setpH_level(Integer.parseInt(pH_levelText.getText()));
        main.userData.tempForm.setgrape_varietals(grape_varietalsText.getText());
        main.userData.tempForm.setwine_appellation(wine_appellationText.getText());

        main.userData.tempForm.setapplicant_street(street1Text.getText() + " " + street2Text.getText());
        main.userData.tempForm.setapplicant_city(applicant_cityText.getText());
        main.userData.tempForm.setapplicant_state(applicant_stateText.getText());
        main.userData.tempForm.setapplicant_zip(applicant_zipText.getText());
        main.userData.tempForm.setapplicant_country(applicant_countryText.getText());

        if (mailing_addressCBox.isSelected()) {
            main.userData.tempForm.setmailing_address(main.userData.tempForm.getapplicant_street() + " " +
                    main.userData.tempForm.getapplicant_city() + " " + main.userData.tempForm.getapplicant_state() +
                    main.userData.tempForm.getapplicant_zip() + " " + main.userData.tempForm.getapplicant_country());
        } else {
            main.userData.tempForm.setmailing_address(mailing_addressText.getText());
        }

        main.userData.tempForm.setSignature(signatureText.getText());
        main.userData.tempForm.setphone_no(phone_noText.getText());
        main.userData.tempForm.setEmail(emailText.getText());

        main.userData.tempForm.setsubmit_date(Date.valueOf(submit_date.getValue()));
        //TODO reference actual applicant name below, if need be
        //main.userData.tempForm.setApplicantID(applicant_nameText.getText());

        /*
        There was previously a setForm function here for the User class
        or Main class but it appears that it does not exist anymore
        */
    }

    public void createAgentForm(){
        /*
        // Initializes necessary radio buttons
        // Source
        domesticRadio=new RadioButton("Domestic");
        importedRadio=new RadioButton("Imported");
        //set selected
        if(main.userData.tempForm.getSource().equals("Imported")) {
            importedRadio.setSelected(true);
        } else if (main.userData.tempForm.getSource().equals("Domestic")) {
            domesticRadio.setSelected(true);
        }
        */

        // Get Source info and set it to display for the Agent
        sourceChoice = new ChoiceBox(FXCollections.observableArrayList("Domestic", "Imported"));
        if(main.userData.tempForm.getSource().equals("Imported")) {
            sourceChoice.getSelectionModel().select(2);
        } else if (main.userData.tempForm.getSource().equals("Domestic")) {
            sourceChoice.getSelectionModel().select(1);
        }

        /*
        // Alcohol Type
        beerRadio=new RadioButton("Beer");
        wineRadio=new RadioButton("Wine");
        spiritsRadio=new RadioButton("Distilled Spirit");
        //set selected
        if(main.userData.tempForm.getalcohol_type()().equals("Beer")) {
            beerRadio.setSelected(true);
        } else if (main.userData.tempForm.getalcohol_type()().equals("Wine")) {
            wineRadio.setSelected(true);
        } else if (main.userData.tempForm.getalcohol_type()().equals("Distilled Spirit")) {
            spiritsRadio.setSelected(true);
        }
        */

        // Get Alcohol Type info and set it to display for the Agent
        alcohol_typeChoice = new ChoiceBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        if(main.userData.tempForm.getalcohol_type().equals("Beer")) {
            alcohol_typeChoice.getSelectionModel().select(1);
        } else if (main.userData.tempForm.getalcohol_type().equals("Wine")) {
            alcohol_typeChoice.getSelectionModel().select(2);
        } else if (main.userData.tempForm.getalcohol_type().equals("Distilled Spirit")) {
            alcohol_typeChoice.getSelectionModel().select(3);
        }

        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        labelApprovalCBox = new CheckBox("Certificate of Label Approval");
        labelExemptionCBox = new CheckBox("Certificate of Exemption from Label Approval");
        distinctBottleCBox = new CheckBox("Distinctive Liquor Bottle Approval");
        resubmissionCBox = new CheckBox("Resubmission After Rejection");

        ArrayList<Boolean> tempBoolArray = main.userData.tempForm.getapplication_type();
        ArrayList<String> tempStrArray = main.userData.tempForm.getapplication_type_text();
        if (tempBoolArray.get(0) == true) {//choice 0
            labelApprovalCBox.setSelected(true);
        } else if (tempBoolArray.get(1) == true) {
            labelExemptionText.setPromptText(tempStrArray.get(1));
            labelExemptionCBox.setSelected(true);
        } else if (tempBoolArray.get(2) == true) {
            distinctBottleText.setPromptText(tempStrArray.get(2));
            distinctBottleCBox.setSelected(true);
        } else if (tempBoolArray.get(3) == true) {
            resubmissionTTBIDText.setPromptText(tempStrArray.get(3));
            resubmissionCBox.setSelected(true);
        }

        rep_idText.setPromptText(main.userData.tempForm.getrep_id());
        permit_noText.setPromptText(main.userData.tempForm.getpermit_no());
        serial_noText.setPromptText(main.userData.tempForm.getserial_no());
        brand_nameText.setPromptText(main.userData.tempForm.getbrand_name());
        fanciful_nameText.setPromptText(main.userData.tempForm.getfanciful_name());
        alcohol_contentText.setPromptText(String.valueOf(main.userData.tempForm.getalcohol_content()));
        formulaText.setPromptText(main.userData.tempForm.getFormula());
        label_textText.setPromptText(main.userData.tempForm.getlabel_text());
        // Wines only
        vintage_yearText.setPromptText(main.userData.tempForm.getvintage_year());
        pH_levelText.setPromptText(String.valueOf(main.userData.tempForm.getpH_level()));
        grape_varietalsText.setPromptText(main.userData.tempForm.getgrape_varietals());
        wine_appellationText.setPromptText(main.userData.tempForm.getwine_appellation());

        //TODO maybe seperate street1Text and street2Text because it might be too long
        street1Text.setPromptText(main.userData.tempForm.getapplicant_street());
        applicant_cityText.setPromptText(main.userData.tempForm.getapplicant_city());
        applicant_stateText.setPromptText(main.userData.tempForm.getapplicant_state());
        applicant_zipText.setPromptText(main.userData.tempForm.getapplicant_zip());
        applicant_countryText.setPromptText(main.userData.tempForm.getapplicant_country());

        mailing_addressText.setPromptText(main.userData.tempForm.getmailing_address());

        signatureText.setPromptText(main.userData.tempForm.getSignature());
        phone_noText.setPromptText(main.userData.tempForm.getphone_no());
        emailText.setPromptText(main.userData.tempForm.getEmail());

        submit_date.setValue(main.userData.tempForm.getsubmit_date().toLocalDate());

        //Agent Headers
        main.userData.tempForm.setapproved_date(Date.valueOf(approved_date.getValue()));
        //TODO reference agent name if needed
        main.userData.tempForm.setAgentName(agent_nameText.getText());
        main.userData.tempForm.setexpiration_date(Date.valueOf(expiration_date.getValue()));
        main.userData.tempForm.setapproval_comments(approval_commentsText.getText());

        /*
        There was previously a setForm function here for the User class
        or Main class but it appears that it does not exist anymore
        */
    }

    // Probably outdated method
    @FXML
    public void startApplication(){
        try{
            main.setDisplayToApply();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO use DBManager functions to display agent review page from list
    //TODO make a function for applicant to choose form from list
    public void chooseForm () {
        Form review = new Form();

        main.setDisplayToAgentReview();

        //display the form
    }

    @FXML
    public void approveForm(){
        Form review = new Form();
        returnToMainPage(); //TODO return to the list of applications
    }
    @FXML
    public void rejectForm(){
        Form review = new Form();
        returnToMainPage(); //TODO return to the list of applications
    }

    @FXML
    public void returnToMainPage(){
        try{
            if(main.userData.getUserInformation().getAuthentication() == 1) {
                main.setDisplayToApplicantMain();
            }
            else if(main.userData.getUserInformation().getAuthentication() >= 2){
                main.setDisplayToAgentMain();
            }
            else{
                main.setDisplayToDefaultMain();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
