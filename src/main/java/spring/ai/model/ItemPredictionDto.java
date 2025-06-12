package spring.ai.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;


public class ItemPredictionDto {
    private String itemName;
    private String itemCategory;
    private Timestamp lastPurchase;
    private BigDecimal avgDaysBetweenPurchases;
    private Timestamp predictedNextPurchase;

    public ItemPredictionDto(String itemName, String itemCategory, Timestamp lastPurchase, BigDecimal avgDaysBetweenPurchases, Timestamp predictedNextPurchase) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.lastPurchase = lastPurchase;
        this.avgDaysBetweenPurchases = avgDaysBetweenPurchases;
        this.predictedNextPurchase = predictedNextPurchase;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public Timestamp getLastPurchase() {
        return lastPurchase;
    }

    public BigDecimal getAvgDaysBetweenPurchases() {
        return avgDaysBetweenPurchases;
    }

    public Timestamp getPredictedNextPurchase() {
        return predictedNextPurchase;
    }
}

