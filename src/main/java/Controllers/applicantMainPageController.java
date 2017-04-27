package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Status: mostly done, needs some work.
 * TODO: make sure things work properly, add doxygen
 */
public class applicantMainPageController extends UIController{

    @FXML
    private Button viewFormsButton, submissionButton;

    /**
     * Redirects to applicationStatusForApplicant.fxml
     * @throws IOException - throws exception
     */
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

    /**
     * Redirects to iter2application.fxml
     * TODO: fix the menu bar - throws nullPointer
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToIter2application() throws IOException{
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        BorderPane root = super.main.getBorderPane();
        URL iter2applicationURL = getClass().getResource("iter2application.fxml");
        FXMLLoader loader = new FXMLLoader();
        ScrollPane pane = loader.load(iter2applicationURL);
        root.setTop(menuBarSingleton.getInstance().getBar());
        root.setBottom(pane);


        Scene scene = root.getScene();
        stage.setScene(root.getScene());
        stage.show();


        iter2applicationController controller = loader.getController();
        controller.init(super.main);
        controller.initializeComboBox();
    }
}
