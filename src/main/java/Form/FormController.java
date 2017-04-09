package Form;
import DatabaseSearch.QueryBuilder;
import DatabaseSearch.TTB_database;
import Initialization.Main;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.*;
import java.time.LocalDate;
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
    public TextField permitNoText;

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
    public RadioButton beerRadio; //use this to translate to int 901
    @FXML
    public RadioButton wineRadio; //use this to translate to int 80

    @FXML
    public TextField brandNameText;

    @FXML
    public TextField fancifulNameText;

    //Address
    @FXML
    public TextField companyNameText;
    @FXML
    public TextField address1Text;
    @FXML
    public TextField address2Text; //make method to combine with address1Text
    @FXML
    public TextField cityText;
    @FXML
    public TextField stateText;
    @FXML
    public TextField zipCodeText;
    @FXML
    public TextField countryText;
    @FXML
    public TextField tradeNameText;
    //Mailing Address
    @FXML
    public TextField address1MailingText;
    @FXML
    public TextField address2MailingText; //make method to combine with address1Text
    @FXML
    public TextField cityMailingText;
    @FXML
    public TextField stateMailingText;
    @FXML
    public TextField zipCodeMailingText;
    @FXML
    public TextField countryMailingText;

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
    public TextField applicantNameText = new TextField();

    //TTB Use (accept/reject)
    //RadioButtons to accept or reject application
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
    public TextField applicantAddress1;
    public TextField applicantAddress2;
    public TextField applicantCity;
    public TextField applicantState;
    public TextField applicantPostalCode;
    public TextField applicantCountry;
    public TextField mailingName;
    public TextField mailingAddress1;
    public TextField mailingAddress2;
    public TextField mailingCity;
    public TextField mailingState;
    public TextField mailingPostalCode;
    public TextField mailingCountry;
    public TextField applicationDate;
    public TextField authorizedAgent;
    public TextField alcoholContent;


    //Label info page
    public Form createFormPage1() {
        //initializes necessary radio buttons
        //Source
        //make radio buttons and group them
        domesticRadio = new RadioButton("domestic");
        importedRadio = new RadioButton("imported");
        //set selected
        importedRadio.setSelected(true);
        //create group for radio buttons
        sourceGroup = new ToggleGroup();
        domesticRadio.setToggleGroup(sourceGroup);
        importedRadio.setToggleGroup(sourceGroup);

        //Type
        //make radio buttons and group them
        beerRadio = new RadioButton("beer");
        wineRadio = new RadioButton("wine");
        //set selected
        beerRadio.setSelected(true);
        //create group for radio buttons
        typeGroup = new ToggleGroup();
        beerRadio.setToggleGroup(typeGroup);
        wineRadio.setToggleGroup(typeGroup);

        main.userData.tempForm.setTtbID(main.userData.tempForm.makeUniqueID());
        System.out.println(main.userData.tempForm.getTtbID());
        main.userData.tempForm.setRepID(repIDNoText.getText());
        System.out.println(main.userData.tempForm.getRepID());
        main.userData.tempForm.setPermitNo(permitNoText.getText());
        System.out.println(main.userData.tempForm.getPermitNo());
        //determine inputted source
        if (domesticRadio.isSelected()) {
            main.userData.tempForm.setSource("domestic");
        } else if (importedRadio.isSelected()) {
            main.userData.tempForm.setSource("imported");
        }
        //determine inputted type
        if (beerRadio.isSelected()) {
            main.userData.tempForm.setAlcoholType(901);
        } else if (wineRadio.isSelected()) {
            main.userData.tempForm.setAlcoholContent(80);
        }
        main.userData.tempForm.setBrandName(brandNameText.getText());

        return main.userData.tempForm;
    }

    //Applicant info page
    public Form createFormPage2() {
        main.userData.tempForm.setCompanyName(companyNameText.getText());
        main.userData.tempForm.setAddress1(address1Text.getText());
        main.userData.tempForm.setAddress2(address2Text.getText());
        main.userData.tempForm.setAddress(address1Text.getText() + " " + address2Text.getText());
        main.userData.tempForm.setCity(cityText.getText());
        main.userData.tempForm.setState(stateText.getText());
        main.userData.tempForm.setZipCode(zipCodeText.getText());
        main.userData.tempForm.setCountry(countryText.getText());
        main.userData.tempForm.setTradeName(tradeNameText.getText());
        System.out.println(main.userData.tempForm.getTradeName());

        return main.userData.tempForm;
    }

    //Applicant info page (mailing address)
    public Form createFormPage3() {
        main.userData.tempForm.setAddressMailing1(address1MailingText.getText());
        main.userData.tempForm.setAddressMailing2(address2MailingText.getText());
        main.userData.tempForm.setAddressMailing(address1MailingText.getText() + " " + address2MailingText.getText());
        main.userData.tempForm.setCityMailing(cityMailingText.getText());
        main.userData.tempForm.setStateMailing(stateMailingText.getText());
        main.userData.tempForm.setZipCodeMailing(zipCodeMailingText.getText());
        main.userData.tempForm.setCountryMailing(countryMailingText.getText());

        return main.userData.tempForm;
    }

    //Applicant info (phone # and email)
    public Form createFormPage4() {
        main.userData.tempForm.setPhoneNumber(phoneNumberText.getText());
        main.userData.tempForm.setEmail(emailText.getText());

        return main.userData.tempForm;
    }

    //Additional label info
    public Form createFormPage5() {
        main.userData.tempForm.setAlcoholContent(Double.parseDouble(alcoholContentText.getText()));
        main.userData.tempForm.setVintageYear(vintageYearText.getText());
        main.userData.tempForm.setpHLevel(Double.parseDouble(pHLevelText.getText()));
        main.userData.tempForm.setSubmitDate(completedDate.getValue());
        main.userData.tempForm.setApplicantName(applicantNameText.getText());

        return main.userData.tempForm;
    }



    //Label info page AGENTS ONLY
    public Form createAgentFormPage1() {
        //initializes necessary radio buttons
        //Source
        domesticRadio=new RadioButton("domestic");
        importedRadio=new RadioButton("imported");
        //set selected
        if(main.userData.tempForm.getSource().equals("imported")) {
            importedRadio.setSelected(true);
        } else if (main.userData.tempForm.getSource().equals("domestic")) {
            domesticRadio.setSelected(true);
        }

        //Type
        beerRadio=new RadioButton("beer");
        wineRadio=new RadioButton("wine");
        //set selected
        if(main.userData.tempForm.getAlcoholType() == 901) {
            beerRadio.setSelected(true);
        } else if (main.userData.tempForm.getAlcoholType() == 80) {
            wineRadio.setSelected(true);
        }

        repIDNoText.setPromptText(main.userData.tempForm.getRepID());
        permitNoText.setPromptText(main.userData.tempForm.getPermitNo());
        brandNameText.setPromptText(main.userData.tempForm.getBrandName());

        return main.userData.tempForm;
    }

    //Applicant info page AGENTS ONLY
    public Form createAgentFormPage2() {
        companyNameText.setPromptText(main.userData.tempForm.getCompanyName());
        address1Text.setPromptText(main.userData.tempForm.getAddress1());
        address2Text.setPromptText(main.userData.tempForm.getAddress2());
        cityText.setPromptText(main.userData.tempForm.getCity());
        stateText.setPromptText(main.userData.tempForm.getState());
        zipCodeText.setPromptText(main.userData.tempForm.getZipCode());
        countryText.setPromptText(main.userData.tempForm.getCountry());
        tradeNameText.setPromptText(main.userData.tempForm.getTradeName());

        return main.userData.tempForm;
    }

    //Applicant info page (mailing address) AGENTS ONLY
    public Form createAgentFormPage3() {
        address1MailingText.setPromptText(main.userData.tempForm.getAddressMailing1());
        address2MailingText.setPromptText(main.userData.tempForm.getAddressMailing2());
        cityMailingText.setPromptText(main.userData.tempForm.getCityMailing());
        stateMailingText.setPromptText(main.userData.tempForm.getStateMailing());
        zipCodeMailingText.setPromptText(main.userData.tempForm.getZipCodeMailing());
        countryMailingText.setPromptText(main.userData.tempForm.getCountryMailing());

        return main.userData.tempForm;
    }

    //Applicant info (phone # and email) AGENTS ONLY
    public Form createAgentFormPage4() {
        phoneNumberText.setPromptText(main.userData.tempForm.getPhoneNumber());
        emailText.setPromptText(main.userData.tempForm.getEmail());

        return main.userData.tempForm;
    }

    //Additional label info AGENTS ONLY
    public Form createAgentFormPage5() {
        alcoholContentText.setPromptText(String.valueOf(main.userData.tempForm.getAlcoholContent()));
        vintageYearText.setPromptText(main.userData.tempForm.getVintageYear());
        pHLevelText.setPromptText(String.valueOf(main.userData.tempForm.getpHLevel()));
        completedDate.setValue(main.userData.tempForm.getSubmitDate());
        applicantNameText.setPromptText(main.userData.tempForm.getApplicantName());

        main.userData.tempForm.setApprovedDate(approvalDate.getValue());
        main.userData.tempForm.setAgentName(agentNameText.getText());
        main.userData.tempForm.setExpirationDate(expirationDate.getValue());

        return main.userData.tempForm;
    }


    //TODO On review next application clicked query database and provide the agent with he first application
    public void chooseForm () {
        Form review = new Form();
        String query = "";
        query = "SELECT * FROM APP.FORM WHERE FORM.ASSIGNED_TO = 0  AND FORM.STATUS = \'Pending\'";

        try{
            Connection c = DBConnect();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(query);

            while(rs.next()) {
                String TTB_ID = rs.getString("TTB_ID");
                Date date = rs.getDate("completed_date");
                int repID = rs.getInt("rep_ID");
                String brandName = rs.getString("brand_name");
                String fancifulName = rs.getString("fanciful_name");
                String source = rs.getString("source");
                String companyName = rs.getString("company_name");
                String permitNO = rs.getString("permit_no");
                String tradeName = rs.getString("tradename");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String alcoholContent = rs.getString("alcohol_content");
                String applicantName = rs.getString("applicant_name");
                int typeID = rs.getInt("type_id");
                String status = rs.getString("status");
                LocalDate completedDate = date.toLocalDate();
                String repIDString = Integer.toString(repID);
                review = new Form(TTB_ID, completedDate, repIDString, brandName, fancifulName, source,
                        companyName, permitNO, tradeName, phoneNumber, email, alcoholContent, applicantName,
                        typeID, status);

                main.userData.getUserInformation().setForm(review);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        /*
            TODO create a function that will take a rs row and fill it to a form because writing that out 5 times would be dumb
        */
        main.setDisplayToAgentReview();

        //display the form
    }

    public void retrieveForm(String ttbID) {
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

    public void createFormFromApp(){
        Form form = new Form();

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



        return;
    }
    @FXML
    public void submitForm() {
        createFormFromApp();
        Form form = main.userData.getUserInformation().getForm();
        String query = "INSERT INTO APP.FORM VALUES (\'"+form.getTtbID()+"\',\'"+form.getSubmitDate()+
                "\',"+form.getRepID()+",\'"+form.getBrandName()+"\',\'"+
                form.getFancifulName()+ "\',\'"+form.getSource()+"\',\'"+form.getCompanyName()+"\',\'"
                +form.getPermitNo()+"\',\'serial_num\',\'" +form.getTradeName()+ "\',\'"+form.getPhoneNumber()+"\',\'"
                +form.getEmail()+"\',\'"+form.getAlcoholContent()+"\',\'"+form.getApplicantName()+ "\',"+form.getAlcoholType()
                +",0,0,\'MA\',\'Pending\',0)";

        try{
            Connection c = DBConnect();
            Statement s = c.createStatement();
            s.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
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
        String query = "";
        query = "UPDATE APP.FORM SET FORM.STATUS = \'Accepted\' WHERE FORM.TTB_ID = \'"
                + main.userData.getUserInformation().getForm().getTtbID() + "\'";

        try {
            Connection c = DBConnect();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int a = s.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        returnToMainPage();
    }
    @FXML
    public void rejectForm(){
        Form review = new Form();
        String query = "";
        query = "UPDATE APP.FORM SET FORM.STATUS = \'Rejected\' WHERE FORM.TTB_ID = \'"
                + main.userData.getUserInformation().getForm().getTtbID() + "\'";

        try {
            Connection c = DBConnect();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int a = s.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
       returnToMainPage();
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

}
