package sample;

import javafx.beans.property.SimpleStringProperty;

public class Issues {
    private final SimpleStringProperty issueName = new SimpleStringProperty();
    private String createTime;
    private String reporterName;
    private String dueTime;
    private String status;
    private String severity;
    private String reproducible;

    public Issues (SimpleStringProperty issueName, String createTime, String reporterName, String dueTime, String status, String severity, String reproducible) {
        this.issueName = issueName;
        this.createTime = createTime;
        this.reporterName = reporterName;
        this.dueTime = dueTime;
        this.status = status;
        this.severity = severity;
        this.reproducible = reproducible;
    }

    public final SimpleStringProperty issueNameProperty() {
        return issueName;
    }

    public final String getIssueName() {
        return issueName.get();
    }

    public final void setIssueName(String value) {
        issueName.set(value);
    }
}
