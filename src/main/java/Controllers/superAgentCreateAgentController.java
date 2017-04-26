package Controllers;

import UserAccounts.Authentication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Status:
 * TODO: review this
 */
public class superAgentCreateAgentController extends UIController {

    @FXML
    private TextField username, password, firstName, middleInitial, lastName, email, phoneNo;
    @FXML
    public Label errorLabel;

    private Authentication isAuthentic = new Authentication();

    /**
     * Creates a user using the Authentication entity
     * @throws IOException - throws exception
     */
    @FXML
    public void createUserAction() throws IOException{
        String emailText = email.getText();
        String loginNameText = username.getText();
        String passwordText = password.getText();
        String phoneNum = phoneNo.getText();
        String firstNameText = firstName.getText();
        String middleIn = middleInitial.getText();
        String lastNameText = lastName.getText();

        int authLvl = 2;

        isAuthentic.createUser(firstNameText, middleIn, lastNameText, loginNameText, passwordText, emailText, phoneNum, authLvl);
        try {
            returnToMainPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}