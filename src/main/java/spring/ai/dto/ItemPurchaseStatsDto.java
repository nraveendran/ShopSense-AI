package spring.ai.dto;

public interface ItemPurchaseStatsDto {
    String getItemName();
    Long getTimesPurchased();
    String getGenericStoreName();       // New
    String getStoreCategory();   // New
}

