package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

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

    public void saveNewPasswordOnAction(ActionEvent actionEvent) {
    }


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
}
