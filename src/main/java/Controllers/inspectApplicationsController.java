package Controllers;

import DBManager.DBManager;
import DatabaseSearch.SuperAgentAppRecord;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import Form.Form;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by DanielKim on 4/27/2017.
 */
public class inspectApplicationsController extends UIController {

    @FXML
    private Text ttbid;
    @FXML
    private TextField repID, permitNo, serialNo, brandName, fancifulName, alcoholContent,
            formula, vintageYear, phLevel, grapeVarietals, wineAppellation, otherStreet,
            otherCity, otherState, otherZip, otherCountry, applicantStreet, applicantCity,
            applicantState, applicantZip, applicantCountry, phoneNo, signature, email;
    @FXML
    public Label vintageYearLabel, phLevelLabel, grapeVarietalsLabel, wineAppellationLabel,
            otherCityLabel, otherStateLabel, otherZipcodeLabel, otherCountryLabel, otherStreetLabel;;
    @FXML
    public ComboBox source_combobox, alcohol_type_combobox;
    @FXML
    private TextArea extraLabelInfo;
    @FXML
    private Rectangle wineRec;
    @FXML
    private ImageView label_image;
    @FXML
    private CheckBox sameAsApplicantBox;
    // CheckBoxes and TextFields for the Type of Application
    @FXML
    public CheckBox option_1_checkbox;
    public CheckBox option_2_checkbox;
    public TextField option_2_text; //For sale in <state> only
    public CheckBox option_3_checkbox;
    public TextField option_3_text; //Bottle capacity before closure
    public CheckBox option_4_checkbox;
    public TextField option_4_text;

    private String agentName, applicantName;

    private DBManager dbManager = new DBManager();
    private Form form = new Form();

    void setData(SuperAgentAppRecord superAgentAppRecord) {
        Form form = dbManager.findSingleForm(superAgentAppRecord.getTtbID(), new ArrayList<>());
        this.form = form;
        this.agentName = superAgentAppRecord.getAgentName();
        this.applicantName = superAgentAppRecord.getApplicantName();
        System.out.println("Form: " + form.getttb_id());
        setForm(form);
    }

    private void setForm(Form form) {
        this.ttbid.setText(form.getttb_id());
        if(form.getrep_id() != null) {this.repID.setText(form.getrep_id());}
        System.out.println("PNo: " + form.getpermit_no());
        this.permitNo.setText(form.getpermit_no());
        this.serialNo.setText(form.getserial_no());
        this.brandName.setText(form.getbrand_name());
        if(form.getfanciful_name() != null) {this.fancifulName.setText(form.getfanciful_name());}
        if(form.getalcohol_content() > 0) {this.alcoholContent.setText(Double.toString(form.getalcohol_content()));}
        if(form.getformula() != null) {this.formula.setText(form.getFormula());}
        this.source_combobox.setValue(form.getSource());
        this.alcohol_type_combobox.setValue(form.getalcohol_type());
        if(form.getalcohol_type().equals("Wine")) {
            this.vintageYear.setText(form.getvintage_year());
            this.phLevel.setText(Integer.toString(form.getpH_level()));
            this.grapeVarietals.setText(form.getgrape_varietals());
            this.wineAppellation.setText(form.getwine_appellation());
            this.wineRec.setVisible(true);
            grapeVarietals.setVisible(true);
            grapeVarietalsLabel.setVisible(true);
            wineAppellation.setVisible(true);
            wineAppellationLabel.setVisible(true);
            phLevel.setVisible(true);
            phLevelLabel.setVisible(true);
            vintageYear.setVisible(true);
            vintageYearLabel.setVisible(true);
        }
        if(form.getphone_no() != null) {this.phoneNo.setText(form.getphone_no());}
        this.email.setText(form.getEmail());
        this.signature.setText(form.getSignature());
        if(form.getlabel_text() != null) {this.extraLabelInfo.setText(form.getlabel_text());}
        //TODO: this doesn't work
        if(form.getlabel_image() != null && !form.getlabel_image().isEmpty()) {
            File file = new File(System.getProperty("user.dir") + "/src/mainData/resources/Controllers/images/" + form.getlabel_image());
            try {
                this.label_image.setImage(new Image(file.toURI().toURL().toString()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not find image");
            }
        }
        if(form.getapplication_type() != null && !form.getapplication_type().isEmpty()) {
            System.out.println(form.getapplication_type());
            if(form.getapplication_type().get(0)) {
                this.option_1_checkbox.setSelected(true);
            }
            if(form.getapplication_type().get(1)) {
                this.option_2_checkbox.setSelected(true);
                this.option_2_text.setText(form.getapplication_type_text().get(0));
            }
            if(form.getapplication_type().get(2)) {
                this.option_3_checkbox.setSelected(true);
                this.option_3_text.setText(form.getapplication_type_text().get(1));
            }
            if(form.getapplication_type().get(3)) {
                this.option_4_checkbox.setSelected(true);
                this.option_4_text.setText(form.getapplication_type_text().get(2));
            }
        }
        if(form.getapplicant_street() != null) {this.applicantStreet.setText(form.getapplicant_street());}
        this.applicantCity.setText(form.getapplicant_city());
        this.applicantState.setText(form.getapplicant_state());
        this.applicantZip.setText(form.getapplicant_zip());
        this.applicantCountry.setText(form.getapplicant_country());
        if(form.getmailing_address() != null && !form.getmailing_address().isEmpty()) {
            System.out.println("Mail:[" + form.getmailing_address() + "]");
            this.sameAsApplicantBox.setSelected(false);

            String[] splitAddress1 = form.getmailing_address().split("\n");
            if(splitAddress1.length == 3) {
                this.otherStreet.setText(splitAddress1[0]);
                this.otherCountry.setText(splitAddress1[2]);
            }
            String[] splitAddress2 = splitAddress1[1].split(",");
            if(splitAddress1.length == 3) {
                this.otherCity.setText(splitAddress2[0]);
                this.otherState.setText(splitAddress2[1]);
                this.otherZip.setText(splitAddress2[2]);
            }
            otherStateLabel.setVisible(true);
            otherStreetLabel.setVisible(true);
            otherZipcodeLabel.setVisible(true);
            otherCityLabel.setVisible(true);
            otherCountryLabel.setVisible(true);
            otherZip.setVisible(true);
            otherState.setVisible(true);
            otherStreet.setVisible(true);
            otherCity.setVisible(true);
            otherCountry.setVisible(true);
        } else {
            this.sameAsApplicantBox.setSelected(true);
        }

    }

}
