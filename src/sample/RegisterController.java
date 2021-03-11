package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView registerImageView;
    @FXML
    private Button closeButton1;
    @FXML
    private Button closeButton2;
    @FXML
    private Button registerButton;
    @FXML
    private Label registerMessageLabel;
    @FXML
    private Label firstnameMessageLabel;
    @FXML
    private Label lastnameMessageLabel;
    @FXML
    private Label usernameMessageLabel;
    @FXML
    private Label passwordMessageLabel;
    @FXML
    private Label passwordMatchMessageLabel;
    @FXML
    private Label emailMessageLabel;
    @FXML
    private Label genderMessageLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private ToggleGroup gender;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;


    //Initialize images
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File registerLogoFile = new File("images/register/register-icon.png");
        Image registerLogoImage = new Image(registerLogoFile.toURI().toString());
        registerImageView.setImage(registerLogoImage);

        anchorPane1.setVisible(true);
        anchorPane2.setVisible(false);

        registerMessageLabel.setText("");
        firstnameMessageLabel.setText("");
        lastnameMessageLabel.setText("");
        usernameMessageLabel.setText("");
        passwordMessageLabel.setText("");
        passwordMatchMessageLabel.setText("");
        emailMessageLabel.setText("");
        genderMessageLabel.setText("");
    }

    //Action when nextButton is pressed.
    public void nextButtonOnAction() {
        if (passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            passwordMatchMessageLabel.setText("");
            nextAnchorPoint();
        } else {
            passwordMatchMessageLabel.setText("Password does not match.");
            passwordMatchMessageLabel.setTextFill(Color.web("#ff5555"));
        }
    }

    //Action when registerButton is pressed.
    public void registerButtonOnAction() {
        registerUser();
    }

    //Action when closeButton is pressed.
    public void closeButtonOnAction() {
        if(closeButton1.isPressed()) {
            Stage stage = (Stage) closeButton1.getScene().getWindow();
            stage.close();
        }
        else{
            Stage stage = (Stage) closeButton2.getScene().getWindow();
            stage.close();
        }
    }

    //Add data in user_account table
    public void nextAnchorPoint() {
        //Connect to database
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        //Create SQL code
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();

        String insertToRegister = "INSERT INTO user_account (username, password, email) " +
                "VALUES ('" + username + "','" + password + "','" + email + "');";

        //Verify if username already exist
        String usernameDuplicate = "";
        try {
            Statement st = connectDB.createStatement();
            ResultSet r1 = st.executeQuery("SELECT username FROM user_account WHERE username = '" + username + "';");

            if(r1.next()) {
                usernameDuplicate =  r1.getString("username");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        //Verify if email already exist
        String emailDuplicate = "";
        try {
            Statement st = connectDB.createStatement();
            ResultSet r1 = st.executeQuery("SELECT email FROM user_account WHERE email = '" + email + "';");

            if(r1.next()) {
                emailDuplicate =  r1.getString("email");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        //Register the new user login information
        if (!username.equals("")) {
            usernameMessageLabel.setText("");
            if (!password.equals("")) {
                passwordMessageLabel.setText("");
                if (!username.equals(usernameDuplicate)) {
                    usernameMessageLabel.setText("");
                    if(!email.equals(emailDuplicate)) {
                        emailMessageLabel.setText("");
                        if(username.length()<17) {
                            try {
                                Statement statement = connectDB.createStatement();
                                statement.executeUpdate(insertToRegister);

                                anchorPane1.setVisible(false);
                                anchorPane2.setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                                e.getCause();
                            }
                        }
                        else {
                            usernameMessageLabel.setText("Username too long.");
                        }
                    }
                    else {
                        emailMessageLabel.setText("E-mail already exist.");
                    }
                } else {
                    usernameMessageLabel.setText("Username already exist.");
                }
            }
            else {
                passwordMessageLabel.setText("Please insert a password.");
            }
        }
        else {
            usernameMessageLabel.setText("Please insert your username.");
        }
    }

    //Add data in user_info table
    public void registerUser() {
        //Connect to database
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        //Create SQL code
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String phone = phoneTextField.getText();
        RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
        String gender = selectedRadioButton.getText();

        String insertToRegister = "INSERT INTO user_info (firstname, lastname, phone_number, gender) " +
                "VALUES ('" + firstname + "','" + lastname + "','" + phone + "','" + gender + "');";

        //Register the new user login information
        if (!firstname.equals("")) {
            firstnameMessageLabel.setText("");
            if (!lastname.equals("")) {
                lastnameMessageLabel.setText("");
                if (!gender.equals("")) {
                    genderMessageLabel.setText("");
                    try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(insertToRegister);

                        registerMessageLabel.setText("Account created successfully!");
                        registerButton.setDisable(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
                else {
                    genderMessageLabel.setText("You must select a gender.");
                }
            }
            else {
                lastnameMessageLabel.setText("Lastname must not be empty.");
            }
        }
        else {
            firstnameMessageLabel.setText("Firstname must not be empty.");
        }
    }
}
