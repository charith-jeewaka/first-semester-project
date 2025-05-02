package lk.ijse.florist_pos.final_project.dto.Tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PlantTM {
    private String plantId;
    private String plantName;
    private String plantGrowthStage;
    private String plantHight;
    private String plantPrice;
    private String plantCategory;
    private String plantAvailableQty;
}
