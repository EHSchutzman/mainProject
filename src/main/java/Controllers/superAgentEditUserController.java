package Controllers;

import DBManager.DBManager;
import UserAccounts.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Status:
 * TODO: review this
 */
public class superAgentEditUserController extends UIController{

    @FXML
    private TextField edit_user_id_text;
    @FXML
    private TextField edit_username_text;
    @FXML
    private TextField edit_first_name_text;
    @FXML
    private TextField edit_middle_initial_text;
    @FXML
    private TextField edit_last_name_text;
    @FXML
    private TextField edit_email_text;
    @FXML
    private TextField edit_phone_number_text;
    @FXML
    private ComboBox<String> edit_user_type_combobox;
    @FXML
    private Button edit_user_button;
    @FXML
    private Label edit_errorLabel;

    private DBManager dbManager = new DBManager();

    // prefill edit user page
    public void setEditUser(User user) {
        edit_user_type_combobox.setItems(FXCollections.observableArrayList("0", "1", "2", "3"));
        edit_user_id_text.setText(user.getUid());
        edit_username_text.setText(user.getUsername());
        edit_email_text.setText(user.getEmail());
        edit_phone_number_text.setText(user.getPhoneNo());
        edit_first_name_text.setText(user.getFirstName());
        edit_middle_initial_text.setText(user.getMiddleInitial());
        edit_last_name_text.setText(user.getLastName());
        edit_user_type_combobox.setValue(Integer.toString(user.getAuthenticationLevel()));
    }

    // When super agent presses edit button
    @FXML
    public void editUserAction() {
        User user = dbManager.findUser("user_id='" + edit_user_id_text.getText() + "'");
        User editUser = new User(user.getUid(), edit_username_text.getText(), user.getPassword(),
                edit_first_name_text.getText(), edit_middle_initial_text.getText(), edit_last_name_text.getText(),
                edit_email_text.getText(), edit_phone_number_text.getText(), Integer.parseInt(edit_user_type_combobox.getValue()));
        boolean editSuccess = dbManager.updateUser(editUser);
        System.out.println(editSuccess);
        if (editSuccess) {
            // go to success page
            try {
                Stage stage;
                stage = (Stage) edit_user_button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("editUserConfirmationMessage.fxml"));
                loader.setLocation(getClass().getResource("../../../resources/mainData/Controllers/confirmationMessage.fxml"));
                //System.out.println(loader.getLocation().toString());
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // set error label
            edit_errorLabel.setText("User with the edited username/email already exists");
        }
    }
}
