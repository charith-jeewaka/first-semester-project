package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.dto.SystemUserDto;
import lk.ijse.florist_pos.final_project.model.SystemUserModel;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {
    @FXML
    public AnchorPane ancUserPage;
    @FXML
    public ImageView imageView;
    @FXML
    public TextField txtUserName;
    @FXML
    public TextField txtMobile;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtNic;
    @FXML
    public TextField txtPassword;
    @FXML
    public TextField txtUserRole;
    @FXML
    public Button btnSave;

    private final String passwordPattern = "^[A-Za-z0-9]{6,}$";
    private final String namePattern = "^[A-Za-z ]+$";
    private final String phonePattern = "^[0-9]{10}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public TextField txtVerificationCode;
    public Button btnSendCode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancUserPage.widthProperty());
        imageView.fitHeightProperty().bind(ancUserPage.heightProperty());
    }

    public void saveUserOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String nic = txtNic.getText();
        String password = txtPassword.getText();
        String role = txtUserRole.getText();

        boolean isValidName = userName.matches(namePattern);
        boolean isValidMobile = mobile.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPassword = password.matches(passwordPattern);

        SystemUserDto dto = new SystemUserDto(
                0, 
                userName,
                password,
                role,
                mobile,
                email,
                nic
        );

        if(isValidMobile && isValidEmail && isValidPassword && isValidName) {

            try {
                boolean isSaved = SystemUserModel.saveUser(dto);
                if (isSaved) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("You are successfully saved! An Confirmation is sent to you soon");
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Something went wrong!");
                    alert.showAndWait();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter valid Data ");
            alert.showAndWait();
        }

    }

    public void btnSendCodeOnAction(ActionEvent actionEvent) {
    }
}
