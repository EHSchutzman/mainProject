package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by eschutzman on 4/20/17.
 */
public class csvOptionsController extends UIController{

    @FXML
    private TextField user_specified_value_text;

    private ObservableList<AppRecord> observableList;


    /**
     * Use function upon initialization to pass a list of forms to the CSV controller
     * See SearchResultsPageController.displayCSVOptionsPage() for first use.
     * @param listOfForms
     */
    void passListOfForms(ObservableList<AppRecord> listOfForms){
        this.observableList = listOfForms;
    }

    @FXML
    public void makeCSV() {
        DBManager manager = new DBManager();
        manager.generateCSV(observableList, ",", ".csv");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void makeTab() {
        DBManager manager = new DBManager();
        manager.generateCSV(observableList, "\t", ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function
     */
    @FXML
    public void makeUserSpecified() {
        DBManager manager = new DBManager();
        String separator = user_specified_value_text.getText();
        manager.generateCSV(observableList, separator, ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * So this function displays the confirmation message in a new window
     * TODO Find out why the FXML window size is 299 by 204, it doesn't really make sense.
     * @throws Exception
     */

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
