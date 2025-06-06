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
import lk.ijse.florist_pos.final_project.dto.PlantDto;
import lk.ijse.florist_pos.final_project.dto.Tm.PlantTM;
import lk.ijse.florist_pos.final_project.model.PlantModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlantPageController implements Initializable {

    public AnchorPane ancPlant;
    public Label lblPlantId;

    public JFXComboBox cmbName;
    public JFXComboBox cmbVarient;
    public JFXComboBox cmbHeight;
    public JFXComboBox cmbQty;

    public TextField txtname;
    public TextField txtVarient;
    public TextField txtPrice;
    public TextField txtQty;

    public TableView<PlantTM> tblPlant;
    public TableColumn<PlantTM, String> colId;
    public TableColumn<PlantTM, String> colName;
    public TableColumn<PlantTM, String> colHeight;
    public TableColumn<PlantTM, String> colPrice;
    public TableColumn<PlantTM, String> colVarient;
    public TableColumn<PlantTM, String> colQty;
    public TableColumn<PlantTM, String> colRegisteredTime;

    public JFXButton btnPlantSearch;
    public JFXButton btnPlantUpdate;
    public JFXButton btnPlantDelete;
    public JFXButton btnPlantPageReset;
    public JFXButton btnPlantSave;

    private final PlantModel plantModel = new PlantModel();

    private final String namePattern = "^[A-Za-z ]+$";
    private final String heightPattern = "^(0\\.5|[1-4](\\.5)?|5)$";
    private final String pricePattern = "^\\d+(\\.\\d{2})?$";
    private final String qtyPattern = "^\\d+$";
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageView.fitWidthProperty().bind(ancPlant.widthProperty());
        imageView.fitHeightProperty().bind(ancPlant.heightProperty());

        ObservableList<String> plantNames = FXCollections.observableArrayList(
                "Rose", "Lily", "Orchid", "Tulip", "Daffodil",
                "Sunflower", "Jasmine", "Daisy", "Lavender", "Peony",
                "Chrysanthemum", "Carnation", "Violet", "Iris", "Marigold"
        );
        // Set the plant names to the combo box
        cmbName.setItems(plantNames);

        cmbName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtname.setText(newValue.toString());
            }
        });

        ObservableList<String> plantHeights = FXCollections.observableArrayList(
                "0.5", "1", "1.5", "2", "2.5","3", "3.5", "4", "4.5"
        );
        cmbHeight.setItems(plantHeights);

        ObservableList<String> plantQtys = FXCollections.observableArrayList(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14","15","16","17","18","19","20",
                "21","22","23"
        );
        cmbQty.setItems(plantQtys);
        cmbQty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtQty.setText(newValue.toString());
            }
        });

        ObservableList<String> plantVariatys = FXCollections.observableArrayList(
                "indoor", "Outdoor","Esthatic", "Red", "White", "Purple","Rose", "Yellow", "blue"
        );
        cmbVarient.setItems(plantVariatys);
        cmbVarient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtVarient.setText(newValue.toString());
            }
        });

        colId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("plantName"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("plantHeight"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("plantPrice"));
        colVarient.setCellValueFactory(new PropertyValueFactory<>("plantVarient"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("plantAvailableQty"));
        colRegisteredTime.setCellValueFactory(new PropertyValueFactory<>("PlantRegisteredTime"));

        try {
            resetPage();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database Error");
            alert.show();
        }
    }

    private void loadTableData() throws SQLException {
        ArrayList<PlantDto> plantDtoArrayList = plantModel.getAllPlants();
        ObservableList<PlantTM> plantTMS = FXCollections.observableArrayList();

        for (PlantDto plantDto : plantDtoArrayList) {

            PlantTM plantTM = new PlantTM(
                    plantDto.getPlantId(),
                    plantDto.getPlantName(),
                    plantDto.getPlantHeight(),
                    plantDto.getPlantPrice(),
                    plantDto.getPlantVarient(),
                    plantDto.getPlantAvailableQty(),
                    plantDto.getPlantRegisteredTime()
            );
            plantTMS.add(plantTM);
        }
        tblPlant.setItems(plantTMS);
    }

    private void resetPage() throws SQLException {
        try {
            loadNextId();
            loadTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtname.clear();
        txtVarient.clear();
        txtPrice.clear();
        txtQty.clear();
        cmbName.getSelectionModel().clearSelection();
        cmbVarient.getSelectionModel().clearSelection();
        cmbHeight.getSelectionModel().clearSelection();
        cmbQty.getSelectionModel().clearSelection();

        btnPlantSave.setDisable(false);
        btnPlantDelete.setDisable(true);
        btnPlantUpdate.setDisable(true);
    }

    private void loadNextId() throws SQLException {
        String nextId = plantModel.getNextPlantId();
        lblPlantId.setText(nextId);
    }

    public void onClickTable(MouseEvent mouseEvent) {
        PlantTM selectedItem = tblPlant.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblPlantId.setText(selectedItem.getPlantId());
            txtname.setText(selectedItem.getPlantName());
            txtPrice.setText(selectedItem.getPlantPrice());
            txtVarient.setText(selectedItem.getPlantVarient());
            txtQty.setText(selectedItem.getPlantAvailableQty());
            cmbName.getSelectionModel().select(selectedItem.getPlantName());
            cmbVarient.getSelectionModel().select(selectedItem.getPlantVarient());
            cmbHeight.getSelectionModel().select(selectedItem.getPlantHeight());
            cmbQty.getSelectionModel().select(selectedItem.getPlantAvailableQty());

            // save button disable
            btnPlantSave.setDisable(true);

            // update, delete button enable
            btnPlantUpdate.setDisable(false);
            btnPlantDelete.setDisable(false);
        }
    }

    public void plantSearchOnAction(ActionEvent actionEvent) {
    }

    public void plantUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        String id = lblPlantId.getText();
        String name = txtname.getText();
        String varient = txtVarient.getText();
        String qty = txtQty.getText();
        String price = txtPrice.getText();
        String height = String.valueOf(cmbHeight.getValue());


        PlantTM selectedItem = tblPlant.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.ERROR, "No plant selected from table.").show();
            return;
        }
        String time = selectedItem.getPlantRegisteredTime(); // Use existing time

        PlantDto plantDto = new PlantDto(id, name, height, price, varient, qty, time);

        boolean isValidName = name.matches(namePattern);
        boolean isValidHight = height.matches(heightPattern);
        boolean isValidPrice = price.matches(pricePattern);
        boolean isValidVarient = varient.matches(namePattern);
        boolean isValidQty = qty.matches(qtyPattern);

        if (isValidHight && isValidPrice && isValidVarient && isValidQty && isValidName) {
            boolean isSaved = plantModel.updateCustomer(plantDto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Plant updated successfully.").show();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update plant.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input data.").show();
        }
    }

    public void plantDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure to delete this plant?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {

            String plantId = lblPlantId.getText();
            try {
                boolean isDeleted = plantModel.deletePlant(plantId);

                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Plant deleted successfully.").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete plant.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to delete plant.").show();
            }
        }
    }

    public void plantPageResetOnAction(ActionEvent actionEvent) {
        try {
            resetPage();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database Error");
            alert.show();
        }
    }

    public void plantSaveOnAction(ActionEvent actionEvent) {
        String Id = lblPlantId.getText();
        String name = txtname.getText();
        String height = String.valueOf(cmbHeight.getValue());
        String price = txtPrice.getText();
        String varient = txtVarient.getText(); // Varient is possibly wrong, check your input fields
        String qty = txtQty.getText();

        // Validate inputs with regex
        boolean isValidName = name.matches(namePattern);
        boolean isValidHeight = height.matches(heightPattern);
        boolean isValidPrice = price.matches(pricePattern);
        boolean isValidVarient = varient.matches(namePattern);
        boolean isValidQty = qty.matches(qtyPattern);

        // Validate all fields
        if (isValidName && isValidHeight && isValidPrice && isValidVarient && isValidQty) {

            PlantDto plantDto = new PlantDto(Id,
                    name,
                    height,
                    price,
                    varient,
                    qty,
                    null);

            try {

                boolean isSaved = plantModel.savePlant(plantDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Plant saved successfully.").show();
                    resetPage();
                } else {

                    new Alert(Alert.AlertType.ERROR, "Error saving plant. Please try again.").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error Saving Plant").show();
            }
        } else {

            new Alert(Alert.AlertType.ERROR, "Invalid details entered. Please check your input.").show();
        }
    }
}
