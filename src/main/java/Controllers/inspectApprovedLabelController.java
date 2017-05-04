package Controllers;

import DBManager.DBManager;
import Form.Form;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Status: complete, needs small fixing.
 * TODO: clean code, make sure there are no WARNINGS
 */
public class inspectApprovedLabelController extends UIController{

    /**
     * Sets form object in this controller, and sets all displayable fields
     * @param form - form passed from double clicked row
     */
    void setForm(Form form) {
        this.form = form;
    }

    @FXML
    public void initialize() {
    }
    @FXML
    private Button back_button;
    @FXML
    public Button browse_button;
    @FXML
    public Button submit_button;
    @FXML
    public Button close_button;
    @FXML
    public TextField repID;
    @FXML
    public TextField permitNO;
    @FXML
    public TextField serialNO;

    // Source and Alcohol Type ComboBoxes + their TextFields for Agent Review
    @FXML
    public ComboBox source_combobox;
    @FXML
    public ComboBox alcohol_type_combobox;

    @FXML
    public TextField brandName;
    @FXML
    public TextField fancifulName;
    @FXML
    public TextField alcoholContent;
    @FXML
    public TextField formula;

    @FXML
    public TextArea extraLabelInfo;

    // Wine only
    @FXML
    public TextField vintageYear;
    @FXML
    public TextField phLevel;
    @FXML
    public TextField grapeVarietals;
    @FXML
    public TextField wineAppellation;


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
    public TextField applicantStreet;
    @FXML
    public TextField applicantCity;
    @FXML
    public TextField applicantState;
    @FXML
    public TextField applicantZip;
    @FXML
    public TextField applicantCountry;
    //Mailing Address
    @FXML
    public CheckBox sameAsApplicantButton;
    @FXML
    public TextArea mailingAddress;

    @FXML
    public TextField phoneNo;
    @FXML
    public TextField signature;
    @FXML
    public TextField email;
    @FXML
    public TextArea address_text;
    public TextField submit_date;
    public ImageView label_image;
    public TextField source;
    public TextField alcohol_content;

    private DBManager db = new DBManager();
    private Form form;

    @FXML
    public void setDisplayToApplicantMain() throws IOException {
        Stage stage;
        stage=(Stage) back_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicantMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


    public void createReviseForm(Form form) {
        //TODO pull the applicant search from the DB

        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        option_1_checkbox = new CheckBox("Certificate of Label Approval");
        option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
        option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
        option_4_checkbox = new CheckBox("Resubmission After Rejection");

        //TODO have the DB support type of application
        /*
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
*/
        repID.setText(form.getrep_id());
        permitNO.setText(form.getpermit_no());
        serialNO.setText(form.getserial_no());
        brandName.setText(form.getbrand_name());
        fancifulName.setText(form.getfanciful_name());
        alcoholContent.setText(String.valueOf(form.getalcohol_content()));
        formula.setText(form.getFormula());
        extraLabelInfo.setText(form.getlabel_text());
        // Wines only
        vintageYear.setText(form.getvintage_year());
        phLevel.setText(String.valueOf(form.getpH_level()));
        grapeVarietals.setText(form.getgrape_varietals());
        wineAppellation.setText(form.getwine_appellation());


        applicantStreet.setText(form.getapplicant_street());
        applicantCity.setText(form.getapplicant_city());
        applicantState.setText(form.getapplicant_state());
        applicantZip.setText(form.getapplicant_zip());
        applicantCountry.setText(form.getapplicant_country());
        signature.setPromptText(form.getSignature());
        phoneNo.setPromptText(form.getphone_no());
        System.out.println("trying to display email and phone");
        email.setPromptText(form.getEmail());


        //       applicant_name_text.setText(manager.findUsersName(form.getapplicant_id()));
        email.setText(main.userData.getUserInformation().getEmail());
        phoneNo.setText(main.userData.getUserInformation().getPhoneNo());

        //@TODO: Put on UI
        //submit_date.setText(form.getsubmit_date().toString());

        //Agent Headers
        //form.setapproved_date(Date.valueOf(approved_date.getValue()));
        //TODO reference agent search if needed

        //form.setexpiration_date(Date.valueOf(expiration_date.getValue()));
        //form.setapproval_comments(approval_comments_text.getText());
        try {
            System.out.println("image is " + form.getlabel_image());
            /**
             * IF EXPORTING THIS FOR JAR CHANGE
             */
            String path = (System.getProperty("user.dir") + "/images/" + form.getlabel_image());
            System.out.println(path);
            File file = new File(path);
            String localURL = file.toURI().toURL().toString();
            Image image = new Image(localURL);
            System.out.println("Image loaded");
            label_image.setImage(image);
            System.out.println("displaying image");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.form = form;
    }

}
