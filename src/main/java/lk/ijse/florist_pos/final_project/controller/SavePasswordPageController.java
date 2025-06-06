package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lk.ijse.florist_pos.final_project.model.SystemUserModel;

import java.io.IOException;

public class SavePasswordPageController {
    @FXML
    public PasswordField txtConfirmNewPassword;
    @FXML
    public PasswordField txtNewPassword;
    @FXML
    public Button btnBack;
    @FXML
    public  AnchorPane ancResetPassword;
    @FXML
    public Button btnSaveNewPassword;
    @FXML
    public TextField txtResetUserId;
    @FXML
    public Label lblSavePasswordMassage;




    public void backOnAction(ActionEvent actionEvent) {
        navigateTo("/View/ForgetPassword.fxml");
    }


    private  void navigateTo(String path) {
        try {
            ancResetPassword.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancResetPassword.widthProperty());
            anchorPane.prefHeightProperty().bind(ancResetPassword.heightProperty());

            ancResetPassword.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    public void saveNewPasswordOnAction(ActionEvent actionEvent) throws IOException {
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmNewPassword.getText();

        if (newPassword.equals(confirmPassword)) {
            if (newPassword.length() >= 3) {
                SystemUserModel systemUserModel = new SystemUserModel();
                if (systemUserModel.updateUserPassword(txtResetUserId.getText(), newPassword)) {
                    lblSavePasswordMassage.setText("Password updated successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Password updated successfully!");
                    alert.showAndWait();
                    ancResetPassword.getChildren().clear();
                    BorderPane borderPane = FXMLLoader.load(getClass().getResource("/View/LoginScreen.fxml"));
                    ancResetPassword.getChildren().add(borderPane);

                } else {
                    lblSavePasswordMassage.setText("Failed to update password.");
                }
            } else {
                lblSavePasswordMassage.setText("Password must have at least 3 characters.");
            }
        } else {
            lblSavePasswordMassage.setText("Passwords do not match!");
        }
    }
}
