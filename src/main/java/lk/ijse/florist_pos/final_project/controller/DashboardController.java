package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    public Button btnLogout;
    @FXML
    public AnchorPane ancDashboardContainer;
    @FXML
    public Label lblCurrentUser;
    @FXML
    public Button btnHome;
    @FXML
    public Button btnCustomer;
    @FXML
    public Button btnOrder;
    @FXML
    public AnchorPane ancDashboardContent;
    @FXML
    public Button btnItemWaste;
    @FXML
    public Button btnOrders;
    @FXML
    public Button btnFlowers;
    @FXML
    public Button btnPlants;
    @FXML
    public JFXButton btnSendEmail;
    @FXML
    public Label lblClock;
    @FXML
    public Label lblDashBoardName;
    @FXML
    public ImageView imgDashboardUser2;
    @FXML
    public ImageView imgDashboardUser1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        startClock();
        navigateTo("/View/HomePage.fxml");
        Image image = imgDashboardUser1.getImage();
        imgDashboardUser2.setImage(image);

        Platform.runLater(() -> {
            Notifications.create()
                    .title("Welcome")
                    .text("System started successfully. get full screen for best experience")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .showInformation();
        });
        Platform.runLater(() -> {
            Notifications.create()
                    .title("Manual")
                    .text("You Can Read The Manual From The '?' Icon in Dashboard")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .showInformation();
        });
    }

    public void logoutonAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));

        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED); // Removes the title bar
        newStage.setScene(new Scene(root));
        newStage.show();

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }


    public void homePageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/HomePage.fxml");
    }

    public void customerPageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/CustomerPage.fxml");
    }

    public void ordersOnAction(ActionEvent actionEvent) {
        navigateTo("/View/OrderPage.fxml");
    }

    public void flowersOnAction(ActionEvent actionEvent) {
        navigateTo("/View/FlowerPage.fxml");
    }

    public void plantsOnAction(ActionEvent actionEvent) {
        navigateTo("/View/PlantPage.fxml");
    }

    public void itemWasteOnAction(ActionEvent actionEvent) {
        navigateTo("/View/ItemWastePage.fxml");
    }

    public void sendEmailOnAction(ActionEvent actionEvent) {
        navigateTo( "/View/SendMail.fxml");
    }

    private void navigateTo(String path) {
        try {
            ancDashboardContent.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancDashboardContent.widthProperty());
            anchorPane.prefHeightProperty().bind(ancDashboardContent.heightProperty());

            ancDashboardContent.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }


    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), e -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblClock.setText(currentTime.format(formatter));
        }));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void goUserOnMouseClicked(MouseEvent mouseEvent) {
        navigateTo("/View/UserPage.fxml");

    }

    public void openManuualOnAction(ActionEvent actionEvent) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/Mannual.txt");
            File tempFile = File.createTempFile("Mannual", ".txt");
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Desktop.getDesktop().open(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goStaffPageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/StaffPage.fxml");
    }
}
