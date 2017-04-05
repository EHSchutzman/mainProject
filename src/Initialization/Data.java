package Initialization;
import Form.Form;
import UserAccounts.*;

import java.util.ArrayList;

public class Data {
    private User userInformation;
    private int currentApplicationPage = 0;
    public ArrayList<Form> listOfForms; //unique for each user (agent or applicant)
    public Form tempForm = new Form();

    public Data(User userInformation) {
        this.userInformation = userInformation;
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
