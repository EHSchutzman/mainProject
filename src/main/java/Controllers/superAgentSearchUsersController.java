package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import DatabaseSearch.UserRecord;
import UserAccounts.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status:
 * TODO: review this
 */
public class superAgentSearchUsersController extends UIController {

    @FXML
    public CheckBox usernameCheckbox;
    @FXML
    public CheckBox emailCheckbox;
    @FXML
    public CheckBox firstNameCheckbox;
    @FXML
    public CheckBox lastNameCheckbox;
    @FXML
    public CheckBox agentCheckbox;
    @FXML
    public CheckBox superAgentCheckbox;
    @FXML
    public CheckBox applicantCheckbox;
    @FXML
    public TableColumn UserIDCol;
    @FXML
    public TableColumn UsernameCol;
    @FXML
    public TableColumn firstNameCol;
    @FXML
    public TableColumn lastNameCol;
    @FXML
    public TableColumn middleInitialCol;
    @FXML
    public TableColumn emailCol;
    @FXML
    public TableColumn phoneNumberCol;
    @FXML
    public TableColumn authenticationCol;
    @FXML
    public TableView<UserRecord> resultsTableUsers;
    public Button comma_separated_button;
    public Button tab_separated_button;
    public Button user_specified_button;
    public TextField user_specified_value_text;
    @FXML
    private Button refreshButton;

    private DBManager dbManager = new DBManager();
    private menuBarController mbc = menuBarSingleton.getInstance().getMenuBarController();

/*
    public void initUserAuthenticationChoiceBox() {
        authentication_filter.getItems().addAll("0", "1", "2", "3");
        authentication_filter.setValue("0");
        authentication_filter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchUsers();
            }
        });
    }
*/


    @FXML
    public void searchUsers() {
        ObservableList<UserRecord> ol = mbc.searchUsers(usernameCheckbox.isSelected(), emailCheckbox.isSelected(), firstNameCheckbox.isSelected(), lastNameCheckbox.isSelected(), agentCheckbox.isSelected(), superAgentCheckbox.isSelected(), applicantCheckbox.isSelected());
        displayData(ol);
    }

    @FXML
    public void displayResults() {
        resultsTableUsers.setRowFactory(tv -> {
            TableRow<UserRecord> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((event.getClickCount() == 2) && (!row.isEmpty())) {
                    UserRecord record = row.getItem();
                    // Get user from dbManager using selected row
                    try {
                        String options = "user_id='" + record.getUserID() + "'";
                        User user = dbManager.findUser(options);
                        resultsTableUsers.refresh();
                        displayEditUser(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    //Populates the results table with data from the database
    protected void displayData(ObservableList<UserRecord> list) {
        try {
            resultsTableUsers.setItems(list);
            resultsTableUsers.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Displays edit user page for super agents after they have double clicked a row
    public void displayEditUser(User user) throws Exception {
        try {
            Stage stage = new Stage();
            stage.setTitle("Edit User");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("superAgentEditUser.fxml"));
            AnchorPane newWindow = loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1000, 500);
            stage.setScene(scene);
            // Debugger works better when full screen is off
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            superAgentEditUserController controller = loader.getController();
            controller.init(super.main);
            controller.setEditUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refreshView(){
        System.out.println("super agent refresh");
        resultsTableUsers.refresh();
    }

}