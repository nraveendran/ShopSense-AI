package spring.ai.service;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ai.dao.ReceiptItemRepository;
import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.dto.ItemStorePurchaseDto;

import java.util.List;

@Service
public class StoreAnalyticsServiceImpl implements StoreAnalyticsService {

    private final ReceiptItemRepository receiptItemRepository;

    @Autowired
    public StoreAnalyticsServiceImpl(ReceiptItemRepository receiptItemRepository) {
        this.receiptItemRepository = receiptItemRepository;
    }

    @Tool(description = "Get the items purchased from a store within the last specified  amount of days." +
            " If number of days is not mentioned, a default of 30 days is assumed ")
    @Override
    public List<ItemPurchaseStatsDto> getItemsPurchasedFromStore(
            @ToolParam(description = "Name of the store")String storeName,
            @ToolParam(description = "Number of days the items was purchased within") int days) {
        return receiptItemRepository.findItemStatsByStoreNameAndRecentDays(storeName, days);
    }

    @Tool(description = "Get the items purchased from a store category within the last specified amount of days." +
            " If number of days is not mentioned, a default of 30 days is assumed.")
    @Override
    public List<ItemPurchaseStatsDto> getItemsPurchasedFromStoreCategory(
            @ToolParam(description = "store Category. Examples of store category are: " +
                    "superstore, online marketplace, grocery store, retail chain, warehouse club, etc.") String storeCategory,
            @ToolParam(description = "Number of days the items was purchased within")int days) {
        return receiptItemRepository.findItemStatsByStoreCategoryAndRecentDays(storeCategory, days);
    }

    @Override
    @Tool(description = "Get the store from whicn an item was purchased within the last specified  amount of days." +
            "Store name, store category, purchased date and item category is returned " +
            " If number of days is not mentioned, a default of 30 days is assumed ")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByItemNameWithinDays(
            @ToolParam(description = "item name") String itemName,
            @ToolParam(description = "Number of days the items was purchased within")int days) {
        return receiptItemRepository.findStoresAndDatesByItemNameWithinDays(itemName, days);
    }

    @Override
    @Tool(description = "Get the store from whicn an item category was purchased within the last specified amount of days." +
            "Store name, store category, purchased date and item category is returned " +
            " If number of days is not mentioned, a default of 30 days is assumed ")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(
            @ToolParam(description = "item category")String itemCategory,
            @ToolParam(description = "Number of days the items was purchased within") int days) {
        return receiptItemRepository.findStoresAndDatesByItemCategoryWithinDays(itemCategory, days);
    }


}
