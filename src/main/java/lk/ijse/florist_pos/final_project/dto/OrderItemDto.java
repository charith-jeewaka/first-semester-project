package lk.ijse.florist_pos.final_project.dto;

public class OrderItemDto {
    private int orderId;
    private int orderItemId;
    private int orderQty;
    private int unitPrice;

    public OrderItemDto() {
    }

    public OrderItemDto(int orderId, int orderItemId, int orderQty, int unitPrice) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderQty() {
        return orderQty;
    }
    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return OrderItemDto.class.getSimpleName() + " [orderId=" + orderId + ", orderItemId=" + orderItemId
                + ", orderQty=" + orderQty + ", unitPrice=" + unitPrice + "]";
    }
}
