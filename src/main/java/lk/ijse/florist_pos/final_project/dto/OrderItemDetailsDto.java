package lk.ijse.florist_pos.final_project.dto;

import lk.ijse.florist_pos.final_project.enums.ItemType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderItemDetailsDto {
    private String orderItemDetailId;
    private String orderItemId;
    private ItemType itemType;
    private String itemId;
    private String itemName;
    private double price;
}
