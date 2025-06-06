package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lk.ijse.florist_pos.final_project.model.SystemUserModel;

import java.io.IOException;

public class ForgetPasswordPageController {
    @FXML
    public TextField txtUserId;
    @FXML
    public TextField txtUserNic;
    @FXML
    public TextField txtUserName;
    @FXML
    public TextField txtMobile;
    @FXML
    public TextField txtEmail;
    @FXML
    public Button btnSubmit;
    @FXML
    public Label lblErrorMassage;
    @FXML
    public Button btnCancel;
    @FXML
    public  AnchorPane AncForgetContainer;
    @FXML
    public TextField txtRole;

    @FXML
//    public AnchorPane ancResetPassword;

    public void submitDetailsOnAction(ActionEvent actionEvent) throws IOException {

            String userId = txtUserId.getText();
            String username = txtUserName.getText();
            String email = txtEmail.getText();
            String nic = txtUserNic.getText();
            String phone = txtMobile.getText();
            String role = txtRole.getText();

            SystemUserModel systemUserModel = new SystemUserModel();
            SavePasswordPageController savePasswordPageController = new SavePasswordPageController();

            boolean isValid = systemUserModel.validateUserForPasswordReset(userId,username,role,phone,email,nic);

            if (isValid) {
                lblErrorMassage.setText("User validated!");
                clearFields();
                AncForgetContainer.getChildren().clear();
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/SavePasswordPage.fxml"));
                AncForgetContainer.getChildren().add(pane);

            } else {
                lblErrorMassage.setText("Incorrect details.");
                clearFields();
            }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        AncForgetContainer.getChildren().clear();
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("/View/LoginScreen.fxml"));
        AncForgetContainer.getChildren().add(borderPane);
    }

    private void clearFields() {
        txtUserId.clear();
        txtUserNic.clear();
        txtUserName.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtRole.clear();
    }
}
