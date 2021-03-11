package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
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
    private Button closeButton;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Open", 18),
                new PieChart.Data("Closed", 12)
        );
        issuesChart.setData(pieChartData);

        ObservableList<PieChart.Data> milestoneChartData = FXCollections.observableArrayList(
                new PieChart.Data("Open", 15),
                new PieChart.Data("Closed", 5)
        );
        milestoneChart.setData(milestoneChartData);

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

    //Action when closeButton is pressed.
    public void closeButtonOnAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
