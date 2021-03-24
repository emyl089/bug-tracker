package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users {

    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty email;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phone;
    private final StringProperty gender;

    //Default constructor
    public Users () {
        this(null,null,null,null,null,null,null);
    }

    //Constructor with data
    public Users (String username, String password, String email, String firstName, String lastName, String phone, String gender) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.gender = new SimpleStringProperty(gender);
    }

    public String getUsername() {
        return username.get();
    }
    public void setUsername(String username) { this.username.set(username); }
    public StringProperty usernameProperty() { return username; }

    public String getPassword() {
        return password.get();
    }
    public void setPassword(String password) { this.password.set(password); }
    public StringProperty passwordProperty() { return password; }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }

    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }
    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) { this.lastName.set(lastName); }
    public StringProperty lastNameProperty() { return lastName; }

    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String phone) { this.phone.set(phone); }
    public StringProperty phoneProperty() { return phone; }

    public String getGender() {
        return gender.get();
    }
    public void setGender(String gender) { this.gender.set(gender); }
    public StringProperty genderProperty() { return gender; }
}
