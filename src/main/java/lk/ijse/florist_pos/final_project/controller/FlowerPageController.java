package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.dto.FlowerDto;
import lk.ijse.florist_pos.final_project.dto.Tm.FlowerTM;
import lk.ijse.florist_pos.final_project.model.FlowerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class FlowerPageController implements Initializable {
    public AnchorPane ancFlower;
    public Label lblFlowerId;

    public TextField txtFlowerName;
    public TextField txtFlowerCategory;
    public TextField txtFlowerPrice;
    public TextField txtFlowerQty;

    public JFXComboBox <String> cmbFlowerName;
    public JFXComboBox <String> cmbFlowerCategory;
    public JFXComboBox <String> cmbFlowerPrice;
    public JFXComboBox <String> cmbFlowerQty;

    public TableView <FlowerTM> tblFlower;
    public TableColumn <FlowerTM, String> colFlowerId;
    public TableColumn <FlowerTM, String> colFlowerName;
    public TableColumn <FlowerTM, String> colFlowerCategory;
    public TableColumn <FlowerTM, String> colFlowerPrice;
    public TableColumn <FlowerTM, String> colFlowerQty;
    public TableColumn <FlowerTM, String> colFlowerStatus;
    public TableColumn <FlowerTM, String> colFlowerRegisteredTime;

    public JFXButton btnSearchFlower;
    public JFXButton btnUpdateFlower;
    public JFXButton btnDeleteFlower;
    public JFXButton btnResetFlower;
    public JFXButton btnSaveFlower;
    public ImageView imageView;

    FlowerModel flowerModel = new FlowerModel();

    String namePattern = "^[A-Za-z ]+$";
    String pricePattern = "^\\d+(\\.\\d{2})?$";
    String qtyPattern = "^\\d+$";
    String categoryPattern = "^[A-Za-z ]+$";

    public ObservableList<String> bouquetPrices = FXCollections.observableArrayList(
            "1000","1250","1500","1750","2000","2250","2500","2750","3000"
    );
    public ObservableList<String> separateFlowerPrices = FXCollections.observableArrayList(
            "50","60","70","80","90","100","110","120","130","140","150","160","170"
    );
    public ObservableList<String> decorationPrices = FXCollections.observableArrayList(
             "1000","2000","3000","4000","5000","6000","7000","10000"
    );

    public ObservableList<String> flowerNames = FXCollections.observableArrayList(
            "Rose", "Lily", "Tulip", "Carnation", "Orchid", "Chrysanthemum",
            "Gerbera Daisy", "Peony", "Baby’s Breath", "Hydrangea",
            "Sunflower", "Alstroemeria", "Ranunculus", "Anthurium","Gladiolus"
    );

    public ObservableList<String> flowerCategories = FXCollections.observableArrayList(
            "Bouquet","Decoration","Separate"
    );

    public ObservableList<String> flowerQtys = FXCollections.observableArrayList(
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancFlower.widthProperty());
        imageView.fitHeightProperty().bind(ancFlower.heightProperty());

        colFlowerId.setCellValueFactory(new PropertyValueFactory<>("flowerId"));
        colFlowerName.setCellValueFactory(new PropertyValueFactory<>("flowerName"));
        colFlowerCategory.setCellValueFactory(new PropertyValueFactory<>("flowerCategory"));
        colFlowerPrice.setCellValueFactory(new PropertyValueFactory<>("flowerPrice"));
        colFlowerStatus.setCellValueFactory(new PropertyValueFactory<>("flowerStatus"));
        colFlowerQty.setCellValueFactory(new PropertyValueFactory<>("flowerAvailableQty"));
        colFlowerRegisteredTime.setCellValueFactory(new PropertyValueFactory<>("flowerEnteredTime"));
        try {
            resetFlowerPage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbFlowerName.setItems(flowerNames);

        cmbFlowerName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtFlowerName.setText(newValue.toString());
            }
        });

        cmbFlowerCategory.setItems(flowerCategories);

        cmbFlowerCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtFlowerCategory.setText(newValue.toString());
            }
        });

        cmbFlowerCategory.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                switch (newVal) {
                    case "Bouquet":
                        cmbFlowerPrice.setItems(bouquetPrices);
                        break;
                    case "Decoration":
                        cmbFlowerPrice.setItems(decorationPrices);
                        break;
                    case "Separate":
                        cmbFlowerPrice.setItems(separateFlowerPrices);
                        break;
                    default:
                        cmbFlowerPrice.setItems(FXCollections.observableArrayList());
                }
                cmbFlowerPrice.getSelectionModel().clearSelection();
                txtFlowerPrice.clear();
            }
        });

        cmbFlowerPrice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtFlowerPrice.setText(newValue);
            }
        });

        cmbFlowerQty.setItems(flowerQtys);

        cmbFlowerQty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtFlowerQty.setText(newValue.toString());
            }
        });

    }

    public void loadTableData() throws SQLException {
        ArrayList<FlowerDto> flowerDtos = flowerModel.getAllFlower();
        ObservableList<FlowerTM> flowerTMS = FXCollections.observableArrayList();
        for (FlowerDto flowerDto : flowerDtos) {
            FlowerTM flowerTM = new FlowerTM(
                    flowerDto.getFlowerId(),
                    flowerDto.getFlowerName(),
                    flowerDto.getFlowerCategory(),
                    flowerDto.getFlowerPrice(),
                    flowerDto.getFlowerStatus(),
                    flowerDto.getFlowerAvailableQty(),
                    flowerDto.getFlowerEnteredTime()
            );
            flowerTMS.add(flowerTM);
        }
        tblFlower.setItems(flowerTMS);
    }

    public void loadNextFlowerId() throws SQLException {
        String nextId = flowerModel.getNextFlowerId();
        lblFlowerId.setText(nextId);
    }

    public void flowerSaveOnAction(ActionEvent actionEvent) {
        String id = lblFlowerId.getText();
        String name = txtFlowerName.getText();
        String category = txtFlowerCategory.getText();
        String price = txtFlowerPrice.getText();
        String status = "Available";
        String qty = txtFlowerQty.getText();
        boolean isValidName = name.matches(namePattern);
        boolean isValidCategory = category.matches(categoryPattern);
        boolean isValidPrice = price.matches(pricePattern);
        boolean isValidQty = qty.matches(qtyPattern);

        FlowerDto flowerDto = new FlowerDto(
                id,
                name,
                category,
                price,
                status,
                qty,
                null
        );

        if (isValidName && isValidCategory && isValidPrice && isValidQty) {
            try {
                boolean isSaved = flowerModel.saveFlower(flowerDto);

                if (isSaved) {
                    resetFlowerPage();
                    new Alert(Alert.AlertType.INFORMATION, "Flower saved successfully.").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to save flower.").show();
                    resetFlowerPage();
                }
            } catch (SQLException e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database Error.").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Invalid input data.").show();
            resetFlowerPage();
        }
    }

    public void resetFlowerPage(){
        try {
            flowerModel.updateFlowerLifeStatus();
            loadTableData();
            loadNextFlowerId();
            txtFlowerName.clear();
            txtFlowerCategory.clear();
            txtFlowerPrice.clear();
            txtFlowerQty.clear();

            cmbFlowerName.getSelectionModel().clearSelection();
            cmbFlowerCategory.getSelectionModel().clearSelection();
            cmbFlowerPrice.getSelectionModel().clearSelection();
            cmbFlowerQty.getSelectionModel().clearSelection();

            btnSaveFlower.setDisable(false);
            btnDeleteFlower.setDisable(true);
            btnUpdateFlower.setDisable(true);

            txtFlowerName.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void flowerPageResetOnAction(ActionEvent actionEvent) {
        resetFlowerPage();
    }

    public void deleteFlowerOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this flower?",
                ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {

            String flowerId = lblFlowerId.getText();
            try {
                boolean isDeleted = flowerModel.deleteFlower(flowerId);

                if (isDeleted) {
                    resetFlowerPage();
                    new Alert(Alert.AlertType.INFORMATION, "Flower deleted successfully.").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete flower.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to delete flower.").show();
            }
        }

    }

    public void updateFlowerOnAction(ActionEvent actionEvent){
        String id = lblFlowerId.getText();
        String name = txtFlowerName.getText();
        String category = txtFlowerCategory.getText();
        String status =  "Available";
        String price = txtFlowerPrice.getText();
        String qty = txtFlowerQty.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidCategory = category.matches(categoryPattern);
        boolean isValidPrice = price.matches(pricePattern);
        boolean isValidQty = qty.matches(qtyPattern);

        FlowerDto flowerDto = new FlowerDto(
                id,
                name,
                category,
                price,
                status,
                qty,
                null
        );
        if (isValidName && isValidCategory && isValidPrice && isValidQty) {
            try {
                boolean isUpdated = flowerModel.updateFlower(flowerDto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Flower updated successfully.").show();
                    resetFlowerPage();
                }else {new Alert(Alert.AlertType.ERROR, "Fail to update flower.").show();
                resetFlowerPage();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database Error.").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid input data.").show();
        }
    }

    public void searchFlowerOnAction(ActionEvent actionEvent) {
    }

    public void onClickTable(MouseEvent mouseEvent) {
        FlowerTM selectedItem = tblFlower.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblFlowerId.setText(selectedItem.getFlowerId());
            txtFlowerName.setText(selectedItem.getFlowerName());
            txtFlowerCategory.setText(selectedItem.getFlowerCategory());
            txtFlowerPrice.setText(selectedItem.getFlowerPrice());
            txtFlowerQty.setText(selectedItem.getFlowerAvailableQty());

            // save button disable
            btnSaveFlower.setDisable(true);

            // update, delete button enable
            btnUpdateFlower.setDisable(false);
            btnDeleteFlower.setDisable(false);
        }
    }
}

