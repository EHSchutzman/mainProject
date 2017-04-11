package AgentWorkflow;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Chad on 4/11/2017.
 */
public class AgentRecord {

    private final SimpleStringProperty IDNo = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty date = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");

    public AgentRecord() {
        this("", "", "", "");
    }

    public AgentRecord(String IDNo, String name, String date, String status) {
        setIDNo(IDNo);
        setName(name);
        setDate(date);
        setStatus(status);
    }

    public String getIDNo() {
        return IDNo.get();
    }

    public SimpleStringProperty IDNoProperty() {
        return IDNo;
    }

    public void setIDNo(String formID) {
        this.IDNo.set(formID);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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
