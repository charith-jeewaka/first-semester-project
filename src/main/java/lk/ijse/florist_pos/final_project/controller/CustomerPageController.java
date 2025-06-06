package lk.ijse.florist_pos.final_project.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.florist_pos.final_project.dto.CustomerDto;
import lk.ijse.florist_pos.final_project.dto.Tm.CustomerTM;
import lk.ijse.florist_pos.final_project.model.CustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    public TableView <CustomerTM> tblCustomer;
    public TableColumn <CustomerTM,String> colRegisteredTime;
    public TableColumn <CustomerTM,String> colAddress;
    public TableColumn <CustomerTM,String> colEmail;
    public TableColumn <CustomerTM,String> colMobile;
    public TableColumn <CustomerTM,String> colName;
    public TableColumn <CustomerTM,String> colId;


    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnSearch;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtAddress;
    public TextField txtName;
    public Label lblCustomerId;


    //regex patterns
    private final String namePattern = "^[A-Za-z ]+$";
    private final String phonePattern = "^[0-9]{10}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String addressPattern = "^[A-Za-z ]+$";
    public TextField txtSearchCustomer;
    public AnchorPane ancCustomer;
    public ImageView imageView;

    CustomerModel customerModel = new CustomerModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtName.requestFocus();

        imageView.fitWidthProperty().bind(ancCustomer.widthProperty());
        imageView.fitHeightProperty().bind(ancCustomer.heightProperty());

        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colRegisteredTime.setCellValueFactory(new PropertyValueFactory<>("registeredTime"));

        try {
            loadNextId();
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }


    private void loadTableData() throws SQLException {

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
            lblCustomerId.setText(customerModel.getNextCustomerId());


            btnSave.setDisable(false);


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

        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidPhone = phone.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidAddress = address.matches(addressPattern);

        CustomerDto customerDTO = new CustomerDto(
                customerId,
                name,
                phone,
                email,
                address,
                null
        );

        if (isValidName && isValidPhone && isValidEmail && isValidAddress) {
            try {
                boolean isSaved = customerModel.saveCustomer(customerDTO);

                if (isSaved) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully.").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to save customer.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to save customer.").show();
            }
        }

    }

    private void loadNextId() throws SQLException {
        String nextId = customerModel.getNextCustomerId();
        lblCustomerId.setText(nextId);
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException {

        boolean isValidName = txtName.getText().matches(namePattern);
        boolean isValidPhone = txtPhone.getText().matches(phonePattern);
        boolean isValidEmail = txtEmail.getText().matches(emailPattern);
        boolean isValidAddress = txtAddress.getText().matches(addressPattern);

        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        if (isValidName && isValidPhone && isValidEmail && isValidAddress) {
            CustomerDto customerDto = new CustomerDto(customerId,name,phone,email,address,null);
            customerModel.updateCustomer(customerDto);
            resetPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully.").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid details.").show();
        }

    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this customer?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() != ButtonType.YES) {
            return;
        }
        customerModel.deleteCustomer(lblCustomerId.getText() );
        resetPage();
        new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully.").show();
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException {
        try {
            CustomerDto customerDto = customerModel.searchCustomer(txtSearchCustomer.getText());
            txtName.setText(customerDto.getCustomerName());
            txtPhone.setText(customerDto.getMobileNumber());
            txtEmail.setText(customerDto.getEmail());
            txtAddress.setText(customerDto.getCustomerAddress());
            lblCustomerId.setText(customerDto.getCustomerId());
            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            txtSearchCustomer.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Customer not found.").show();
            btnSave.setDisable(false);
            btnDelete.setDisable(true);
        }
    }

    public void resetCustomerPageOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickTable(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        lblCustomerId.setText(selectedItem.getCustomerId());
        txtName.setText(selectedItem.getCustomerName());
        txtPhone.setText(selectedItem.getCustomerPhone());
        txtEmail.setText(selectedItem.getCustomerEmail());
        txtAddress.setText(selectedItem.getCustomerAddress());
        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    public void sendEmailOnAction(ActionEvent actionEvent) throws IOException {
        CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a customer.").show();
            return;
        }

        String email = selectedCustomer.getCustomerEmail();
        if (!email.matches(emailPattern)) {
            new Alert(Alert.AlertType.ERROR, "Selected customer has an invalid email.").show();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SendMail.fxml"));
        Parent root = loader.load();

        SendMailPageController sendMailController = loader.getController();
        sendMailController.setEmail(email);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Send Email");

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initOwner(((Stage) tblCustomer.getScene().getWindow()));

        stage.showAndWait();
    }
    }

