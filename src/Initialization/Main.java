package Initialization;

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
    private Pane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
    }

    public void setDisplayToLogin() throws  Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("loginPage.fxml"));
            Pane page = loader.load();
            primaryStage.setTitle("login");
            primaryStage.getScene().setRoot(page);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainPage.fxml"));
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
            System.out.println("asdasdsad");
        }
    }

    public void mainPage(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("mainPage.fxml"));
            AnchorPane mainPageOverview = loader.load();

//            rootLayout.setCenter(mainPageOverview);
            ActionController controller = loader.getController();
            controller.setDisplay(this);


        }catch (IOException e){
            e.printStackTrace();
        }

    }



    public void setDisplayToApply() throws Exception{

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FormController.fxml"));
            AnchorPane page = loader.load();

            // Replace KioskOverview with userUI3.
            primaryStage.setTitle("login");
            primaryStage.getScene().setRoot(page);
/*
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();
*/

            // Give controller access to Main App.
//            SearchController controller = loader.getController();
//            controller.setKioskApp( this);
//            controller.setFaulknerHospitalMap(this.faulknerHospitalMap);
////            controller.setBuilding(this.hospitalBuilding);
//            controller.displayResult(searchText);
            Parent root = FXMLLoader.load(getClass().getResource("FormController.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {

        }

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
