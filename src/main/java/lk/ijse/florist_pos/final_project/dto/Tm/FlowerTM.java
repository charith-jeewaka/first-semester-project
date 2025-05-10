package lk.ijse.florist_pos.final_project.dto.Tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class FlowerTM {
    private String flowerId;
    private String flowerName;
    private String flowerCategory;
    private String flowerPrice;
    private String flowerStatus;
    private String flowerAvailableQty;
    private String flowerEnteredTime;
}
