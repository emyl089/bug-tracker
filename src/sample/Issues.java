package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Issues {
    private final StringProperty issueName;
    private final StringProperty createTime;
    private final StringProperty reporterName;
    private final StringProperty dueTime;
    private final StringProperty status;
    private final StringProperty severity;
    private final StringProperty reproducible;

    //Default constructor
    public Issues () {
        this(null,null,null,null,null,null,null);
    }

    //Constructor with data
    public Issues (String issueName, String createTime, String reporterName, String dueTime, String status, String severity, String reproducible) {
        this.issueName = new SimpleStringProperty(issueName);
        this.createTime = new SimpleStringProperty(createTime);
        this.reporterName = new SimpleStringProperty(reporterName);
        this.dueTime = new SimpleStringProperty(dueTime);
        this.status = new SimpleStringProperty(status);
        this.severity = new SimpleStringProperty(severity);
        this.reproducible = new SimpleStringProperty(reproducible);
    }

    public String getIssueName() {
        return issueName.get();
    }
    public void setIssueName(String issueName) { this.issueName.set(issueName); }
    public StringProperty issueNameProperty() { return issueName; }

    public String getCreateTime() {
        return createTime.get();
    }
    public void setCreateTime(String createTime) { this.createTime.set(createTime); }
    public StringProperty createTimeProperty() { return createTime; }

    public String getReporterName() {
        return reporterName.get();
    }
    public void setReporterName(String reporterName) { this.reporterName.set(reporterName); }
    public StringProperty reporterNameProperty() { return reporterName; }

    public String getDueTime() {
        return dueTime.get();
    }
    public void setDueTime(String dueTime) { this.dueTime.set(dueTime); }
    public StringProperty dueTimeProperty() { return dueTime; }

    public String getStatus() {
        return status.get();
    }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }

    public String getSeverity() {
        return severity.get();
    }
    public void setSeverity(String severity) { this.severity.set(severity); }
    public StringProperty severityProperty() { return severity; }

    public String getReproducible() {
        return reproducible.get();
    }
    public void setReproducible(String reproducible) { this.reproducible.set(reproducible); }
    public StringProperty reproducibleProperty() { return reproducible; }
}
