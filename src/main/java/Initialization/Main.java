package Initialization;

import DatabaseSearch.SearchController;
import Form.Form;
import Form.FormController;
import UserAccounts.AuthenticationController;
import UserAccounts.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLDataException;

public class Main extends Application {

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
            loader.setLocation(getClass().getResource("/FXMLLayouts/mainPage.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Debugger works better when full screen is off
            primaryStage.setFullScreen(false);
            //primaryStage.setFullScreen(true);

            primaryStage.show();

            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText("Not Logged In");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToDefaultMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLLayouts/mainPage.fxml"));

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
            loader.setLocation(getClass().getResource("/FXMLLayouts/loginPage.fxml"));
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
            loader.setLocation(getClass().getResource("/FXMLLayouts/applicationPage0.fxml"));
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
            loader.setLocation(getClass().getResource("/FXMLLayouts/searchPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search");
            primaryStage.getScene().setRoot(page);

            SearchController controller = loader.getController();
            controller.setDisplay(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void displaySearchResultsPage() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLLayouts/searchResultsPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search Results");
            primaryStage.getScene().setRoot(page);

            SearchController controller = loader.getController();
            controller.setDisplay(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void changeApplicantFormView(int i){
        String fxmlFile = "/FXMLLayouts/applicationPage0.fxml";
        try{
            FXMLLoader loader = new FXMLLoader();
            switch (i){
                case 1: fxmlFile = "/FXMLLayouts/applicationPage1.fxml";
                    break;
                case 2: fxmlFile = "/FXMLLayouts/applicationPage2.fxml";
                    break;
                case 3: fxmlFile = "/FXMLLayouts/applicationPage3.fxml";
                    break;
                case 4: fxmlFile = "/FXMLLayouts/applicationPage4.fxml";
                    break;
                case 5: fxmlFile = "/FXMLLayouts/applicationPage5.fxml";
                    break;
                case 6: fxmlFile = "/FXMLLayouts/applicationPage6.fxml";
                    break;
            }
            loader.setLocation(getClass().getResource(fxmlFile));
            AnchorPane pane = loader.load();

            primaryStage.setTitle("Search");
            primaryStage.getScene().setRoot(pane);

            FormController controller = loader.getController();
            controller.setDisplay(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void changeAgentFormView(int i){
        String fxmlFile = "/FXMLLayouts/applicationPage0.fxml";
        try{
            FXMLLoader loader = new FXMLLoader();
            switch (i){
                case 1: fxmlFile = "/FXMLLayouts/agentApplication1.fxml";
                    break;
                case 2: fxmlFile = "/FXMLLayouts/agentApplication2.fxml";
                    break;
                case 3: fxmlFile = "/FXMLLayouts/agentApplication3.fxml";
                    break;
                case 4: fxmlFile = "/FXMLLayouts/agentApplication4.fxml";
                    break;
                case 5: fxmlFile = "/FXMLLayouts/agentApplication5.fxml";
                    break;
            }
            loader.setLocation(getClass().getResource(fxmlFile));
            AnchorPane pane = loader.load();

            primaryStage.setTitle("Search");
            primaryStage.getScene().setRoot(pane);

            FormController controller = loader.getController();
            controller.setDisplay(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void displayCreateUser(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLLayouts/createUser.fxml"));
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
            loader.setLocation(getClass().getResource("/FXMLLayouts/agentMainPage.fxml"));

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

    public void setDisplayToApplicantMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLLayouts/applicantMainPage.fxml"));

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
            loader.setLocation(getClass().getResource("/FXMLLayouts/agentApplication0.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);


            FormController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {

        }
    }


}
