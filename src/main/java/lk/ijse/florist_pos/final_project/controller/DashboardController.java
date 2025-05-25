package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigateTo("/View/HomePage.fxml");
    }

    public void logoutonAction(ActionEvent actionEvent) throws IOException {
        // Load the login screen
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));

        // Create a new Stage (window)
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED); // Removes the title bar
        newStage.setScene(new Scene(root));
        newStage.show();

        // Close the current stage
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


    public void sendEmailOnAction(ActionEvent actionEvent) {
        navigateTo( "/View/SendMail.fxml");
    }
}
