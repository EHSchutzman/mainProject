package Initialization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Chad on 4/25/2017.
 */
public class MainTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("/Controllers/mainPage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/agentMainPage.fxml"));
        primaryStage.setTitle("Under Construction...");
        Scene scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add(getClass().getResource("/Controllers/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
