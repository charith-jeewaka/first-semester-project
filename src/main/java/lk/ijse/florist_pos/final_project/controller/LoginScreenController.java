package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginScreenController {
    @FXML
    public TextField txtUserName;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnLoggin;
    @FXML
    public Label lblIncorrectMassage;
    public Button btnForgetPassword;

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnLogginOnAction(ActionEvent actionEvent) {
       if (txtUserName.getText().isBlank() == false && txtPassword.getText().isBlank() == false ) {
           lblIncorrectMassage.setText("Invalid username or password");
           validateLogin();

           clearText();
       }

       else {
           lblIncorrectMassage.setText("Enter username and password");
           clearText();
       }

    }

    public void validateLogin() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "SELECT * FROM system_user WHERE user_name = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, txtUserName.getText());
            statement.setString(2, txtPassword.getText());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Login success
                lblIncorrectMassage.setText("Login successful!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

// Close the login window
                ((Stage) txtUserName.getScene().getWindow()).close();



            } else {
                // Login failed
                lblIncorrectMassage.setText("Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblIncorrectMassage.setText("Database error!");
        }


    }
    public void clearText() {
        txtUserName.clear();
        txtPassword.clear();
    }

    public void forgetPasswordOnAction(ActionEvent actionEvent) {
        
    }
}
