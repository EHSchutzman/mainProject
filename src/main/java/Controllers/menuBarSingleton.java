package Controllers;

import Initialization.Data;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

/**
 * Created by eschutzman on 4/27/17.
 */
public class menuBarSingleton {
    //TODO Add different search controllers and give them initializations

    private static menuBarSingleton menuBarObj;

    private Data globalData;
    private AnchorPane menuBar;
    private menuBarController menuBarController;
    private searchPageController searchPageController;
    private superAgentSearchUsersController superAgentSearchUsersController;
    private applicationStatusForApplicantController applicationStatusForApplicantController;
    private applicationsForAgentController applicationsForAgentController;
    private superAgentSearchPendingController superAgentSearchPendingController;
    private ScrollPane superAgentSearchPendingPane;
    private ScrollPane applicationsForAgentsPane;
    private AnchorPane applicationsForApplicantPane;
    private ScrollPane superAgentSearchUsersPane;
    private ScrollPane searchPagePane;

    private menuBarSingleton() {
    }


    public static menuBarSingleton getInstance() {
        if (menuBarObj == null) {
            System.out.println("making new");
            menuBarObj = new menuBarSingleton();
            try {
                menuBarObj.initializeMenuBar();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return menuBarObj;

    }

    private void initializeMenuBar() throws IOException {
        URL menuBarURL = getClass().getResource("/Controllers/menuBar.fxml");
        this.globalData = new Data();

        FXMLLoader menuLoader = new FXMLLoader();
        menuLoader.setLocation(menuBarURL);
        Parent menuRoot = menuLoader.load();

        AnchorPane menuBar = (AnchorPane) menuRoot;
        menuBarController menuBarController = (menuBarController) menuLoader.getController();
        this.menuBarController = menuBarController;
        initializeApplicationsForAgentController();
        initializeApplicationStatusForApplicantsController();
        initializeFormsSearchController();
        initializeSuperAgentSearchUsersController();
        initializeSuperAgentSearchPendingController();

        System.out.println("MENU BAR CONTROLLER " + menuBarController);
        System.out.println("SEARCHPAGE CONTROLLER " + searchPageController);

        this.menuBar = menuBar;

    }
    public void initializeFormsSearchController(){
        URL searchPageURL = getClass().getResource("/Controllers/searchPage.fxml");
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(searchPageURL);
        try {
            Parent searchRoot = searchLoader.load();
            ScrollPane searchPane = (ScrollPane) searchRoot;
            this.searchPagePane = searchPane;

            searchPageController searchPageController = (searchPageController) searchLoader.getController();
            this.searchPageController = searchPageController;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initializeSuperAgentSearchUsersController(){
        URL searchPageURL = getClass().getResource("/Controllers/userSearchPage.fxml");
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(searchPageURL);
        try {
            Parent searchRoot = searchLoader.load();
            ScrollPane searchPane = (ScrollPane) searchRoot;
            this.superAgentSearchUsersPane = searchPane;

            superAgentSearchUsersController superAgentSearchUsersController = (superAgentSearchUsersController) searchLoader.getController();
            this.superAgentSearchUsersController = superAgentSearchUsersController;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void initializeApplicationStatusForApplicantsController(){
        URL searchPageURL = getClass().getResource("/Controllers/applicationStatusForApplicant.fxml");
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(searchPageURL);
        try {
            Parent searchRoot = searchLoader.load();
            AnchorPane searchPane = (AnchorPane) searchRoot;
            this.applicationsForApplicantPane = searchPane;

            applicationStatusForApplicantController applicationStatusForApplicantController = (applicationStatusForApplicantController) searchLoader.getController();
            this.applicationStatusForApplicantController = applicationStatusForApplicantController;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void initializeApplicationsForAgentController(){
        URL searchPageURL = getClass().getResource("/Controllers/applicationsForAgent.fxml");
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(searchPageURL);
        try {
            Parent searchRoot = searchLoader.load();
            ScrollPane searchPane = (ScrollPane) searchRoot;
            this.applicationsForAgentsPane = searchPane;

            applicationsForAgentController applicationsForAgentController = (applicationsForAgentController) searchLoader.getController();
            this.applicationsForAgentController = applicationsForAgentController;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void initializeSuperAgentSearchPendingController(){
        URL searchPageURL = getClass().getResource("/Controllers/superAgentSearchPending.fxml");
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(searchPageURL);
        try {
            Parent searchRoot = searchLoader.load();
            ScrollPane searchPane = (ScrollPane) searchRoot;
            this.superAgentSearchPendingPane = searchPane;

            superAgentSearchPendingController superAgentSearchPendingController = (superAgentSearchPendingController) menuBarSingleton.getInstance().getSuperAgentSearchPendingController();
            this.superAgentSearchPendingController = superAgentSearchPendingController;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public AnchorPane getBar() {
        return this.menuBar;
    }

    public Controllers.applicationsForAgentController getApplicationsForAgentController() {
        if(menuBarObj.applicationsForAgentController == null) {
            try {
                initializeApplicationsForAgentController();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return applicationsForAgentController;
    }

    public ScrollPane getApplicationsForAgentsPane() {
        return applicationsForAgentsPane;
    }

    public menuBarController getMenuBarController(){
        return this.menuBarController;
    }
    public searchPageController getSearchPageController(){
        if(menuBarObj.searchPageController == null) {
            try {
                initializeFormsSearchController();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.searchPageController;
    }

    public Data getGlobalData() {
        if (menuBarObj == null) {
            menuBarSingleton.getInstance();
        } else {


        }
        return this.globalData;
    }

    public ScrollPane getSearchPagePane(){
        if(menuBarObj.applicationsForAgentController == null) {
            try {
                initializeApplicationsForAgentController();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.searchPagePane;
    }

    public Controllers.superAgentSearchUsersController getSuperAgentSearchUsersController() {
        return superAgentSearchUsersController;
    }

    public Controllers.applicationStatusForApplicantController getApplicationStatusForApplicantController() {
        return applicationStatusForApplicantController;
    }

    public AnchorPane getApplicationsForApplicantPane() {
        return applicationsForApplicantPane;
    }

    public ScrollPane getSuperAgentSearchUsersPane() {
        return superAgentSearchUsersPane;
    }

    public Controllers.superAgentSearchPendingController getSuperAgentSearchPendingController() {
        return superAgentSearchPendingController;
    }

    public ScrollPane getSuperAgentSearchPendingPane() {
        return superAgentSearchPendingPane;
    }
}
