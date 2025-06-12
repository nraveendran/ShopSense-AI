package spring.ai.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "receipt_item", schema = "receiptschema")
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String itemCategory;
    private int quantity;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private StoreReceipt storeReceipt;

    // Constructor
    public ReceiptItem(String itemName, int quantity, double subtotal, String itemCategory) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.itemCategory=itemCategory;
    }

    public ReceiptItem() {

    }

    public void setReceipt(StoreReceipt storeReceipt) {
        this.storeReceipt = storeReceipt;
    }
    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "ReceiptItem{" +
                "itemName='" + itemName + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}

