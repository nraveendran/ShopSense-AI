package spring.ai.model;

import java.math.BigDecimal;
import java.sql.Date;


public class ItemPredictionDto {
    private String itemName;
    private String itemCategory;
    private Date lastPurchase;
    private BigDecimal avgDaysBetweenPurchases;
    private Date predictedNextPurchase;

    public ItemPredictionDto(String itemName, String itemCategory, Date lastPurchase, BigDecimal avgDaysBetweenPurchases, Date predictedNextPurchase) {
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

    public Date getLastPurchase() {
        return lastPurchase;
    }

    public BigDecimal getAvgDaysBetweenPurchases() {
        return avgDaysBetweenPurchases;
    }

    public Date getPredictedNextPurchase() {
        return predictedNextPurchase;
    }
}

