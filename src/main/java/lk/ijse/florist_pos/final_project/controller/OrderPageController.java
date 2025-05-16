package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.florist_pos.final_project.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    public JFXButton btnPlaceOrder;
    public JFXButton btnReset;
    public JFXButton btnAddToCart;

    public TableColumn colItemId;
    public TableColumn colAction;
    public TableColumn colTotal;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colItemName;
    public TableView tblCart;

    public Label lblTotalBill;
    public Label lblItemName;
    public Label lblCustomerName;
    public Label lblItemPrice;
    public Label lblQtyOnHand;
    public Label lblOrderDate;
    public Label lblOrderId;

    public TextField txtAddToCartQty;
    public TextField txtItemId;
    public TextField txtCustomerMobile;

    OrderModel orderModel = new OrderModel();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate currentDate = LocalDate.now();
        lblOrderDate.setText(currentDate.toString());
        txtItemId.setOnAction(e -> txtAddToCartQty.requestFocus());
        txtAddToCartQty.setOnAction(e -> btnAddToCart.fire());
        try {
            resetPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }

    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }

    public void resetOrderOnAction(ActionEvent actionEvent) throws SQLException {
        resetPage();
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }

    public void loadNextOrderId() throws SQLException {
        String nextOid = orderModel.getNextCustomerId();
        lblOrderId.setText(nextOid);
    }


    public void resetPage() throws SQLException {

        try {
            loadNextOrderId();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong Loading id.").show();
        }
        txtAddToCartQty.clear();
        txtCustomerMobile.clear();
        txtItemId.clear();
        lblItemName.setText("");
        lblItemPrice.setText("");
        lblQtyOnHand.setText("");
        lblTotalBill.setText("");
        lblCustomerName.setText("");
    }


}
