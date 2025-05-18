package lk.ijse.florist_pos.final_project.dto;

import lk.ijse.florist_pos.final_project.enums.ItemType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrdersDto {
    private String orderId;
    private String customerId;
    private ItemType itemType ;// Can be null or "ANON" for anonymous
    private String paymentType;
    private String qty;
    private String orderTime;
    private double totalAmount;
}

//"order_id VARCHAR(10) PRIMARY KEY,
//customer_id VARCHAR(10) NULL,   -- Allow null for anonymous orders
//ordered_flower_id VARCHAR(10)NULL,
//ordered_plant_id VARCHAR(10)NULL,
//payment_type VARCHAR(10),
//qty VARCHAR(20),
//order_time "
