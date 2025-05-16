package lk.ijse.florist_pos.final_project.dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class FlowerWasteTm {
    private String wastedId;
    private String flowerId;
    private String flowerName;
    private String flowerQty;
    private String reason;
    private String wasteDate;
}
