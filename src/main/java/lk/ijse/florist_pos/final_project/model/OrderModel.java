package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.dto.OrderDetailsDto;
import lk.ijse.florist_pos.final_project.dto.OrderItemDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderModel {
    private final FlowerModel flowerModel = new FlowerModel();

    public String getNextOrderId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");
        char tableCharacter = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(tableCharacter + "%03d", nextIdNumber);
        }
        return tableCharacter + "001";
    }

    public String getCustomerName(String number) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM customer WHERE phone_number = ?", number);
        if (rs.next()) {
            return rs.getString("name");
        }
        return null;
    }

    public OrderItemDto getItemDetails(String code) throws SQLException {
        ResultSet rs;
        if (code.startsWith("P")) {
            rs = CrudUtil.execute("SELECT plant_name AS item_name, plant_available_qty AS qty, plant_price AS price FROM plant WHERE plant_id = ?", code);
        } else if (code.startsWith("F")) {
            rs = CrudUtil.execute("SELECT flower_name AS item_name, flower_available_qty AS qty, flower_price AS price FROM flower WHERE flower_id = ?", code);
        } else {
            throw new IllegalArgumentException("Invalid code format: " + code);
        }

        if (rs.next()) {
            String name = rs.getString("item_name");
            int qty = rs.getInt("qty");
            double unitPrice = rs.getDouble("price");
            return new OrderItemDto(name, unitPrice, qty);
        }
        return null;
    }

    public boolean placeOrder(List<OrderDetailsDto> orderDetailsList) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean isSuccess = false;

        try {
            connection.setAutoCommit(false); // Start transaction

            // 1. Insert orders
            String orderSql = "INSERT INTO orders (order_id, customer_name, item_name, item_id, payment_type, item_qty, total_amount, handled_by, total_bill, order_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement orderPstm = connection.prepareStatement(orderSql);

            for (OrderDetailsDto dto : orderDetailsList) {
                orderPstm.setString(1, dto.getOrderId());
                orderPstm.setString(2, dto.getCustomerName());
                orderPstm.setString(3, dto.getItemName());
                orderPstm.setString(4, dto.getItemId());
                orderPstm.setString(5, dto.getPaymentType());
                orderPstm.setString(6, dto.getItemQty());
                orderPstm.setString(7, dto.getTotalAmount());
                orderPstm.setString(8, dto.getHandleBy());
                orderPstm.setString(9, dto.getTotalBill());
                orderPstm.setString(10, dto.getOrderDate());

                orderPstm.addBatch();

                // 2. Reduce item quantity
                String itemId = dto.getItemId();
                int qtyToReduce = Integer.parseInt(dto.getItemQty());

                if (itemId.startsWith("P")) {
                    // It's a plant
                    if (!PlantModel.reduceQty(itemId, qtyToReduce, connection)) {
                        connection.rollback();
                        return false;
                    }
                } else if (itemId.startsWith("F")) {
                    // It's a flower
                    if (!FlowerModel.reduceQty(itemId, qtyToReduce, connection)) {
                        connection.rollback();
                        return false;
                    }
                }
            }

            int[] orderResult = orderPstm.executeBatch();

            connection.commit();
            isSuccess = true;

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }

        return isSuccess;
    }



}
