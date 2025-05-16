package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class FlowerWasteDto {
 private String wastedId;
 private String flowerId;
 private String flowerName;
 private String flowerQty;
 private String reason;
 private String wasteDate;
}
