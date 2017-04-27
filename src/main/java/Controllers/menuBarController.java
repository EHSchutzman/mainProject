package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import UserAccounts.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ethan on 4/26/2017.
 */
public class menuBarController extends UIController {

    DBManager db = new DBManager();

    //Variables to hold the appropriate FXML buttons and fields
    @FXML
    private Button backButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button aboutButton;
    @FXML
    private TextField searchBar;
    @FXML
    private TableView<AppRecord> resultsTable;

    //Display functions
    @FXML
    private void setDisplayToLoginPage() throws IOException {
        if (main.userData.getUserInformation().getUid() == null) {
            BorderPane root = main.getBorderPane();
            FXMLLoader loader = new FXMLLoader();
            URL loginPageURL = getClass().getResource("loginPage.fxml");
            loader.setLocation(loginPageURL);
            AnchorPane pane = loader.load();
            Stage stage;
            stage=(Stage) searchButton.getScene().getWindow();
            Scene scene = root.getScene();
            stage.setScene(scene);
            root.setTop(main.getMenuBar());
            root.setBottom(pane);
            stage.show();
            loginPageController controller = loader.getController();
            controller.init(main);
        } else {
            main.userData.setUserInformation(new User());
            BorderPane root = main.getBorderPane();
            FXMLLoader loader = new FXMLLoader();
            URL mainPageURL = getClass().getResource("mainPage.fxml");
            loader.setLocation(mainPageURL);
            AnchorPane pane = loader.load();
            Stage stage;
            stage=(Stage) searchButton.getScene().getWindow();
            Scene scene = root.getScene();
            stage.setScene(scene);
            root.setTop(main.getMenuBar());
            root.setBottom(pane);
            stage.show();
            mainPageController controller = loader.getController();
            controller.init(main);
        }
    }

    @FXML
    protected void setDisplayToAboutPage() throws IOException {
        BorderPane root = main.getBorderPane();
        FXMLLoader loader = new FXMLLoader();
        URL aboutPageURL = getClass().getResource("aboutPage.fxml");
        loader.setLocation(aboutPageURL);
        AnchorPane pane = loader.load();
        Stage stage;
        stage=(Stage) aboutButton.getScene().getWindow();
        Scene scene = root.getScene();
        stage.setScene(scene);
        root.setTop(main.getMenuBar());
        root.setBottom(pane);
        stage.show();
        aboutPageController controller = loader.getController();
        controller.init(main);
    }

    @FXML
    protected void setDisplayToSearchFromOffPage() throws IOException{
        BorderPane root = main.getBorderPane();
        FXMLLoader loader = new FXMLLoader();
        URL searchPageURL = getClass().getResource("searchPage.fxml");
        loader.setLocation(searchPageURL);
        ScrollPane pane = loader.load();
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        Scene scene = root.getScene();
        stage.setScene(scene);
        root.setTop(main.getMenuBar());
        root.setBottom(pane);
        stage.show();
        searchPageController controller = loader.getController();
        controller.init(main);
        controller.initApplicationTableView();
        controller.displayData(searchOffPage());
    }

    @FXML
    private ObservableList<AppRecord> searchOffPage() {
        try {
            //Set all variables equal to input data
            String searchBarContent = searchBar.getText();
            String params = " WHERE STATUS = 'Accepted'";
            params += " AND (UPPER(BRAND_NAME) LIKE UPPER('%" + searchBarContent + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + searchBarContent + "%'))";
            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();
            ObservableList<AppRecord> array = db.findLabels(searchParams, params);
            System.out.println(array);
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }

    protected ObservableList<AppRecord> simpleSearch(boolean isMalt, boolean isWine, boolean isSpirit){
        try {
            //Set all variables equal to input data
            if (!searchBar.getText().equals(null)) {
                String searchBarContent = searchBar.getText();
                String params = " WHERE STATUS = 'Accepted'";
                if (isMalt || isSpirit || isWine) {
                    params += " AND (ALCOHOL_TYPE = ";
                    if (isWine) {
                        params += "'Wine'";
                    }
                    if (isSpirit && !isWine) {
                        params += "'Distilled Spirits'";
                    }
                    if (isSpirit && isWine) {
                        params += " OR ALCOHOL_TYPE = 'Distilled Spirits'";
                    }
                    if (isMalt && !(isWine || isSpirit)) {
                        params += "'Malt Beverages'";
                    }
                    if (isMalt && (isWine || isSpirit)) {
                        params += " OR ALCOHOL_TYPE = 'Malt Beverages'";
                    }
                    params += ")";
                }
                params += " AND (UPPER(BRAND_NAME) LIKE UPPER('%" + searchBarContent + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + searchBarContent + "%'))";

                ArrayList<ArrayList<String>> searchParams = new ArrayList<>();
                ObservableList<AppRecord> array = db.findLabels(searchParams, params);
                System.out.println(array);
                return array;
            } else {
                String params = " WHERE STATUS = 'Accepted'";
                if (isMalt || isSpirit || isWine) {
                    params += " AND (ALCOHOL_TYPE = ";
                    if (isWine) {
                        params += "'Wine'";
                    }
                    if (isSpirit && !isWine) {
                        params += "'Distilled Spirits'";
                    }
                    if (isSpirit && isWine) {
                        params += " OR ALCOHOL_TYPE = 'Distilled Spirits'";
                    }
                    if (isMalt && !(isWine || isSpirit)) {
                        params += "'Malt Beverages'";
                    }
                    if (isMalt && (isWine || isSpirit)) {
                        params += " OR ALCOHOL_TYPE = 'Malt Beverages'";
                    }
                    params += ")";
                }
                ArrayList<ArrayList<String>> searchParams = new ArrayList<>();
                ObservableList<AppRecord> array = db.findLabels(searchParams, params);
                System.out.println(array);
                return array;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }

    protected void advSearch(){}
}
