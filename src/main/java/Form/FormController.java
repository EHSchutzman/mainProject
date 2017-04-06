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

public class FormController{
    public int currentPage;

    private Main main;

    public void setDisplay(Main main) {
        this.main = main;
    }

    @FXML
    private Button mainPageButton;

    @FXML
    private Button nextButton;

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
    @FXML
    private TextField address1Text;
    @FXML
    private TextField address2Text; //make method to combine with address1Text
    @FXML
    private TextField cityText;
    @FXML
    private TextField stateText;
    @FXML
    private TextField zipCodeText;
    @FXML
    private TextField countryText;
    @FXML
    private TextField tradenameText;
    //Mailing Address
    @FXML
    private TextField address1MailingText;
    @FXML
    private TextField address2MailingText; //make method to combine with address1Text
    @FXML
    private TextField cityMailingText;
    @FXML
    private TextField stateMailingText;
    @FXML
    private TextField zipCodeMailingText;
    @FXML
    private TextField countryMailingText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField alcoholContentText;

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

    //public FormController () {}
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
        newForm.setAlcoholContent(alcoholContentText.getText());
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

        main.userData.tempForm.setFormID(main.userData.tempForm.makeUniqueID());
        System.out.println(main.userData.tempForm.getFormID());
        main.userData.tempForm.setRepID(repIDText.getText());
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
            main.userData.tempForm.setType(901);
        } else if (wineRadio.isSelected()) {
            main.userData.tempForm.setType(80);
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
        main.userData.tempForm.setTradename(tradenameText.getText());
        System.out.println(main.userData.tempForm.getTradename());

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
        main.userData.tempForm.setAlcoholContent(alcoholContentText.getText());
        main.userData.tempForm.setVintageYear(vintageYearText.getText());
        main.userData.tempForm.setpHLevel(pHLevelText.getText());
        main.userData.tempForm.setCompletedDate(completedDate.getValue());
        main.userData.tempForm.setApplicantName(applicantNameText.getText());

        return main.userData.tempForm;
    }

    //Revisions
    public Form createFormPage6() {
        main.userData.tempForm.setAlterVintageDate(alterVintageDate.getText());
        main.userData.tempForm.setAlterpHLevel(pHLevelText.getText());
        main.userData.tempForm.setAlterWineAlcoholContent(alterWineAlcoholContent.getText());
        main.userData.tempForm.setAlterBeerAlcoholContent(alterBeerAlcoholContent.getText());

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
        if(main.userData.tempForm.getType() == 901) {
            beerRadio.setSelected(true);
        } else if (main.userData.tempForm.getType() == 80) {
            wineRadio.setSelected(true);
        }

        repIDText.setPromptText(main.userData.tempForm.getRepID());
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
        tradenameText.setPromptText(main.userData.tempForm.getTradename());

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
        alcoholContentText.setPromptText(main.userData.tempForm.getAlcoholContent());
        vintageYearText.setPromptText(main.userData.tempForm.getVintageYear());
        pHLevelText.setPromptText(main.userData.tempForm.getpHLevel());
        completedDate.setValue(main.userData.tempForm.getCompletedDate());
        applicantNameText.setPromptText(main.userData.tempForm.getApplicantName());

        main.userData.tempForm.setApprovalDate(approvalDate.getValue());
        main.userData.tempForm.setAgentName(agentNameText.getText());
        main.userData.tempForm.setExpirationDate(expirationDate.getValue());

        return main.userData.tempForm;
    }

    public void acceptForm() {
        main.userData.tempForm.setStatus("accepted");
        submitForm();
    }

    public void rejectForm() {
        main.userData.tempForm.setStatus("rejected");
        submitForm();
    }

    //TODO On review next application clicked query database and provide the agent with he first application
    public void chooseForm () {
        //select a form from the list of forms that need to be processed
        //assign agent to form
        //review form
        //resubmit form

        //display the form
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

    @FXML
    public void submitForm() {
        try {
            String formID = null;
            Connection c = DBConnect();
            QueryBuilder qb = new QueryBuilder("FORM", "FORM.FORM_ID", main.userData.tempForm.getFormID());
            System.out.println(qb.getQuery());
            ResultSet rs = queryDB(qb.getQuery());
            while(rs.next()) {
                formID = rs.getString("FORM_ID");
                System.out.println(formID);
            }
            if (formID == null || formID.isEmpty()) {
                String q = "INSERT INTO APP.FORM VALUES (" + main.userData.tempForm.getFormID()
                        + ",'1000-10-10'," + main.userData.tempForm.getRepID() +
                        ",'willie','facn','idk','cp','1','122','fda','123223','#df','3%','john',2,321,123,'26')";
                Statement s = c.createStatement();
                s.execute(q);
                s.close();
                c.close();
                System.out.println("before insert Row");
            } else {
                System.out.println(main.userData.tempForm.getPermitNo());
                String u = "UPDATE APP.FORM SET PERMIT_NO = ?";
                PreparedStatement ps = c.prepareStatement(u);
                //Statement s = c.createStatement();
                ps.setString(1, "43");
                ps.executeUpdate();
                //ps.close();
                //c.close();
                System.out.println("before update Row");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("no results");
        }
        try {
            main.setDisplayToDefaultMain();
        }catch (Exception e){
            e.printStackTrace();
        }
        main.userData.setCurrentApplicationPage(0);
    }

    @FXML
    public void startApplication(){
        currentPage = 0;
        nextPage();
    }
    //
    @FXML
    public void nextPage() {

        if (main.userData.getUserInformation().getAuthenticationLevel() > 1) {
            if (main.userData.getCurrentApplicationPage() == 0) {
                System.out.println("0");
                main.userData.setCurrentApplicationPage(1);
                main.changeAgentFormView(1);
            } else if (main.userData.getCurrentApplicationPage() == 1) {
                System.out.println("1");
                main.userData.setCurrentApplicationPage(2);
                main.changeAgentFormView(2);

            } else if (main.userData.getCurrentApplicationPage() == 2) {
                System.out.println("2");
                main.userData.setCurrentApplicationPage(3);
                main.changeAgentFormView(3);

            } else if (main.userData.getCurrentApplicationPage() == 3) {
                main.userData.setCurrentApplicationPage(4);
                main.changeAgentFormView(4);

            } else if (main.userData.getCurrentApplicationPage() == 4) {
                main.userData.setCurrentApplicationPage(5);
                main.changeAgentFormView(5);

            } else if (main.userData.getCurrentApplicationPage() == 5) {
                main.userData.setCurrentApplicationPage(6);
                main.changeAgentFormView(6);

            } else if (main.userData.getCurrentApplicationPage() == 6) {
                main.userData.setCurrentApplicationPage(0);

            }
        } else {
            System.out.println("Next applicant page");
            if (main.userData.getCurrentApplicationPage() == 0) {
                System.out.println("0");
                main.userData.setCurrentApplicationPage(1);
                main.changeApplicantFormView(1);
            } else if (main.userData.getCurrentApplicationPage() == 1) {
                System.out.println("1");
                main.userData.setCurrentApplicationPage(2);
                main.changeApplicantFormView(2);
                createFormPage1();
            } else if (main.userData.getCurrentApplicationPage() == 2) {
                System.out.println("2");
                main.userData.setCurrentApplicationPage(3);
                main.changeApplicantFormView(3);
                createFormPage2();
            } else if (main.userData.getCurrentApplicationPage() == 3) {
                System.out.println("3");
                main.userData.setCurrentApplicationPage(4);
                main.changeApplicantFormView(4);
                createFormPage3();
            } else if (main.userData.getCurrentApplicationPage() == 4) {
                System.out.println("4");
                main.userData.setCurrentApplicationPage(5);
                main.changeApplicantFormView(5);
                createFormPage4();
            } else if (main.userData.getCurrentApplicationPage() == 5) {
                System.out.println("5");
                main.userData.setCurrentApplicationPage(6);
                main.changeApplicantFormView(6);
                createFormPage5();
            } else if (main.userData.getCurrentApplicationPage() == 6) {
                main.userData.setCurrentApplicationPage(0);
                System.out.println("6");

            }
        }

    }


    @FXML
    public void returnToMainPage(){
        try{
            main.setDisplayToDefaultMain();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
