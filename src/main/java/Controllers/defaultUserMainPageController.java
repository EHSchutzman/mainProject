package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchResultsPage.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        searchResultsPageController controller = loader.getController();
        controller.init(super.main);
        controller.initApplicationTableView();
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
