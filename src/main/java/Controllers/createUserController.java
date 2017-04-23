package Controllers;

import UserAccounts.Authentication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: incomplete.
 * TODO: clean code, WARNINGS, doxygen
 */
public class createUserController extends UIController{

    @FXML
    private TextField username, password, firstName, middleInitial, lastName, email, phoneNo;
    @FXML
    private ComboBox<String> authentication;
    private ObservableList<String> user_types = FXCollections.observableArrayList("User","Agent","Applicant","SuperAgent");
    @FXML
    public Label errorLabel;

    private Authentication isAuthentic = new Authentication();

    /**
     * Initialize the authentication combo box
     */
    void initializeComboBox() {authentication.getItems().addAll(user_types);}

    @FXML
    public void createUserAction() throws IOException{
        String emailText = email.getText();
        String loginNameText = username.getText();
        String passwordText = password.getText();
        String phoneNum = phoneNo.getText();
        String firstName = this.firstName.getText();
        String middleIn = middleInitial.getText();
        String lastname = lastName.getText();
        String combo = authentication.getValue();

        int authLvl;
        if (combo.equals("User")) {
            authLvl = 0;
        } else if (combo.equals("Applicant")) {
            authLvl = 1;
        } else if (combo.equals("Agent")) {
            authLvl = 2;
        } else {
            authLvl = 3;
        }

        isAuthentic.createUser(firstName, middleIn, lastname, loginNameText, passwordText, emailText, phoneNum, authLvl);
        try {
            setDisplayToMainPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
