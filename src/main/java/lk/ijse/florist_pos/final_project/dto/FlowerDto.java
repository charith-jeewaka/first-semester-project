package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class FlowerDto {
    private String flowerId;
    private String flowerName;
    private String flowerCategory;
    private String flowerPrice;
    private String flowerStatus;
    private String flowerAvailableQty;
    private String flowerEnteredTime;

//    public FlowerDto() {
//    }
//
//    public FlowerDto(int flowerId,String flowerName,int flowerPrice,String flowerColor,String flowerCategory,
//                     String flowerStatus,String flowerAvailableQty) {
//        this.flowerId = flowerId;
//        this.flowerName = flowerName;
//        this.flowerPrice = flowerPrice;
//        this.flowerColor = flowerColor;
//        this.flowerCategory = flowerCategory;
//        this.flowerStatus = flowerStatus;
//        this.flowerAvailableQty = flowerAvailableQty;
//    }
//    public int getFlowerId() {
//        return flowerId;
//    }
//    public void setFlowerId(int flowerId) {
//        this.flowerId = flowerId;
//    }
//
//    public String getFlowerName() {
//        return flowerName;
//    }
//    public void setFlowerName(String flowerName) {
//        this.flowerName = flowerName;
//    }
//
//    public int getFlowerPrice(){
//        return flowerPrice;
//    }
//    public void setFlowerPrice(int flowerPrice){
//        this.flowerPrice = flowerPrice;
//    }
//
//    public String getFlowerColor() {
//        return flowerColor;
//    }
//    public void setFlowerColor(String flowerColor) {
//        this.flowerColor = flowerColor;
//    }
//
//    public String getFlowerCategory() {
//        return flowerCategory;
//    }
//    public void setFlowerCategory(String flowerCategory) {
//        this.flowerCategory = flowerCategory;
//    }
//
//    public String getFlowerStatus() {
//        return flowerStatus;
//    }
//    public void setFlowerStatus(String flowerStatus) {
//        this.flowerStatus = flowerStatus;
//    }
//
//    public String getFlowerAvailableQty() {
//        return flowerAvailableQty;
//    }
//    public void setFlowerAvailableQty(String flowerAvailableQty) {
//        this.flowerAvailableQty = flowerAvailableQty;
//    }
//
//    @Override
//    public String toString(){
//        return FlowerDto.class.getSimpleName() + " [flowerId=" + flowerId + ", flowerName=" + flowerName
//                + ", flowerPrice=" + flowerPrice + ", flowerColor=" + flowerColor
//                + ", flowerCategory=" + flowerCategory + ", flowerStatus=" + flowerStatus
//                + ", flowerAvailableQty=" + flowerAvailableQty + "]";
//    }
}
