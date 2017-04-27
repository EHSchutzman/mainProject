package Controllers;

import DBManager.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by eschutzman on 4/20/17.
 */
public class csvOptionsController extends UIController{

    @FXML
    private Button close_button, user_specified_button, tab_separated_button, comma_separated_button;

    @FXML
    private TextField user_specified_value_text;


    @FXML
    public void makeCSV() {
        DBManager manager = new DBManager();
        System.out.println("MAIN HAS NOW" + main.userData.getObservableList());

        manager.generateCSV(main.userData.getObservableList(), ",", ".csv");
    }

    @FXML
    public void makeTab() {
        DBManager manager = new DBManager();
        manager.generateCSV(main.userData.getObservableList(), "\t", ".txt");
    }

    @FXML
    public void makeUserSpecified() {
        DBManager manager = new DBManager();
        String separator = user_specified_value_text.getText();
        manager.generateCSV(main.userData.getObservableList(), separator, ".txt");
    }


    @FXML
    public void closeApplication() {

        // Close the window
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();

        // Display confirmation message
        try {
            //displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayConfirmationMessage() throws Exception {
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

}
