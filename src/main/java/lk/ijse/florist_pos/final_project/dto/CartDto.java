package lk.ijse.florist_pos.final_project.dto;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class CartDto {
    private String itemId;
    private String itemName;
    private String customerName;
    private double cartQty;
    private double unitPrice;
    private double total;
    private JFXButton remove;
}
