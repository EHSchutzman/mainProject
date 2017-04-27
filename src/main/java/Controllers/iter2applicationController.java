package Controllers;

import DBManager.DBManager;
import Form.Form;
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
 * Created by Anthony on 4/18/2017.
 */
public class iter2applicationController {

    @FXML
    public Button back_button;
    @FXML
    public Button submit_button;
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

    @FXML
    public void setDisplayToApplicantMain() throws IOException {
        Stage stage;
        stage = (Stage) back_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicantMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void createApplicantForm() {

        form.setttb_id(form.makeUniqueID());
        form.setrep_id(rep_id_text.getText());
        form.setpermit_no(permit_no_text.getText());
        form.setserial_no(serial_no_text.getText());

        // Initialize Source ChoiceBox and get value
        source_combobox = new ComboBox(FXCollections.observableArrayList("Domestic", "Imported"));
        form.setSource((String) source_combobox.getValue());

        // Initialize Alcohol Type ChoiceBox, get and set value
        alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
        form.setalcohol_type((String) alcohol_type_combobox.getValue());

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
    public void submitForm() {
        createApplicantForm();
        int pH_level;
//        String ttb_id = ttb_id_label.getText();

        String rep_id = rep_id_text.getText();
        String permit_no = permit_no_text.getText();
        String serial_no = serial_no_text.getText();

        String source = (String) source_combobox.getValue();

        String alcohol_type = (String) alcohol_type_combobox.getValue();

        // Determine which checkboxes were selected
        // Make a temporary array to store the boolean values set them to the Form object, same with string array
        ArrayList<Boolean> application_type = new ArrayList<Boolean>();
        for (int i = 0; i < 5; i++) {
            application_type.add(false);
        }
        ArrayList<String> application_type_text = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            application_type_text.add("hello");
        }
        if (option_1_checkbox.isSelected()) {//choice 0
            application_type.set(0, true);
        } else if (option_2_checkbox.isSelected()) {
            application_type_text.set(1, option_2_text.getText());
            application_type.set(1, true);
        } else if (option_3_checkbox.isSelected()) {
            application_type_text.set(2, option_3_text.getText());
            application_type.set(2, true);
        } else if (option_4_checkbox.isSelected()) {
            application_type_text.set(3, option_4_text.getText());
            application_type.set(3, true);
        }

        String brand_name = brand_name_text.getText();
        String fanciful_name = (fanciful_name_text.getText());
        Double alcohol_content = (Double.parseDouble(alcohol_content_text.getText()));
        String formula = (formula_text.getText());
        String labeltext = label_text.getText();

        // Wines only
        String vintage_year = (vintage_year_text.getText());
        if (source.equals("Wine")) {
            pH_level = (Integer.parseInt(ph_level_text.getText()));
        } else {
            pH_level = -1;
        }
        String grape_varietals = (grape_varietals_text.getText());
        String wine_appellation = (wine_appellation_text.getText());

        String applicant_street = (applicant_street_1_text.getText() + " " + applicant_street_2_text.getText());
        String applicant_city = (applicant_city_text.getText());
        String applicant_state = (applicant_state_text.getText());
        String applicant_zip = (applicant_zip_text.getText());
        String applicant_country = (applicant_country_text.getText());

        String mailing_address;
        if (sameAsApplicantButton.isSelected()) {
            mailing_address = (applicant_street + " " +
                    applicant_city + " " + applicant_state +
                    applicant_zip + " " + applicant_country);
        } else {
            mailing_address = (mailing_name_text.getText() + mailing_street_1_text.getText()
                    + mailing_street_2_text.getText() + mailing_city_text.getText() + mailing_zip_text.getText()
                    + mailing_country_text.getText() + mailing_state_text.getText());
        }

        String signature = (signature_text.getText());
        String phone_no = (phone_no_text.getText());
        String email = (email_text.getText());
        Date submitdate = new Date(System.currentTimeMillis());


        //TODO Update image when adding image display things
        //TODO check if label_image.getId() pulls right value
        form = new Form(rep_id, permit_no, source, serial_no, alcohol_type,
                brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state,
                applicant_zip, applicant_country, mailing_address, formula, phone_no, email,
                labeltext, label_image.getId(), submitdate, signature, "Pending", null, lpc.getUser().getUid(), null, null,
                vintage_year, pH_level, grape_varietals, wine_appellation, application_type, application_type_text,
                null);

        db.persistForm(form);

        //TODO return to applicant's application list page
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
}
