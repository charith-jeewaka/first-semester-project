package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate currentDate = LocalDate.now();
        lblOrderDate.setText(currentDate.toString());

    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }

    public void resetOrderOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }


}
