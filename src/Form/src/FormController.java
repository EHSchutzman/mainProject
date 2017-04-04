//package Form;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.xml.soap.Text;
import java.util.Collection;

public class FormController {
    private Collection<Form> listOfForms;

    @FXML
    private TextField repIDText;

    @FXML
    private TextField registryText;

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
    private RadioButton beerRadio;
    @FXML
    private RadioButton wineRadio;

    @FXML
    private TextField brandNameText;

    //Address/Mailing Address
    @FXML
    private TextField companyNameText;
    private TextField address1Text;
    private TextField address2Text;
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
    private TextField alcoholPercentageText;

    //Wine only
    @FXML
    private TextField vintageYearText;
    @FXML
    private TextField pHLevelText;

    //Applicant's Certification
    @FXML
    private DatePicker appDate;
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
    private String agentNameText;
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
        //create group for radio buttons
        sourceGroup = new ToggleGroup();
        domesticRadio.setToggleGroup(sourceGroup);
        importedRadio.setToggleGroup(sourceGroup);

        //Type
        //make radio buttons and group them
        beerRadio=new RadioButton("beer");
        wineRadio=new RadioButton("wine");
        //create group for radio buttons
        typeGroup = new ToggleGroup();
        beerRadio.setToggleGroup(typeGroup);
        wineRadio.setToggleGroup(typeGroup);

        //Status
        //make radio buttons and group them
        acceptRadio=new RadioButton("accept");
        rejectRadio=new RadioButton("reject");
        //create group for radio buttons
        statusGroup = new ToggleGroup();
        acceptRadio.setToggleGroup(statusGroup);
        rejectRadio.setToggleGroup(statusGroup);
        */
    }

    public void authenticateApplicant () {
        //authenticate applicant
    }

    public Form creatForm() {
        Form newForm = new Form();
        //Set values of the application

    }

    public Form submitForm() {
        //save form in database
    }

    public Form getForm() {
        //get a form from database by formNumber
    }

    public Form reviseForm() {

    }

    public void viewForm() {
        //lets the applicant see the status of the form they submitted
        //throw error if no update yet
        //let user revise their label if accepted
    }

    //for agents only
    public void processApplicationForm() {
        //lets the agents access the fields available only for TTB agents
    }

}
