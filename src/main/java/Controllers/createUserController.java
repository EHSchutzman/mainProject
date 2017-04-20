package Controllers;

import UserAccounts.Authentication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DanielKim on 4/14/2017.
 */
public class createUserController extends UIController{
    @FXML
    private TextField login_name_text;
    @FXML
    private TextField password_text;
    @FXML
    private TextField first_name_text;
    @FXML
    private TextField middle_initial_text;
    @FXML
    private TextField last_name_text;
    @FXML
    private TextField email_text;
    @FXML
    private TextField phone_number_text;
    ObservableList<String> user_types = FXCollections.observableArrayList("User","Agent","Applicant","SuperAgent");
    @FXML
    private ComboBox<String> user_type_combobox;
    @FXML
    public Label errorLabel;
    @FXML
    private Button returnToMain;

    private Authentication isAuthentic = new Authentication();

    public void initializeComboBox() {
        user_type_combobox.getItems().addAll(user_types);
    }

    @FXML
    public void setDisplayToMainPage() throws IOException {
        Stage stage;
        stage=(Stage) returnToMain.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createUser() throws IOException{
        String emailText = email_text.getText();
        String loginNameText = login_name_text.getText();
        String passwordText = password_text.getText();
        String phoneNum = phone_number_text.getText();
        String firstName = first_name_text.getText();
        String middleIn = middle_initial_text.getText();
        String lastname = last_name_text.getText();
        String combo = user_type_combobox.getValue();
        System.out.println(combo);

        int authLvl;
        if (combo.equals("User")) {
            authLvl = 0;
        } else if (combo.equals("Applicant")) {
            authLvl = 1;
        } else if (combo.equals("Agent")) {
            authLvl = 2;
        } else {
            authLvl = 3;
        }

        isAuthentic.createUser(firstName, middleIn, lastname, loginNameText, passwordText, emailText, phoneNum, authLvl);
        try {
            setDisplayToMainPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
