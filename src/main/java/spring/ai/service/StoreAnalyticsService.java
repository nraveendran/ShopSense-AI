package spring.ai.service;

import org.springframework.ai.tool.annotation.Tool;
import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.dto.ItemStorePurchaseDto;

import java.util.List;

public interface StoreAnalyticsService {
    List<ItemPurchaseStatsDto> getItemsPurchasedFromStore(String genericStoreName, int days);

    List<ItemPurchaseStatsDto> getItemsPurchasedFromStoreCategory(String storeCategory, int days);


    List<ItemStorePurchaseDto> getItemPurchaseHistoryByItemNameWithinDays(String itemName, int days);

    List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(String itemCategory, int days);

    @Tool(description = "Retrieve the unique list of store names supported by tools using store name ")
    List<String> getUniqueGenericStoreNames();

    @Tool(description = "Retrieve the unique list of Item Categories supported by tools using Item Categories ")
    List<String> getUniqueItemCategories();
}
