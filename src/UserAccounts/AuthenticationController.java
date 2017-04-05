package UserAccounts;


import Initialization.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.awt.*;
import java.awt.Label;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AuthenticationController {
    Authentication isAuthentic;
    User user;


    private Main main;
    @FXML
    public TextField username;
    public TextField password;
    public Label errorMessage;
    public Button loginButton;

    public AuthenticationController() {
        errorMessage = null;
        password = null;
        username = null;
    }

    @FXML
    public void loginAction(){
        String name = username.getText();
        String pass = password.getText();

        isAuthentic = new Authentication(name,pass);
        if(isAuthentic.authenticate()) {

            //make a user
            user = new User(isAuthentic.getUsername(), isAuthentic.getRealName(), isAuthentic.getAuthenticationLevel());

        }
        else{
            //reset fields and wait
            //@TODO make extra checks for if name exists, and just that password is wrong
            password.setText(null);
            username.setText(null);

            errorMessage.setText("Incorrect username or password, please try again! 8^)");
            //
        }
        main.userData.setUserInformation(user);
        main.setDisplayToMain();
    }
    public void setDisplay(Main main){
        this.main = main;
    }

}
