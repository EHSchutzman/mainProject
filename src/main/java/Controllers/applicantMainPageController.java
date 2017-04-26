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

    @FXML
    public void setDisplayToApplicationStatusForApplicant() throws IOException{
        BorderPane borderPane = main.getBorderPane();

        FXMLLoader loader = new FXMLLoader();
        URL iter2URL = getClass().getResource("applicationStatusForApplicant.fxml");
        loader.setLocation(getClass().getResource("applicationStatusForApplicant.fxml"));
        //System.out.println(loader.getLocation().getPath());
        AnchorPane pane = loader.load(iter2URL);
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(main.getMenuBar());
        borderPane.setCenter(pane);
        stage.show();
        Parent root = (Parent) loader.load();
    }

    @FXML
    public void setDisplayToIter2application() throws IOException{
        BorderPane borderPane = main.getBorderPane();

        FXMLLoader loader = new FXMLLoader();
        URL iter2URL = getClass().getResource("iter2application.fxml");
        loader.setLocation(getClass().getResource("iter2application.fxml"));
        //System.out.println(loader.getLocation().getPath());
        ScrollPane scrollPane = loader.load(iter2URL);
        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(main.getMenuBar());
        borderPane.setCenter(scrollPane);
        stage.show();
        Parent root = (Parent) loader.load();
        super.init(super.main);



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
