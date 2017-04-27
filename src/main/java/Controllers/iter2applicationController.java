package Controllers;

import Form.Form;
import DBManager.DBManager;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class iter2applicationController extends UIController {
    private String uid;
    @FXML
    public void initialize(){
        createApplicantForm();
        super.init(main);
        source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
        alcohol_type_combobox.setItems(FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits"));
    }
    public void setUID(String uid){
        System.out.println("UID SENT IS " + uid);
        this.uid = uid;
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
    private Form form = new Form();

    //TODO Gut this funciton, it is never used and I need to do other things instead
    public void createApplicantForm() {
        form.setttb_id(form.makeUniqueID());
        ttbid.setText(form.getttb_id());



    }

    @FXML
    public void submitForm() {
        int pH_level;
        String grape_varietals = "";
        String wine_appellation = "";
        String vintage_year = "";
//        String ttb_id = ttb_id_label.getText();

        String rep_id = repID.getText();
        String permit_no = permitNO.getText();
        String serial_no = serialNO.getText();

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

        String brand_name = brandName.getText();
        String fanciful_name = (fancifulName.getText());
        Double alcohol_content = (Double.parseDouble(alcoholContent.getText()));
        String formula = (this.formula.getText());
        String labeltext = extraLabelInfo.getText();

        // Wines only

        if (source_combobox.getValue().toString().equalsIgnoreCase("Wine")) {
            pH_level = (Integer.parseInt(phLevel.getText()));
             grape_varietals = (grapeVarietals.getText());
             wine_appellation = (wineAppellation.getText());
             vintage_year = (vintageYear.getText());
        } else {

            pH_level = -1;
        }


        String applicant_street = (applicantStreet.getText());
        String applicant_city = (applicantCity.getText());
        String applicant_state = (applicantState.getText());
        String applicant_zip = (applicantZip.getText());
        String applicant_country = (applicantCountry.getText());

        String mailing_address;
        if (sameAsApplicantBox.isSelected()) {
            mailing_address = "";
        } else {
            mailing_address = (otherStreet.getText() + "\n" + otherCity.getText() + " " + otherState.getText()
                    + "," + otherZip.getText() + "\n" + otherCountry.getText());
        }

        String signature = (this.signature.getText());
        String phone_no = (phoneNo.getText());
        String email = (this.email.getText());
        Date submitdate = new Date(System.currentTimeMillis());


        //TODO Update image when adding image display things
        //TODO check if label_image.getId() pulls right value
        form = new Form(rep_id, permit_no, source, serial_no, alcohol_type,
                brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state,
                applicant_zip, applicant_country, mailing_address, formula, phone_no, email,
                labeltext, label_image.getId(), submitdate, signature, "Pending", null, main.userData.getUserInformation().getUid(), null, null,
                vintage_year, pH_level, grape_varietals, wine_appellation, application_type, application_type_text,
                null);

        db.persistForm(form);
//        System.out.println(form.getsubmit_date());
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


    }

    @FXML
    public void showSecondAddress() {
        System.out.println("Showing second address");
        if (sameAsApplicantBox.isSelected()) {
            otherCityLabel.setVisible(true);
            otherStateLabel.setVisible(true);
            otherStreetLabel.setVisible(true);
            otherZipcodeLabel.setVisible(true);
            otherCityLabel.setVisible(true);
            otherCountry.setVisible(true);
            otherCountryLabel.setVisible(true);
            otherZip.setVisible(true);
            otherState.setVisible(true);
            otherStreet.setVisible(true);
            otherCity.setVisible(true);
        } else {
            otherCityLabel.setVisible(false);
            otherStateLabel.setVisible(false);
            otherStreetLabel.setVisible(false);
            otherZipcodeLabel.setVisible(false);
            otherCityLabel.setVisible(false);
            otherCountry.setVisible(false);
            otherCountryLabel.setVisible(true);
            otherZip.setVisible(false);
            otherState.setVisible(false);
            otherStreet.setVisible(false);
            otherCity.setVisible(false);
        }
    }


    @FXML
    public void checkType(){
        System.out.println("in checkType");
        if(alcohol_type_combobox.getValue().toString().equalsIgnoreCase("Wine")){
            wineRec.setVisible(true);
            grapeVarietals.setVisible(true);
            grapeVarietalsLabel.setVisible(true);
            
            wineAppellation.setVisible(true);
            wineAppellationLabel.setVisible(true);
            
            phLevel.setVisible(true);
            phLevelLabel.setVisible(true);
            
            vintageYear.setVisible(true);
            vintageYearLabel.setVisible(true);
        }
        else{
            wineRec.setVisible(false);
            grapeVarietals.setVisible(false);
            grapeVarietalsLabel.setVisible(false);

            wineAppellation.setVisible(false);
            wineAppellationLabel.setVisible(false);

            phLevel.setVisible(false);
            phLevelLabel.setVisible(false);

            vintageYear.setVisible(false);
            vintageYearLabel.setVisible(false);
        }
    }



}
