package spring.ai.dto;

public interface ItemPurchaseStatsDto {
    String getItemName();
    Long getTimesPurchased();
    String getStoreName();       // New
    String getStoreCategory();   // New
}

