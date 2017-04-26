package Initialization;

import AgentWorkflow.WorkflowController;
import Controllers.ActionController;
import Controllers.mainPageController;
import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import DatabaseSearch.SearchController;
import Form.Form;
import Form.FormController;
import UserAccounts.AuthenticationController;
import UserAccounts.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Status: incomplete.
 * TODO: after controller haul, delete unnecessary methods (specified), clean code, doxygen, warnings, TODOs
 */
public class Main extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public Data userData = new Data(new User());
    private Stage primaryStage;
    private AnchorPane rootLayout;
    public static BorderPane root = new BorderPane();
    public BorderPane getBorderPane(){
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
    }
    public AnchorPane getMenuBar() throws IOException{
        URL menuBarURL = getClass().getResource("/Controllers/menuBar.fxml");
        AnchorPane menuBar = FXMLLoader.load(menuBarURL);
        menuBar.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        return menuBar;
    }
    public void initRootLayout() {
        try {
            AnchorPane menuBar = getMenuBar();
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Controllers/mainPage.fxml"));
            //System.out.println(loader.getLocation().getPath());
            rootLayout = loader.load();
            rootLayout.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            Scene scene = new Scene(root, 1000, 2000);
            root.setTop(menuBar);
            root.setCenter(rootLayout);
            primaryStage.setScene(scene);
            // Debugger works better when full screen is off
            primaryStage.setFullScreen(false);
            primaryStage.show();
            // initialize mainPageController
            mainPageController controller = loader.getController();
            controller.init(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- SHOULDN'T NEED ANYTHING PAST HERE AFTER CONTROLLER HAUL ---------------

    public void setDisplayToDefaultMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("defaultUserMainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getFirstName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToLogin() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("loginPage.fxml"));
            AnchorPane page = loader.load();
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            primaryStage.setTitle("Login Page");
            primaryStage.getScene().setRoot(page);


            AuthenticationController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToReviseForm(Form form, ArrayList<Boolean> booleanArrayList) throws Exception {

        try {

            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("reviseApplication.fxml"));
            AnchorPane newWindow = loader.load();
            stage.setTitle("Form Page");

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1500, 1000);
            stage.setScene(scene);

            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

            FormController controller = loader.getController();
            controller.ReviewDisplay(this, form, booleanArrayList);
            try {
                System.out.println("image is " + form.getlabel_image());
                /**
                 * IF EXPORTING THIS FOR JAR CHANGE
                 */
                String path = (System.getProperty("user.dir") + "/images/" + form.getlabel_image());
                System.out.println(path);
                File file = new File(path);
                String localURL = file.toURI().toURL().toString();
                Image image = new Image(localURL);
                System.out.println("Image loaded");
                controller.label_image.setImage(image);
                System.out.println("displaying image");
            }catch (Exception e){
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToRevisionsMenu() throws Exception {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("revisionsMenu.fxml"));
            AnchorPane newWindow = loader.load();
            //rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            stage.setTitle("Revisions Menu Page");

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1500, 1000);
            stage.setScene(scene);

            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

            FormController controller = loader.getController();
            controller.createRevisionsMenu(this.userData.getForm(), this);
            System.out.println("in main" + this.userData.getForm());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setDisplayToApply() throws Exception {

        try {
            DBManager manager = new DBManager();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("iter2application.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Form Page");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            FormController controller = loader.getController();
            controller.setDisplay(this);
            //controller.source_combobox.getSelectionModel().selectFirst();
            //controller.alcohol_type_combobox.getSelectionModel().selectFirst();
            controller.applicant_name_text.setText(manager.findUsersName(this.userData.getUserInformation().getUid()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToSearch() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("searchPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            SearchController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void displaySearchResultsPage(ObservableList<AppRecord> list) throws Exception{
        try {
            //System.out.println("Hiiiiii");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("searchPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search Results");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            SearchController controller = loader.getController();
            controller.setDisplay2(this, list);

            controller.resultsTable.setItems(list);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Currently attempting to open in a new page
    public void displayWorkflowApplication(Form application) throws Exception{
        try {

            Stage stage = new Stage();
            stage.setTitle("Workflow Results");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("agentApplicationReview.fxml"));
            AnchorPane newWindow = loader.load();
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1500, 1000);

            stage.setScene(scene);
            stage.getScene().getStylesheets().add(getClass().getResource("agentApplicationReview.css").toExternalForm());
            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

            FormController controller = loader.getController();

            controller.setDisplay2(this, application);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void displayCreateUser() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("createUser.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Create User Page");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            AuthenticationController controller = loader.getController();
            controller.setDisplay(this);
            controller.user_type_combobox.setPromptText("Select Type of User");

            controller.user_type_combobox.getItems().addAll(FXCollections.observableArrayList("Default User", "Applicant", "Agent", "Super Agent"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToAgentMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("agentMainPage.fxml"));

            AnchorPane page = loader.load();
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());


            primaryStage.setTitle("Agent Main Page");
            primaryStage.getScene().setRoot(page);


            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getFirstName());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToApplicantMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("applicantMainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Applicant Page");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());




            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getFirstName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setDisplayToMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());




            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getFirstName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDisplayToAgentReview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("agentApplicationReview.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);
            primaryStage.getScene().getStylesheets().add(getClass().getResource("agentApplicationReview.css").toExternalForm());



            FormController controller = loader.getController();
            controller.setDisplay(this);

/*
            controller.applicantNameText.setText(this.userData.getForm().getApplicantName());
            controller.repIDNoText.setText(this.userData.getForm().getRepID());
            controller.brandNameText.setText(this.userData.getForm().getBrandName());
//            controller.permitNoText.setText(this.userData.getForm().getPermitNo());
            controller.tradenameText.setText(this.userData.getForm().getTradename());
            controller.phoneNumberText.setText(this.userData.getForm().getPhoneNumber());
            controller.emailText.setText(this.userData.getForm().getEmail());
//            controller.permitNoText.setText(this.userData.getForm().getPermitNo());
*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        try {

           launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToApplicantApply() throws Exception {

        try {

            Stage stage = new Stage();
            stage.setTitle("Application Form");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("iter2application.fxml"));
            AnchorPane newWindow = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1500, 1000);
            stage.setScene(scene);

            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

            FormController controller = loader.getController();
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            controller.ApplyDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //public void displayWorkflowResultsPage(ObservableList<ObservableList<String>> list) throws Exception{
    public void displayWorkflowResultsPage() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("applicationsForAgent.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Workflow Results");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            WorkflowController controller = loader.getController();
            controller.setDisplay(this);
/*
            ObservableList<AgentRecord> one = FXCollections.observableArrayList();
            one.add(new AgentRecord("Hey", "What's", "Up", "Hello"));
            one.add(new AgentRecord("Saw", "Yo", "Pretty", "Ass"));
            one.add(new AgentRecord("Soon'", "As", "You", "Came"));

            controller.resultsTable.setItems(one);
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayApplicantFormList() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("applicationStatusForApplicant.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Applicant Forms");
            primaryStage.getScene().setRoot(page);
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());



            FormController controller = loader.getController();
            controller.ReviseDisplay(this);
/*
            ObservableList<AgentRecord> one = FXCollections.observableArrayList();
            one.add(new AgentRecord("Hey", "What's", "Up", "Hello"));
            one.add(new AgentRecord("Saw", "Yo", "Pretty", "Ass"));
            one.add(new AgentRecord("Soon'", "As", "You", "Came"));

            controller.resultsTable.setItems(one);
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayAboutPage() throws Exception {
        try {

            Stage stage = new Stage();
            stage.setTitle("About Page");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("aboutPage.fxml"));
            AnchorPane newWindow = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 518, 840);
            stage.setScene(scene);

            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void displayConfirmationMessage() throws Exception {
        try {

            Stage stage = new Stage();
            stage.setTitle("Action confirmation!");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("confirmationMessage.fxml"));
            AnchorPane newWindow = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 299, 204);
            stage.setScene(scene);

            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToSuperAgentMain() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("superAgentInitialPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Super Agent Main");
            primaryStage.getScene().setRoot(page);
            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getFirstName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToSuperAgentSearchUsers() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("superAgentSearchUsers.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search Users");
            primaryStage.getScene().setRoot(page);
            SearchController controller = loader.getController();
            controller.initUserAuthenticationChoiceBox();
            controller.setDisplayUsers(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisplayToSuperAgentCreateAgent() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("superAgentCreateAgent.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Create Agents");
            primaryStage.getScene().setRoot(page);
            AuthenticationController controller = loader.getController();
            controller.setDisplay(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Displays edit user page for super agents after they have double clicked a row
    public void displayEditUser(User user) throws Exception {
        try {
            Stage stage = new Stage();
            stage.setTitle("Edit User");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("superAgentEditUser.fxml"));
            AnchorPane newWindow = loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1000, 500);
            stage.setScene(scene);
            // Debugger works better when full screen is off
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            AuthenticationController controller = loader.getController();
            controller.setDisplay2(this, user);

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void displayCSVOptionsPage() throws Exception {
        try {

            Stage stage = new Stage();
            stage.setTitle("CSV Options");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("csvOptions.fxml"));
            AnchorPane newWindow = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 600, 400);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            stage.setScene(scene);

            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();
            SearchController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayApprovedLabel(Form form) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("inspectApprovedLabel.fxml"));
            AnchorPane newWindow = loader.load();

            Scene scene = new Scene(newWindow, 1500, 1000);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();

            FormController controller = loader.getController();
            controller.setDisplay2(this, form);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayApprovedLabelUser(Form form){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("viewApprovedLabel.fxml"));
            AnchorPane newWindow = loader.load();

            Scene scene = new Scene(newWindow, 1500, 1000);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();

            FormController controller = loader.getController();
            controller.createAgentForm(form);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDisplayToPrintableVersion() {
        try {
            Stage stage = new Stage();
            //stage = (Stage) printable_version.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("testingForm.fxml"));
            //loader.setLocation(getClass().getResource("../../../resources/main/Controllers/testingForm.fxml"));
            //System.out.println(loader.getLocation().toString());
            ScrollPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1000, 700);
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            FormController controller = loader.getController();
            controller.setFormControllerForm(this, this.userData.getForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
