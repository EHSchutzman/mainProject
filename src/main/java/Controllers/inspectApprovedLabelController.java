package Controllers;

import DBManager.DBManager;
import UserAccounts.User;
import Form.Form;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

/**
 * Status: incomplete.
 * TODO: clean code, make sure there are no WARNINGS
 */
public class inspectApprovedLabelController extends UIController{
    @FXML
    private TextField rep_id, permit_no, brand_name, phone_no, email, alcohol_content,
    vintage_year, ph_level, serial_no, fanciful_name, formula, grape_varietals, wine_appellation,
    label_text, signature, applicant_name, source, alcohol_type;
    @FXML
    private Button printableVersionButton;
    @FXML
    private TextArea address;
    @FXML
    private ImageView label_image;
    @FXML
    private Label errorLabel; //TODO: Check if this is necessary

    private DBManager dbManager = new DBManager();
    private Form form = new Form();

    @FXML
    public void setDisplayPrintableVersion() {
        try {
            Stage stage;
            stage = (Stage) printableVersionButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("testingForm.fxml"));
            ScrollPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1000, 700);
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            testingFormController controller = loader.getController();
            controller.setFormDisplay(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets form object in this controller, and sets all displayable fields
     * @param form - form passed from double clicked row
     */
    void setForm(Form form) {
        this.form = form;
        setFormDisplay();
    }

    private void setFormDisplay() {
        // Get current user info
        User currentUser = dbManager.findUser("user_id = \'" + form.getapplicant_id() + "\'");
        String name = currentUser.getFirstName() + " " + currentUser.getMiddleInitial() + " " + " " + currentUser.getLastName();
        // Set applicant name
        applicant_name.setText(name);
        // Set source
        if (form.getSource().equals("Imported")) {
            source.setPromptText("Imported");
        } else if (form.getSource().equals("Domestic")) {
            source.setPromptText("Domestic");
        }
        // Set alcohol type
        if (form.getalcohol_type().equals("Malt Beverages")) {
            alcohol_type.setText("Malt Beverages");
        } else if (form.getalcohol_type().equals("Wine")) {
            alcohol_type.setText("Wine");
        } else if (form.getalcohol_type().equals("Distilled Spirits")) {
            alcohol_type.setText("Distilled Spirits");
        }
        // Set type of application
        /*option_1_checkbox = new CheckBox("Certificate of Label Approval");
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
        }*/

        // Set other text fields
        rep_id.setText(form.getrep_id());
        permit_no.setText(form.getpermit_no());
        serial_no.setText(form.getserial_no());
        brand_name.setText(form.getbrand_name());
        fanciful_name.setText(form.getfanciful_name());
        alcohol_content.setText(String.valueOf(form.getalcohol_content()));
        formula.setText(form.getFormula());
        label_text.setText(form.getlabel_text());
        // If wine, set wine only fields
        if (form.getalcohol_type().equals("Wine")) {
            vintage_year.setText(form.getvintage_year());
            ph_level.setText(String.valueOf(form.getpH_level()));
            grape_varietals.setText(form.getgrape_varietals());
            wine_appellation.setText(form.getwine_appellation());
        } else {
            vintage_year.setText("");
            ph_level.setText("");
            grape_varietals.setText("");
            wine_appellation.setText("");
        }
        // Set address
        address.setText(form.getapplicant_street() + ", " + form.getapplicant_city() + ", " +
                form.getapplicant_state() + " " + form.getapplicant_zip() + ", " + form.getapplicant_country());
        // Set more text fields
        applicant_name.setText(dbManager.findUsersName(form.getapplicant_id()));
        signature.setText(form.getSignature());
        phone_no.setText(form.getphone_no());
        email.setText(form.getEmail());
        // Set label image
        try {
            /*
             * IF EXPORTING THIS FOR JAR CHANGE
             */
            String path = (System.getProperty("user.dir") + "/images/" + form.getlabel_image());
            File file = new File(path);
            String localURL = file.toURI().toURL().toString();
            Image image = new Image(localURL);
            label_image.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
