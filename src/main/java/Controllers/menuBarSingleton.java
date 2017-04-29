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

    private static menuBarSingleton menuBarObj;

    private Data globalData;
    private AnchorPane menuBar;
    private menuBarController menuBarController;
    private searchPageController searchPageController;
    private ScrollPane searchPane;

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



        System.out.println("MENU BAR CONTROLLER " + menuBarController);
        System.out.println("SEARCHPAGE CONTROLLER " + searchPageController);
        menuBarController.setSearchPageController(searchPageController);

        this.menuBar = menuBar;

    }
    public void initializeSearchController(){
        URL searchPageURL = getClass().getResource("/Controllers/searchPage.fxml");
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(searchPageURL);
        try {
            Parent searchRoot = searchLoader.load();
            ScrollPane searchPane = (ScrollPane) searchRoot;
            this.searchPane = searchPane;

            searchPageController searchPageController = (searchPageController) searchLoader.getController();
            this.searchPageController = searchPageController;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AnchorPane getBar() {
        return this.menuBar;
    }

    public menuBarController getMenuBarController(){
        return this.menuBarController;
    }
    public searchPageController getSearchPageController(){
        if(menuBarObj.searchPageController == null) {
            try {
                initializeSearchController();
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

    public ScrollPane getSearchPane(){
        return this.searchPane;
    }

}
