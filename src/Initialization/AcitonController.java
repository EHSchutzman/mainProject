package Initialization;

import DatabaseSearch.*;
import Form.*;
import UserAccounts.*;
import javafx.application.Application;
import javafx.stage.Stage;
import DatabaseSearch.TTB_database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Controller;

import java.sql.Connection;


public class AcitonController{



    //Initialize other controllers
    SearchController searchController = new SearchController();
    AuthenticationController authenticationController = new AuthenticationController();
    FormController formController = new FormController();
    User defaultUser = new User("Default", "Default User", 0);
    User loggedInUser = null;




    public void displayLogin(){
        Main main = new Main();
        try{

            main.setDisplayToLogin();
        } catch (Exception e){
            return;
        }
        try {
            main.setDisplayToMain();
        } catch (Exception e){
            return;
        }

    }

    public void searchAction(){

    }
    public void applyAction(){

    }

}
