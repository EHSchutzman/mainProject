package Controllers;

import UserAccounts.Authentication;
import UserAccounts.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: needs review.
 * TODO: doxygen, fix all WARNINGS
 */
public class loginPageController extends UIController{

    @FXML
    private TextField username, password;
    @FXML
    private Button loginButton, createUserButton;
    @FXML
    private Label errorLabel;

    private Authentication isAuthentic = new Authentication();
    public User user = new User();

    /**
     * Redirects to defaultUserMainPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToDefaultUserMain() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("defaultUserMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        defaultUserMainPageController controller = loader.getController();
        controller.init(super.main);
    }

    /**
     * Redirects to applicantMainPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToApplicantMain() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicantMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        applicantMainPageController controller = loader.getController();
        controller.init(super.main);
    }

    /**
     * Redirects to agentMainPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToAgentMain() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("agentMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        agentMainPageController controller = loader.getController();
        controller.init(super.main);
    }

    /**
     * Redirects to superAgentInitialPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToSuperAgentMain() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentInitialPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        superAgentInitialPageController controller = loader.getController();
        controller.init(super.main);
    }

    /**
     * Redirects to createUser.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToCreateUser() throws IOException{
        Stage stage;
        stage=(Stage) createUserButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createUser.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        createUserController controller = loader.getController();
        controller.init(main);
        controller.initializeComboBox();
    }

    /**
     * Login user if valid, redirect to page with correct authentication.
     * Update errorLabel otherwise
     */
    @FXML
    public void loginAction() {
        String name = username.getText();
        String pass = password.getText();
        isAuthentic.setUsername(name);
        isAuthentic.setPassword(pass);
        // Check if valid user
        if(isAuthentic.authenticate()) {
            user = isAuthentic.getFoundUser();
        }
        // Redirect to correct page if valid
        if(user.getUsername() != null && user.getUsername().equals(name)){
            try {
                switch (user.getAuthenticationLevel()) {
                    case 0:
                        super.main.userData.setUserInformation(user);
                        setDisplayToDefaultUserMain();
                        break;
                    case 1:
                        super.main.userData.setUserInformation(user);
                        setDisplayToApplicantMain();
                        break;
                    case 2:
                        super.main.userData.setUserInformation(user);
                        setDisplayToAgentMain();
                        break;
                    case 3:
                        super.main.userData.setUserInformation(user);
                        setDisplayToSuperAgentMain();
                        break;
                    default:
                        errorLabel.setText("There was an issue processing your request. Please try again.");
                        clearFields();
                        break;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        } else{
            errorLabel.setText("Invalid Username / Password, Please try again");
            clearFields();
        }
    }

    /**
     * Clears username and password text fields
     */
    private void clearFields(){
        username.clear();
        password.clear();
    }

    /**
     * Getter for user
     * @return returns user field
     * TODO: figure out why we need this
     */
    public User getUser() {
        return this.user;
    }

}
