package lk.ijse.florist_pos.final_project.dto;

public class Orders {
    private int orderId;
    private int orderDate;
    private int customerId;
    private double orderAmount;
    private String paymentType;

    public Orders() {
    }
    public Orders(int orderId, int orderDate, int customerId, double orderAmount, String paymentType) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.orderAmount = orderAmount;
        this.paymentType = paymentType;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(int orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return Orders.class.getSimpleName() + " [orderId=" + orderId + ", orderDate=" + orderDate
                + ", customerId=" + customerId + ", orderAmount=" + orderAmount + ", paymentType=" + paymentType
                + "]";
    }

}
