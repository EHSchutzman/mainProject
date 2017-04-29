package Controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Status: mostly done, needs some work.
 * TODO: make sure things work properly, add doxygen
 */
public class applicantMainPageController extends UIController{
    ArrayList<ImageView> imageArrayList = new ArrayList<>();
    int count = 0;

    @FXML
    public HBox imagebox;
    @FXML
    private Button viewFormsButton;
    @FXML
    private Button submissionButton;

    slideshowController slideshow = new slideshowController();

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
    public void initialize(){
        super.init(main);
    }

    /**
     * Redirects to applicationStatusForApplicant.fxml
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToApplicationStatusForApplicant() throws IOException{
        BorderPane pane = main.getBorderPane();
        Stage stage;
        stage=(Stage) viewFormsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicationStatusForApplicant.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = pane.getScene();
        pane.setTop(menuBarSingleton.getInstance().getBar());
        pane.setCenter(loader.load());
        stage.setScene(scene);
        slideshow.stopAnimation();
        stage.show();
        applicationStatusForApplicantController controller = loader.getController();
        controller.init(super.main);
        controller.initApplicationStatusTableView();
    }


    @FXML
    public void setDisplayToIter2application() throws IOException{
        BorderPane borderPane = main.getBorderPane();

        FXMLLoader loader = new FXMLLoader();
        URL iter2URL = getClass().getResource("iter2application.fxml");
        loader.setLocation(iter2URL);
        //System.out.println(loader.getLocation().getPath());

        Stage stage;
        stage=(Stage) submissionButton.getScene().getWindow();

        BorderPane root = super.main.getBorderPane();
        ScrollPane pane = loader.load();

        root.setTop(menuBarSingleton.getInstance().getBar());


        root.setBottom(pane);
        stage.setScene(root.getScene());
        slideshow.stopAnimation();
        stage.show();


        iter2applicationController controller = loader.getController();
        System.out.println(controller);
        controller.init(main);
        controller.initialize();




    }

    /*@FXML
    public void logoutAction() throws IOException{
        //TODO: Logout user first
        Stage stage;
        stage=(Stage) logOutButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }*/

}
