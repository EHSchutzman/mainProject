package Initialization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DanielKim on 4/13/2017.
 */
public class Test2 {
    @FXML
    private Label test2Label;
    @FXML
    private Button test2Button;

    @FXML
    public void setDisplayToTest() throws IOException {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) test2Button.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("test.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setTest2LabelText(String s) {
        System.out.println("In the fn");
        test2Label.setText(s);
    }
}