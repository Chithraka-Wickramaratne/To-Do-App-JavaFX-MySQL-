package controller;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class RegistrationFormController {
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;
    public Label lblPasswordNotMatch2;
    public Label lblPasswordNotMatch1;
    public Label lblUserID;
    public TextField txtUserName;
    public TextField txtEmail;
    public Button btnSignUp;

    private final Connection connection = DBConnection.getInstance().getConnection();
    public AnchorPane root;

    public void initialize() {
        setLableVisibility(false);
        setDisableCommon(true);
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) {
       register();
    }

    public void setBorderColor(String color) {
        txtPassword.setStyle("-fx-border-color: " + color);
        txtConfirmPassword.setStyle("-fx-border-color: " + color);
    }

    public void setLableVisibility(boolean isVisible) {
        lblPasswordNotMatch1.setVisible(isVisible);
        lblPasswordNotMatch2.setVisible(isVisible);
    }

    public void btnCreateIDOnAction(ActionEvent actionEvent) throws SQLException {
        autoGeneratedID();
        setDisableCommon(false);
        txtUserName.requestFocus();
    }

    public void setDisableCommon(boolean isDisabled) {
        txtEmail.setDisable(isDisabled);
        txtUserName.setDisable(isDisabled);
        txtPassword.setDisable(isDisabled);
        txtConfirmPassword.setDisable(isDisabled);
        btnSignUp.setDisable(isDisabled);
    }

    public void autoGeneratedID() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");

        if (resultSet.next()) {
            String userId = resultSet.getString(1).substring(1);
            int id = Integer.parseInt(userId) + 1;

            if (id < 10) {
                lblUserID.setText("U00" + id);
            } else if (id < 100) {
                lblUserID.setText("U0" + id);
            } else {
                lblUserID.setText("U" + id);
            }
        } else {
            lblUserID.setText("U001");
        }
    }

    public void txtConfirmPasswordOnAction(ActionEvent actionEvent) {

        register();

    }

    public void register(){

        String newPassword = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (newPassword.equals(confirmPassword)) {
            setBorderColor("transparent");
            setLableVisibility(false);

            String id = lblUserID.getText();
            String userName = txtUserName.getText();
            String email = txtEmail.getText();

            try {
                String query = "INSERT INTO user (user_id, user_name, email, password) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setObject(1, id);
                preparedStatement.setObject(2, userName);
                preparedStatement.setObject(3, email);
                preparedStatement.setObject(4, confirmPassword);

                int i = preparedStatement.executeUpdate();

                if(i > 0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Successfully Registerd");
                    alert.showAndWait();

                    Parent parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));

                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.centerOnScreen();
                }

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            setBorderColor("red");
            setLableVisibility(true);
        }
    }

}

