package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {
    public AnchorPane ancUserPage;
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancUserPage.widthProperty());
        imageView.fitHeightProperty().bind(ancUserPage.heightProperty());
    }

    public void saveUserOnAction(ActionEvent actionEvent) {
    }
}
