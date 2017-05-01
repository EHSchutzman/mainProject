package Controllers;

import DBManager.DBManager;
import Form.Form;
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
 * Status: incomplete, needs work.
 * TODO: make sure revisions checkboxes allow editable fields
 */
public class reviseApplicationController extends UIController {
    @FXML
    public void initialize() {
        source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
        alcohol_type_combobox.setItems(FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits"));
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

    @FXML
    public void submitRevisedForm() {
        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> application_type = new ArrayList<Boolean>();
        for (int i = 0; i < 4; i++) {
            application_type.add(false);
        }
        ArrayList<String> application_type_text = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            application_type_text.add("");
        }
        if (option_1_checkbox.isSelected()) {//choice 0
            application_type.set(0, true);
        }
        if (option_2_checkbox.isSelected()) {
            application_type_text.set(0, option_2_text.getText());
            application_type.set(1, true);
        }
        if (option_3_checkbox.isSelected()) {
            application_type_text.set(1, option_3_text.getText());
            application_type.set(2, true);
        }
        if (option_4_checkbox.isSelected()) {
            application_type_text.set(2, option_4_text.getText());
            application_type.set(3, true);
        }

        String brand_name = brandName.getText();
        String fanciful_name = (fancifulName.getText());
        Double alcohol_content = (Double.parseDouble(alcoholContent.getText()));
        String formula = (this.formula.getText());
        String labeltext = extraLabelInfo.getText();

        // Wines only
        String vintage_year = (vintageYear.getText());
        int pH_level = (Integer.parseInt(phLevel.getText()));
        String grape_varietals = (grapeVarietals.getText());
        String wine_appellation = (wineAppellation.getText());

        String applicant_street = (applicantStreet.getText());
        String applicant_city = (applicantCity.getText());
        String applicant_state = (applicantState.getText());
        String applicant_zip = (applicantZip.getText());
        String applicant_country = (applicantCountry.getText());

        String signatureText = (signature.getText());
        String phone_no = (phoneNo.getText());
        String emailText = (email.getText());

        Date date = new Date(0);
        Date submitdate = new Date(System.currentTimeMillis());
        Form updatedForm = new Form(form.getttb_id(), form.getrep_id(), form.getpermit_no(), form.getSource(), form.getserial_no(), form.getalcohol_type(),
                brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state,
                applicant_zip, applicant_country, form.getmailing_address(), formula, form.getphone_no(), form.getEmail(),
                labeltext, form.getlabel_image(), form.getsubmit_date(), form.getSignature(), form.getStatus(), form.getagent_id(), form.getapplicant_id(), form.getapproved_date(), form.getexpiration_date(),
                vintage_year, pH_level, grape_varietals, wine_appellation, application_type, application_type_text,
                form.getapproval_comments());

        db.updateForm(updatedForm);
        System.out.println(form.getttb_id());
        try {
            super.closeWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createReviseForm(Form form, ArrayList<Boolean> booleanArrayList) {
        System.out.println("creating revision form" + booleanArrayList);
        //TODO pull the applicant search from the DB
        if (form.getSource().equals("Imported")) {
            source_combobox.setValue("Imported");
        } else if (form.getSource().equals("Domestic")) {
            source_combobox.setValue("Domestic");
        }

        // Get Alcohol Type info and set it to display for the Agent
        //alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        if (form.getalcohol_type().equals("Malt Beverages")) {
            //alcohol_type_combobox.getSelectionModel().select(1);
            alcohol_type_combobox.setValue("Malt Beverages");
        } else if (form.getalcohol_type().equals("Wine")) {
            alcohol_type_combobox.setValue("Wine");
        } else if (form.getalcohol_type().equals("Distilled Spirits")) {
            alcohol_type_combobox.setValue("Distilled Spirits");
        }

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

        if (booleanArrayList.get(0) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(1) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(2) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(3) == true && form.getalcohol_type().equals("Wine")) {
            grapeVarietals.setDisable(false);
            wineAppellation.setDisable(false);

        }
        if (booleanArrayList.get(4) == true && form.getalcohol_type().equals("Wine")) {
            vintageYear.setDisable(false);

        }
        if (booleanArrayList.get(5) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(6) == true && form.getalcohol_type().equals("Wine")) {
            phLevel.setDisable(false);
            //ph_level_text.setEditable(true);

        }
        if (booleanArrayList.get(7) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(8) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(9) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(10) == true) {
            alcoholContent.setDisable(false);

        }
        if (booleanArrayList.get(11) == true) {
            alcoholContent.setDisable(false);

        }

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
