package Initialization;
import Form.Form;
import UserAccounts.*;

import java.sql.ResultSet;

import java.util.ArrayList;

public class Data {
    private User userInformation;
    private int currentApplicationPage = 0;
    private ResultSet rs;
    public ArrayList<Form> listOfForms; //unique for each user (agent or applicant)
    public Form tempForm = new Form();

    public Data(User userInformation) {
        this.userInformation = userInformation;
    }

    public Data(){}

    public void setSearchResults(ResultSet rs){
        this.rs = rs;
        System.out.println(this.rs);
        System.out.println(rs);
    }

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
}
