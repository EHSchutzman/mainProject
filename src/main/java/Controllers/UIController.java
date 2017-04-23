package Controllers;

import Initialization.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DanielKim on 4/13/2017.
 */
public abstract class UIController {
    @FXML
    protected Label currentUserLabel;
    @FXML
    protected Button returnToMainButton, closeButton;

    protected Main main = new Main();

    /**
     * Redirects to mainPage.fxml
     * @throws IOException
     */
    @FXML
    public void setDisplayToMainPage() throws IOException {
        Stage stage;
        stage=(Stage) returnToMainButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        mainPageController controller = loader.getController();
        controller.init(main);
    }

    /**
     * Closes current window
     * @throws IOException
     */
    @FXML
    public void closeWindow() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

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
    public void submit() {}

    // Init
    @FXML
    public void init(Main main) {
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
