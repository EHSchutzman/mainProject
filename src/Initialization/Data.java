package Initialization;
import UserAccounts.*;

public class Data {
    private User userInformation;

    public Data(User userInformation) {
        this.userInformation = userInformation;
    }

    public User getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(User userInformation) {
        this.userInformation = userInformation;
    }
}
