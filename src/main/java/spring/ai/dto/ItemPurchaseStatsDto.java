package spring.ai.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public interface ItemPurchaseStatsDto {

    @JsonPropertyDescription(". e.g: Whole Milk,Cheddar Cheese,Greek Yogurt,Apples,Bananas," +
            "Baby Carrots,Ground Beef,Chicken Breast,Frozen Peas,Frozen Pizza,Potato Chips,Granola Bars," +
            "Bottled Water,Orange Juice,Laundry Detergent,Paper Towels,Toilet Paper,Dish Soap," +
            "Shampoo,Toothpaste,Toothbrush,Cat Food,Dog Treats,Diapers,Baby Wipes,One-Piece Swimsuit," +
            "Swim Trunks,Men's T-Shirt,Women's Leggings,Sneakers,Ballpoint Pens,Notebook," +
            "Printer Paper,USB Charger,AA Batteries,LED Light Bulbs,Multi-Surface Cleaner," +
            "Body Wash,Pain Reliever,Deodorant, etc.")
    String getGenericItemName();
    Long getTimesPurchased();
    String getGenericStoreName();       // New
    String getStoreCategory();   // New
    Double getTotalSpent();
    Double getAveragePrice();
}

