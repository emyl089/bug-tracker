package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("bug-tracker.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.getStylesheets().add("button-header.css");
        root.getStylesheets().add("button-refresh.css");
        root.getStylesheets().add("button-side-panel.css");
        root.getStylesheets().add("pie-chart.css");
        primaryStage.setScene(new Scene(root, 1024, 640));//login 520-400
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
