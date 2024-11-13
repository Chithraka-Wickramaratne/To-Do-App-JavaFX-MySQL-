package controller;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginFormController {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;

    public static String loginUserName;
    public static String loginUserID;

    Connection connection = DBConnection.getInstance().getConnection();

    public void lblCreateNewAccountOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/RegistrationForm.fxml"));

        Scene scene = new Scene(parent);

        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.centerOnScreen();


    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        login();
    }

    public void txtpasswordOnAction(ActionEvent actionEvent) {
        login();
    }

    public void login(){
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            PreparedStatement preparedStatement =connection.prepareStatement("select * from  user where user_name = ? and password = ?");
            preparedStatement.setObject(1,userName);
            preparedStatement.setObject(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                loginUserName = resultSet.getString(2);
                loginUserID = resultSet.getString(1);

                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/DashBordForm.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("To-Do List");
                stage.centerOnScreen();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid User Name or Password");
                alert.showAndWait();
                txtUserName.clear();
                txtPassword.clear();
                txtUserName.requestFocus();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
