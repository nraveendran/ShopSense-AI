package spring.ai.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "store_receipt", schema = "receiptschema")
public class StoreReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private String genericStoreName;
    private String storeAddress;
    private String storeCategory;
    private Date purchaseDate;

    private double total;

    @OneToMany(mappedBy = "storeReceipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItem> receiptItemEntities;

    public StoreReceipt() {

    }

    public void setReceiptItemEntities(List<ReceiptItem> receiptItemEntities) {
        this.receiptItemEntities = receiptItemEntities;
        if (receiptItemEntities != null) {
            receiptItemEntities.forEach(receiptItem ->
                    receiptItem.setReceipt(this));
        }
    }

    // Constructors
    public StoreReceipt(String storeName, String storeAddress, Date purchaseDate,
                        List<ReceiptItem> receiptItemEntities, double total, String storeCategory, String genericStoreName) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.receiptItemEntities = receiptItemEntities;
        this.total = total;
        this.purchaseDate = purchaseDate;
        this.storeCategory = storeCategory;
        this.genericStoreName=genericStoreName;
    }

    public String getGenericStoreName() {
        return genericStoreName;
    }

    public void setGenericStoreName(String genericStoreName) {
        this.genericStoreName = genericStoreName;
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

    @Override
    public String toString() {
        return "StoreReceipt{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", genericStoreName='" + genericStoreName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storeCategory='" + storeCategory + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", total=" + total +
                ", receiptItemEntities=" + receiptItemEntities +
                '}';
    }
}
