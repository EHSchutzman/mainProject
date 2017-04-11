package Initialization;

import DatabaseSearch.AppRecord;
import DatabaseSearch.SearchController;
import DatabaseSearch.TTB_database;
import Form.Form;
import Form.FormController;
import UserAccounts.AuthenticationController;
import UserAccounts.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public Data userData = new Data(new User());
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainPage.fxml"));

            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 2000, 1000);
            primaryStage.setScene(scene);

            // Debugger works better when full screen is off
            primaryStage.setFullScreen(false);
            //primaryStage.setFullScreen(true);

            primaryStage.show();

            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText("Not Logged In");

        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace().toString());
        }
    }

    public void setDisplayToDefaultMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("defaultUserMainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);


            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getRealName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToLogin() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("loginPage.fxml"));
            AnchorPane page = loader.load();

            primaryStage.setTitle("Login Page");
            primaryStage.getScene().setRoot(page);


            AuthenticationController controller = loader.getController();
            controller.setDisplay(this);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDisplayToApply() throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("applicantApplicationPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Form Page");
            primaryStage.getScene().setRoot(page);

            FormController controller = loader.getController();
            controller.setDisplay(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToSearch() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("searchPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search");
            primaryStage.getScene().setRoot(page);

            SearchController controller = loader.getController();
            controller.setDisplay(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void displaySearchResultsPage(ObservableList<AppRecord> list) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("searchResultsPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search Results");
            primaryStage.getScene().setRoot(page);


            SearchController controller = loader.getController();
            controller.setDisplay(this);

            controller.resultsTable.setItems(list);


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void displayCreateUser(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("createUser.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Create User Page");
            primaryStage.getScene().setRoot(page);

            AuthenticationController controller = loader.getController();
            controller.setDisplay(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToAgentMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("agentMainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Agent Main Page");
            primaryStage.getScene().setRoot(page);


            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getRealName());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToApplicantMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("applicantMainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Applicant Page");
            primaryStage.getScene().setRoot(page);


            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getRealName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setDisplayToMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);


            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getRealName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToReviewApplicaiton() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("agentApplicationReview.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);


            FormController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setDisplayToAgentReview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("newAgentApplication.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);

            FormController controller = loader.getController();
            controller.setDisplay(this);


            controller.applicantNameText.setText(this.userData.getUserInformation().getForm().getApplicantName());
            controller.repIDNoText.setText(this.userData.getUserInformation().getForm().getRepID());
            controller.brandNameText.setText(this.userData.getUserInformation().getForm().getBrandName());
//            controller.permitNoText.setText(this.userData.getUserInformation().getForm().getPermitNo());
            controller.tradenameText.setText(this.userData.getUserInformation().getForm().getTradename());
            controller.phoneNumberText.setText(this.userData.getUserInformation().getForm().getPhoneNumber());
            controller.emailText.setText(this.userData.getUserInformation().getForm().getEmail());
//            controller.permitNoText.setText(this.userData.getUserInformation().getForm().getPermitNo());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        try {

           launch(args);
//            Connection c = TTB_database.connect();
        } catch (Exception e) {

        }
    }

    public void setDisplayToApplicantApply() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("applicantApplicationPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Applicant Application Page");
            primaryStage.getScene().setRoot(page);


            FormController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
