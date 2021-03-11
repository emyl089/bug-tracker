package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //Initialization for controls.
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView loginLogoView;
    @FXML
    private ImageView lockIconView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    //Initialize images
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File loginLogoFile = new File("images/login/login-account-icon.png");
        Image loginLogoImage = new Image(loginLogoFile.toURI().toString());
        loginLogoView.setImage(loginLogoImage);

        File lockIconFile = new File("images/login/lock-icon.png");
        Image lockIconImage = new Image(lockIconFile.toURI().toString());
        lockIconView.setImage(lockIconImage);

        loginMessageLabel.setText("");
    }

    //Action when loginButton is pressed.
    public void loginButtonOnAction() {
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            validateLogin();
        }
        else {
            loginMessageLabel.setText("Please enter username and password.");
            loginMessageLabel.setTextFill(Color.web("#ff5555"));
            loginMessageLabel.setLayoutX(43);
        }
    }

    //Action when registerButton is pressed.
    public void registerButtonOnAction() {
        createAccountStage();
    }

    //Action when cancelButton is pressed.
    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //Validate login by searching if an account 'username' and 'password' correspond.
    private void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(*) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordTextField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Login successful.");
                    loginMessageLabel.setTextFill(Color.web("#3253ca"));
                    loginMessageLabel.setLayoutX(103);
                    cancelButtonOnAction();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("bug-tracker.fxml"));
                        Stage registerStage = new Stage();
                        registerStage.initStyle(StageStyle.UNDECORATED);
                        registerStage.setScene(new Scene(root, 1024, 640));
                        registerStage.show();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
                else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                    loginMessageLabel.setTextFill(Color.web("#ff5555"));
                    loginMessageLabel.setLayoutX(66);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //After pressing 'register' button, create Register Stage.
    public void createAccountStage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 550));
            registerStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
