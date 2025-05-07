package lk.ijse.florist_pos.final_project.model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.controller.DashboardController;
import lk.ijse.florist_pos.final_project.controller.LoginScreenController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SystemUserModel {
    public void validateLogin(TextField username, PasswordField password, Label label) {
        LoginScreenController loginScreenController = new LoginScreenController();
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "SELECT * FROM system_user WHERE user_name = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username.getText());
            statement.setString(2, password.getText());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Login success
                label.setText("Login successful!");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
                Parent root = loader.load();
                DashboardController dashboardController = loader.getController();
                dashboardController.lblCurrentUser.setText(resultSet.getString("user_name"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

// Close the login window
                ((Stage) username.getScene().getWindow()).close();

            } else {
                // Login failed
                label.setText("Invalid username or password");
                username.clear();
                password.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Database error!");
        }

        loginScreenController.clearText();
    }


    public boolean validateUserForPasswordReset(String id, String userName, String role, String mobile, String email, String nic) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "SELECT * FROM system_user WHERE user_id = ? AND user_name = ? AND user_role = ? AND user_mobile = ? AND user_email = ? AND user_nic = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, userName);
            statement.setString(3, role);
            statement.setString(4, mobile);
            statement.setString(5, email);
            statement.setString(6, nic);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // returns true if user found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPassword(String userId, String newPassword) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "UPDATE system_user SET password = ? WHERE user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, userId);

            return statement.executeUpdate() > 0; // true if at least 1 row updated
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}




