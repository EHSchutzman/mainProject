package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: mostly done, needs some work.
 * TODO: make sure things work properly (logging info)
 */
public class applicantMainPageController extends UIController{

    @FXML
    private Button viewFormsButton, submissionButton;

    @FXML
    public void setDisplayToApplicationStatusForApplicant() throws IOException{
        Stage stage;
        stage=(Stage) viewFormsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicationStatusForApplicant.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        applicationStatusForApplicantController controller = loader.getController();
        controller.init(super.main);
        controller.initApplicationStatusTableView();
    }

    @FXML
    public void setDisplayToIter2application() throws IOException{
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("iter2application.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        iter2applicationController controller = loader.getController();
        controller.init(super.main);
        controller.initializeComboBox();
    }
}
