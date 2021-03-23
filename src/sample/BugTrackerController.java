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
    private Button dashboardButton;
    @FXML
    private Button issuesButton;
    @FXML
    private Button closeButton;
    @FXML
    private ScrollPane dashboardScrollPane;
    @FXML
    private ScrollPane issuesScrollPane;
    @FXML
    private AnchorPane newEntryAnchorPane;
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

    private ObservableList<Issues> issuesObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateImages();
        initiateIssuesPieChart();
        initiateMilestonePieChart();
        initiateBarChart();
        initiateTable();

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
    }

    //-----------------------------
    //Action when newEntryButton is pressed.
    //-----------------------------
    public void newEntryButtonOnAction() {
        if(newEntryAnchorPane.isVisible())
            newEntryAnchorPane.setVisible(false);
        else
            newEntryAnchorPane.setVisible(true);
    }

    //-----------------------------
    //Action when issuesButton is pressed.
    //-----------------------------
    public void issuesButtonOnAction() {
        dashboardScrollPane.setVisible(false);
        issuesScrollPane.setVisible(true);
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
}
