package Form;
import DatabaseSearch.QueryBuilder;
import DatabaseSearch.TTB_database;
import Initialization.Main;
import com.sun.tools.javac.comp.Check;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;

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
    public TextField repIDNoText;
    @FXML
    public TextField basicPermitText;

    @FXML
    public TextField containerText;

    @FXML
    public TextField permitNoText;

    @FXML
    public TextField serialNoText;

    @FXML
    public TextField formulaText;

    //RadioButtons to choose domestic or imported
    @FXML
    public ToggleGroup sourceGroup;
    @FXML
    public RadioButton domesticRadio;
    @FXML
    public RadioButton importedRadio;

    //RadioButtons to choose beer or wine
    @FXML
    public ToggleGroup typeGroup;
    @FXML
    public RadioButton beerRadio;
    @FXML
    public RadioButton wineRadio;
    @FXML
    public RadioButton spiritsRadio;

    //CheckBoxes and TextFields for the type of application
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
    public TextField brandNameText;

    @FXML
    public TextField fancifulNameText;

    //Address
    //@FXML
    //public TextField companyNameText;
    @FXML
    public TextField street1Text;
    @FXML
    public TextField street2Text;
    @FXML
    public TextField cityText;
    @FXML
    public TextField stateText;
    @FXML
    public TextField zipCodeText;
    @FXML
    public TextField countryText;

    //Mailing Address
    @FXML
    public CheckBox mailingCBox;
    @FXML
    public TextArea mailingAddressText;

    @FXML
    public TextField phoneNumberText;

    @FXML
    public TextField emailText;

    @FXML
    public TextField alcoholContentText;

    //Wine only
    @FXML
    public TextField vintageYearText;
    @FXML
    public TextField pHLevelText;

    //Applicant's Certification
    @FXML
    public DatePicker completedDate;
    @FXML
    public TextField applicantNameText;
    @FXML
    public TextField signatureText;

    //TTB Use (accept/reject)
    @FXML
    public Button acceptButton;
    @FXML
    public Button rejectButton;
    //
    @FXML
    public DatePicker approvalDate;
    @FXML
    public TextField agentNameText;
    @FXML
    public DatePicker expirationDate;
    @FXML
    public TextArea commentText;


    //Label info page
    public Form createFormPage1() {
        //initializes necessary radio buttons
        //Source
        //make radio buttons and group them
        domesticRadio = new RadioButton("Domestic");
        importedRadio = new RadioButton("Imported");
        //set selected
        importedRadio.setSelected(true);
        //create group for radio buttons
        sourceGroup = new ToggleGroup();
        domesticRadio.setToggleGroup(sourceGroup);
        importedRadio.setToggleGroup(sourceGroup);

        //Type
        //make radio buttons and group them
        beerRadio = new RadioButton("Beer");
        wineRadio = new RadioButton("Wine");
        spiritsRadio = new RadioButton("Distilled Spirit");
        //set selected
        beerRadio.setSelected(true);
        //create group for radio buttons
        typeGroup = new ToggleGroup();
        beerRadio.setToggleGroup(typeGroup);
        wineRadio.setToggleGroup(typeGroup);
        spiritsRadio.setToggleGroup(typeGroup);

        //initialize checkboxes
        //Type of Application Check Boxes and their corresponding TextFields
        labelApprovalCBox = new CheckBox("Certificate of Label Approval");
        labelExemptionCBox = new CheckBox("Certificate of Exemption from Label Approval");
        distinctBottleCBox = new CheckBox("Distinctive Liquor Bottle Approval");
        resubmissionCBox = new CheckBox("Resubmission After Rejection");

        main.userData.tempForm.setTtbID(main.userData.tempForm.makeUniqueID());
        main.userData.tempForm.setRepID(repIDNoText.getText());
        main.userData.tempForm.setPermitNo(permitNoText.getText());

        //determine inputted source
        if (domesticRadio.isSelected()) {
            main.userData.tempForm.setSource("Domestic");
        } else if (importedRadio.isSelected()) {
            main.userData.tempForm.setSource("Imported");
        }
        //determine inputted type
        if (beerRadio.isSelected()) {
            main.userData.tempForm.setAlcoholType("Beer");
        } else if (wineRadio.isSelected()) {
            main.userData.tempForm.setAlcoholType("Wine");
        } else if (spiritsRadio.isSelected()) {
            main.userData.tempForm.setAlcoholType("Distilled Spirit");
        }
        //determine which checkboxes were selected
        //make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> tempBoolArray = main.userData.tempForm.getApplicationType();
        ArrayList<String> tempStrArray = main.userData.tempForm.getTypeText();
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
        main.userData.tempForm.setApplicationType(tempBoolArray);
        main.userData.tempForm.setTypeText(tempStrArray);

        main.userData.tempForm.setBrandName(brandNameText.getText());
        main.userData.tempForm.setFormula(formulaText.getText());

        return main.userData.tempForm;
    }

    //Applicant info page
    public Form createFormPage2() {
        main.userData.tempForm.setApplicantStreet(street1Text.getText() + " " + street2Text.getText());
        main.userData.tempForm.setApplicantCity(cityText.getText());
        main.userData.tempForm.setApplicantState(stateText.getText());
        main.userData.tempForm.setApplicantZip(zipCodeText.getText());
        main.userData.tempForm.setApplicantCountry(countryText.getText());

        return main.userData.tempForm;
    }

    //Applicant info page (mailing address)
    public Form createFormPage3() {
        if (mailingCBox.isSelected()) {
            main.userData.tempForm.setMailingAddress(main.userData.tempForm.getApplicantStreet() + " " +
                    main.userData.tempForm.getApplicantCity() + " " + main.userData.tempForm.getApplicantState() +
                    main.userData.tempForm.getApplicantZip() + " " + main.userData.tempForm.getApplicantCountry());
        } else {
            main.userData.tempForm.setMailingAddress(mailingAddressText.getText());
        }

        return main.userData.tempForm;
    }

    //Applicant info (phone # and email)
    public Form createFormPage4() {
        main.userData.tempForm.setPhoneNo(phoneNumberText.getText());
        main.userData.tempForm.setEmail(emailText.getText());

        return main.userData.tempForm;
    }

    //Additional label info
    public Form createFormPage5() {
        main.userData.tempForm.setLabelText(containerText.getText());
        main.userData.tempForm.setAlcoholContent(Double.parseDouble(alcoholContentText.getText()));
        main.userData.tempForm.setVintageYear(vintageYearText.getText());
        main.userData.tempForm.setPhLevel(Integer.parseInt(pHLevelText.getText()));
        //TODO convert LocalDate to Date and viceversa when necessary
        main.userData.tempForm.setSubmitDate(completedDate.getValue());
        main.userData.tempForm.setApplicantID(applicantNameText.getText());
        main.userData.tempForm.setSerialNo(serialNoText.getText());
        main.userData.tempForm.setSignature(signatureText.getText());

        return main.userData.tempForm;
    }



    //Label info page AGENTS ONLY
    public Form createAgentFormPage1() {
        //initializes necessary radio buttons
        //Source
        domesticRadio=new RadioButton("Domestic");
        importedRadio=new RadioButton("Imported");
        //set selected
        if(main.userData.tempForm.getSource().equals("Imported")) {
            importedRadio.setSelected(true);
        } else if (main.userData.tempForm.getSource().equals("Domestic")) {
            domesticRadio.setSelected(true);
        }

        //Type
        beerRadio=new RadioButton("Beer");
        wineRadio=new RadioButton("Wine");
        spiritsRadio=new RadioButton("Distilled Spirit");
        //set selected
        if(main.userData.tempForm.getAlcoholType().equals("Beer")) {
            beerRadio.setSelected(true);
        } else if (main.userData.tempForm.getAlcoholType().equals("Wine")) {
            wineRadio.setSelected(true);
        } else if (main.userData.tempForm.getAlcoholType().equals("Distilled Spirit")) {
            spiritsRadio.setSelected(true);
        }

        //initialize checkboxes
        //Type of Application Check Boxes and their corresponding TextFields
        labelApprovalCBox = new CheckBox("Certificate of Label Approval");
        labelExemptionCBox = new CheckBox("Certificate of Exemption from Label Approval");
        distinctBottleCBox = new CheckBox("Distinctive Liquor Bottle Approval");
        resubmissionCBox = new CheckBox("Resubmission After Rejection");

        ArrayList<Boolean> tempBoolArray = main.userData.tempForm.getApplicationType();
        ArrayList<String> tempStrArray = main.userData.tempForm.getTypeText();
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

        repIDNoText.setPromptText(main.userData.tempForm.getRepID());
        permitNoText.setPromptText(main.userData.tempForm.getPermitNo());
        brandNameText.setPromptText(main.userData.tempForm.getBrandName());

        return main.userData.tempForm;
    }

    //Applicant info page AGENTS ONLY
    public Form createAgentFormPage2() {
        street1Text.setPromptText(main.userData.tempForm.getApplicantStreet());
        cityText.setPromptText(main.userData.tempForm.getApplicantCity());
        stateText.setPromptText(main.userData.tempForm.getApplicantState());
        zipCodeText.setPromptText(main.userData.tempForm.getApplicantZip());
        countryText.setPromptText(main.userData.tempForm.getApplicantCountry());

        return main.userData.tempForm;
    }

    //Applicant info page (mailing address) AGENTS ONLY
    public Form createAgentFormPage3() {
        mailingAddressText.setPromptText(main.userData.tempForm.getMailingAddress());

        return main.userData.tempForm;
    }

    //Applicant info (phone # and email) AGENTS ONLY
    public Form createAgentFormPage4() {
        phoneNumberText.setPromptText(main.userData.tempForm.getPhoneNo());
        emailText.setPromptText(main.userData.tempForm.getEmail());

        return main.userData.tempForm;
    }

    //Additional label info AGENTS ONLY
    public Form createAgentFormPage5() {
        alcoholContentText.setPromptText(String.valueOf(main.userData.tempForm.getAlcoholContent()));
        vintageYearText.setPromptText(main.userData.tempForm.getVintageYear());
        pHLevelText.setPromptText(String.valueOf(main.userData.tempForm.getPhLevel()));
        completedDate.setValue(main.userData.tempForm.getSubmitDate());

        main.userData.tempForm.setApprovedDate(approvalDate.getValue());
        //TODO reference agent name if needed
        main.userData.tempForm.setAgentName(agentNameText.getText());
        main.userData.tempForm.setExpirationDate(expirationDate.getValue());
        main.userData.tempForm.setApprovalComments(commentText.getText());

        return main.userData.tempForm;
    }


    //TODO use DBManager functions to display agent review page
    public void chooseForm () {
        Form review = new Form();

        main.setDisplayToAgentReview();

        //display the form
    }

    public void createApplicantForm(){
        Form form = new Form();

        /*
        form.setApplicantName(applicantNameText.getText());
        form.setPhoneNumber(phoneNumberText.getText());
        form.setEmail(emailText.getText());
        form.setAddress(applicantAddress1.getText() + applicantAddress2.getText()); //finalize address stuff
        Double ttbID = Math.floor(Math.random());
        form.setTtbID(ttbID.toString());
        // TODO copy button code and put below
        form.setSource("Imported"); //TODO change to take input from buttons
        Date date = new Date(04102017);
        form.setSubmitDate(date.toLocalDate()); //TODO make this be today
        form.setRepID(repIDNoText.getText());
        form.setBrandName(brandNameText.getText());
        form.setTradeName(tradeNameText.getText());
        form.setFancifulName(fancifulNameText.getText());
        form.setPermitNo(basicPermitText.getText());
        form.setCompanyName(brandNameText.getText());
        form.setTradeName(tradeNameText.getText());
        form.setPhoneNumber(phoneNumberText.getText());
        form.setEmail(emailText.getText());
        form.setAlcoholContent(Double.parseDouble(alcoholContent.getText()));
        form.setAlcoholType(1);//TODO figure out what type is and make it work

        main.userData.getUserInformation().setForm(form);
        */
    }

    public void createAgentForm(){
        main.userData.getUserInformation().setForm(form);
    }

    @FXML
    public void startApplication(){
        try{
            main.setDisplayToApply();
        } catch (Exception e){
            e.printStackTrace();
        }

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
