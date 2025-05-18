package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.florist_pos.final_project.dto.OrderItemDto;
import lk.ijse.florist_pos.final_project.dto.Tm.CartTm;
import lk.ijse.florist_pos.final_project.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {
    public Button btnSearchItem;
    public JFXButton btnPlaceOrder;
    public JFXButton btnReset;
    public JFXButton btnAddToCart;
    public Button btnSearchCustomer;

    public TableColumn colCustomerName;
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

    private ObservableList<CartTm> cartTmList = FXCollections.observableArrayList();


    OrderModel orderModel = new OrderModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
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

    public void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        String itemId = txtItemId.getText();
        String itemName = lblItemName.getText();
        String unitPriceText = lblItemPrice.getText();
        String qtyText = txtAddToCartQty.getText();
        String qtyOnHandText = lblQtyOnHand.getText();
        String customerName = lblCustomerName.getText();

        // Validation
        if (itemId.isEmpty() || qtyText.isEmpty() || unitPriceText.isEmpty() || itemName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        int qty = Integer.parseInt(qtyText);
        double unitPrice = Double.parseDouble(unitPriceText); // Changed to double
        int qtyOnHand = Integer.parseInt(qtyOnHandText);

        if (qty > qtyOnHand) {
            new Alert(Alert.AlertType.ERROR, "Quantity is greater than available stock").show();
            return;
        }

        double totalAmount = qty * unitPrice;

        // Check if item already exists in the cart
        CartTm existingItem = null;
        for (CartTm item : cartTmList) {
            if (item.getItemId().equals(itemId)) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            existingItem.setCartQty(existingItem.getCartQty() + qty);
            existingItem.setTotal(existingItem.getCartQty() * unitPrice); // Changed to double
        } else {
            JFXButton btnRemove = new JFXButton("Remove");
            CartTm cartTm = new CartTm(itemId, itemName, qty, unitPrice, totalAmount, customerName, btnRemove);

            // Add remove button functionality
            btnRemove.setOnAction(e -> {
                cartTmList.remove(cartTm);
                tblCart.refresh();
            });

            cartTmList.add(cartTm);
        }

        tblCart.setItems(cartTmList);
        tblCart.refresh();

        int updatedQtyOnHand = qtyOnHand - qty;
        lblQtyOnHand.setText(String.valueOf(updatedQtyOnHand));

        // Clear inputs
        txtItemId.clear();
        txtAddToCartQty.clear();
        lblItemPrice.setText("");
        lblItemName.setText("");
        lblQtyOnHand.setText("");
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


    public void searchCustomerOnAction(ActionEvent actionEvent) {
        try {
            String customerMobile = txtCustomerMobile.getText();
            String customerName = orderModel.getCustomerName(customerMobile);

            if (customerName != null) {
                lblCustomerName.setText(customerName);
            } else {
                lblCustomerName.setText("Unknown");
                new Alert(Alert.AlertType.INFORMATION, "Customer not found").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database error occurred").show();
        }
    }

    public void SearchItemOnAction(ActionEvent actionEvent) {
        try {
            String itemId = txtItemId.getText();
            OrderItemDto item = orderModel.getItemDetails(itemId);

            if (item != null) {
                lblItemName.setText(item.getOrderItemName());
                lblQtyOnHand.setText(String.valueOf(item.getQuantity()));
                lblItemPrice.setText(String.valueOf(item.getUnitPrice()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "No item found for ID: " + itemId).show();
            }

        } catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database error occurred").show();
        }


    }
}
