package Initialization;
import UserAccounts.*;

import java.sql.ResultSet;

public class Data {
    private User userInformation;
    private int currentApplicationPage = 0;
    private ResultSet rs;

    public Data(User userInformation) {
        this.userInformation = userInformation;
    }

    public Data(){}

    public void setSearchResults(ResultSet rs){
        this.rs = rs;
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
