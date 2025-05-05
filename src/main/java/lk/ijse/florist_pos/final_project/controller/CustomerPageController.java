package lk.ijse.florist_pos.final_project.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.florist_pos.final_project.dto.Tm.CustomerTM;
import lk.ijse.florist_pos.final_project.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {
    public TableColumn colRegisteredTime;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colMobile;
    public TableColumn colName;
    public TableColumn colId;
    public TableView tblCustomer;

    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnSearch;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtAddress;
    public TextField txtName;
    public TextArea txtSearchCustomer;
    public Label lblCustomerId;

    CustomerModel customerModel = new CustomerModel();

    //regex patterns
    private final String namePattern = "^[A-Za-z ]+$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // table column and tm class properties link
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colRegisteredTime.setCellValueFactory(new PropertyValueFactory<>("registeredTime"));


        try {
//            loadTableData();
//            loadNextId();
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }


    private void loadTableData() throws SQLException {
//        1.
//        ArrayList<CustomerDTO> customerDTOArrayList = customerModel.getAllCustomer();
//        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

//        for (CustomerDTO customerDTO : customerDTOArrayList) {
//            CustomerTM customerTM = new CustomerTM(
//                    customerDTO.getCustomerId(),
//                    customerDTO.getName(),
//                    customerDTO.getNic(),
//                    customerDTO.getEmail(),
//                    customerDTO.getPhone()
//            );
//            customerTMS.add(customerTM);
//        }
//        tblCustomer.setItems(customerTMS);

//        2. Full short code (Single line)
        tblCustomer.setItems(FXCollections.observableArrayList(
                customerModel.getAllCustomer().stream()
                        .map(customerDTO -> new CustomerTM(
                                customerDTO.getCustomerId(),
                                customerDTO.getCustomerName(),
                                customerDTO.getMobileNumber(),
                                customerDTO.getEmail(),
                                customerDTO.getCustomerAddress(),
                                customerDTO.getRegisteredTime()
                        )).toList()
        ));
    }


    private void resetPage() {
        try {
            loadTableData();
            customerModel.getNextCustomerId();

            // save button (id) -> enable
            btnSave.setDisable(false);

            // update, delete button (id) -> disable
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtName.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            //new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }


    public void saveCustomerOnAction(ActionEvent actionEvent) {
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) {
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) {
    }

    public void resetCustomerPageOnAction(ActionEvent actionEvent) {
    }
}
