package spring.ai.model;

public class ReceiptItemDTO {
    private String itemName;
    private String itemCategory;
    private int quantity;
    private double subtotal;


    // Constructor
    public ReceiptItemDTO(String itemName, int quantity, double subtotal, String itemCategory) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.itemCategory=itemCategory;
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

