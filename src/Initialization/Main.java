package Initialization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        initRootLayout();
        mainPage();
    }

    public void setDisplayToLogin() throws  Exception{
        try {
            // Load About Page.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("loginPage.fxml"));
            AnchorPane page = loader.load();

            // Set kiosk overview into the center of root layout.
            primaryStage.setTitle("Login Screen");
            primaryStage.getScene().setRoot(page);

            // Give the controller access to the main app.
            ActionController controller = loader.getController();
            controller.setDisplay(this);

//            controller.setListeners();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("mainPage.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Debugger works better when full screen is off
            primaryStage.setFullScreen(false);
            //primaryStage.setFullScreen(true);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainPage(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("mainPage.fxml"));
            AnchorPane mainPageOverview = loader.load();

            rootLayout.setCenter(mainPageOverview);
            ActionController controller = loader.getController();
            controller.setDisplay(this);


        }catch (IOException e){
            e.printStackTrace();
        }

    }



    public void setDisplayToApply() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FormController.fxml"));
        primaryStage.getScene().setRoot(root);

    }
    public void setDisplayToSearch() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SearchController.fxml"));
        primaryStage.getScene().setRoot(root);
    }
    public void setDisplayToMain() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.getScene().setRoot(root);
    }


    public static void main(String[] args){
        try{
            launch(args);
        }catch (Exception e){

        }
    }


}
