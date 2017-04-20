package Initialization;

import DatabaseSearch.AppRecord;
import Form.Form;
import DBManager.DBManager;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by DanielKim on 4/16/2017.
 */
public class applicationStatusForApplicantController {
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TableView resultsTable;

    private DBManager db = new DBManager();
    private Form viewForm;
    private Stage primaryStage;

    @FXML
    public void setDisplayToApplicantMainPage() throws IOException {
        Stage stage;
        stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicantMainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logoutAction() throws IOException {
        //TODO: logout user first
        Stage stage;
        stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void displayApplicantResults() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageController"));
        loginPageController lpc = loader.<loginPageController>getController();
        User user = lpc.getUser();

        ObservableList<AppRecord> olAR = FXCollections.observableArrayList();
        System.out.println(user.getAuthenticationLevel());
        olAR = db.findForms(user);
        System.out.println(olAR);
        // Query for batch
        // Display batch in table
        resultsTable.setItems(olAR);

        // This block monitors the user's interaction with the tableview,
        //  determining when they double-click a row
        resultsTable.setRowFactory(tv -> {
            TableRow<AppRecord> row = new TableRow<>();

            // Open application if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AppRecord rowData = row.getItem();

                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");

                    // Get form from DB using selected row's ID
                    try {
                        viewForm = db.findSingleForm(rowData.getFormID(), fieldList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    public void setDisplayToReviseApplication() throws IOException {
        //TODO: logout user first
        Stage stage;
        stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public Form getForm(){
        return viewForm;
    }
}
