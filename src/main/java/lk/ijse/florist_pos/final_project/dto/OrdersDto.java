package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrdersDto {
    private String orderId;
    private String customerId; // Can be null or "ANON" for anonymous
    private String orderTime;
    private String paymentType;
    private double totalAmount;
}
