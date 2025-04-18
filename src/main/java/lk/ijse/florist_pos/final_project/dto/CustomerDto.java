package lk.ijse.florist_pos.final_project.dto;

public class CustomerDto {
    private int customerId;
    private String customerName;
    private String mobileNumber;
    private String email;
    private String registeredTime;
    private String customerAddress;

    public CustomerDto() {
    }

    public CustomerDto(int customerId, String customerName, String mobileNumber, String email,
                       String registeredTime, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.registeredTime = registeredTime;
        this.customerAddress = customerAddress;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }
    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return CustomerDto.class.getSimpleName() + " [customerId=" + customerId + ", customerName=" + customerName
                + ", mobileNumber=" + mobileNumber + ", email=" + email + ", registeredTime=" + registeredTime
                + ", customerAddress=" + customerAddress + "]";

    }





}
