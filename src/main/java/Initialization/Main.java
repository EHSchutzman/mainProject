package Initialization;

import AgentWorkflow.WorkflowController;
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
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public Data userData = new Data(new User());
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
//        rootLayout.getStylesheets().add(getClass().getResource("style.css").toString());
        initRootLayout();
    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainPage.fxml"));

            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 1000, 500);
            primaryStage.setScene(scene);

            // Debugger works better when full screen is off
            primaryStage.setFullScreen(false);

            primaryStage.show();

            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText("Not Logged In");

        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace().toString());
        }
    }

    public void setDisplayToDefaultMain() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("defaultUserMainPage.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);


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

            primaryStage.setTitle("Login Page");
            primaryStage.getScene().setRoot(page);


            AuthenticationController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDisplayToReviseForm(Form form) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("reviseApplication.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Form Page");
            primaryStage.getScene().setRoot(page);

            FormController controller = loader.getController();
            controller.ReviewDisplay(this, form);
            controller.source_combobox.setPromptText("Select Alcohol Soruce");
            controller.source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
            controller.alcohol_type_combobox.setPromptText("Select Type of Alcohol");
            controller.alcohol_type_combobox.setItems(FXCollections.observableArrayList("Wine", "Malt Beverage", "Distilled Spirits"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setDisplayToApply() throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("iter2application.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Form Page");
            primaryStage.getScene().setRoot(page);

            FormController controller = loader.getController();
            controller.setDisplay(this);
            controller.source_combobox.setPromptText("Select Alcohol Soruce");
            controller.source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
            controller.alcohol_type_combobox.setPromptText("Select Type of Alcohol");
            controller.alcohol_type_combobox.setItems(FXCollections.observableArrayList("Wine", "Malt Beverage", "Distilled Spirits"));
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
            loader.setLocation(getClass().getResource("searchResultsPage.fxml"));
            AnchorPane page = loader.load();
            primaryStage.setTitle("Search Results");
            primaryStage.getScene().setRoot(page);

            SearchController controller = loader.getController();
            controller.setDisplay(this);

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

            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1500, 1000);
            stage.setScene(scene);

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


            ActionController controller = loader.getController();
            controller.setDisplay(this);
            controller.currentUserLabel.setText(this.userData.getUserInformation().getFirstName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public void setDisplayToReviewApplicaiton() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("applicationsForAgent.fxml"));


            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);


            FormController controller = loader.getController();
            controller.setDisplay(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
    public void setDisplayToAgentReview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("agentApplicationReview.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Main Page");
            primaryStage.getScene().setRoot(page);

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("iter2application.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Applicant Application Page");
            primaryStage.getScene().setRoot(page);

            FormController controller = loader.getController();

            controller.ApplyDisplay(this);
            controller.source_combobox.setPromptText("Select Alcohol Soruce");
            controller.source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
            controller.alcohol_type_combobox.setPromptText("Select Type of Alcohol");
            controller.alcohol_type_combobox.setItems(FXCollections.observableArrayList("Wine", "Malt Beverage", "Distilled Spirits"));

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

}
