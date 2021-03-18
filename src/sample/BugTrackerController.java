package sample;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BugTrackerController implements Initializable {

    @FXML
    private Button dashboardButton;
    @FXML
    private Button issuesButton;
    @FXML
    private Button closeButton;
    @FXML
    private ScrollPane dashboardScrollpane;
    @FXML
    private ScrollPane issuesScrollpane;
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
    private PieChart issuesChart;
    @FXML
    private PieChart milestoneChart;
    @FXML
    private BarChart barChart;
    @FXML
    private TableView<Issues> issuesTableView;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateImages();
        initiateIssuesPieChart();
        initiateMilestonePieChart();
        initiateBarChart();
        initiateTable();

        //Add columns to the Table View
        issuesTableColumn.setCellValueFactory(cellData -> cellData.getValue().issueNameProperty());
        createdTableColumn.setCellValueFactory(cellData -> cellData.getValue().createTimeProperty());
        reporterTableColumn.setCellValueFactory(cellData -> cellData.getValue().reporterNameProperty());
        dueTableColumn.setCellValueFactory(cellData -> cellData.getValue().dueTimeProperty());
        statusTableColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        severityTableColumn.setCellValueFactory(cellData -> cellData.getValue().severityProperty());
        reproducibleTableColumn.setCellValueFactory(cellData -> cellData.getValue().reproducibleProperty());
    }

    //-----------------------------
    //Action when dashboardButton is pressed.
    //-----------------------------
    public void dashboardButtonOnAction() {
        dashboardScrollpane.setVisible(true);
        issuesScrollpane.setVisible(false);
    }

    //-----------------------------
    //Action when issuesButton is pressed.
    //-----------------------------
    public void issuesButtonOnAction() {
        dashboardScrollpane.setVisible(false);
        issuesScrollpane.setVisible(true);
    }

    //-----------------------------
    //Action when closeButton is pressed.
    //-----------------------------
    public void closeButtonOnAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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
        //Add rows to the Table View
        issuesTableView.getItems().addAll(getIssuesList());
        //Set placeholder for empty table
        issuesTableView.setPlaceholder(new Label("No visible columns and/or data exist."));
    }
    //-----------------------------
    //Returns an observable list of Issues
    //-----------------------------
    public static ObservableList<Issues> getIssuesList()
    {
        ObservableList<Issues> issues = FXCollections.observableArrayList();

        issues.add(new Issues("Feed Issue","12-03-2021","John Wick","14-03-2021","In Progress","Major","Always"));
        issues.add(new Issues("Logistic Delay","10-03-2021","Tony Stark","21-03-2021","Open","Minor","Always"));
        issues.add(new Issues("UI Not Responding","11-03-2021","Thor Odinson","13-03-2021","In Progress","Critical","Sometimes"));

        return issues;
    }
}
