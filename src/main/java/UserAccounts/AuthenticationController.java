package UserAccounts;


import Initialization.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AuthenticationController {
    Authentication isAuthentic = new Authentication();
    User user;

    ObservableList<String> menuOptions = FXCollections.observableArrayList("Agent", "User", "Applicant");

    private Main main;
    @FXML
    public TextField username;
    public PasswordField password_text;
    public Button loginButton;
    @FXML
    public TextField userID;
    public TextField email_text;
    public TextField login_name_text;
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


}