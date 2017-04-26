package Controllers;

import Form.Form;
import DBManager.DBManager;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

/**
 * Status: incomplete, needs fixing.
 * TODO: need to make menu bar work first!
 * TODO: make this a popup?
 * TODO: no closeButton in fxml - no need to make it in this controller
 * TODO: - just make the onMouseClick = "closeWindow" and it will work! (UIController rocks!)
 * TODO: do we need browseButton in iter2application.fxml? (remove)
 */
public class iter2applicationController extends UIController{

    @FXML
    public Button submitButton;
    @FXML
    public Button browse_button;
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
    private loginPageController lpc = new loginPageController();
    private Form form = new Form();

    void initializeComboBox() {
        source_combobox.setItems(FXCollections.observableArrayList("Domestic", "Imported"));
        alcohol_type_combobox.setItems(FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits"));
    }

    public void createApplicantForm() {
        form.setttb_id(form.makeUniqueID());
        form.setrep_id(rep_id_text.getText());
        form.setpermit_no(permit_no_text.getText());
        form.setserial_no(serial_no_text.getText());
        // Initialize Source ChoiceBox and get value
        form.setSource(source_combobox.getValue().toString());
        // Initialize Alcohol Type ChoiceBox, get and set value
        form.setalcohol_type(alcohol_type_combobox.getValue().toString());
        // Initialize checkboxes
        // Type of Application Check Boxes and their corresponding TextFields
        option_1_checkbox = new CheckBox("Certificate of Label Approval");
        option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
        option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
        option_4_checkbox = new CheckBox("Resubmission After Rejection");
        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> tempBoolArray = form.getapplication_type();
        ArrayList<String> tempStrArray = form.getapplication_type_text();
        if (option_1_checkbox.isSelected()) {//choice 0
            tempBoolArray.set(0, true);
        } else if (option_2_checkbox.isSelected()) {
            tempStrArray.set(1, option_2_text.getText());
            tempBoolArray.set(1, true);
        } else if (option_3_checkbox.isSelected()) {
            tempStrArray.set(2, option_3_text.getText());
            tempBoolArray.set(2, true);
        } else if (option_4_checkbox.isSelected()) {
            tempStrArray.set(3, option_4_text.getText());
            tempBoolArray.set(3, true);
        }
        form.setapplication_type(tempBoolArray);
        form.setapplication_type_text(tempStrArray);
        form.setbrand_name(brand_name_text.getText());
        form.setfanciful_name(fanciful_name_text.getText());
        //form.setalcohol_content(Double.parseDouble(alcohol_content_text.getText()));
        form.setFormula(formula_text.getText());
        form.setlabel_text(label_text.getText());
        // Wines only
        form.setvintage_year(vintage_year_text.getText());
        //form.setpH_level(Integer.parseInt(ph_level_text.getText()));
        form.setgrape_varietals(grape_varietals_text.getText());
        form.setwine_appellation(wine_appellation_text.getText());
        form.setapplicant_street(applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        form.setapplicant_city(applicant_city_text.getText());
        form.setapplicant_state(applicant_state_text.getText());
        form.setapplicant_zip(applicant_zip_text.getText());
        form.setapplicant_country(applicant_country_text.getText());
        if (sameAsApplicantButton.isSelected()) {
            form.setmailing_address(form.getapplicant_street() + " " +
                    form.getapplicant_city() + " " + form.getapplicant_state() +
                    form.getapplicant_zip() + " " + form.getapplicant_country());
        } else {
            form.setmailing_address(mailing_addressText.getText());
        }
        form.setSignature(signature_text.getText());
        form.setphone_no(phone_no_text.getText());
        form.setEmail(email_text.getText());
    }

    @FXML
    public void submitForm() throws IOException{
        createApplicantForm();
        int pH_level = 7;
/*        if (rep_id_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (permit_no_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (serial_no_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (brand_name_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (fanciful_name_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        try {
            Double.parseDouble(alcohol_content_text.getText());
        } catch (Exception e) {
            errorLabel.setText("Please FIll in all fields");
            return;
        }
        if (formula_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (label_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (vintage_year_text.getText() == null && source_combobox.getValue().toString().equals("Wine")) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (grape_varietals_text.getText() == null && source_combobox.getValue().toString().equals("Wine")) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (ph_level_text.getText() == null && source_combobox.getValue().toString().equals("Wine")) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (wine_appellation_text.getText() == null && source_combobox.getValue().toString().equals("Wine")) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (applicant_street_1_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (applicant_city_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (applicant_state_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (applicant_country_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (applicant_zip_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (applicant_country_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (signature_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (phone_no_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }
        if (email_text.getText() == null) {
            errorLabel.setText("Please Fill in all fields");
            return;
        }*/


        String rep_id = rep_id_text.getText();
        String permit_no = permit_no_text.getText();
        String serial_no = serial_no_text.getText();

//        String source = source_combobox.getValue().toString();

//        String alcohol_type = alcohol_type_combobox.getValue().toString();
        String source = "Domestic";
        String alcohol_type = "Malt Beverage";

        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> application_type = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            application_type.add(false);
        }
        ArrayList<String> application_type_text = new ArrayList<>();
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

        String vintage_year = null;
        String grape_varietals = null;
        String wine_appellation = null;
/*        if (alcohol_type_combobox.getValue().toString().equals("Wine")) {
            vintage_year = (vintage_year_text.getText());
            grape_varietals = (grape_varietals_text.getText());
            pH_level = (Integer.parseInt(ph_level_text.getText()));
            wine_appellation = (wine_appellation_text.getText());
        } else {
            vintage_year = (null);
            grape_varietals = (null);
            pH_level = -1;
            wine_appellation = (null);
        }*/

        String applicant_street = (applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        String applicant_city = (applicant_city_text.getText());
        String applicant_state = (applicant_state_text.getText());
        String applicant_zip = (applicant_zip_text.getText());
        String applicant_country = (applicant_country_text.getText());

        String mailing_address;
        if (sameAsApplicantButton.isSelected()) {
            mailing_address = (applicant_street + " " + applicant_city + " " + applicant_state + applicant_zip + " " + applicant_country);
        } else {
            mailing_address = (mailing_name_text.getText() + mailing_street_1_text.getText() + mailing_street_2_text.getText() + mailing_city_text.getText() + mailing_zip_text.getText() + mailing_country_text.getText() + mailing_state_text.getText());
        }

        String signature = (signature_text.getText());
        String phone_no = (phone_no_text.getText());
        String email = (email_text.getText());
        Date submitdate = new Date(System.currentTimeMillis());


        //TODO Update image when adding image display things
        Form form = new Form(rep_id, permit_no, source, serial_no, alcohol_type, brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state, applicant_zip, applicant_country, mailing_address, formula, phone_no, email, labeltext, main.userData.form.getlabel_image(), submitdate, signature, "Pending", null, main.userData.getUserInformation().getUid(), null, null, vintage_year, pH_level, grape_varietals, wine_appellation, application_type, application_type_text, null);

        db.persistForm(form);
        super.returnToMainPage();
    }

    @FXML
    public void browseForFile() {
        FileChooser fc = new FileChooser();
        String currentDir = System.getProperty("user.dir");
//        System.out.println(currentDir);

        fc.setInitialDirectory(new File(currentDir));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files (.jpg .png)", "*.jpg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);


        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                label_image.setImage(image);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid File");
        }

        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

        String newFileName = selectedFile.getName().split("\\.")[0] + dateFormat.format(date) + "." + selectedFile.getName().split("\\.")[1];
        File destInSys = new File(System.getProperty("user.dir") + "/src/main/resources/Controllers/images/" + newFileName);
        try {
            Files.copy(selectedFile.toPath(), destInSys.toPath(), StandardCopyOption.REPLACE_EXISTING, NOFOLLOW_LINKS);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        String path = getClass().getResource("images/").toExternalForm();
//        System.out.println(path);
        form.setlabel_image(newFileName);
        try {
            System.out.println("here");
            String path = (System.getProperty("user.dir") + "/src/main/resources/Controllers/images/" + newFileName);
            File file = new File(path);
            String localURL = file.toURI().toURL().toString();
            Image image = new Image(localURL);
            System.out.println("Now here");
            System.out.println("down");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //        Image image = new Image(getClass().getResource("images/" + newFileName).toExternalForm());

//        label_image.setImage(image);
    }

    @FXML
    protected void disableFields() {
        if (mailing_country_text.isDisabled()) {
            mailing_country_text.setDisable(false);
            mailing_city_text.setDisable(false);
            mailing_name_text.setDisable(false);
            mailing_state_text.setDisable(false);
            mailing_street_1_text.setDisable(false);
            mailing_street_2_text.setDisable(false);
            mailing_zip_text.setDisable(false);

        } else {
            mailing_country_text.setDisable(true);
            mailing_city_text.setDisable(true);
            mailing_name_text.setDisable(true);
            mailing_state_text.setDisable(true);
            mailing_street_1_text.setDisable(true);
            mailing_street_2_text.setDisable(true);
            mailing_zip_text.setDisable(true);
        }
    }

}
