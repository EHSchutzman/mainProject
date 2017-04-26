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

    private DBManager manager = new DBManager();


    /**
     * Use function upon initialization to pass a list of forms to the CSV controller
     * See SearchResultsPageController.displayCSVOptionsPage() for first use.
     * @param listOfForms
     */
    void passListOfForms(ObservableList<AppRecord> listOfForms){
        this.observableList = listOfForms;
    }

    /**
     * Function makes a csv file out of observable list in this controller.
     */
    @FXML
    public void makeCSV() {
        manager.generateCSV(observableList, ",", ".csv");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void makeColon1() {
        manager.generateCSV(observableList, ":", ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void makeColon() {
        ObservableList<AppRecord> observer = observableList;
        String appID;
        String brandName;
        String completeDate;
        String fancifulName;
        String formID;
        String permitNo;
        String serialNo;
        String status;
        String ttbID;
        String typeID;

        for(AppRecord app : observer) {
            appID = app.getApplicantID();
            //ApplicantID
            appID = "\"" + appID + "\"";
            app.setApplicantID(appID);
            //BrandName
            brandName = app.getBrandName();
            brandName = "\"" + brandName + "\"";
            app.setBrandName(brandName);
            //CompletedDate
            completeDate = app.getCompletedDate();
            completeDate = "\"" + completeDate + "\"";
            app.setCompletedDate(completeDate);
            //FancifulName
            fancifulName = app.getFancifulName();
            fancifulName = "\"" + fancifulName + "\"";
            app.setFancifulName(fancifulName);
            //FormID
            formID = app.getFormID();
            formID = "\"" + formID + "\"";
            app.setFormID(formID);
            //PermitNo
            permitNo = app.getPermitNo();
            permitNo = "\"" + permitNo + "\"";
            app.setPermitNo(permitNo);
            //SerialNo
            serialNo = app.getSerialNo();
            serialNo = "\"" + serialNo + "\"";
            app.setSerialNo(serialNo);
            //GetStatus
            status = app.getStatus();
            status = "\"" + status + "\"";
            app.setStatus(status);
            //TtbID
            ttbID = app.getTtbID();
            ttbID = "\"" + ttbID + "\"";
            app.setTtbID(ttbID);
            //TypeID
            typeID = app.getTypeID();
            typeID = "\"" + typeID + "\"";
            app.setTypeID(typeID);

        }
        System.out.println(observableList.get(0).getBrandName());
        manager.generateCSV(observableList, ":", ".txt");

    }
    /**
     * Function makes a tab delimited format text file out of observable list in this controller.
     */
    @FXML
    public void makeTab() {
        manager.generateCSV(observableList, "\t", ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function passes a parameter from the fxml file and sets the delimiter to this character, then exports text file.
     */
    @FXML
    public void makeUserSpecified() {
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
