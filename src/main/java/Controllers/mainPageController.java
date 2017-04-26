package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: complete.
 */
public class mainPageController extends UIController {

    @FXML
    private Button loginButton;

    @FXML
    public void initialize(){
        super.init(main);
    }

    /**
     * Redirects to loginPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToLoginPage() throws IOException{
        String loginStatus = loginButton.getText();
        if(loginStatus.equals("LOGIN")) {
            Stage stage;
            stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
            System.out.println(loader.getLocation().getPath());
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
            loginPageController controller = loader.getController();
            controller.init(super.main);
            controller.setActionOnEnter();
            loginButton.setText("LOGOUT");
        } else {

        }
    }

}
