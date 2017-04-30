package Controllers;

import Initialization.Main;
import UserAccounts.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static Initialization.Main.root;

/**
 * Status: incomplete.
 * TODO: add all functionality, doxygen, TODOs, WARNINGS, clean code
 */
public abstract class UIController {

    @FXML
    protected Label currentUserLabel, loginPageErrorLabel;
    @FXML
    protected Button returnToMainButton, closeButton, logoutButton, loginButton, backButton,
    searchButton;
    @FXML
    protected Hyperlink aboutLink; //TODO: keep either button or link

    protected Main main = new Main();

    public void setMain(Main main){
        this.main = main;
    }
    /**
     * Initializes main
     * @param main - main class to be passed through pages
     */
    @FXML
    public void init(Main main) {
        this.main = main;
        /*if(currentUserLabel != null) {
            if (menuBarSingleton.getInstance().getGlobalData() == null || menuBarSingleton.getInstance().getGlobalData().getUserInformation().getUid() == null) {
                currentUserLabel.setText("Not Logged In");
            } else {
                currentUserLabel.setText(menuBarSingleton.getInstance().getGlobalData().getUserInformation().getUsername());
            }
        }*/
    }

    /**
     * So this function displays the confirmation message in a new window
     * TODO Find out why the FXML window size is 299 by 204, it doesn't really make sense.
     * @throws Exception
     */
    void displayConfirmationMessage() throws Exception {
        try {
            Stage stage = new Stage();
            stage.setTitle("Action confirmation!");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("confirmationMessage.fxml"));
            AnchorPane newWindow = loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 299, 204);
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Redirects to aboutPage.fxml
     * @throws IOException - throws exception
     * TODO: implement this!
     */
    @FXML
    protected void setDisplayToAboutPage() throws IOException {
        Stage stage;
        //Button button = aboutButton;
        stage=(Stage) aboutLink.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        searchPageController controller = loader.getController();
        controller.init(main);
        controller.initApplicationTableView();
    }

    /**
     * Redirects to mainPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    protected void setDisplayToMainPage() throws IOException {
        BorderPane borderPane = main.getBorderPane();

        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Controllers/mainPage.fxml"));
        //System.out.println(loader.getLocation().getPath());
        ScrollPane pane = loader.load();
        Scene scene = borderPane.getScene();
        scene.getStylesheets().add(getClass().getResource("/Controllers/style.css").toExternalForm());

        borderPane.setLeft(pane);
        mainPageController controller = loader.getController();
        System.out.println("CONTROLLER IS " + controller);
        controller.initSlideshow();
        controller.startAnimation();
    }

    /**
     * Redirect to defaultUserMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToDefaultUserMainPage() throws IOException {
        BorderPane borderPane = main.getBorderPane();

        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Controllers/defaultUserMainPage.fxml"));
        //System.out.println(loader.getLocation().getPath());
        ScrollPane pane = loader.load();
        Scene scene = borderPane.getScene();
        scene.getStylesheets().add(getClass().getResource("/Controllers/style.css").toExternalForm());

        borderPane.setLeft(pane);
        defaultUserMainPageController controller = loader.getController();
        System.out.println("CONTROLLER IS " + controller);
        controller.initSlideshow();
        controller.startAnimation();
    }

    /**
     * Redirect to applicantMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToApplicantMainPage() throws IOException {

        BorderPane borderPane = main.getBorderPane();

        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Controllers/applicantMainPage.fxml"));
        //System.out.println(loader.getLocation().getPath());
        ScrollPane pane = loader.load();
        Scene scene = borderPane.getScene();
        scene.getStylesheets().add(getClass().getResource("/Controllers/style.css").toExternalForm());

        borderPane.setLeft(pane);
        applicantMainPageController controller = loader.getController();
        System.out.println("CONTROLLER IS " + controller);
        controller.initSlideshow();
        controller.startAnimation();
    }

    /**
     * Redirects to agentMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToAgentMainPage() throws IOException {
        BorderPane borderPane = main.getBorderPane();

        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Controllers/agentMainPage.fxml"));
        //System.out.println(loader.getLocation().getPath());
        ScrollPane pane = loader.load();
        Scene scene = borderPane.getScene();
        scene.getStylesheets().add(getClass().getResource("/Controllers/style.css").toExternalForm());

        borderPane.setLeft(pane);
        agentMainPageController controller = loader.getController();
        System.out.println("CONTROLLER IS " + controller);
        controller.initSlideshow();
        controller.startAnimation();

    }

    /**
     * Redirects to superAgentMainPage.fxml
     * Used in returnToMainPage()
     * @throws IOException - throws exception
     */
    private void setDisplayToSuperAgentMainPage() throws IOException {
        BorderPane borderPane = main.getBorderPane();

        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Controllers/superAgentInitialPage.fxml"));
        //System.out.println(loader.getLocation().getPath());
        ScrollPane pane = loader.load();
        Scene scene = borderPane.getScene();
        scene.getStylesheets().add(getClass().getResource("/Controllers/style.css").toExternalForm());

        borderPane.setLeft(pane);
        superAgentMainPageController controller = loader.getController();
        System.out.println("CONTROLLER IS " + controller);
    }

    /**
     * Redirects to correct main page depending on authentication
     * Login user if logging in from loginPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    protected void returnToMainPage() throws IOException {
        menuBarSingleton.getInstance().getMenuBarController().setOnSearchPage(false);
        int auth;
        try{
            auth = menuBarSingleton.getInstance().getGlobalData().getUserInformation().getAuthenticationLevel();
        }catch (NullPointerException e){
            auth = -1;
        }
        System.out.println("Going back to Main page");
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
        menuBarSingleton.getInstance().getGlobalData().setUserInformation(new User());
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
