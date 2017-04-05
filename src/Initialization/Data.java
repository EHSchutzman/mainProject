package Initialization;
import UserAccounts.*;

public class Data {
    private User userInformation;
    private int currentApplicationPage = 0;

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
