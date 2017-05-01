package DatabaseSearch;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by DanielKim on 4/26/2017.
 */
public class SuperAgentAppRecord {
    private final SimpleStringProperty fancifulName = new SimpleStringProperty("");
    private final SimpleStringProperty brandName = new SimpleStringProperty("");
    private final SimpleStringProperty typeID = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final SimpleStringProperty applicantName = new SimpleStringProperty("");
    private final SimpleStringProperty agentName = new SimpleStringProperty("");
    private final SimpleStringProperty ttbID = new SimpleStringProperty("");

    public SuperAgentAppRecord() {
        this("", "", "", "", "", "", "");
    }

    public SuperAgentAppRecord(String ttbID, String agentName, String applicantName, String brandName, String fancifulName, String typeID, String status) {
        setFancifulName(fancifulName);
        setBrandName(brandName);
        setTypeID(typeID);
        setStatus(status);
        setApplicantName(applicantName);
        setAgentName(agentName);
        setTtbID(ttbID);
    }

    public void print() {
        System.out.println(ttbID);
        System.out.println(agentName);
        System.out.println(applicantName);
        System.out.println(brandName);
        System.out.println(fancifulName);
        System.out.println(status);
        System.out.println(typeID);
    }

    public String getFancifulName() {
        return fancifulName.get();
    }

    public SimpleStringProperty fancifulNameProperty() {
        return fancifulName;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName.set(fancifulName);
    }

    public String getBrandName() {
        return brandName.get();
    }

    public SimpleStringProperty brandNameProperty() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName.set(brandName);
    }

    public String getTypeID() {
        return typeID.get();
    }

    public SimpleStringProperty typeIDProperty() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID.set(typeID);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getApplicantName() {
        return applicantName.get();
    }

    public SimpleStringProperty applicantNameProperty() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName.set(applicantName);
    }

    public String getAgentName() {
        return agentName.get();
    }

    public SimpleStringProperty agentNameProperty() {
        return agentName;
    }

    public void setAgentName(String agentID) {
        this.agentName.set(agentID);
    }

    public String getTtbID() {
        return ttbID.get();
    }

    public SimpleStringProperty ttbIDProperty() {
        return ttbID;
    }

    public void setTtbID(String ttbID) {
        this.ttbID.set(ttbID);
    }

}
