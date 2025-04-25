package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public void submitDetailsOnAction(ActionEvent actionEvent) {
        navigateTo("/View/SavePasswordPage.fxml");

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginScreen.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED); // Removes title bar
        stage.show();
        ((Stage) txtUserName.getScene().getWindow()).close();

    }


    private  void navigateTo(String path) {
        try {
            AncForgetContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(AncForgetContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(AncForgetContainer.heightProperty());

            AncForgetContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }


}
