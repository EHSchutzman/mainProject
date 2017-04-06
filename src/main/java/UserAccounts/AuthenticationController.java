package UserAccounts;


import Initialization.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import DatabaseSearch.*;
import java.awt.*;
import java.awt.Label;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AuthenticationController {
    Authentication isAuthentic = new Authentication();
    User user;


    private Main main;
    @FXML
    public TextField username;
    public TextField password;
    public Label errorMessage;
    public Button loginButton;
    @FXML
    public TextField userID;
    public TextField email;
    public TextField loginName;
    public TextField fullName;

    public AuthenticationController() {
        errorMessage = null;
        password = null;
        username = null;
    }
    @FXML
    public void displayCreateUserPage(){
        try{
            main.displayCreateUser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void returnToMain(){
        try{
            main.setDisplayToMain();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void createUserAction(){

        String userIDText = userID.getText();
        String emailText = email.getText();
        String loginNameText = loginName.getText();
        String passwordText = password.getText();
        String fullNameText = fullName.getText();
        isAuthentic.createUser(userIDText, emailText, loginNameText, passwordText, fullNameText);
    }

    @FXML
    public void loginAction(){
        String name = username.getText();
        String pass = password.getText();
        Boolean foundUser = false;


//        isAuthentic = new Authentication(name,pass);
//        if(isAuthentic.authenticate()) {
//            //make a user
//            user = new User(isAuthentic.getUsername(), isAuthentic.getRealName(), isAuthentic.getAuthenticationLevel());
//
//        }
//        else{
//            //reset fields and wait
//            //@TODO make extra checks for if name exists, and just that password is wrong
//            password.setText(null);
//            username.setText(null);
//
//            errorMessage.setText("Incorrect username or password, please try again! 8^)");
//            //
//        }
        main.userData.setUserInformation(user);
        main.setDisplayToMain();
    }
    public void setDisplay(Main main){
        this.main = main;
    }



}
