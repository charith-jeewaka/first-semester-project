package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.dto.OrderItemDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public String getNextCustomerId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");
        char tableCharacter = 'O'; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(1); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
            return nextIdString;
        }
        // No data recode in table so return initial primary key
        return tableCharacter + "001";
    }

    public String getCustomerName(String number) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM customer WHERE phone_number = ?", number);
        if (rs.next()) {
            return rs.getString("name");
        }
        return null; // or throw exception if not found
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





}
