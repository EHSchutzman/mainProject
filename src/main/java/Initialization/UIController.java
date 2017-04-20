package Initialization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by DanielKim on 4/13/2017.
 */
public abstract class UIController {
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
}
