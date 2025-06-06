package lk.ijse.florist_pos.final_project.model;

import javafx.scene.chart.XYChart;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.dto.OrderDetailsDto;
import lk.ijse.florist_pos.final_project.dto.OrderItemDto;
import lk.ijse.florist_pos.final_project.dto.Tm.DailySalesTM;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import javax.swing.plaf.PanelUI;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            String orderSql = "INSERT INTO orders (order_id, customer_name, item_name, item_id, payment_type, item_qty, total_amount, handled_by, total_bill) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

            // Insert order summary
            if (!insertOrderSummary()) {
                connection.rollback();
                return false;
            }

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

    public static boolean insertOrderSummary() throws SQLException {
        String sql = """
            INSERT INTO order_item_details (order_id, customer_name, total_bill, handled_by, order_date)
            SELECT 
                o1.order_id,
                o1.customer_name,
                o1.total_bill,
                o1.handled_by,
                o1.order_date
            FROM orders o1
            WHERE NOT EXISTS (
                SELECT 1 
                FROM order_item_details od 
                WHERE od.order_id = o1.order_id
            )
            AND o1.single_order_id = (
                SELECT MIN(o2.single_order_id)
                FROM orders o2
                WHERE o2.order_id = o1.order_id
            )
        """;

        return CrudUtil.execute(sql);
    }

    public static BigDecimal getTodayTotalSales() throws SQLException {
        String sql = "SELECT SUM(CAST(total_bill AS DECIMAL(10, 2))) AS today_total " +
                "FROM order_item_details " +
                "WHERE DATE(order_date) = CURDATE()";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return resultSet.getBigDecimal("today_total") != null
                    ? resultSet.getBigDecimal("today_total")
                    : BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getYesterdayTotalSales() throws SQLException {
        String qry = "SELECT SUM(CAST(total_bill AS DECIMAL(10, 2))) AS yesterday_total FROM order_item_details WHERE" +
                " DATE(order_date) = CURDATE() - INTERVAL 1 DAY";


        ResultSet resultSet = CrudUtil.execute(qry);

        if (resultSet.next()) {
            return resultSet.getBigDecimal("yesterday_total") != null
                    ? resultSet.getBigDecimal("yesterday_total")
                    : BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    public static Map<String, Double> getDailySales() throws SQLException {
        String sql = "SELECT DATE(order_date) AS orderDay, " +
                "SUM(CAST(total_amount AS DECIMAL(10,2))) AS totalSales " +
                "FROM orders GROUP BY orderDay ORDER BY orderDay";

        ResultSet rs = CrudUtil.execute(sql);

        Map<String, Double> dailySales = new LinkedHashMap<>(); // keep insertion order
        while (rs.next()) {
            String date = rs.getString("orderDay");
            double totalSales = rs.getDouble("totalSales");
            dailySales.put(date, totalSales);
        }
        return dailySales;
    }

    public static double getTodayTotalSale() throws SQLException {
        String sql = "SELECT SUM(CAST(total_bill AS DECIMAL(10,2))) AS today_sales FROM order_item_details WHERE DATE(order_date) = CURDATE()";

        ResultSet rs = CrudUtil.execute(sql);
        double todaySale = 0;
        if (rs.next()) {
            todaySale = rs.getInt("today_sales");
        }
        return todaySale;
    }

    public static double getYesterdayTotalSale() throws SQLException {
        String sql = "SELECT SUM(CAST(total_bill AS DECIMAL(10,2))) AS yesterday_sales FROM order_item_details WHERE DATE(order_date) = CURDATE() - INTERVAL 1 DAY";


        ResultSet rs = CrudUtil.execute(sql);
        double yesterdaySale = 0;
        if (rs.next()) {
            yesterdaySale = rs.getInt("yesterday_sales");
        }
        return yesterdaySale;
    }


}
