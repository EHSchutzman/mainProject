package DatabaseSearch;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by DanielKim on 4/17/2017.
 */
public class UserRecord {
    private final SimpleStringProperty userID = new SimpleStringProperty("");
    private final SimpleStringProperty username = new SimpleStringProperty("");
    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty middleInitial = new SimpleStringProperty("");
    private final SimpleStringProperty lastName = new SimpleStringProperty("");
    private final SimpleStringProperty email = new SimpleStringProperty("");
    private final SimpleStringProperty phoneNumber = new SimpleStringProperty("");
    private final SimpleStringProperty authentication = new SimpleStringProperty("");

    public UserRecord() {}
    public UserRecord(String userID, String username, String firstName, String middleInitial, String lastName, String email, String phoneNumber, String authentication) {
        this.userID.set(userID);
        this.username.set(username);
        this.firstName.set(firstName);
        this.middleInitial.set(middleInitial);
        this.lastName.set(lastName);
        this.email.set(email);
        this.phoneNumber.set(phoneNumber);
        this.authentication.set(authentication);
    }

    public String getUserID() {
        return userID.get();
    }

    public SimpleStringProperty userIDProperty() {
        return userID;
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial.get();
    }

    public SimpleStringProperty middleInitialProperty() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getAuthentication() {
        return authentication.get();
    }

    public SimpleStringProperty authenticationProperty() {
        return authentication;
    }
}
