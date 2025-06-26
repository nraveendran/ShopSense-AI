package spring.ai.service;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ai.dao.ReceiptItemRepository;
import spring.ai.dao.StoreReceiptRepository;
import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.dto.ItemStorePurchaseDto;

import java.util.List;

@Service
public class StoreAnalyticsServiceImpl implements StoreAnalyticsService {

    private final ReceiptItemRepository receiptItemRepository;

    private final StoreReceiptRepository storeReceiptRepository;

    @Autowired
    public StoreAnalyticsServiceImpl(ReceiptItemRepository receiptItemRepository, StoreReceiptRepository storeReceiptRepository) {
        this.receiptItemRepository = receiptItemRepository;
        this.storeReceiptRepository=storeReceiptRepository;
    }

    @Tool(description = "Get the items purchased from a store within the last specified  amount of days." +
            " If number of days is not mentioned, a default of 30 days is assumed ")
    @Override
    public List<ItemPurchaseStatsDto> getItemsPurchasedFromStore(
            @ToolParam(description = "Name of the store")String genericStoreName,
            @ToolParam(description = "Number of days the items was purchased within") int days) {
        return receiptItemRepository.findItemStatsByStoreNameAndRecentDays(genericStoreName, days);
    }

    @Tool(description = "Get the items purchased from a store category within the last specified amount of days." +
            " If number of days is not mentioned, a default of 30 days is assumed.")
    @Override
    public List<ItemPurchaseStatsDto> getItemsPurchasedFromStoreCategories(
            @ToolParam(description = "A list of Store Categories Examples of store category are: " +
                    "superstore, online marketplace, grocery store, retail chain, warehouse club, etc.") List<String> storeCategories,
            @ToolParam(description = "Number of days the items was purchased within") int days) {
        return receiptItemRepository.findItemStatsByStoreCategoryAndRecentDays(storeCategories, days);
    }

    @Override
    @Tool(description = "Get the store from which an item was purchased within the last specified amount of days." +
            "Store name, store category, purchased date and item category is returned " +
            " If number of days is not mentioned, a default of 30 days is assumed ")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByItemNameWithinDays(
            @ToolParam(description = "a list of generic item names") List<String> genericItemNames,
            @ToolParam(description = "Number of days the items was purchased within")int days) {
        return receiptItemRepository.findStoresAndDatesByItemNameWithinDays(genericItemNames, days);
    }

    @Override
    @Tool(description = "Get the store and items by an Item categories purchased within the last specified amount of days." +
            "Store name, store category, purchased date and item category is returned " +
            " If number of days is not mentioned, a default of 30 days is assumed ")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(
            @ToolParam(description = "a list of Item Categories")List<String> itemCategories,
            @ToolParam(description = "Number of days the items was purchased within") int days) {
        return receiptItemRepository.findStoresAndDatesByItemCategoryWithinDays(itemCategories, days);
    }

    @Tool(description = "Retrieve the unique list of Store names" +
            "You should use the values returned from this function as parameter to tools using Store name")
    @Override
    public List<String> getUniqueGenericStoreNames() {
        return storeReceiptRepository.findAllDistinctGenericStoreNames();
    }

    @Tool(description = "Retrieve the unique list of Item Categories" +
            "You should use the values returned from this function as parameter to tools using Item Categories")
    @Override
    public List<String> getUniqueItemCategories() {
        return receiptItemRepository.findAllDistinctItemCategories();
    }

    @Tool(description = "Retrieve the unique list of Generic Item Names" +
            "You should use the values returned from this function as parameter to tools using Item Names")
    @Override
    public List<String> getUniqueGenericItemNames() {
        return receiptItemRepository.findAllDistinctGenericItemNames();
    }

    @Tool(description = "Retrieve the unique list of Store Categories" +
            "You should use the values returned from this function as parameter to tools using Store Categories")
    @Override
    public List<String> getUniqueStoreCategories() {
        return storeReceiptRepository.findAllDistinctStoreCategories();
    }

}
