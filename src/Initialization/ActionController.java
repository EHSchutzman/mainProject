package Initialization;

import DatabaseSearch.SearchController;
import Form.FormController;
import UserAccounts.AuthenticationController;
import UserAccounts.User;


public class ActionController {
    private Main main;
    public Data userData = new Data(null);

    //Initialize other controllers
    public SearchController searchController = new SearchController();
    public FormController formController = new FormController();
    public User defaultUser = new User("Default", "Default User", 0);
    protected User loggedInUser = null;


    public void displayLogin() {
        try {
            main.setDisplayToLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayMainPage() {
        try {
            System.out.println("TESTSTST");
            main.setDisplayToMain();
            System.out.println("Tasdasdasd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void searchAction() {
        try {
            main.setDisplayToSearch();
        } catch (Exception e) {
            return;
        }

    }

    public void applyAction() {
        try {
            main.setDisplayToApply();
        } catch (Exception e) {
            return;
        }
    }



    public void setDisplay(Main main) {
        this.main = main;
    }

}
