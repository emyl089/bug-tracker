package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BugTrackerController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button newEntryAddButton;
    @FXML
    private ScrollPane dashboardScrollPane;
    @FXML
    private ScrollPane issuesScrollPane;
    @FXML
    private AnchorPane newEntryAnchorPane;
    @FXML
    private AnchorPane adminAnchorPane;
    @FXML
    private ImageView applicationImageView;
    @FXML
    private ImageView closeImageView;
    @FXML
    private ImageView issuesRefreshImageView;
    @FXML
    private ImageView milestoneRefreshImageView;
    @FXML
    private ImageView topFixersRefreshImageView;
    @FXML
    private ImageView weeklyRefreshImageView;
    @FXML
    private ImageView userProfileImage;
    @FXML
    private PieChart issuesChart;
    @FXML
    private PieChart milestoneChart;
    @FXML
    private BarChart barChart;
    @FXML
    private TableView<Issues> issuesTableView;
    @FXML
    private TableView<Users> usersTableView;
    @FXML
    private TableColumn<Issues, String> issuesTableColumn;
    @FXML
    private TableColumn<Issues, String> createdTableColumn;
    @FXML
    private TableColumn<Issues, String> reporterTableColumn;
    @FXML
    private TableColumn<Issues, String> dueTableColumn;
    @FXML
    private TableColumn<Issues, String> statusTableColumn;
    @FXML
    private TableColumn<Issues, String> severityTableColumn;
    @FXML
    private TableColumn<Issues, String> reproducibleTableColumn;
    @FXML
    private TableColumn<Users, String> firstNameTableColumn;
    @FXML
    private TableColumn<Users, String> lastNameTableColumn;
    @FXML
    private TableColumn<Users, String> phoneTableColumn;
    @FXML
    private TableColumn<Users, String> emailTableColumn;
    @FXML
    private TextField issueNameTextField;
    @FXML
    private ComboBox reporterNameComboBox;
    @FXML
    private ComboBox severityComboBox;
    @FXML
    private ComboBox reproducibleComboBox;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private Label issueNameMessageLabel;
    @FXML
    private Label reporterNameMessageLabel;
    @FXML
    private Label dueMessageLabel;
    @FXML
    private Label severityMessageLabel;
    @FXML
    private Label reproducibleMessageLabel;
    @FXML
    private Label entrySuccessMessageLabel;

    private ObservableList<Issues> issuesObservableList = FXCollections.observableArrayList();
    private ObservableList<Users> usersObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        issueNameMessageLabel.setText("");
        reporterNameMessageLabel.setText("");
        dueMessageLabel.setText("");
        severityMessageLabel.setText("");
        reproducibleMessageLabel.setText("");
        entrySuccessMessageLabel.setText("");

        initiateImages();
        initiateIssuesPieChart();
        initiateMilestonePieChart();
        initiateBarChart();
        initiateTable();
        initiateNewEntry();
        initiateUsersTable();

        //Change Scroll Pane scrolling speed
        final double SPEED = 0.01;
        dashboardScrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            dashboardScrollPane.setVvalue(dashboardScrollPane.getVvalue() - deltaY);
        });
        issuesScrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            issuesScrollPane.setVvalue(issuesScrollPane.getVvalue() - deltaY);
        });
    }

    //-----------------------------
    //Action when dashboardButton is pressed.
    //-----------------------------
    public void dashboardButtonOnAction() {
        dashboardScrollPane.setVisible(true);
        issuesScrollPane.setVisible(false);
        adminAnchorPane.setVisible(false);
    }

    //-----------------------------
    //Action when newEntryButton is pressed.
    //-----------------------------
    public void newEntryButtonOnAction() {
        if(newEntryAnchorPane.isVisible())
            newEntryAnchorPane.setVisible(false);
        else
            newEntryAnchorPane.setVisible(true);
        newEntryAddButton.setDisable(false);
    }

    //-----------------------------
    //Action when issuesButton is pressed.
    //-----------------------------
    public void issuesButtonOnAction() {
        dashboardScrollPane.setVisible(false);
        issuesScrollPane.setVisible(true);
        adminAnchorPane.setVisible(false);
    }

    //-----------------------------
    //Action when adminButton is pressed.
    //-----------------------------
    public void adminButtonOnAction() {
        dashboardScrollPane.setVisible(false);
        issuesScrollPane.setVisible(false);
        adminAnchorPane.setVisible(true);
    }

    //-----------------------------
    //Action when closeButton is pressed.
    //-----------------------------
    public void closeButtonOnAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //-----------------------------
    //Action when New Entry addEntryButton is pressed.
    //-----------------------------
    public void addEntryButtonOnAction() {
        addNewIssue();
    }

    //-----------------------------
    //Action when New Entry cancelButton is pressed.
    //-----------------------------
    public void cancelEntryButtonOnAction() {
        newEntryAnchorPane.setVisible(false);
    }

    //-----------------------------
    //Action when New Entry cancelButton is pressed.
    //-----------------------------
    public void refreshButtonOnAction() {
        issuesTableView.getItems().clear();
        initiateTable();
    }

    //-----------------------------
    //Initializations
    //-----------------------------
    public void initiateImages() {
        File applicationImageFile = new File("images/bug-tracker/bug.png");
        Image applicationImageIcon = new Image(applicationImageFile.toURI().toString());
        applicationImageView.setImage(applicationImageIcon);

        File closeImageFile = new File("images/bug-tracker/x.png");
        Image closeImageIcon = new Image(closeImageFile.toURI().toString());
        closeImageView.setImage(closeImageIcon);

        File issuesRefreshImageFile = new File("images/bug-tracker/refresh.png");
        Image issuesRefreshImageIcon = new Image(issuesRefreshImageFile.toURI().toString());
        issuesRefreshImageView.setImage(issuesRefreshImageIcon);
        milestoneRefreshImageView.setImage(issuesRefreshImageIcon);
        topFixersRefreshImageView.setImage(issuesRefreshImageIcon);
        weeklyRefreshImageView.setImage(issuesRefreshImageIcon);

        File profileLogoFile = new File("images/login/login-account-icon.png");
        Image profileLogoImage = new Image(profileLogoFile.toURI().toString());
        userProfileImage.setImage(profileLogoImage);
    }
    public void initiateIssuesPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Open", 18),
                new PieChart.Data("Closed", 12)
        );
        issuesChart.setData(pieChartData);
    }
    public void initiateMilestonePieChart() {
        ObservableList<PieChart.Data> milestoneChartData = FXCollections.observableArrayList(
                new PieChart.Data("Open", 15),
                new PieChart.Data("Closed", 5)
        );
        milestoneChart.setData(milestoneChartData);
    }
    public void initiateBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Items");
        yAxis.setLabel("Issues");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Week 1");
        series1.getData().add(new XYChart.Data("austria", 25601.34));
        series1.getData().add(new XYChart.Data("brazil", 20148.82));
        series1.getData().add(new XYChart.Data("france", 10000));
        series1.getData().add(new XYChart.Data("italy", 35407.15));
        series1.getData().add(new XYChart.Data("usa", 12000));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Week 2");
        series2.getData().add(new XYChart.Data("austria", 57401.85));
        series2.getData().add(new XYChart.Data("brazil", 41941.19));
        series2.getData().add(new XYChart.Data("france", 45263.37));
        series2.getData().add(new XYChart.Data("italy", 117320.16));
        series2.getData().add(new XYChart.Data("usa", 14845.27));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Week 3");
        series3.getData().add(new XYChart.Data("austria", 45000.65));
        series3.getData().add(new XYChart.Data("brazil", 44835.76));
        series3.getData().add(new XYChart.Data("france", 18722.18));
        series3.getData().add(new XYChart.Data("italy", 17557.31));
        series3.getData().add(new XYChart.Data("usa", 92633.68));

        barChart.getData().addAll(series1);
        barChart.getData().addAll(series2);
        barChart.getData().addAll(series3);
    }
    //-----------------------------
    // Initiate Issues Table
    //-----------------------------
    public void initiateTable() {
        //Set placeholder for empty table
        issuesTableView.setPlaceholder(new Label("No visible columns and/or data exist."));

        //Set cells value from Issues model
        issuesTableView.setItems(issuesObservableList);

        issuesTableColumn.setCellValueFactory(data -> data.getValue().issueNameProperty());
        createdTableColumn.setCellValueFactory(data -> data.getValue().createTimeProperty());
        reporterTableColumn.setCellValueFactory(data -> data.getValue().reporterNameProperty());
        dueTableColumn.setCellValueFactory(data -> data.getValue().dueTimeProperty());
        statusTableColumn.setCellValueFactory(data -> data.getValue().statusProperty());
        severityTableColumn.setCellValueFactory(data -> data.getValue().severityProperty());
        reproducibleTableColumn.setCellValueFactory(data -> data.getValue().reproducibleProperty());

        //Make connection to database and get selected data
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String query = "SELECT * FROM issues";

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            while(queryResult.next()) {
                issuesObservableList.add(new Issues(
                        queryResult.getString("issue_name"),
                        queryResult.getString("create_time"),
                        queryResult.getString("reporter_name"),
                        queryResult.getString("due_time"),
                        queryResult.getString("status"),
                        queryResult.getString("severity"),
                        queryResult.getString("reproducible")
                        )
                );
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    //-----------------------------
    // Initiate Users Table
    //-----------------------------
    public void initiateUsersTable() {
        //Set placeholder for empty table
        usersTableView.setPlaceholder(new Label("No visible columns and/or data exist."));

        //Set cells value from Issues model
        usersTableView.setItems(usersObservableList);

        firstNameTableColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
        lastNameTableColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());
        emailTableColumn.setCellValueFactory(data -> data.getValue().emailProperty());
        phoneTableColumn.setCellValueFactory(data -> data.getValue().phoneProperty());

        //Make connection to database and get selected data
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String query1 = "SELECT * FROM user_account";

            Statement statement1 = connectDB.createStatement();
            ResultSet queryResult1 = statement1.executeQuery(query1);

            String query2 = "SELECT * FROM user_info";

            Statement statement2 = connectDB.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(query2);

            while(queryResult1.next() && queryResult2.next()) {
                usersObservableList.add(new Users(
                        queryResult1.getString("username"),
                        queryResult1.getString("password"),
                        queryResult1.getString("email"),
                        queryResult2.getString("firstname"),
                        queryResult2.getString("lastname"),
                        queryResult2.getString("phone_number"),
                        queryResult2.getString("gender")
                        )
                );
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    //-----------------------------
    // Initiate New Entry
    //-----------------------------
    public void initiateNewEntry() {
        //Add People to Assign Combo Box
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String query = "SELECT * FROM user_info";

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            while(queryResult.next()) {
                String firstname = queryResult.getString("firstname");
                String lastname = queryResult.getString("lastname");

                reporterNameComboBox.getItems().add(firstname + " " + lastname);
            }

            connectDB.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        //Severity ComboBox
        severityComboBox.getItems().addAll(
                "Minor",
                "Major",
                "Critical"
        );

        //Reproducible ComboBox
        reproducibleComboBox.getItems().addAll(
                "Always",
                "Sometimes"
        );
    }

    //-----------------------------
    // Add new Issue
    //-----------------------------
    public void addNewIssue() {
        //Connect to database
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        //Create SQL code
        String issueName = issueNameTextField.getText();
        String reporterName = reporterNameComboBox.getValue().toString();
        String dueTime = dueDatePicker.getValue().toString();
        String status = "Open";
        String severity = severityComboBox.getValue().toString();
        String reproducible = reproducibleComboBox.getValue().toString();

        String insertToIssues = "INSERT INTO issues (issue_name, reporter_name, due_time, status, severity, reproducible) " +
                "VALUES ('" + issueName + "','" + reporterName + "','" + dueTime + "','" + status + "','" + severity + "','" + reproducible + "');";

        //Add new issue information to database
        if (!issueName.equals("")) {
            issueNameMessageLabel.setText("");
            if (!reporterName.equals("")) {
                reporterNameMessageLabel.setText("");
                if (!dueTime.equals("")) {
                    dueMessageLabel.setText("");
                    if (!severity.equals("")) {
                        severityMessageLabel.setText("");
                        if (!reproducible.equals("")) {
                            reproducibleMessageLabel.setText("");
                            try {
                                Statement statement = connectDB.createStatement();
                                statement.executeUpdate(insertToIssues);

                                refreshButtonOnAction();

                                entrySuccessMessageLabel.setText("New issue added successfully!");
                                newEntryAddButton.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                                e.getCause();
                            }
                        }
                        else {
                            reproducibleMessageLabel.setText("Select reproducibility.");
                        }
                    }
                    else{
                            severityMessageLabel.setText("Select severity.");
                        }
                    }
                else {
                    dueMessageLabel.setText("Select a date.");
                }
            }
            else {
                reporterNameMessageLabel.setText("Assign the issue.");
            }
        }
        else {
            issueNameMessageLabel.setText("Insert a title.");
        }
    }
}
