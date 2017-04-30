package Controllers;

import DBManager.DBManager;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jamescorse on 4/26/17.
 */
public class newUpgradeRequestFormController extends UIController {
    @FXML
    public void initialize(){

        if (menuBarSingleton.getInstance().getGlobalData().getUserInformation().getAuthenticationLevel() == 0) {
            requestedLevel.setItems(FXCollections.observableArrayList("Applicant"));
        }
        if(menuBarSingleton.getInstance().getGlobalData().getUserInformation().getAuthenticationLevel() == 2) {
            requestedLevel.setItems(FXCollections.observableArrayList("Super Agent"));
        }
    }
    @FXML
    public ComboBox requestedLevel;
    @FXML
    public TextField saName;
    @FXML
    public Label error1Label, error2Label, error3Label;

    private DBManager dbManager = new DBManager();
    //public User user = new User();

    public void initializeRequestedLevel() {
        ArrayList<String> list = new ArrayList<>();
        if (super.main.userData.getUserInformation().getAuthenticationLevel() == 0) {
            requestedLevel.setValue("Applicant");
        }
        if(super.main.userData.getUserInformation().getAuthenticationLevel() == 2) {
            requestedLevel.setValue("Super Agent");
        }
        //requestedLevel.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * flags user
     *
     * DB:
     * new db that holds flagged users
     * id number
     * desired position level
     * super agent name
     *
     *
     * flag identifier on regular user db?
     */
    public void flagUser() throws IOException{
        boolean isRequestedLevelEmpty = !(requestedLevel.getValue() == null);
        // check if super agent name is correct
        if(isRequestedLevelEmpty) {
            error1Label.setVisible(false);
                    // adds user to db
                    upgradeRequest();
                    // close window
                    super.closeWindow();
        }
        else {
            error1Label.setVisible(true);
        }
    }

    private void upgradeRequest() {
        int authLevel = 0;
        if (requestedLevel.getValue().equals("Applicant")) {
            authLevel = 1;
        }
        if (requestedLevel.getValue().equals("Super Agent")) {
            authLevel = 3;
        }
        dbManager.persistUpgrade(menuBarSingleton.getInstance().getGlobalData().getUserInformation().getUid(),authLevel, "Pending");
    }
}
