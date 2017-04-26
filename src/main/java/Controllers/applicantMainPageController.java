package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    public void setDisplayToApplicationStatusForApplicant() throws IOException{
        Stage stage;
        stage=(Stage) viewFormsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicationStatusForApplicant.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setDisplayToIter2application() throws IOException{
        BorderPane borderPane = main.getBorderPane();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("iter2application.fxml"));
        //System.out.println(loader.getLocation().getPath());
        AnchorPane anchorPane = loader.load();
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(main.getMenuBar());
        borderPane.setBottom(anchorPane);
        stage.show();
    }

    @FXML
    public void logoutAction() throws IOException{
        //TODO: Logout user first
        Stage stage;
        stage=(Stage) logOutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


}
