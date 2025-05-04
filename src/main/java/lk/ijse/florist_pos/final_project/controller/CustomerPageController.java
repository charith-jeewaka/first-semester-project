package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class CustomerPageController {
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

    //regex patterns
    private final String namePattern = "^[A-Za-z ]+$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";


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
