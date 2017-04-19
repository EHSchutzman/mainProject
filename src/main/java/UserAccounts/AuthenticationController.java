package UserAccounts;


import DBManager.DBManager;
import DatabaseSearch.SearchController;
import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class AuthenticationController {
    Authentication isAuthentic = new Authentication();
    User user;
    DBManager dbManager = new DBManager();

    ObservableList<String> menuOptions = FXCollections.observableArrayList("Agent", "User", "Applicant");

    private Main main;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password_text;
    @FXML
    public Button loginButton;
    @FXML
    public TextField userID;
    @FXML
    public TextField email_text;
    @FXML
    public TextField login_name_text;
    @FXML
    public TextField phone_number_text;
    @FXML
    public TextField last_name_text;
    @FXML
    public TextField middle_initial_text;
    @FXML
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
            if(main.userData.getUserInformation().getAuthenticationLevel() == 3) {
                main.setDisplayToSuperAgentMain();
            } else {
                main.setDisplayToMain();
            }
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

        if(main.userData.getUserInformation().getAuthenticationLevel() == 3) {
            authLvl = 2;
            boolean newUser = isAuthentic.createUser(firstName,middleIn,lastname,loginNameText,passwordText,emailText,phoneNum,authLvl);
            if(newUser) {
                try {
                    main.setDisplayToSuperAgentMain();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("User already exists!");
                errorLabel.setText("User already exists!");
                return;
            }
        }

        if (combo.equals("Default User")) {
            authLvl = 0;
        } else if (combo.equals("Applicant")) {
            authLvl = 1;
        } else if (combo.equals("Agent")) {
            authLvl = 2;
        } else {
            authLvl = 3;
        }

        boolean authenticUser = isAuthentic.createUser(firstName, middleIn, lastname, loginNameText, passwordText, emailText, phoneNum, authLvl);

        if(authenticUser) {
            try {
                main.setDisplayToLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {    // User already exists!
            System.out.println("User already exists!");
            errorLabel.setText("User already exists!");
        }
    }

    @FXML
    public void loginAction() {
        String name = username.getText();
        String pass = password_text.getText();
        Boolean authenticUser;

        isAuthentic.setUsername(name);
        isAuthentic.setPassword(pass);
        authenticUser = isAuthentic.authenticate();


        if(authenticUser) {
        //if(isAuthentic.getFoundUser().getUsername() != null && isAuthentic.getFoundUser().getUsername().equals(name) ){

            // Create persistent user
            main.userData.setUserInformation(isAuthentic.getFoundUser());
            System.out.println(isAuthentic.getFoundUser());
            System.out.println(isAuthentic.getFoundUser().getAuthenticationLevel());
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
                main.setDisplayToSuperAgentMain();
            }
            else if(isAuthentic.getFoundUser().getAuthenticationLevel() == 0){
                System.out.println("This user has authentication level 0");
                main.setDisplayToDefaultMain();

            }

        } else {    // Could not authenticate user

            System.out.println("Could not authenticate user.");
            errorLabel.setText("Invalid Username or Password, Please try again");
            clearFields();

        }


    }

    public void clearFields(){
        username.clear();
        password_text.clear();
    }
    public void setDisplay(Main main) {
        this.main = main;
    }

    public void setDisplay2(Main main, User user) {
        this.main = main;
        this.user = user;
        setEditUser(user);
    }

    @FXML
    private TextField edit_user_id_text;
    @FXML
    private TextField edit_username_text;
    @FXML
    private TextField edit_first_name_text;
    @FXML
    private TextField edit_middle_initial_text;
    @FXML
    private TextField edit_last_name_text;
    @FXML
    private TextField edit_email_text;
    @FXML
    private TextField edit_phone_number_text;
    @FXML
    private ComboBox<String> edit_user_type_combobox;
    @FXML
    private Button edit_user_button;
    @FXML
    private Label edit_errorLabel;

    // prefill edit user page
    public void setEditUser(User user) {
        edit_user_type_combobox.setItems(FXCollections.observableArrayList("0","1","2","3"));
        edit_user_id_text.setText(user.getUid());
        edit_username_text.setText(user.getUsername());
        edit_email_text.setText(user.getEmail());
        edit_phone_number_text.setText(user.getPhoneNo());
        edit_first_name_text.setText(user.getFirstName());
        edit_middle_initial_text.setText(user.getMiddleInitial());
        edit_last_name_text.setText(user.getLastName());
        edit_user_type_combobox.setValue(Integer.toString(user.getAuthenticationLevel()));
    }

    // When super agent presses edit button
    @FXML
    public void editUserAction() {
        User user = dbManager.findUser("user_id='" + edit_user_id_text.getText() + "'");
        User editUser = new User(user.getUid(), edit_username_text.getText(), user.getPassword(),
                edit_first_name_text.getText(), edit_middle_initial_text.getText(), edit_last_name_text.getText(),
                edit_email_text.getText(), edit_phone_number_text.getText(), Integer.parseInt(edit_user_type_combobox.getValue()));
        boolean editSuccess = dbManager.updateUser(editUser);
        System.out.println(editSuccess);
        if(editSuccess) {
            // go to success page
            try {
                Stage stage;
                stage=(Stage) edit_user_button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("editUserConfirmationMessage.fxml"));
                loader.setLocation(getClass().getResource("../../../resources/main/Initialization/editUserConfirmationMessage.fxml"));
                //System.out.println(loader.getLocation().toString());
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // set error label
            edit_errorLabel.setText("User with the edited username/email already exists");
        }
    }

    @FXML
    private Button edit_closeButton;

    @FXML
    public void closeWindow() throws IOException{
        Stage stage;
        stage = (Stage) edit_closeButton.getScene().getWindow();
        stage.close();
    }

}