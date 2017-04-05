package Form;
import DatabaseSearch.QueryBuilder;
import DatabaseSearch.TTB_database;
import Initialization.ActionController;
import Initialization.Main;
import DatabaseSearch.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FormController{
    private ArrayList<Form> listOfForms; //unique for each user (agent or applicant)
    Form tempForm = new Form();
    public int currentPage;

    private Main main;

    public void setDisplay(Main main) {
        this.main = main;
    }


    @FXML
    private Button nextButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField repIDText;

    @FXML
    private TextField permitNoText;

    //RadioButtons to choose domestic or imported
    @FXML
    private ToggleGroup sourceGroup;
    @FXML
    private RadioButton domesticRadio;
    @FXML
    private RadioButton importedRadio;

    //RadioButtons to choose beer or wine
    @FXML
    private ToggleGroup typeGroup;
    @FXML
    private RadioButton beerRadio; //use this to translate to int 901
    @FXML
    private RadioButton wineRadio; //use this to translate to int 80

    @FXML
    private TextField brandNameText;

    //Address
    @FXML
    private TextField companyNameText;
    private TextField address1Text;
    private TextField address2Text; //make method to combine with address1Text
    private TextField cityText;
    private TextField stateText;
    private TextField zipCodeText;
    private TextField countryText;
    private TextField tradenameText;
    //Mailing Address
    private TextField address1MailingText;
    private TextField address2MailingText; //make method to combine with address1Text
    private TextField cityMailingText;
    private TextField stateMailingText;
    private TextField zipCodeMailingText;
    private TextField countryMailingText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField alcoholContent;

    //Wine only
    @FXML
    private TextField vintageYearText;
    @FXML
    private TextField pHLevelText;

    //Applicant's Certification
    @FXML
    private DatePicker completedDate;
    @FXML
    private TextField applicantNameText;

    //TTB Use (accept/reject)
    //RadioButtons to accept or reject application
    @FXML
    private Button acceptButton;
    @FXML
    private Button rejectButton;
    //
    @FXML
    private DatePicker approvalDate;
    @FXML
    private TextField agentNameText;
    @FXML
    private DatePicker expirationDate;

    //Revisions
    //Wine
    @FXML
    private TextField alterVintageDate;
    @FXML
    private TextField alterpHLevel;
    @FXML
    private TextField alterWineAlcoholContent;
    //Beer
    @FXML
    private TextField alterBeerAlcoholContent;
    /*
    //might need to go in main
    //initializes all the radio buttons
    public void start() {
        //Source
        //make radio buttons and group them
        domesticRadio=new RadioButton("domestic");
        importedRadio=new RadioButton("imported");
        //set selected
        importedRadio.setSelected(true);
        //create group for radio buttons
        sourceGroup = new ToggleGroup();
        domesticRadio.setToggleGroup(sourceGroup);
        importedRadio.setToggleGroup(sourceGroup);

        //Type
        //make radio buttons and group them
        beerRadio=new RadioButton("beer");
        wineRadio=new RadioButton("wine");
        //set selected
        beerRadio.setSelected(true);
        //create group for radio buttons
        typeGroup = new ToggleGroup();
        beerRadio.setToggleGroup(typeGroup);
        wineRadio.setToggleGroup(typeGroup);

        //Status
        //make radio buttons and group them
        acceptRadio=new RadioButton("accept");
        rejectRadio=new RadioButton("reject");
        //set selected
        //rejectRadio.setSelected(true);
        //create group for radio buttons
        statusGroup = new ToggleGroup();
        acceptRadio.setToggleGroup(statusGroup);
        rejectRadio.setToggleGroup(statusGroup);
    }*/
    /*
    public Form createForm() {
        Form newForm = new Form();
        //Set values of the application
        newForm.setFormID(newForm.makeUniqueID());
        newForm.setRepID(repIDText.getText());
        newForm.setPermitNo(permitNoText.getText());
        //determine inputted source
        if (domesticRadio.isSelected()) {
            newForm.setSource("domestic");
        } else if (importedRadio.isSelected()) {
            newForm.setSource("imported");
        }
        //determine inputted type
        if (beerRadio.isSelected()) {
            newForm.setType(901);
        } else if (wineRadio.isSelected()) {
            newForm.setType(80);
        }
        newForm.setBrandName(brandNameText.getText());
        newForm.setCompanyName(companyNameText.getText());
        newForm.setAddress(address1Text.getText() + " " + address2Text.getText());
        newForm.setCity(cityText.getText());
        newForm.setState(stateText.getText());
        newForm.setZipCode(zipCodeText.getText());
        newForm.setCountry(countryText.getText());
        newForm.setTradename(tradenameText.getText());
        newForm.setPhoneNumber(phoneNumberText.getText());
        newForm.setEmail(emailText.getText());
        newForm.setAlcoholContent(alcoholContent.getText());
        newForm.setVintageYear(vintageYearText.getText());
        newForm.setpHLevel(pHLevelText.getText());
        newForm.setCompletedDate(completedDate.getValue());
        newForm.setApplicantName(applicantNameText.getText());
        //determine inputted status of the
        if (acceptRadio.isSelected()) {
            newForm.setStatus("accepted");
        } else if (rejectRadio.isSelected()) {
            newForm.setStatus("rejected");
        }
        newForm.setApprovalDate(approvalDate.getValue());
        newForm.setAgentName(agentNameText.getText());
        newForm.setExpirationDate(expirationDate.getValue());
        newForm.setAlterVintageDate(alterVintageDate.getText());
        newForm.setAlterpHLevel(pHLevelText.getText());
        newForm.setAlterWineAlcoholContent(alterWineAlcoholContent.getText());
        newForm.setAlterBeerAlcoholContent(alterBeerAlcoholContent.getText());

        return newForm;
    }*/

    //Label info page
    public Form createFormPage1() {
        //initializes necessary radio buttons
        //Source
        //make radio buttons and group them
        domesticRadio=new RadioButton("domestic");
        importedRadio=new RadioButton("imported");
        //set selected
        importedRadio.setSelected(true);
        //create group for radio buttons
        sourceGroup = new ToggleGroup();
        domesticRadio.setToggleGroup(sourceGroup);
        importedRadio.setToggleGroup(sourceGroup);

        //Type
        //make radio buttons and group them
        beerRadio=new RadioButton("beer");
        wineRadio=new RadioButton("wine");
        //set selected
        beerRadio.setSelected(true);
        //create group for radio buttons
        typeGroup = new ToggleGroup();
        beerRadio.setToggleGroup(typeGroup);
        wineRadio.setToggleGroup(typeGroup);

        tempForm.setFormID(tempForm.makeUniqueID());
        tempForm.setRepID(repIDText.getText());
        tempForm.setPermitNo(permitNoText.getText());
        //determine inputted source
        if (domesticRadio.isSelected()) {
            tempForm.setSource("domestic");
        } else if (importedRadio.isSelected()) {
            tempForm.setSource("imported");
        }
        //determine inputted type
        if (beerRadio.isSelected()) {
            tempForm.setType(901);
        } else if (wineRadio.isSelected()) {
            tempForm.setType(80);
        }
        tempForm.setBrandName(brandNameText.getText());

        return tempForm;
    }

    //Applicant info page
    public Form createFormPage2() {
        tempForm.setCompanyName(companyNameText.getText());
        tempForm.setAddress1(address1Text.getText());
        tempForm.setAddress2(address2Text.getText());
        tempForm.setAddress(address1Text.getText() + " " + address2Text.getText());
        tempForm.setCity(cityText.getText());
        tempForm.setState(stateText.getText());
        tempForm.setZipCode(zipCodeText.getText());
        tempForm.setCountry(countryText.getText());
        tempForm.setTradename(tradenameText.getText());

        return tempForm;
    }

    //Applicant info page (mailing address)
    public Form createFormPage3() {
        tempForm.setAddressMailing1(address1MailingText.getText());
        tempForm.setAddressMailing2(address2MailingText.getText());
        tempForm.setAddressMailing(address1MailingText.getText() + " " + address2MailingText.getText());
        tempForm.setCityMailing(cityMailingText.getText());
        tempForm.setStateMailing(stateMailingText.getText());
        tempForm.setZipCodeMailing(zipCodeMailingText.getText());
        tempForm.setCountryMailing(countryMailingText.getText());

        return tempForm;
    }

    //Applicant info (phone # and email)
    public Form createFormPage4() {
        tempForm.setPhoneNumber(phoneNumberText.getText());
        tempForm.setEmail(emailText.getText());

        return tempForm;
    }

    //Additional label info
    public Form createFormPage5() {
        tempForm.setAlcoholContent(alcoholContent.getText());
        tempForm.setVintageYear(vintageYearText.getText());
        tempForm.setpHLevel(pHLevelText.getText());
        tempForm.setCompletedDate(completedDate.getValue());
        tempForm.setApplicantName(applicantNameText.getText());

        return tempForm;
    }

    //Revisions
    public Form createFormPage6() {
        tempForm.setAlterVintageDate(alterVintageDate.getText());
        tempForm.setAlterpHLevel(pHLevelText.getText());
        tempForm.setAlterWineAlcoholContent(alterWineAlcoholContent.getText());
        tempForm.setAlterBeerAlcoholContent(alterBeerAlcoholContent.getText());

        return tempForm;
    }

    //Label info page AGENTS ONLY
    public Form createAgentFormPage1() {
        //initializes necessary radio buttons
        //Source
        domesticRadio=new RadioButton("domestic");
        importedRadio=new RadioButton("imported");
        //set selected
        if(tempForm.getSource().equals("imported")) {
            importedRadio.setSelected(true);
        } else if (tempForm.getSource().equals("domestic")) {
            domesticRadio.setSelected(true);
        }

        //Type
        beerRadio=new RadioButton("beer");
        wineRadio=new RadioButton("wine");
        //set selected
        if(tempForm.getType() == 901) {
            beerRadio.setSelected(true);
        } else if (tempForm.getType() == 80) {
            wineRadio.setSelected(true);
        }

        repIDText.setPromptText(tempForm.getRepID());
        permitNoText.setPromptText(tempForm.getPermitNo());
        brandNameText.setPromptText(tempForm.getBrandName());

        return tempForm;
    }

    //Applicant info page AGENTS ONLY
    public Form createAgentFormPage2() {
        companyNameText.setPromptText(tempForm.getCompanyName());
        address1Text.setPromptText(tempForm.getAddress1());
        address2Text.setPromptText(tempForm.getAddress2());
        cityText.setPromptText(tempForm.getCity());
        stateText.setPromptText(tempForm.getState());
        zipCodeText.setPromptText(tempForm.getZipCode());
        countryText.setPromptText(tempForm.getCountry());
        tradenameText.setPromptText(tempForm.getTradename());

        return tempForm;
    }

    //Applicant info page (mailing address) AGENTS ONLY
    public Form createAgentFormPage3() {
        address1MailingText.setPromptText(tempForm.getAddressMailing1());
        address2MailingText.setPromptText(tempForm.getAddressMailing2());
        cityMailingText.setPromptText(tempForm.getCityMailing());
        stateMailingText.setPromptText(tempForm.getStateMailing());
        zipCodeMailingText.setPromptText(tempForm.getZipCodeMailing());
        countryMailingText.setPromptText(tempForm.getCountryMailing());

        return tempForm;
    }

    //Applicant info (phone # and email) AGENTS ONLY
    public Form createAgentFormPage4() {
        phoneNumberText.setPromptText(tempForm.getPhoneNumber());
        emailText.setPromptText(tempForm.getEmail());

        return tempForm;
    }

    //Additional label info AGENTS ONLY
    public Form createAgentFormPage5() {
        alcoholContent.setPromptText(tempForm.getAlcoholContent());
        vintageYearText.setPromptText(tempForm.getVintageYear());
        pHLevelText.setPromptText(tempForm.getpHLevel());
        completedDate.setValue(tempForm.getCompletedDate());
        applicantNameText.setPromptText(tempForm.getApplicantName());

        tempForm.setApprovalDate(approvalDate.getValue());
        tempForm.setAgentName(agentNameText.getText());
        tempForm.setExpirationDate(expirationDate.getValue());

        return tempForm;
    }

    public void acceptForm(Form form) {
        tempForm.setStatus("accepted");
        submitForm(tempForm);
    }

    public void rejectForm() {
        tempForm.setStatus("rejected");
        submitForm(tempForm);
    }

    public void chooseForm () {
        //select a form from the list of forms that need to be processed
    }

    public void retrieveForm(String formID) {
        //get a form form DB
    }

    // Connect to the DB
    protected Connection DBConnect() throws SQLException {
        return TTB_database.connect();
    }

    // Function will query the DB
    protected ResultSet queryDB(String query) {
        Connection c;
        Statement stmt;
        ResultSet rs = null;
        try {
            c = DBConnect();
            stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            stmt = null;
        }
        return rs;
    }

    public void submitForm(Form form) {
        try {
            String formID = null;
            Connection c = DBConnect();
            QueryBuilder qb = new QueryBuilder("FORM", "FORM.FORM_ID", form.getFormID());
            ResultSet rs = queryDB(qb.getQuery());
            Statement s = rs.getStatement();
            while(rs.next()) {
                formID = rs.getString("FORM_ID");
            }
            if (formID == null || formID.isEmpty()) {
                rs.insertRow();
            } else {
                rs.updateRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //check if form exists if not save a new one, else update form in DB
    }

    //
    public void nextPage() {
        //next button functions
        //save previous information into a form object
    }
}
