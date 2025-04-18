package lk.ijse.florist_pos.final_project.dto;

public class ItemWasteDto {
    private int flowerId;
    private int plantId;
    private int wastedFlowerQty;
    private int wastedPlantQty;
    private String reason;
    public ItemWasteDto() {
    }

    public ItemWasteDto(int flowerId,int plantId,int wastedFlowerQty,int wastedPlantQty,String reason) {
        this.flowerId = flowerId;
        this.plantId = plantId;
        this.wastedFlowerQty = wastedFlowerQty;
        this.wastedPlantQty = wastedPlantQty;
        this.reason = reason;
    }

    public int getFlowerId() {
        return flowerId;
    }
    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public int getPlantId() {
        return plantId;
    }
    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getWastedFlowerQty() {
        return wastedFlowerQty;
    }
    public void setWastedFlowerQty(int wastedFlowerQty) {
        this.wastedFlowerQty = wastedFlowerQty;
    }

    public int getWastedPlantQty() {
        return wastedPlantQty;
    }
    public void setWastedPlantQty(int wastedPlantQty) {
        this.wastedPlantQty = wastedPlantQty;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return ItemWasteDto.class.getSimpleName() + " [flowerId=" + flowerId + ", plantId="
                + plantId + ", wastedFlowerQty=" + wastedFlowerQty + ", wastedPlantQty="
                + wastedPlantQty + ", reason=" + reason+ "]";

    }
}
