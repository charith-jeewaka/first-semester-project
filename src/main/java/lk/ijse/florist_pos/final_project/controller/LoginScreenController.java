package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lk.ijse.florist_pos.final_project.model.SystemUserModel;





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
    @FXML
    public Button btnForgetPassword;
    @FXML
    public BorderPane AncLoginContainer;

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnLogginOnAction(ActionEvent actionEvent) {
       if (txtUserName.getText().isBlank() == false && txtPassword.getText().isBlank() == false ) {
           lblIncorrectMassage.setText("Invalid username or password");
           SystemUserModel systemUserModel = new SystemUserModel();
           systemUserModel.validateLogin(txtUserName,txtPassword,lblIncorrectMassage);
           clearText();

       }

       else {
           lblIncorrectMassage.setText("Enter username and password");
           clearText();
       }
    }


    public void clearText() {
        txtPassword.clear();
    }

    public void forgetPasswordOnAction(ActionEvent actionEvent) {
        navigateTo("/View/ForgetPassword.fxml");
    }

    private  void navigateTo(String path) {
        try {
            AncLoginContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(AncLoginContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(AncLoginContainer.heightProperty());

            AncLoginContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }
}


