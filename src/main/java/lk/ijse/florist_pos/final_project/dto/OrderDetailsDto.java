package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailsDto {

    private String orderId;
    private String customerName;
    private String itemName;
    private String itemId;
    private String paymentType;
    private String itemQty;
    private String totalAmount;
    private String handleBy;

    private String totalBill;
}

