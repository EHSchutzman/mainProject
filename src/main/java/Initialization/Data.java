package Initialization;

import DatabaseSearch.AppRecord;
import DatabaseSearch.UserRecord;
import Form.Form;
import UserAccounts.User;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Data {
    private User userInformation;
    private int currentApplicationPage = 0;
    private ResultSet rs;
    public ArrayList<Form> listOfForms; //unique for each user (agent or applicant)
    public Form form = new Form();
    public ObservableList<AppRecord> observableListApp;
    public ObservableList<UserRecord> observableListUser;



    public Data(User userInformation) {
        this.userInformation = userInformation;
    }

    public Data(){}

    public void setSearchResults(ResultSet rs){
        this.rs = rs;
        System.out.println(this.rs);
        System.out.println(rs);
    }

    public ObservableList<AppRecord> getObservableListApp() {
        return observableListApp;
    }

    public void setObservableListApp(ObservableList<AppRecord> observableList) {
        this.observableListApp = observableList;
    }

    public ObservableList<UserRecord> getObservableListUser() { return observableListUser; }

    public void setObservableListUser(ObservableList<UserRecord> observableList) { this.observableListUser = observableList; }

    public ResultSet getSearchResults(){
        return this.rs;
    }

    public int getCurrentApplicationPage() {
        return currentApplicationPage;
    }

    public void setCurrentApplicationPage(int currentApplicationPage) {
        this.currentApplicationPage = currentApplicationPage;
    }

    public User getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(User userInformation) {
        this.userInformation = userInformation;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ArrayList<Form> getListOfForms() {
        return this.listOfForms;
    }

    public void setListOfForms(ArrayList<Form> listOfForms) {
        this.listOfForms = listOfForms;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
