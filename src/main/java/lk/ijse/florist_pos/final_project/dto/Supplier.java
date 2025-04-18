package lk.ijse.florist_pos.final_project.dto;

public class Supplier {
    private int supplierId;
    private String supplierName;
    private String product;
    private String neededProduct;
    private String totalAmount;
    private String contact;

    public Supplier() {
    }

    public Supplier(int supplierId,String supplierName, String product, String neededProduct, String totalAmount, String contact) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.product = product;
        this.neededProduct = neededProduct;
        this.totalAmount = totalAmount;
        this.contact = contact;

    }
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }

    public String getNeededProduct() {
        return neededProduct;
    }
    public void setNeededProduct(String neededProduct) {
        this.neededProduct = neededProduct;
    }

    public String getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return Supplier.class.getSimpleName() + " [supplierId=" + supplierId + ", supplierName=" + supplierName
                + ", product=" + product
                + ", neededProduct=" + neededProduct + ", totalAmount=" + totalAmount
                + ", contact=" + contact + "]";
    }
}
