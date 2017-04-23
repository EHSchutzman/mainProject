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
 * Created by DanielKim on 4/13/2017.
 */
public class loginPageController extends UIController{
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Button createUserButton;

    private Authentication isAuthentic = new Authentication();
    public User user = new User();

    /**
     * Redirects to defaultUserMainPage.fxml
     * @throws IOException
     */
    @FXML
    public void setDisplayToDefaultUserMainPage() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("defaultUserMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        defaultUserMainPageController controller = loader.getController();
        controller.init(super.main);
    }

    @FXML
    public void setDisplayToApplicantMain() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicantMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        //applicantMainPageController controller = loader.getController();
        //controller.init(user);
    }

    @FXML
    public void setDisplayToAgentMain() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("agentMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        //agentMainPageController controller = loader.getController();
        //controller.initiailizeCurrentUserLabel(user);
    }

    @FXML
    public void setDisplayToSuperAgentMain() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentInitialPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        //superAgentInitialPageController controller = loader.getController();
        //controller.init(user);
    }

    @FXML
    public void setDisplayToCreateUser() throws IOException{
        Stage stage;
        stage=(Stage) createUserButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createUser.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        createUserController controller = loader.getController();
        controller.initializeComboBox();
    }

    @FXML
    public void loginAction() {
        String name = username.getText();
        String pass = password.getText();

        isAuthentic.setUsername(name);
        isAuthentic.setPassword(pass);

        if(isAuthentic.authenticate()) {
            user = isAuthentic.getFoundUser();
        }

        if(user.getUsername() != null && user.getUsername().equals(name)){
            try {
                switch (user.getAuthenticationLevel()) {
                    case 0:
                        System.out.println("This user has authentication level 0");
                        super.main.userData.setUserInformation(user);
                        setDisplayToDefaultUserMainPage();
                        break;
                    case 1:
                        System.out.println("user has authentication lvl 1");
                        setDisplayToApplicantMain();
                        break;
                    case 2:
                        System.out.println("user has authentication lvl 2");
                        setDisplayToAgentMain();
                        break;
                    case 3:
                        System.out.println("user has authentication lvl 3");
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

    public void clearFields(){
        username.clear();
        password.clear();
    }

    public User getUser() {
        return this.user;
    }

}
