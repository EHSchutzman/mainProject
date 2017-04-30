package Controllers;

import DBManager.DBManager;
import DatabaseSearch.UpgradeUserRecord;
import UserAccounts.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by jamescorse on 4/28/17.
 */
public class superAgentUpgradeReviewController extends UIController{
    public User user = new User();
    @FXML
    public Label nameLabel, usernameLabel, uIDLabel, emailLabel, phoneNumberLabel, currentAuthLabel, requestedAuthLabel;

    private DBManager dbManager = new DBManager();
    public void setUser(User user) {
        this.user = user;
    }

    // prefill edit user page
    public void setUpgradeReview(User user) {
        nameLabel.setText(user.getFirstName() + " " + user.getLastName());
        usernameLabel.setText(user.getUsername());
        uIDLabel.setText(user.getUid());
        emailLabel.setText(user.getEmail());
        phoneNumberLabel.setText(user.getPhoneNo());
        currentAuthLabel.setText("" + (user.getAuthenticationLevel()));
        requestedAuthLabel.setText("" + (user.getAuthenticationLevel()+1));
    }

    @FXML
    public void approveUpgradeRequest(){
        dbManager.dropUpgradeRequest(user.getUid());
        User updatedUser = dbManager.findUser("user_id='" + user.getUid() +"'");
        System.out.println("this is user" + user.getAuthenticationLevel());
        updatedUser.setAuthenticationLevel(user.getAuthenticationLevel()+1);
        dbManager.updateUser(updatedUser);
        try {
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void denyUpgradeRequest(){
        ObservableList<UpgradeUserRecord> records = dbManager.findUpgradeUsers("user_id='" + user.getUid() + "'");
        UpgradeUserRecord record = records.get(0);
        record.setRequestStatus("Denied");
        dbManager.updateUserUpgrade(record);
        try {
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
