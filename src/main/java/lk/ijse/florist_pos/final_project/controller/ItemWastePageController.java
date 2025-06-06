package lk.ijse.florist_pos.final_project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.dto.FlowerWasteDto;
import lk.ijse.florist_pos.final_project.dto.Tm.FlowerWasteTm;
import lk.ijse.florist_pos.final_project.model.FlowerWasteModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemWastePageController implements Initializable {
    public TableColumn colWid;
    public TableColumn colFlowerId;
    public TableColumn colFlowerName;
    public TableColumn colFlowerQty;
    public TableColumn colReason;
    public TableColumn colWasteDate;
    public TableView tblFWaste;
    public ImageView imageView;
    public AnchorPane ancWaste;

    FlowerWasteModel flowerWasteModel = new FlowerWasteModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancWaste.widthProperty());
        imageView.fitHeightProperty().bind(ancWaste.heightProperty());
        setCellValues();
        try {
            resetPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void setCellValues() {
        colWid.setCellValueFactory(new PropertyValueFactory<>("wastedId"));
        colFlowerId.setCellValueFactory(new PropertyValueFactory<>("flowerId"));
        colFlowerName.setCellValueFactory(new PropertyValueFactory<>("flowerName"));
        colFlowerQty.setCellValueFactory(new PropertyValueFactory<>("flowerQty"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colWasteDate.setCellValueFactory(new PropertyValueFactory<>("wasteDate"));
    }

    public void resetPage() throws SQLException {
        loadTableData();
    }

    public void loadTableData() throws SQLException {
        ArrayList<FlowerWasteDto> floweWDtos = flowerWasteModel.getAllWFlower();
        ObservableList<FlowerWasteTm> flowerWTMS = FXCollections.observableArrayList();
        for (FlowerWasteDto flowerWDto : floweWDtos) {
            FlowerWasteTm flowerWTM = new FlowerWasteTm(
                    flowerWDto.getWastedId(),
                    flowerWDto.getFlowerId(),
                    flowerWDto.getFlowerName(),
                    flowerWDto.getFlowerQty(),
                    flowerWDto.getReason(),
                    flowerWDto.getWasteDate()
            );
            flowerWTMS.add(flowerWTM);
        }
        tblFWaste.setItems(flowerWTMS);
    }

    public void removeOnAction(ActionEvent actionEvent) {

    }
}
