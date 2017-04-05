
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class FormController extends ActionController{
    private ArrayList<Form> listOfForms; //unique for each user (agent or applicant)

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

    //Address/Mailing Address
    @FXML
    private TextField companyNameText;
    private TextField address1Text;
    private TextField address2Text; //make method to combine with address1Text
    private TextField cityText;
    private TextField stateText;
    private TextField zipCodeText;
    private TextField countryText;
    private TextField tradenameText;

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
    private ToggleGroup statusGroup;
    @FXML
    private RadioButton acceptRadio;
    @FXML
    private RadioButton rejectRadio;
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

    //might need to go in main
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
    }

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
    }

    public void CheckAuthenticationLevel() {

    }

    public void viewForm() {
        //goes to the first page of the application
    }

    public void nextPage() {
        //next button functions
    }

    public void submitForm() {
        //save form in database
    }

    public void retrieveForm() {
        //get the list of forms from database by formID
    }

    public void chooseForm() {
        //choose a form from the listofForms
    }


}
