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
    private final SimpleStringProperty originCode = new SimpleStringProperty("");
    private final SimpleStringProperty typeID = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");

    public AppRecord() {
        this("", "", "", "", "", "", "", "", "");
    }

    public AppRecord(String formID, String permitNo, String serialNo, String completedDate, String fancifulName, String brandName, String originCode, String typeID, String status) {
        setFormID(formID);
        setPermitNo(permitNo);
        setSerialNo(serialNo);
        setCompletedDate(completedDate);
        setFancifulName(fancifulName);
        setBrandName(brandName);
        setOriginCode(originCode);
        setTypeID(typeID);
        setStatus(status);
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

    public String getOriginCode() {
        return originCode.get();
    }

    public SimpleStringProperty originCodeProperty() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode.set(originCode);
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
}
