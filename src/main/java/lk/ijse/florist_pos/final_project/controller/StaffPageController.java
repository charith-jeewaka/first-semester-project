package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.dto.EmployeeDto;
import lk.ijse.florist_pos.final_project.dto.Tm.StaffTM;
import lk.ijse.florist_pos.final_project.model.StaffModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffPageController implements Initializable {

    public AnchorPane ancStaff;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colSalary;
    public TableColumn colRole;
    public TableColumn colEmail;
    public TableView tblEmployee;
    public ImageView imageView;
    public TextField txtName;
    public TextField txtEmail;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnReset;
    public Label lblEmpId;
    public TextField txtSalary;
    public TextField txtRole;
    StaffModel staffModel = new StaffModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancStaff.widthProperty());
        imageView.fitHeightProperty().bind(ancStaff.heightProperty());

        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("employeeSalary"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("employeePosition"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("employeeEmail"));
        resetPage();


    }

    public void resetPage(){

        loadTableData();

    }
    public void loadTableData() {
        ArrayList<EmployeeDto> employeeDtoArrayList;
        try {
            employeeDtoArrayList = staffModel.getAllEmployees();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<StaffTM> staffTMS = FXCollections.observableArrayList();
        for (EmployeeDto employeeDto : employeeDtoArrayList) {
            StaffTM staffTM = new StaffTM(
                    employeeDto.getEmployeeId(),
                    employeeDto.getEmployeeName(),
                    employeeDto.getEmployeeSalary(),
                    employeeDto.getEmployeePosition(),
                    employeeDto.getEmployeeEmail()
            );
            staffTMS.add(staffTM);
        }

        tblEmployee.setItems(staffTMS);
    }

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void PageResetOnAction(ActionEvent actionEvent) {
    }

    public void mouseClickOnAction(MouseEvent mouseEvent) {
    }

    public void onClickTable(MouseEvent mouseEvent) {
    }
}
