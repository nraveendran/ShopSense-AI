package spring.ai.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.Date;
import java.util.List;

public class StoreReceiptDTO {
    @JsonPropertyDescription("Store Name as it appears in the image")
    private String storeName;
    @JsonPropertyDescription("Extract a generic Store name which is easier to lookup " +
            "from the Store Name. Here are some examples " +
            "Store Name is Target #3523, Generic Store name is Target" +
            "Store Name is Walmart Richland Hills, Generic Store name is Walmart" +
            "Store Name is Costco LTD, Generic Store Name is Costco")
    private String genericStoreName;
    @JsonPropertyDescription("Store Address as it appears in the image")
    private String storeAddress;
    private String storeCategory;
    private Date purchaseDate;
    private List<ReceiptItemDTO> items;
    private double total;

    // Constructors
    public StoreReceiptDTO(String storeName, String genericStoreName, String storeAddress, Date purchaseDate,
                           List<ReceiptItemDTO> items, double total, String storeCategory) {
        this.storeName = storeName;
        this.genericStoreName = genericStoreName;
        this.storeAddress = storeAddress;
        this.items = items;
        this.total = total;
        this.purchaseDate = purchaseDate;
        this.storeCategory = storeCategory;
    }

    @Override
    public String toString() {
        return "StoreReceiptDTO{" +
                "total=" + total +
                ", items=" + items +
                ", purchaseDate=" + purchaseDate +
                ", storeCategory='" + storeCategory + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", genericStoreName='" + genericStoreName + '\'' +
                ", storeName='" + storeName + '\'' +
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

    public String getGenericStoreName() {
        return genericStoreName;
    }

    public void setGenericStoreName(String genericStoreName) {
        this.genericStoreName = genericStoreName;
    }
}
