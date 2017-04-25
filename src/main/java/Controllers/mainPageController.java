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
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Status: needs review.
 * TODO: do all TODOs
 */
public class mainPageController extends UIController {
    public ArrayList<ImageView> imageArrayList = new ArrayList<>();
    public int count = 0;

    @FXML
    private HBox imagebox;
    @FXML
    private Button loginButton, searchButton;
    @FXML
    private Hyperlink aboutLink;

    /**
     * Redirects to loginPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToLoginPage() throws IOException{
        Stage stage;
        stage=(Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        loginPageController controller = loader.getController();
        controller.init(super.main);
    }

    /**
     * Redirects to searchResultsPage.fxml TODO: make sure this is correct
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToSearchPage() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchResultsPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        searchResultsPageController controller = loader.getController();
        controller.init(super.main);
        controller.initApplicationTableView();
    }

    /**
     * Redirects to aboutPage.fxml
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToAboutPage() throws IOException{
        Stage stage;
        Parent root;
        stage=(Stage) aboutLink.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("aboutPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * adds images to an array of ImageViews and put them in a HBox(horizontal box)
     * also gives the click on image functionality TODO make it display label after image click
     */
    public void initSlideshow () {
        // load the images
        imageArrayList.add(new ImageView("file:images/BIG BLONDE.jpg"));
        System.out.println(imageArrayList.get(0).getImage().isError());
        imageArrayList.get(0).setFitHeight(300);
        imageArrayList.get(0).setFitWidth(300);
        imageArrayList.add(new ImageView("file:images/AZOI.jpg"));
        System.out.println(imageArrayList.get(1).getImage().isError());
        imageArrayList.get(1).setFitHeight(300);
        imageArrayList.get(1).setFitWidth(300);
        imageArrayList.add(new ImageView("file:images/BIRRA DENICOLA.jpg"));
        System.out.println(imageArrayList.get(2).getImage().isError());
        imageArrayList.get(2).setFitHeight(300);
        imageArrayList.get(2).setFitWidth(300);
        imageArrayList.add(new ImageView("file:images/GIGANTIS.jpg"));
        System.out.println(imageArrayList.get(3).getImage().isError());
        imageArrayList.get(3).setFitHeight(300);
        imageArrayList.get(3).setFitWidth(300);
        imageArrayList.add(new ImageView("file:images/BOCADO.jpg"));
        System.out.println(imageArrayList.get(4).getImage().isError());
        imageArrayList.get(4).setFitHeight(300);
        imageArrayList.get(4).setFitWidth(300);

        imagebox.getChildren().addAll(imageArrayList);

        imagebox.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && imageArrayList.get(count).getImage().impl_getUrl().equals("file:src/image/bacardi.jpg")) {
                final Stage dialog = new Stage();
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });
    }

    /**
     * actually animates the slideshow
     */
    public void startAnimation() {
        //error occurred on (ActionEvent t) line
        //slide action
        EventHandler<ActionEvent> slideAction = (ActionEvent t) -> {
            count++;
            System.out.println("we slidin");
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1.5), imagebox);
            trans.setByX(-300);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
        //eventHandler
        EventHandler<ActionEvent> resetAction = (ActionEvent t) -> {
            count = 0;
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1), imagebox);
            trans.setByX((imageArrayList.size() - 1) * 300);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };

        List<KeyFrame> keyFrames = new ArrayList<>();
        for (int i = 1; i <= imageArrayList.size(); i++) {
            if (i == imageArrayList.size()) {
                keyFrames.add(new KeyFrame(Duration.seconds(i * 3), resetAction));
            } else {
                keyFrames.add(new KeyFrame(Duration.seconds(i * 3), slideAction));
            }
        }

        //add timeLine
        Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[imageArrayList.size()]));

        anim.setCycleCount(Timeline.INDEFINITE);
        anim.playFromStart();
    }
}
