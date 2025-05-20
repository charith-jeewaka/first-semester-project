package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItemDto {
    private String orderItemName;
    private double unitPrice;
    private int quantity;
}
