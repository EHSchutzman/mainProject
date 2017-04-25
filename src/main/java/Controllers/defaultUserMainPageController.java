package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

/**
 * Status: incomplete.
 * TODO: many things
 */
public class defaultUserMainPageController extends UIController{
    @FXML
    private Button searchButton;
    @FXML
    private Hyperlink aboutLink;

    @FXML
    public void setDisplayToSearchPage() throws IOException{
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        BorderPane root = super.main.getBorderPane();

        URL searchPageURL = getClass().getResource("searchPage.fxml");
        FXMLLoader loader = new FXMLLoader();
        ScrollPane pane = loader.load(searchPageURL);
        root.setTop(main.getMenuBar());
        root.setBottom(pane);

        Scene scene = root.getScene();
        stage.setScene(root.getScene());
        stage.show();
        searchPageController controller = loader.getController();
        controller.init(super.main);

    }

    @FXML
    public void setDisplayToAboutPage() throws IOException{
        Stage stage;
        stage=(Stage) aboutLink.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
