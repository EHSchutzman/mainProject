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
     * @param listOfForms - ObservableList to be downloaded as csv
     */
    void passListOfForms(ObservableList<AppRecord> listOfForms){
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
