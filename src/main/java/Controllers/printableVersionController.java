package Controllers;

import DBManager.DBManager;
import Form.Form;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Status: complete, needs reimplementation.
 * TODO: clean code, add functionality, need UI to make pages first (COMMUNICATE!), make sure there are no WARNINGS
 */
public class printableVersionController extends UIController{

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label ttb_id, rep_id, permit_no, brand_name, phone_no, email,
            serial_no, fanciful_name, formula, grape_varietals, wine_appellation,
            label_text, signature, applicant_name, mailing_street_address2, mailing_street_address1,
            mailing_second_line2, mailing_second_line1, mailing_third_line2, mailing_third_line1,
            submit_date;
    @FXML
    private CheckBox domesticCheck, importedCheck, wineCheck, distilledSpiritsCheck, maltBeverageCheck,
            cert_label_approval, cert_exemption, cert_distinctive, cert_resubmission;

    private DBManager dbManager = new DBManager();
    private Form form = new Form();

    public void setForm(Form form) {
        this.form = form;
        setFormDisplay();
    }

    private void setFormDisplay() {
        form = dbManager.findSingleForm(form.getttb_id(), new ArrayList<>());
        ttb_id.setText(form.getttb_id());
        rep_id.setText(form.getrep_id());
        permit_no.setText(form.getpermit_no());
        serial_no.setText(form.getserial_no());
        brand_name.setText(form.getbrand_name());
        fanciful_name.setText(form.getfanciful_name());
        formula.setText(form.getformula());
        wine_appellation.setText(form.getwine_appellation());
        grape_varietals.setText(form.getgrape_varietals());
        phone_no.setText(form.getphone_no());
        email.setText(form.getEmail());
        mailing_street_address1.setText("");
        mailing_street_address2.setText("");
        mailing_second_line1.setText("");
        mailing_second_line2.setText("");
        mailing_third_line1.setText("");
        mailing_third_line2.setText("");
        if(form.getSource().equals("Domestic")) {
            domesticCheck.setSelected(true);
        } else {
            importedCheck.setSelected(true);
        }
        if(form.getalcohol_type().equals("Wine")) {
            wineCheck.setSelected(true);
        } else if(form.getalcohol_type().equals("Malt Beverages")) {
            maltBeverageCheck.setSelected(true);
        } else {
            distilledSpiritsCheck.setSelected(true);
        }
        label_text.setText(form.getlabel_text());
        submit_date.setText(form.getsubmit_date().toString());
        signature.setText(form.getSignature());
        applicant_name.setText(form.getapplicant_id()); // TODO: add query for getting search
        if(!form.getapplication_type().isEmpty() && form.getapplication_type().get(0)) {
            cert_label_approval.setSelected(true);
        } else if(!form.getapplication_type().isEmpty() && form.getapplication_type().get(1)) {
            cert_exemption.setSelected(true);
        } else if(!form.getapplication_type().isEmpty() && form.getapplication_type().get(2)) {
            cert_distinctive.setSelected(true);
        } else {
            cert_resubmission.setSelected(true);
        }
    }

    @FXML
    public void saveAsPng() {
        WritableImage image = anchorPane.snapshot(new SnapshotParameters(), null);
        // TODO: probably use a file chooser here, make disabled checkboxes more visible/change this?
        File file = new File(System.getProperty("user.dir") + "/form_" + ttb_id.getText() +".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
            e.printStackTrace();
        }
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
