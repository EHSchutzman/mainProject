package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DanielKim on 4/13/2017.
 */
public class Test {
    @FXML
    private Label testLabel;
    @FXML
    private Button testButton;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;

    @FXML
    public void setDisplayToTest2() throws IOException {
        Stage stage;
        //Parent root;
        //get reference to the button's stage
        stage=(Stage) testButton.getScene().getWindow();
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("test2.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test2.fxml"));
        //Test2 test2 = loader.<Test2>getController();
        //test2.setTest2LabelText(textField1.getText() + textField2.getText());
        //create a new scene with root and set the stage
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        Test2 test2 = loader.getController();
        test2.setTest2LabelText(textField1.getText() + textField2.getText());
        stage.show();
    }
}