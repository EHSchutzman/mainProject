package Controllers;

import Initialization.Main;
import UserAccounts.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ActionController {
    private Main main;

    @FXML
    public Label currentUserLabel;

    public void displayLogin() {
        try {
            main.setDisplayToLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayMainPage() {
        try {
            main.setDisplayToMain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void displaySearchPage() {
        try {
            main.setDisplayToSearch();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    public void displayApplicationPage() {
        try {
            main.setDisplayToApplicantApply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDisplay(Main main) {
        this.main = main;
    }

    @FXML
    public void logoutAction(){
       main.userData.setUserInformation(new User());
      main.setDisplayToMain();
    }

    //@FXML
    //public void displayReviseApplication(){
    //    main.setDisplayToReviewApplicaiton();
    //}

    @FXML
    public void displayReviewApplication(){
        try{
            main.displayWorkflowResultsPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void displayApplicantApplicationPage(){
        try {
            main.setDisplayToApplicantApply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayApplicantFormsPage() {
        try {
            main.displayApplicantFormList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayUserSearchPage() {
        try {
            main.setDisplayToSuperAgentSearchUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayCreateAgentPage() {
        try {
            main.setDisplayToSuperAgentCreateAgent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML public void displayAboutPage() {
        try {
            main.displayAboutPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
