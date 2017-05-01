package DatabaseSearch;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by DanielKim on 4/17/2017.
 */
public class UpgradeUserRecord {

    private final SimpleStringProperty userID = new SimpleStringProperty("");
    private final SimpleStringProperty superAgentID = new SimpleStringProperty("");
    private final SimpleStringProperty requestedAuthentication = new SimpleStringProperty("");
    private final SimpleStringProperty requestStatus = new SimpleStringProperty("");

    public UpgradeUserRecord() {}

    public UpgradeUserRecord(String userID, String superAgentID, String requestedAuthentication, String requestStatus) {
        this.userID.set(userID);
        this.superAgentID.set(superAgentID);
        this.requestedAuthentication.set(requestedAuthentication);
        this.requestStatus.set(requestStatus);
    }

    public String getUserID() {
        return userID.get();
    }

    public SimpleStringProperty userIDProperty() {
        return userID;
    }


    public String getSuperAgentID() {
        return superAgentID.get();
    }

    public SimpleStringProperty superAgentIDProperty() {
        return superAgentID;
    }

    public String getRequestedAuthentication() {
        return requestedAuthentication.get();
    }

    public SimpleStringProperty requestedAuthenticationProperty() {
        return requestedAuthentication;
    }

    public String getRequestStatus() {
        return requestStatus.get();
    }

    public SimpleStringProperty requestStatusProperty() {
        return requestStatus;
    }

    public void setUserID(String userID) {
        this.userID.set(userID);
    }

    public void setSuperAgentID(String superAgentID) {
        this.superAgentID.set(superAgentID);
    }

    public void setRequestedAuthentication(String requestedAuthentication) {
        this.requestedAuthentication.set(requestedAuthentication);
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus.set(requestStatus);
    }
}
