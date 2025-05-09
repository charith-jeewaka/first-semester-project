package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.dto.PlantDto;
import lk.ijse.florist_pos.final_project.dto.Tm.PlantTM;
import lk.ijse.florist_pos.final_project.model.PlantModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("plantName"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("plantHeight"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("plantPrice"));
        colVarient.setCellValueFactory(new PropertyValueFactory<>("plantVarient"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("plantAvailableQty"));
        colRegisteredTime.setCellValueFactory(new PropertyValueFactory<>("PlantRegisteredTime"));
        try {
            loadTableData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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



    public void onClickTable(MouseEvent mouseEvent) {
    }


    private void loadNextId() throws SQLException {
        String nextId = plantModel.getNextPlantId();
        lblPlantId.setText(nextId);
    }





    public void plantSearchOnAction(ActionEvent actionEvent) {
    }

    public void plantUpdateOnAction(ActionEvent actionEvent) {
    }

    public void plantDeleteOnAction(ActionEvent actionEvent) {
    }

    public void plantPageResetOnAction(ActionEvent actionEvent) {
    }

    public void plantSaveOnAction(ActionEvent actionEvent) {
    }
}
