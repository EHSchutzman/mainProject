package Controllers;

import DBManager.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import Form.Form;

import java.io.IOException;

/**
 * Created by James Corse on 4/25/17.
 */
public class agentApplicationReviewController extends UIController{
    private Form form = new Form();
    private DBManager dbManager = new DBManager();
    @FXML
    private TextArea approval_comments_text;
    @FXML
    private Button rejectButton, acceptButton;
    public void setReviewForm(Form form){
        this.form = form;
    }

    @FXML
    public void acceptAction() throws IOException{
        form.setapproval_comments(approval_comments_text.getText());
        form.setStatus("Accepted");
        dbManager.updateForm(form);
        System.out.println("accepted form");

        closeWindow();
    }

    @FXML
    public void rejectAction() throws IOException{
        form.setapproval_comments(approval_comments_text.getText());
        form.setStatus("Rejected");
        dbManager.updateForm(form);
        System.out.println("rejected form");

        closeWindow();
    }
}

