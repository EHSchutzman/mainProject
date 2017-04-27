package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: complete, needs reimplementation.
 * TODO: fix this page
 */
public class aboutPageController extends UIController {

    @FXML
    AnchorPane anchorPane;

    // TODO: fix this to returnToMainPage
    // For now, just returns to mainPage.fxml onMouseClick
    @FXML
    void timeOutBack() throws IOException{
        Stage stage;
        stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        mainPageController controller = loader.getController();
        controller.init(super.main);
    }
}
