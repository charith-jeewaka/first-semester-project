package lk.ijse.florist_pos.final_project.dto;

public class OrderItemDetails {
    private int orderItemId;
    private int orderId;

    public OrderItemDetails(){
    }
    public OrderItemDetails(int orderItemId, int orderId) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
    }
    public int getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    @Override
    public String toString() {
        return OrderItemDetails.class.getSimpleName() + " [orderItemId=" + orderItemId + ", orderId="
                + orderId + "]";
    }
}
