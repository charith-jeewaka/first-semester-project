package lk.ijse.florist_pos.final_project.dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTm {
    private String itemId;
    private String itemName;
    private int cartQty;
    private double unitPrice;
    private double total;
    private String customerName;
    private JFXButton btnRemove;
}
