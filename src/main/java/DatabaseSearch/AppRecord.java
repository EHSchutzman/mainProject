package DatabaseSearch;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Chad on 4/6/2017.
 */
public class AppRecord {

    private final SimpleStringProperty formID = new SimpleStringProperty("");
    private final SimpleStringProperty permitNo = new SimpleStringProperty("");
    private final SimpleStringProperty serialNo = new SimpleStringProperty("");
    private final SimpleStringProperty completedDate = new SimpleStringProperty("");
    private final SimpleStringProperty fancifulName = new SimpleStringProperty("");
    private final SimpleStringProperty brandName = new SimpleStringProperty("");
    private final SimpleStringProperty country = new SimpleStringProperty("");
    private final SimpleStringProperty state = new SimpleStringProperty("");
    private final SimpleStringProperty applicantName = new SimpleStringProperty("");
    private final SimpleStringProperty assignedAgent = new SimpleStringProperty("");
    //private final SimpleStringProperty originCode = new SimpleStringProperty("");
    private final SimpleStringProperty typeID = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final SimpleStringProperty applicantID = new SimpleStringProperty("");
    private final SimpleStringProperty ttbID = new SimpleStringProperty("");

    public AppRecord() {
        this("", "", "", "", "", "", "", "", "");
    }

    public AppRecord(String formID, String permitNo, String serialNo, String completedDate, String fancifulName, String brandName, String country, String state, String typeID) {
        setFormID(formID);
        setPermitNo(permitNo);
        setSerialNo(serialNo);
        setCompletedDate(completedDate);
        setFancifulName(fancifulName);
        setBrandName(brandName);
        setCountry(country);
        setState(state);
        setTypeID(typeID);
    }

    public AppRecord(String formID, String permitNo, String serialNo, String applicantName, String assignedAgent, String fancifulName, String brandName, String typeID) {
        setFormID(formID);
        setPermitNo(permitNo);
        setSerialNo(serialNo);
        setApplicantName(applicantName);
        setAssignedAgent(assignedAgent);
        setFancifulName(fancifulName);
        setBrandName(brandName);
        setTypeID(typeID);
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

    public String getFormID() {
        return formID.get();
    }

    public SimpleStringProperty formIDProperty() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID.set(formID);
    }

    public String getPermitNo() {
        return permitNo.get();
    }

    public SimpleStringProperty permitNoProperty() {
        return permitNo;
    }

    public void setPermitNo(String permitNo) {
        this.permitNo.set(permitNo);
    }

    public String getSerialNo() {
        return serialNo.get();
    }

    public SimpleStringProperty serialNoProperty() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo.set(serialNo);
    }

    public String getCompletedDate() {
        return completedDate.get();
    }

    public SimpleStringProperty completedDateProperty() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate.set(completedDate);
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

    public void setCountry(String country) { this.country.set(country); }

    public String getCountry() { return country.get(); }

    public void setState(String state) { this.state.set(state); }

    public String getState() { return state.get(); }

    public void setApplicantName(String applicantName) { this.applicantName.set(applicantName); }

    public String getApplicantName() { return this.applicantName.get(); }

    public void setAssignedAgent(String assignedAgent) { this.assignedAgent.set(assignedAgent); }

    public String getAssignedAgent() { return this.assignedAgent.get(); }

//    public String getOriginCode() {
//        return originCode.get();
//    }
//
//    public SimpleStringProperty originCodeProperty() {
//        return originCode;
//    }
//
//    public void setOriginCode(String originCode) {
//        this.originCode.set(originCode);
//    }

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

    public String getApplicantID() {
        return applicantID.get();
    }

    public SimpleStringProperty applicantIDProperty() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID.set(applicantID);
    }
}
