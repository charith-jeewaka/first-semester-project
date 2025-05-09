package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PlantDto {
    private String plantId;
    private String plantName;
    private String plantHeight;
    private String plantPrice;
    private String plantVarient;
    private String plantAvailableQty;
    private String plantRegisteredTime;
//    public PlantDto() {
//    }

//    public PlantDto(int plantId,String plantName,String plantGrowthStage,String plantHeight,int plantPrice,
//                    String plantCategory,String plantAvailableQty) {
//        this.plantId = plantId;
//        this.plantName = plantName;
//        this.plantGrowthStage = plantGrowthStage;
//        this.plantHeight = plantHeight;
//        this.plantPrice = plantPrice;
//        this.plantCategory = plantCategory;
//        this.plantAvailableQty = plantAvailableQty;
//    }
//
//    public int getPlantId() {
//        return plantId;
//    }
//    public void setPlantId(int plantId) {
//        this.plantId = plantId;
//    }
//
//    public String getPlantName() {
//        return plantName;
//    }
//    public void setPlantName(String plantName) {
//        this.plantName = plantName;
//    }
//
//    public String getPlantGrowthStage() {
//        return plantGrowthStage;
//    }
//    public void setPlantGrowthStage(String plantGrowthStage) {
//        this.plantGrowthStage = plantGrowthStage;
//    }
//
//    public String getPlantHeight() {
//        return plantHeight;
//    }
//    public void setPlantHeight(String plantHeight) {
//        this.plantHeight = plantHeight;
//    }
//
//    public int getPlantPrice() {
//        return plantPrice;
//    }
//    public void setPlantPrice(int plantPrice) {
//        this.plantPrice = plantPrice;
//    }
//
//    public String getPlantCategory() {
//        return plantCategory;
//    }
//    public void setPlantCategory(String plantCategory) {
//        this.plantCategory = plantCategory;
//    }
//
//    public String getPlantAvailableQty() {
//        return plantAvailableQty;
//    }
//    public void setPlantAvailableQty(String plantAvailableQty) {
//        this.plantAvailableQty = plantAvailableQty;
//    }
//
//    @Override
//    public String toString() {
//        return PlantDto.class.getSimpleName() + " [plantId=" + plantId + ", plantName=" + plantName
//                + " [growthStage=" + plantGrowthStage + " [plantHeight=" + plantHeight  + " [plantPrice=" + plantPrice
//                + " [plantCategory=" + plantCategory  + " [plantAvailableQty=" + plantAvailableQty + "]";
//
//    }

}
