package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

/**
 * Created by eschutzman on 4/27/17.
 */
public class menuBarSingleton {

    private static menuBarSingleton menuBarObj;

    private AnchorPane menuBar;
    private menuBarSingleton(){}


    public static menuBarSingleton getInstance(){
        if(menuBarObj == null){
            System.out.println("making new");
            menuBarObj = new menuBarSingleton();
            try {
                menuBarObj.initializeMenuBar();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return menuBarObj;

    }

    private void initializeMenuBar() throws IOException{
        URL menuBarURL = getClass().getResource("/Controllers/menuBar.fxml");
        URL searchPageURL = getClass().getResource("/Controllers/searchPage.fxml");


        FXMLLoader menuLoader = new FXMLLoader();
        menuLoader.setLocation(menuBarURL);
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(searchPageURL);

        Parent menuRoot = menuLoader.load();
        Parent searchRoot = searchLoader.load();
        AnchorPane menuBar = (AnchorPane) menuRoot;
        menuBarController menuBarController = (menuBarController) menuLoader.getController();
        searchPageController searchPageController = (searchPageController) searchLoader.getController();


        System.out.println("MENU BAR CONTROLLER " + menuBarController);
        System.out.println("SEARCHPAGE CONTROLLER " + searchPageController);
        menuBarController.setSearchPageController(searchPageController);
        this.menuBar = menuBar;

    }
    public AnchorPane getBar(){
        return this.menuBar;
    }

}
