package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: empty, needs work.
 * TODO: finish up functionality for this - need to redo superAgent functionality + UI pages
 * TODO: is this what we really want?
 */
public class superAgentMainPageController extends UIController{

    public slideshowController slideshow = new slideshowController();

    @FXML
    public HBox imagebox;


    public void initialize(){
        super.init(main);
    }

    @FXML
    private Button reviewLabelButton, userSearchButton, createAgentButton;

    @FXML
    public void setDisplayToSuperAgentSearchPending() throws IOException {
        BorderPane pane = main.getBorderPane();
        Stage stage;
        stage=(Stage) reviewLabelButton.getScene().getWindow();



        stage.setScene(pane.getScene());
        pane.setLeft(menuBarSingleton.getInstance().getSuperAgentSearchPendingPane());
        superAgentPendingApplicationSearchController controller = menuBarSingleton.getInstance().getSuperAgentPendingApplicationSearchController();
        System.out.println(controller);
        controller.initApplicationTableView();
        controller.simpleSearch();
        menuBarSingleton.getInstance().getMenuBarController().setOnSearchPage(true);
        menuBarSingleton.getInstance().getMenuBarController().setSearchType(1);

    }

    @FXML
    public void setDisplayToSuperAgentSearchUsers() throws IOException {
        Stage stage;
        stage=(Stage) userSearchButton.getScene().getWindow();

        BorderPane pane = main.getBorderPane();
        stage.setScene(pane.getScene());
        pane.setLeft(menuBarSingleton.getInstance().getSuperAgentSearchUsersPane());
        superAgentSearchUsersController controller = menuBarSingleton.getInstance().getSuperAgentSearchUsersController();
        menuBarSingleton.getInstance().getMenuBarController().menuTitle.setText("Search System Users");
        menuBarSingleton.getInstance().getMenuBarController().setOnSearchPage(true);
        menuBarSingleton.getInstance().getMenuBarController().setSearchType(2);
        controller.displayResults();
        slideshow.stopAnimation();
        System.out.println("Controller is" + controller);
        controller.searchUsers();

    }

    // TODO: change to createUser page? is this what we really want?
    @FXML
    public void setDisplayToSuperAgentCreateAgent() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane newWindow = loader.load(getClass().getResource("superAgentCreateAgent.fxml"));
        Scene scene = new Scene(newWindow, 1000, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.getScene().setRoot(newWindow);
        stage.show();
        superAgentCreateAgentController controller = loader.getController();
        slideshow.stopAnimation();

    }

    @FXML
    private void setDisplayToSuperAgentSearchApplications() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane newWindow = loader.load(getClass().getResource("superAgentSearchApplications.fxml"));
        Scene scene = new Scene(newWindow, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.getScene().setRoot(newWindow);
        stage.show();
        superAgentSearchApplicationsController controller = loader.getController();
        controller.initsuperAgentApplicationTableView();
        slideshow.stopAnimation();

    }
    @FXML
    private void viewPendingRoleUpgrades(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane newWindow = loader.load(getClass().getResource("superAgentUpgradeRequests.fxml"));
            Scene scene = new Scene(newWindow, 1000, 850);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
