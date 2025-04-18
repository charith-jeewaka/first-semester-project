package lk.ijse.florist_pos.final_project.dto;

public class Payment {
    private int paymentId;
    private int orderId;
    private int customerId;
    private int userId;
    private double totalAmount;
    private String paymentMethod;
    private String paymentDate;

    public Payment() {
    }

    public Payment(int paymentId, int orderId, int customerId, int userId, double totalAmount,String paymentMethod, String paymentDate) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return Payment.class.getSimpleName() + " [paymentId=" + paymentId + ", orderId=" + orderId
                + ", customerId=" + customerId + ", userId=" + userId + ", totalAmount=" + totalAmount
                + ", paymentMethod=" + paymentMethod + ", paymentDate=" + paymentDate + "]";
    }


}
