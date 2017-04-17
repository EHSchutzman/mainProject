package Initialization;

import AgentWorkflow.WorkflowController;
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
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
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
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            rootLayout.getStylesheets().add(getClass().getResource("general.css").toExternalForm());

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 1000, 500);
            scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
            primaryStage.setScene(scene);

            // Debugger works better when full screen is off
            primaryStage.setFullScreen(false);
            //primaryStage.setFullScreen(true);

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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());

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
            rootLayout.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("reviseApplication.css").toExternalForm());



            FormController controller = loader.getController();
            controller.ReviewDisplay(this, form);
//            controller.source_combobox.setPromptText("Select Alcohol Source");
//            controller.source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
//            controller.alcohol_type_combobox.setPromptText("Select Type of Alcohol");
//            controller.alcohol_type_combobox.setItems(FXCollections.observableArrayList("Wine", "Malt Beverages", "Distilled Spirits"));
            if (form.getSource().equals("Imported")) {
                controller.source_text.setText("Imported");
                //source_text.setPromptText("Imported");
            } else if (form.getSource().equals("Domestic")) {
                controller.source_text.setText("Domestic");
                //source_text.setPromptText("Domestic");
            }

            // Get Alcohol Type info and set it to display for the Agent
            //alcohol_type_combobox = new ComboBox(FXCollections.observableArrayList("Beer", "Wine", "Distilled Spirit"));
            if (form.getalcohol_type().equals("Malt Beverages")) {
                controller.alcohol_type_text.setText("Malt Beverages");

                //alcohol_content_text.setPromptText("Beer");
            } else if (form.getalcohol_type().equals("Wine")) {
                controller.alcohol_type_text.setText("Wine");
                //alcohol_content_text.setPromptText("Wine");
            } else if (form.getalcohol_type().equals("Distilled Spirit")) {
                controller.alcohol_type_text.setText("Distilled Spirit");

                //alcohol_content_text.setPromptText("Distilled Spirit");
            }

            // Initialize checkboxes
            // Type of Application Check Boxes and their corresponding TextFields
            controller.option_1_checkbox = new CheckBox("Certificate of Label Approval");
            controller.option_2_checkbox = new CheckBox("Certificate of Exemption from Label Approval");
            controller.option_3_checkbox = new CheckBox("Distinctive Liquor Bottle Approval");
            controller.option_4_checkbox = new CheckBox("Resubmission After Rejection");

//            //@TODO: Whatever this shit is supposed to do
//

            controller.rep_id_text.setText(form.getrep_id());
            controller.permit_no_text.setText(form.getpermit_no());
            controller.serial_no_text.setText(form.getserial_no());
            controller.brand_name_text.setText(form.getbrand_name());
            controller.fanciful_name_text.setText(form.getfanciful_name());
            controller.alcohol_content_text.setText(String.valueOf(form.getalcohol_content()));
            controller.formula_text.setText(form.getFormula());
            controller.label_text.setText(form.getlabel_text());
            if(controller.alcohol_type_text.getText().equals("Wine")) {
                controller.vintage_year_text.setText(form.getvintage_year());
                controller.ph_level_text.setText(String.valueOf(form.getpH_level()));
                controller.grape_varietals_text.setText(form.getgrape_varietals());
                controller.wine_appellation_text.setText(form.getwine_appellation());
            } else {
                controller.vintage_year_text.setText(null);
                controller.ph_level_text.setText(null);
                controller.grape_varietals_text.setText(null);
                controller.wine_appellation_text.setText(null);
            }

            //TODO maybe seperate applicant_street_1_text and applicant_street_2_text because it might be too long
        /*applicant_street_1_text.setPromptText(form.getapplicant_street());
        applicant_city_text.setPromptText(form.getapplicant_city());
        applicant_state_text.setPromptText(form.getapplicant_state());
        applicant_zip_text.setPromptText(form.getapplicant_zip());
        applicant_country_text.setPromptText(form.getapplicant_country());*/

            controller.address_text.setText(form.getapplicant_street() + ", " + form.getapplicant_city() + ", " + form.getapplicant_state() + " " + form.getapplicant_zip() + ", " + form.getapplicant_country());

            //mailing_addressText.setPromptText(form.getmailing_address());

            controller.signature_text.setPromptText(form.getSignature());
            controller.phone_no_text.setPromptText(form.getphone_no());
            controller.email_text.setPromptText(form.getEmail());



        } catch (IOException e) {
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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



            FormController controller = loader.getController();
            controller.setDisplay(this);
            controller.source_combobox.setPromptText("Select Alcohol Soruce");
            controller.source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
            controller.alcohol_type_combobox.setPromptText("Select Type of Alcohol");
            controller.alcohol_type_combobox.setItems(FXCollections.observableArrayList("Wine", "Malt Beverages", "Distilled Spirits"));
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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



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
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



            SearchController controller = loader.getController();
            controller.setDisplay(this);


//            for(AppRecord a : list){
//                System.out.println(a.getFormID());
//            }

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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



            // Show the scene containing the root layout.
            Scene scene = new Scene(newWindow, 1500, 1000);

            stage.setScene(scene);
            stage.getScene().getStylesheets().add(getClass().getResource("agentApplicationReview.css").toExternalForm());
            // Debugger works better when full screen is off
            stage.setFullScreen(false);

            stage.getScene().setRoot(newWindow);
            stage.show();

            FormController controller = loader.getController();

            //WorkflowController controller = loader.getController();
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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());


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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());




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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());




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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("iter2application.fxml"));

            AnchorPane page = loader.load();
            primaryStage.setTitle("Applicant Application Page");
            primaryStage.getScene().setRoot(page);

            FormController controller = loader.getController();
            rootLayout.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



            controller.ApplyDisplay(this);
            controller.source_combobox.setPromptText("Select Alcohol Soruce");
            controller.source_combobox.setItems(FXCollections.observableArrayList("Imported", "Domestic"));
            controller.alcohol_type_combobox.setPromptText("Select Type of Alcohol");
            controller.alcohol_type_combobox.setItems(FXCollections.observableArrayList("Wine", "Malt Beverages", "Distilled Spirits"));

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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



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
            primaryStage.getScene().getStylesheets().add(getClass().getResource("general.css").toExternalForm());



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
