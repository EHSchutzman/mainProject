package UserAccounts;


import Initialization.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AuthenticationController {
    Authentication isAuthentic = new Authentication();
    User user;

    ObservableList<String> menuOptions = FXCollections.observableArrayList("Agent", "User", "Applicant");

    private Main main;
    @FXML
    public TextField username;
    public TextField password;
    public Button loginButton;
    @FXML
    public TextField userID;
    public TextField email;
    public TextField loginName;
    public TextField fullName;
    public TextField phoneNumber;
    @FXML
    public ChoiceBox<String> userType;

    @FXML
    public Label errorLabel;
    @FXML
    public void displayCreateUserPage() {

        try {
            main.displayCreateUser();
//            userType.getItems().removeAll();
//            userType.getItems().addAll("User", "Agent", "Applicant");
//            userType.getSelectionModel().select("Option B");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void returnToMain() {
        try {

            main.setDisplayToMain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createUserAction() {

        String userIDText = userID.getText();
        String emailText = email.getText();
        String loginNameText = loginName.getText();
        String passwordText = password.getText();
        String fullNameText = fullName.getText();
        String phoneNum = phoneNumber.getText();
        isAuthentic.createUser(userIDText, fullNameText, loginNameText, passwordText, emailText, phoneNum);
        try {
            main.setDisplayToLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginAction() {
        String name = username.getText();
        String pass = password.getText();


        isAuthentic.setUsername(name);
        isAuthentic.setPassword(pass);
        isAuthentic.authenticate();
        main.userData.setUserInformation(isAuthentic.getFoundUser());

        if(isAuthentic.getFoundUser().getUsername() != null && isAuthentic.getFoundUser().getUsername().equals(name) ){
            //@TODO If user is authenticated return to proper screen
            if(isAuthentic.getFoundUser().getAuthenticationLevel() == 1){
                System.out.println("user has authentication lvl 1");
                main.setDisplayToApplicantMain();
            }
            else if(isAuthentic.getFoundUser().getAuthenticationLevel() == 2){
                System.out.println("user has authentication lvl 2");
                main.setDisplayToAgentMain();
                //set display to agent main page
            }
            else if(isAuthentic.getFoundUser().getAuthenticationLevel() == 3){
                System.out.println("user has authentication lvl 3");
                main.setDisplayToAgentMain();
            }
            else if(isAuthentic.getFoundUser().getAuthenticationLevel() == 0){
                System.out.println("This user has authentication level 0");
                main.setDisplayToDefaultMain();

            }

        } else{
            System.out.println("user not found");
            errorLabel.setText("Invalid Username / Password, Please try again");
            clearFields();
            return;

        }


    }

    public void clearFields(){
        username.clear();
        password.clear();
    }
    public void setDisplay(Main main) {
        this.main = main;
    }


}
