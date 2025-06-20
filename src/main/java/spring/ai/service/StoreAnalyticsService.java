package spring.ai.service;

import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.dto.ItemStorePurchaseDto;

import java.util.List;

public interface StoreAnalyticsService {
    List<ItemPurchaseStatsDto> getItemsPurchasedFromStore(String genericStoreName, int days);

    List<ItemPurchaseStatsDto> getItemsPurchasedFromStoreCategory(String storeCategory, int days);


    List<ItemStorePurchaseDto> getItemPurchaseHistoryByItemNameWithinDays(String itemName, int days);

    List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(String itemCategory, int days);
}
