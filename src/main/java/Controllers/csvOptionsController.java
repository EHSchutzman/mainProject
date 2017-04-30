package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Status: complete, needs review. TODO: empty todo
 */
public class csvOptionsController extends UIController{

    @FXML
    private TextField userSpecifiedValueText;

    private ObservableList<AppRecord> observableList;
    private DBManager dbManager = new DBManager();

    /**
     * Use function upon initialization to pass a list of forms to the CSV controller
     * See SearchResultsPageController.displayCSVOptions() for first use.
     *
     * @param listOfForms - ObservableList to be downloaded as csv
     */
    void passListOfForms(ObservableList<AppRecord> listOfForms) {
        this.observableList = listOfForms;
    }

    /**
     * Function makes a csv file out of observable list in this controller.
     */
    @FXML
    public void makeCSV() {
        dbManager.generateCSV(observableList, ",", ".csv");
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
        dbManager.generateCSV(observableList, ":", ".txt");

    }
    /**
     * Function makes a tab delimited format text file out of observable list in this controller.
     */
    @FXML
    public void makeTab() {
        dbManager.generateCSV(observableList, "\t", ".txt");
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
        String separator = userSpecifiedValueText.getText();
        dbManager.generateCSV(observableList, separator, ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
