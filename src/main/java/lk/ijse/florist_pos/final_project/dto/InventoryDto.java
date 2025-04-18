package lk.ijse.florist_pos.final_project.dto;

public class InventoryDto {
    private int orderId;
    private int supplierId;
    private int stockStatus;
    private int restockDate;

    public InventoryDto() {
    }
    public InventoryDto(int orderId, int supplierId, int stockStatus, int restockDate) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.stockStatus = stockStatus;
        this.restockDate = restockDate;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getStockStatus() {
        return stockStatus;
    }
    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }

    public int getRestockDate() {
        return restockDate;
    }
    public void setRestockDate(int restockDate) {
        this.restockDate = restockDate;
    }

    @Override
    public String toString() {
        return InventoryDto.class.getSimpleName() + " [orderId=" + orderId + ", supplierId=" + supplierId
                + ", stockStatus=" + stockStatus + ", restockDate=" + restockDate + "]";

    }

}
