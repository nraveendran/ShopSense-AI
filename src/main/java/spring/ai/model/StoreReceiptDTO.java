package spring.ai.model;

import java.util.Date;
import java.util.List;

public class StoreReceiptDTO {
    private String storeName;
    private String storeAddress;
    private String storeCategory;
    private Date purchaseDate;
    private List<ReceiptItemDTO> items;
    private double total;

    // Constructors
    public StoreReceiptDTO(String storeName, String storeAddress, Date purchaseDate,
                           List<ReceiptItemDTO> items, double total, String storeCategory) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.items = items;
        this.total = total;
        this.purchaseDate = purchaseDate;
        this.storeCategory = storeCategory;
    }

    @Override
    public String toString() {
        return "StoreReceipt{" +
                "storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storeCategory='" + storeCategory + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", items=" + items +
                ", total=" + total +
                '}';
    }

    public String getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory;
    }

    // Getters and Setters
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public List<ReceiptItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItemDTO> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
