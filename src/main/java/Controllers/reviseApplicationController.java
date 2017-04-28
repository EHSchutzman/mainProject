package Controllers;

import DBManager.DBManager;
import Form.Form;
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
public class reviseApplicationController extends UIController{

    @FXML
    private Button back_button;
    @FXML
    public Button browse_button;
    @FXML
    public Button submit_button;
    @FXML
    public Button close_button;
    @FXML
    public TextField rep_id_text;
    @FXML
    public TextField permit_no_text;
    @FXML
    public TextField serial_no_text;

    // Source and Alcohol Type ComboBoxes + their TextFields for Agent Review
    @FXML
    public ComboBox source_combobox;
    @FXML
    public TextField source_text;
    @FXML
    public ComboBox alcohol_type_combobox;
    @FXML
    public TextField alcohol_type_text;

    @FXML
    public TextField brand_name_text;
    @FXML
    public TextField fanciful_name_text;
    @FXML
    public TextField alcohol_content_text;
    @FXML
    public TextField formula_text;

    @FXML
    public TextField label_text;

    // Wine only
    @FXML
    public TextField vintage_year_text;
    @FXML
    public TextField ph_level_text;
    @FXML
    public TextField grape_varietals_text;
    @FXML
    public TextField wine_appellation_text;


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
    public TextField applicant_street_1_text;
    @FXML
    public TextField applicant_street_2_text;
    @FXML
    public TextField applicant_city_text;
    @FXML
    public TextField applicant_state_text;
    @FXML
    public TextField applicant_zip_text;
    @FXML
    public TextField applicant_country_text;
    //Mailing Address
    @FXML
    public RadioButton sameAsApplicantButton;
    @FXML
    public TextArea mailing_addressText;
    @FXML
    public TextField mailing_name_text;
    public TextField mailing_street_2_text;
    public TextField mailing_street_1_text;
    public TextField mailing_city_text;
    public TextField mailing_zip_text;
    public TextField mailing_country_text;
    public TextField mailing_state_text;
    @FXML
    public TextField phone_no_text;
    @FXML
    public TextField signature_text;
    @FXML
    public TextField email_text;
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

        String brand_name = brand_name_text.getText();
        String fanciful_name = (fanciful_name_text.getText());
        Double alcohol_content = (Double.parseDouble(alcohol_content_text.getText()));
        String formula = (formula_text.getText());
        String labeltext = label_text.getText();

        // Wines only
        String vintage_year = (vintage_year_text.getText());
        int pH_level = (Integer.parseInt(ph_level_text.getText()));
        String grape_varietals = (grape_varietals_text.getText());
        String wine_appellation = (wine_appellation_text.getText());

        String applicant_street = (applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        String applicant_city = (applicant_city_text.getText());
        String applicant_state = (applicant_state_text.getText());
        String applicant_zip = (applicant_zip_text.getText());
        String applicant_country = (applicant_country_text.getText());

        String signature = (signature_text.getText());
        String phone_no = (phone_no_text.getText());
        String email = (email_text.getText());

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
            source_text.setPromptText("Imported");
        } else if (form.getSource().equals("Domestic")) {
            source_text.setPromptText("Domestic");
        }

        // Get Alcohol Type info and set it to display for the Agent
        //alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        if (form.getalcohol_type().equals("Malt Beverages")) {
            //alcohol_type_combobox.getSelectionModel().select(1);
            alcohol_type_text.setText("Malt Beverages");
        } else if (form.getalcohol_type().equals("Wine")) {
            //alcohol_type_combobox.getSelectionModel().select(2);
            alcohol_type_text.setText("Wine");
        } else if (form.getalcohol_type().equals("Distilled Spirits")) {
            //alcohol_type_combobox.getSelectionModel().select(3);
            alcohol_type_text.setText("Distilled Spirits");
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
        rep_id_text.setText(form.getrep_id());
        permit_no_text.setText(form.getpermit_no());
        serial_no_text.setText(form.getserial_no());
        brand_name_text.setText(form.getbrand_name());
        fanciful_name_text.setText(form.getfanciful_name());
        alcohol_content_text.setText(String.valueOf(form.getalcohol_content()));
        formula_text.setText(form.getFormula());
        label_text.setText(form.getlabel_text());
        // Wines only
        vintage_year_text.setText(form.getvintage_year());
        ph_level_text.setText(String.valueOf(form.getpH_level()));
        grape_varietals_text.setText(form.getgrape_varietals());
        wine_appellation_text.setText(form.getwine_appellation());

        //address_text.setPromptText(form.getapplicant_street() + ", " + form.getapplicant_city() + ", " + form.getapplicant_state() + " " + form.getapplicant_zip() + ", " + form.getapplicant_country());

        applicant_street_1_text.setText(form.getapplicant_street());
        applicant_city_text.setText(form.getapplicant_city());
        applicant_state_text.setText(form.getapplicant_state());
        applicant_zip_text.setText(form.getapplicant_zip());
        applicant_country_text.setText(form.getapplicant_country());
        signature_text.setPromptText(form.getSignature());
        phone_no_text.setPromptText(form.getphone_no());
        System.out.println("trying to display email and phone");
        email_text.setPromptText(form.getEmail());

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
            grape_varietals_text.setDisable(false);
            wine_appellation_text.setDisable(false);

        }
        if (booleanArrayList.get(4) == true && form.getalcohol_type().equals("Wine")) {
            vintage_year_text.setDisable(false);

        }
        if (booleanArrayList.get(5) == true) {
            browse_button.setDisable(false);

        }
        if (booleanArrayList.get(6) == true && form.getalcohol_type().equals("Wine")) {
            ph_level_text.setDisable(false);
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
            alcohol_content_text.setDisable(false);

        }
        if (booleanArrayList.get(11) == true) {
            alcohol_content_text.setDisable(false);

        }

        //       applicant_name_text.setText(manager.findUsersName(form.getapplicant_id()));
        email_text.setText(main.userData.getUserInformation().getEmail());
        phone_no_text.setText(main.userData.getUserInformation().getPhoneNo());

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