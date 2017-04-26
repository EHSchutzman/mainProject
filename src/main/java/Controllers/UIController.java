package Controllers;

import Initialization.Main;
import UserAccounts.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: incomplete.
 * TODO: add all functionality, doxygen, TODOs, WARNINGS, clean code
 */
public abstract class UIController {

    @FXML
    protected Label currentUserLabel, loginPageErrorLabel;
    @FXML
    protected Button returnToMainButton, closeButton, logoutButton, loginButton, backButton,
    aboutButton, searchButton;
    //@FXML
    //protected Hyperlink aboutLink;

    protected Main main = new Main();

    /**
     * Initializes main
     * @param main - main class to be passed through pages
     */
    @FXML
    public void init(Main main) {
        this.main = main;
        /*if(currentUserLabel != null) {
            if (main.userData == null || main.userData.getUserInformation().getUid() == null) {
                currentUserLabel.setText("Not Logged In");
            } else {
                currentUserLabel.setText(main.userData.getUserInformation().getUsername());
            }
        }*/
    }

    /**
     * Redirects to searchResultsPage.fxml TODO: make sure this is correct
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToSearchPage() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        searchPageController controller = loader.getController();
        controller.init(main);
    }

    /**
     * Redirects to aboutPage.fxml
     * @throws IOException - throws exception
     * TODO: implement this!
     */
    @FXML
    protected void setDisplayToAboutPage() throws IOException {
        Stage stage;
        Button button = aboutButton;
        stage=(Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        aboutPageController controller = loader.getController();
        controller.init(main);
    }

    /**
     * Redirects to mainPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    protected void setDisplayToMainPage() throws IOException {
        Stage stage;
        Button button = returnToMainButton;
        if(button == null) {button = backButton;}
        if(button == null) {button = loginButton;}
        stage=(Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        main.userData.setUserInformation(new User()); //TODO: check if this is correct
        mainPageController controller = loader.getController();
        controller.init(main);
    }

    /**
     * Redirect to defaultUserMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToDefaultUserMainPage() throws IOException {
        System.out.println("we here");
        Stage stage;
        Button button = returnToMainButton;
        if(button == null) {button = backButton;}
        if(button == null) {button = loginButton;}
        stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("defaultUserMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        defaultUserMainPageController controller = loader.getController();
        controller.init(main);
        controller.initSlideshow();
        controller.startAnimation();
        stage.show();
    }

    /**
     * Redirect to applicantMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToApplicantMainPage() throws IOException {

        BorderPane pane = main.getBorderPane();

        Stage stage;
        Button button = returnToMainButton;
        if(button == null) {button = backButton;}
        if(button == null) {button = loginButton;}
        stage = (Stage) button.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicantMainPage.fxml"));
        Scene scene = pane.getScene();
        pane.setTop(main.getMenuBar());
        pane.setBottom(loader.load());
        stage.setScene(scene);
        applicantMainPageController controller = loader.getController();
        controller.init(main);
        controller.initSlideshow();
        controller.startAnimation();
        stage.show();
    }

    /**
     * Redirects to agentMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToAgentMainPage() throws IOException {
        System.out.println("diplaying agent main page");
        BorderPane pane = main.getBorderPane();

        Stage stage;
        Button button = returnToMainButton;
        if(button == null) {button = backButton;}
        if(button == null) {button = loginButton;}
        stage = (Stage) button.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("agentMainPage.fxml"));
        Scene scene = pane.getScene();

        stage.setScene(scene);
        pane.setTop(main.getMenuBar());
        pane.setBottom(loader.load());
        agentMainPageController controller = loader.getController();
        controller.init(main);
        controller.initSlideshow();
        controller.startAnimation();
        stage.show();

    }

    /**
     * Redirects to superAgentMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToSuperAgentMainPage() throws IOException {
        /*BorderPane pane = main.getBorderPane();
        Stage stage;
        Button button = returnToMainButton;
        if(button == null) {button = backButton;}
        if(button == null) {button = loginButton;}
        stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentMainPage.fxml"));
        Scene scene = pane.getScene();
        stage.setScene(scene);
        superAgentMainPageController controller = loader.getController();
        System.out.println(controller);
        System.out.println(main);
        controller.init(main);
        controller.initSlideshow();
        controller.startAnimation();
        stage.show();*/
        BorderPane pane = main.getBorderPane();

        Stage stage;
        Button button = returnToMainButton;
        if(button == null) {button = backButton;}
        if(button == null) {button = loginButton;}
        stage = (Stage) button.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentInitialPage.fxml"));
        Scene scene = pane.getScene();

        stage.setScene(scene);
        pane.setTop(main.getMenuBar());
        pane.setBottom(loader.load());
        superAgentMainPageController controller = loader.getController();
        controller.init(main);
        controller.initSlideshow();
        controller.startAnimation();
        stage.show();
    }

    /**
     * Redirects to correct main page depending on authentication
     * Login user if logging in from loginPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    protected void returnToMainPage() throws IOException {
        int auth = main.userData.getUserInformation().getAuthenticationLevel();
        switch (auth) {
            case 0: setDisplayToDefaultUserMainPage(); break;
            case 1: setDisplayToApplicantMainPage(); break;
            case 2: setDisplayToAgentMainPage(); break;
            case 3: setDisplayToSuperAgentMainPage(); break;
            default:
                if(loginPageErrorLabel != null) {
                    loginPageErrorLabel.setText("There was an issue processing your request. Please try again.");
                } else {
                    setDisplayToMainPage();
                }
                break;
        }
    }

    /**
     * Closes current window
     * @throws IOException - throws exception
     */
    @FXML
    public void closeWindow() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Logout a user
     * @throws IOException - throws exception
     */
    @FXML
    public void logoutAction() throws IOException{
        main.userData.setUserInformation(new User());
        Stage stage;
        stage=(Stage) logoutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        mainPageController controller = loader.getController();
        controller.init(main);
    }

}
