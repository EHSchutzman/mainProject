package Initialization;

import DatabaseSearch.SearchController;
import Form.Form;
import Form.FormController;
import UserAccounts.AuthenticationController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Main extends Application {

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


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLLayouts/mainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);

            ActionController controller = loader.getController();
            controller.setDisplay(this);

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
            Pane page = loader.load();
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
            Pane page = loader.load();
            primaryStage.setTitle("Search");
            primaryStage.getScene().setRoot(page);

            SearchController controller = loader.getController();
//            controller.setDisplay(this);
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
