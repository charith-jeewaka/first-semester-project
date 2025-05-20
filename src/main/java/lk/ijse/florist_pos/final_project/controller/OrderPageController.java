package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.florist_pos.final_project.dto.OrderDetailsDto;
import lk.ijse.florist_pos.final_project.dto.OrderItemDto;
import lk.ijse.florist_pos.final_project.dto.Tm.CartTm;
import lk.ijse.florist_pos.final_project.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    public Button btnSearchItem;
    public JFXButton btnPlaceOrder;
    public JFXButton btnReset;
    public JFXButton btnAddToCart;
    public Button btnSearchCustomer;

    public TableColumn<CartTm, String> colItemId;
    public TableColumn<CartTm, String> colItemName;
    public TableColumn<CartTm, Integer> colQuantity;
    public TableColumn<CartTm, Double> colPrice;
    public TableColumn<CartTm, Double> colTotal;
    public TableColumn<CartTm, String> colCustomerName;
    public TableColumn<CartTm, Button> colAction;
    public TableView<CartTm> tblCart;

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

    private final ObservableList<CartTm> cartTmList = FXCollections.observableArrayList();
    private final OrderModel orderModel = new OrderModel();
    public JFXRadioButton rbtnCard;
    public JFXRadioButton rbtnCash;
    public ToggleGroup paymentType = new ToggleGroup();
    private DashboardController dashboardController = new DashboardController();





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rbtnCard.setToggleGroup(paymentType);
        rbtnCash.setToggleGroup(paymentType);
        rbtnCash.setSelected(true);
        setCellValueFactory();
        lblOrderDate.setText(LocalDate.now().toString());
        txtCustomerMobile.requestFocus();

        txtCustomerMobile.setOnAction(e -> {
            String customerMobile = txtCustomerMobile.getText();
            try {
                String customerName = orderModel.getCustomerName(customerMobile);
                if (customerName != null) {
                    lblCustomerName.setText(customerName);
                    txtItemId.requestFocus();
                } else {
                    lblCustomerName.setText("Unknown");
                    new Alert(Alert.AlertType.INFORMATION, "Customer not found").show();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database error occurred").show();
            }
        });

        txtItemId.setOnAction(e -> txtAddToCartQty.requestFocus());
        txtAddToCartQty.setOnAction(e -> btnAddToCart.fire());

        try {
            resetPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void setCellValueFactory() {
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

        if (itemId.isEmpty() || qtyText.isEmpty() || unitPriceText.isEmpty() || itemName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        int qty = Integer.parseInt(qtyText);
        double unitPrice = Double.parseDouble(unitPriceText);
        int qtyOnHand = Integer.parseInt(qtyOnHandText);

        if (qty > qtyOnHand) {
            new Alert(Alert.AlertType.ERROR, "Quantity is greater than available stock").show();
            return;
        }

        double totalAmount = qty * unitPrice;

        CartTm existingItem = cartTmList.stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setCartQty(existingItem.getCartQty() + qty);
            existingItem.setTotal(existingItem.getCartQty() * unitPrice);
        } else {
            JFXButton btnRemove = new JFXButton("Remove");
            CartTm cartTm = new CartTm(itemId, itemName, qty, unitPrice, totalAmount, customerName, btnRemove);

            btnRemove.setOnAction(e -> {
                cartTmList.remove(cartTm);
                tblCart.refresh();
                lblTotalBill.setText(getTotalBill());

                // Re-enable customer input if cart becomes empty
                if (cartTmList.isEmpty()) {
                    txtCustomerMobile.setDisable(false);
                    lblCustomerName.setDisable(false);
                }
            });

            cartTmList.add(cartTm);
        }

        tblCart.setItems(cartTmList);
        tblCart.refresh();
        lblTotalBill.setText(getTotalBill());

        int updatedQtyOnHand = qtyOnHand - qty;
        lblQtyOnHand.setText(String.valueOf(updatedQtyOnHand));

        txtItemId.clear();
        txtAddToCartQty.clear();
        lblItemPrice.setText("");
        lblItemName.setText("");
        lblQtyOnHand.setText("");

        // Disable customer input after item is added
        txtCustomerMobile.setDisable(true);
        lblCustomerName.setDisable(true);
    }

    public void resetOrderOnAction(ActionEvent actionEvent) throws SQLException {
        resetPage();
        txtCustomerMobile.setDisable(false);
        lblCustomerName.setDisable(false);
        lblCustomerName.setText("");
        txtCustomerMobile.clear();
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException {
        if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add items to cart").show();
            return;
        }

        String orderId = lblOrderId.getText();
        String selectedPaymentType = ((JFXRadioButton) paymentType.getSelectedToggle()).getText();
        String handledBy = "admin";
        String orderDate = lblOrderDate.getText();
        String totalBill = lblTotalBill.getText();

        ObservableList<CartTm> cartList = tblCart.getItems();
        List<OrderDetailsDto> orderDetailsList = new ArrayList<>();

        for (CartTm cartItem : cartList) {
            OrderDetailsDto dto = new OrderDetailsDto(
                    orderId,
                    cartItem.getCustomerName(),
                    cartItem.getItemName(),
                    cartItem.getItemId(),
                    selectedPaymentType,
                    String.valueOf(cartItem.getCartQty()),
                    String.valueOf(cartItem.getTotal()),
                    handledBy,
                    orderDate,
                    totalBill
            );
            orderDetailsList.add(dto);
        }

        boolean isPlaced = orderModel.placeOrder(orderDetailsList);

        if (isPlaced) {
            new Alert(Alert.AlertType.INFORMATION, "Order placed successfully").show();
            resetPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong while placing the order.").show();
        }
    }



    private void loadNextOrderId() throws SQLException {
        String nextOid = orderModel.getNextOrderId();
        lblOrderId.setText(nextOid);
    }

    public void resetPage() throws SQLException {
        loadNextOrderId();
        txtAddToCartQty.clear();
        txtCustomerMobile.clear();
        txtItemId.clear();
        lblItemName.setText("");
        lblItemPrice.setText("");
        lblQtyOnHand.setText("");
        lblTotalBill.setText("");
        cartTmList.clear();
        tblCart.setItems(cartTmList);
        tblCart.refresh();
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

    public String getTotalBill() {
        double total = tblCart.getItems()
                .stream()
                .mapToDouble(CartTm::getTotal)
                .sum();
        return String.valueOf(total);
    }
}
