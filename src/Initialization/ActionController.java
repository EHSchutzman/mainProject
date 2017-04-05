package Initialization;

import DatabaseSearch.SearchController;
import Form.FormController;
import UserAccounts.User;


public class ActionController {
    private Main main;
    public Data userData = new Data(null);

    //Initialize other controllers
    public SearchController betterSearchController = new SearchController();
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
            main.setDisplayToApply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDisplay(Main main) {
        this.main = main;
    }

}
