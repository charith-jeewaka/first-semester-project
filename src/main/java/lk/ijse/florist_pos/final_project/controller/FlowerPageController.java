package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FlowerPageController {
    public AnchorPane ancFlower;
    public Label lblFlowerId;

    public TextField txtFlowerName;
    public TextField txtFlowerCategory;
    public TextField txtFlowerPrice;
    public TextField txtFlowerQty;

    public JFXComboBox cmbFlowerName;
    public JFXComboBox cmbFlowerCategory;
    public JFXComboBox cmbFlowerPrice;
    public JFXComboBox cmbFlowerQty;

    public TableView tblFlower;
    public TableColumn colFlowerId;
    public TableColumn colFlowerName;
    public TableColumn colFlowerCategory;
    public TableColumn colFlowerPrice;
    public TableColumn colFlowerQty;
    public TableColumn colFlowerStatus;
    public TableColumn colFlowerRegisteredTime;

    public JFXButton btnSearchFlower;
    public JFXButton btnUpdateFlower;
    public JFXButton btnDeleteFlower;
    public JFXButton btnResetFlower;
    public JFXButton btnPlantSaveFlower;

    public void flowerSaveOnAction(ActionEvent actionEvent) {
    }

    public void flowerPageResetOnAction(ActionEvent actionEvent) {
    }

    public void deleteFlowerOnAction(ActionEvent actionEvent) {
    }

    public void updateFlowerOnAction(ActionEvent actionEvent) {
    }

    public void searchFlowerOnAction(ActionEvent actionEvent) {
    }

    public void onClickTable(MouseEvent mouseEvent) {
    }
}
