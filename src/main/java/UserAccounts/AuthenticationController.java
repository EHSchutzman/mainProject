package UserAccounts;


import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AuthenticationController {
    Authentication isAuthentic = new Authentication();
    User user;

    ObservableList<String> menuOptions = FXCollections.observableArrayList("Agent", "User", "Applicant");

    private Main main;
    @FXML
    public TextField username;
    public TextField password_text;
    public Button loginButton;
    @FXML
    public TextField userID;
    public TextField email_text;
    public TextField login_name_text;
    public TextField fullName;
    public TextField phone_number_text;
    public TextField last_name_text;
    public TextField middle_initial_text;
    public TextField first_name_text;
    @FXML
    public ComboBox<String> user_type_combobox;

    @FXML
    public Label errorLabel;
    @FXML
    public void displayCreateUserPage() {

        try {
            main.displayCreateUser();
//            user_type_combobox.getItems().removeAll();
//            user_type_combobox.getItems().addAll("User", "Agent", "Applicant");
//            user_type_combobox.getSelectionModel().select("Option B");
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

        String emailText = email_text.getText();
        String loginNameText = login_name_text.getText();
        String passwordText = password_text.getText();
        String phoneNum = phone_number_text.getText();
        String firstName = first_name_text.getText();
        String middleIn = middle_initial_text.getText();
        String lastname = last_name_text.getText();
        String combo = user_type_combobox.getValue();
        int authLvl;
        if (combo.equals("Default User")) {
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
            main.setDisplayToLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginAction() {
        String name = username.getText();
        String pass = password_text.getText();


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
        password_text.clear();
    }
    public void setDisplay(Main main) {
        this.main = main;
    }


}