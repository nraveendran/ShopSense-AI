package spring.ai.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class ReceiptItemDTO {


    @JsonPropertyDescription("Item Name")
    private String itemName;
    @JsonPropertyDescription("itemCategory e.g: Dairy,Produce,Frozen Foods,Snacks,Beverages,Cleaning Supplies," +
            "Paper Products,Laundry Care,Personal Care,Oral Care,Pet Food,Baby Products,Swimwear," +
            "Clothing,Footwear,Office Supplies,Electronics,Home Essentials,Health & Wellness,Toiletries, etc.")
    private String itemCategory;
    @JsonPropertyDescription(". e.g: Whole Milk,Cheddar Cheese,Greek Yogurt,Apples,Bananas," +
            "Baby Carrots,Ground Beef,Chicken Breast,Frozen Peas,Frozen Pizza,Potato Chips,Granola Bars," +
            "Bottled Water,Orange Juice,Laundry Detergent,Paper Towels,Toilet Paper,Dish Soap," +
            "Shampoo,Toothpaste,Toothbrush,Cat Food,Dog Treats,Diapers,Baby Wipes,One-Piece Swimsuit," +
            "Swim Trunks,Men's T-Shirt,Women's Leggings,Sneakers,Ballpoint Pens,Notebook," +
            "Printer Paper,USB Charger,AA Batteries,LED Light Bulbs,Multi-Surface Cleaner," +
            "Body Wash,Pain Reliever,Deodorant, etc.")
    private String genericItemName;
    
    private int quantity;
    private double subtotal;


    // Constructor
    public ReceiptItemDTO(String itemName, int quantity, double subtotal, String itemCategory, String genericItemName) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.itemCategory=itemCategory;
        this.genericItemName=genericItemName;
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

    public String getGenericItemName() {
        return genericItemName;
    }

    public void setGenericItemName(String genericItemName) {
        this.genericItemName = genericItemName;
    }

    @Override
    public String toString() {
        return "ReceiptItemDTO{" +
                "itemName='" + itemName + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", genericItemName='" + genericItemName + '\'' +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}

