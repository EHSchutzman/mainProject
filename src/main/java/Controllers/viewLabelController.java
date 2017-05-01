package Controllers;

import DBManager.DBManager;
import Form.Form;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by eschutzman on 4/20/17.
 * TODO: where are we even using this?
 */
public class viewLabelController extends UIController{

    @FXML
    public void initialize() {
        source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
        alcohol_type_combobox.setItems(FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits"));
    }
    @FXML
    public Button submit_button;
    public Button browse_button;
    public TextField repID;
    public TextField permitNO;
    public TextField serialNO;
    public Text ttbid;

    // Source and Alcohol Type ComboBoxes + their TextFields for Agent Review
    @FXML
    public ComboBox source_combobox;
    public ComboBox alcohol_type_combobox;
    public TextField brandName;
    public TextField fancifulName;
    public TextField alcoholContent;
    public TextField formula;
    public TextArea extraLabelInfo;

    // Wine only
    @FXML
    public TextField vintageYear;
    public TextField phLevel;
    public TextField grapeVarietals;
    public TextField wineAppellation;
    public Label vintageYearLabel;
    public Label phLevelLabel;
    public Label grapeVarietalsLabel;
    public Label wineAppellationLabel;
    public Rectangle wineRec;

    // CheckBoxes and TextFields for the Type of Application
    @FXML
    public CheckBox option_1_checkbox;
    public CheckBox option_2_checkbox;
    public TextField option_2_text; //For sale in <state> only
    public CheckBox option_3_checkbox;
    public TextField option_3_text; //Bottle capacity before closure
    public CheckBox option_4_checkbox;
    public TextField option_4_text;

    // Applicant Info
    // Addresses
    // street1 and street2 correspond to applicant_street
    @FXML
    public TextField otherStreet;
    public TextField otherCity;
    public TextField otherState;
    public TextField otherZip;
    public TextField otherCountry;
    public Label otherCityLabel;
    public Label otherStateLabel;
    public Label otherZipcodeLabel;
    public Label otherCountryLabel;
    public Label otherStreetLabel;


    @FXML
    public TextField applicantStreet;
    public TextField applicantCity;
    public TextField applicantState;
    public TextField applicantZip;
    public TextField applicantCountry;

    //Mailing Address
    @FXML
    public CheckBox sameAsApplicantBox;
    public TextField phoneNo;
    public TextField signature;
    public TextField email;
    public ImageView label_image;
    public Group wineInfo;

    private DBManager db = new DBManager();
    private loginPageController lpc = new loginPageController();
    private Form storedForm = new Form();


    void setReviewForm(Form form){
        this.storedForm = form;
    }

    void createIncompleteForm(){
        System.out.println("in createIncompleteForm: " + storedForm.getalcohol_type());
        // Get Source info and set it to display for the Agent
        if (storedForm.getSource().equals("Imported")) {
            source_combobox.getSelectionModel().select(1);
        } else if (storedForm.getSource().equals("Domestic")) {
            source_combobox.getSelectionModel().select(0);
        }
        // Get Alcohol Type info and set it to display for the Agent
        if (storedForm.getalcohol_type().equals("Malt Beverages")) {
            alcohol_type_combobox.getSelectionModel().select(0);
        } else if (storedForm.getalcohol_type().equals("Wine")) {
            System.out.println("trying to select wine");
            alcohol_type_combobox.getSelectionModel().select(1);
        } else if (storedForm.getalcohol_type().equals("Distilled Spirits")) {
            alcohol_type_combobox.getSelectionModel().select(2);
        }
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
        repID.setText(storedForm.getrep_id());
        permitNO.setText(storedForm.getpermit_no());
        serialNO.setText(storedForm.getserial_no());
        brandName.setText(storedForm.getbrand_name());
        fancifulName.setText(storedForm.getfanciful_name());
        alcoholContent.setText(String.valueOf(storedForm.getalcohol_content()));
        formula.setText(storedForm.getFormula());
        extraLabelInfo.setText(storedForm.getlabel_text());
        // Wines only
        if (storedForm.getalcohol_type().equals("Wine")) {
            vintageYear.setText(storedForm.getvintage_year());
            phLevel.setText(String.valueOf(storedForm.getpH_level()));
            grapeVarietals.setText(storedForm.getgrape_varietals());
            wineAppellation.setText(storedForm.getwine_appellation());
        } else {
            vintageYear.setText(null);
            phLevel.setText(null);
            grapeVarietals.setText(null);
            wineAppellation.setText(null);
        }
        applicantStreet.setText(storedForm.getapplicant_street());
        applicantCity.setText(storedForm.getapplicant_city());
        applicantState.setText(storedForm.getapplicant_state());
        applicantZip.setText(storedForm.getapplicant_zip());
        applicantCountry.setText(storedForm.getapplicant_country());
        //address_text.setText(form.getapplicant_street() + ", " + form.getapplicant_city() + ", " + form.getapplicant_state() + " " + form.getapplicant_zip() + ", " + form.getapplicant_country());
        //TODO The database stores the mailing address as one String. However, we need to display it in different parts. I used the TODO to bring attention to this because yellow is an eye catching color
        //mailing_addressText.setPromptText(form.getmailing_address());
        DBManager manager = new DBManager();
        System.out.println("NAME IS THIS");
        System.out.println(manager.findUsersName(storedForm.getapplicant_id()));
        signature.setText(storedForm.getSignature());
        phoneNo.setText(storedForm.getphone_no());
        email.setText(storedForm.getEmail());
        System.out.println("Image creation");
        File file = new File(System.getProperty("user.dir") + "/images/"+ storedForm.getlabel_image());
        try{
            String localURL = file.toURI().toURL().toString();
            System.out.println("URL IS " + localURL);
            Image image = new Image(localURL);
            label_image.setImage(image);
        }catch (Exception e){

        }



    }
}
