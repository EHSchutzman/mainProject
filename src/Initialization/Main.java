package Initialization;

import DatabaseSearch.TTB_database;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    public Initialization.AcitonController displayActionController;
    public Form.FormController displayApply;
    public UserAccounts.AuthenticationController displayLogin;
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("TTB Applicaiton");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        TTB_database.connect();
    }

    public void setDisplayToLogin() throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        primaryStage.show();
    }
    public void setDisplayToApply() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FormController.fxml"));
        primaryStage.show();
    }
    public void setDisplayToSearch() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SearchController.fxml"));
        primaryStage.show();
    }
    public void setDisplayToMain() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.show();
    }


    public static void main(String[] args) {
       // launch(args);
    }
}
