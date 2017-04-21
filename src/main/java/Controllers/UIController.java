package Controllers;

import Initialization.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by DanielKim on 4/13/2017.
 */
public abstract class UIController {
    @FXML
    protected Label currentUserLabel;

    protected Main main = new Main();

    public void back() {}
    /*@FXML
    public void logoutAction() {
        //TODO: Logout user first
        Stage stage;
        stage=(Stage) this.getLogoutButton().getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }*/
    public void returnToMain() {}
    public void clear() {}
    public void submit() {}

    // Init
    @FXML
    public void initializeCurrentUserLabel(Main main) {
        this.main = main;
        if(currentUserLabel != null) {
            if (main.userData == null || main.userData.getUserInformation().getUid() == null) {
                currentUserLabel.setText("Not Logged In");
            } else {
                currentUserLabel.setText(main.userData.getUserInformation().getUsername());
            }
        }
    }
}
