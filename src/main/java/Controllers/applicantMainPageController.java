package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
 * Created by DanielKim on 4/16/2017.
 */
public class applicantMainPageController extends UIController{

    @FXML
    private Button searchButton;
    @FXML
    private Button viewFormsButton;
    @FXML
    private Button submissionButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Label currentUserLabel;

    @FXML
    public void setDisplayToSearchPage() throws IOException {
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Redirects to applicationStatusForApplicant.fxml
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToApplicationStatusForApplicant() throws IOException{
        BorderPane pane = mainData.getBorderPane();
        Stage stage;
        stage=(Stage) viewFormsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicationStatusForApplicant.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = pane.getScene();
        pane.setTop(mainData.getMenuBar());
        pane.setCenter(loader.load());
        stage.setScene(scene);
        stage.show();
        applicationStatusForApplicantController controller = loader.getController();
        controller.init(super.mainData);
        controller.initApplicationStatusTableView();
    }


    @FXML
    public void setDisplayToIter2application() throws IOException{
        BorderPane borderPane = mainData.getBorderPane();

        FXMLLoader loader = new FXMLLoader();
        URL iter2URL = getClass().getResource("iter2application.fxml");
        //loader.setLocation(getClass().getResource("iter2application.fxml"));
        loader.setLocation(iter2URL);
        //System.out.println(loader.getLocation().getPath());
        ScrollPane scrollPane = loader.load();
        System.out.println("LOADER: " + super.mainData.userData.getUserInformation().getUid());
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(mainData.getMenuBar());
        borderPane.setCenter(scrollPane);
        stage.show();
        //Parent root = (Parent) loader.load();
        System.out.println("UID here her here: " + super.mainData.userData.getUserInformation().getUid());
        iter2applicationController iter2applicationController = loader.getController();
        System.out.println("UID HERE AFTER: " + super.mainData.userData.getUserInformation().getUid());
        System.out.println("CONTROLLER HERE: " + iter2applicationController);
        iter2applicationController.init(super.mainData);
        iter2applicationController.initialize();
    }

    @FXML
    public void logoutAction1() throws IOException{
        //TODO: Logout user first
        Stage stage;
        stage=(Stage) logOutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


}
