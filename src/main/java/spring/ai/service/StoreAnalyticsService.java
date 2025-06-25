package spring.ai.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.dto.ItemStorePurchaseDto;

import java.util.List;

public interface StoreAnalyticsService {
    List<ItemPurchaseStatsDto> getItemsPurchasedFromStore(String genericStoreName, int days);

    @Tool(description = "Get the items purchased from referenced Store Categories within the last specified amount of days." +
            " If number of days is not mentioned, a default of 30 days is assumed.")
    List<ItemPurchaseStatsDto> getItemsPurchasedFromStoreCategories(
            @ToolParam(description = "A list of Store Categories Examples of store categories are: " +
                    "superstore, online marketplace, grocery store, retail chain, warehouse club, etc.") List<String> storeCategories,
            @ToolParam(description = "Number of days the items was purchased within") int days);

    List<ItemStorePurchaseDto> getItemPurchaseHistoryByItemNameWithinDays(String itemName, int days);

    List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(String itemCategory, int days);

    @Tool(description = "Retrieve the unique list of store names supported by tools using Store Names ")
    List<String> getUniqueGenericStoreNames();

    @Tool(description = "Retrieve the unique list of Item Categories that can be used by tools needing Item Categories ")
    List<String> getUniqueItemCategories();

    @Tool(description = "Retrieve the unique list of Store Categories that can be used by tools use Store Categories ")
    List<String> getUniqueStoreCategories();
}
