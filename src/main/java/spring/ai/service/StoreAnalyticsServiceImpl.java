package spring.ai.service;


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

    @Override
    public List<ItemPurchaseStatsDto> getItemsPurchasedFromStore(String storeName, int days) {
        return receiptItemRepository.findItemStatsByStoreNameAndRecentDays(storeName, days);
    }

    @Override
    public List<ItemPurchaseStatsDto> getItemsPurchasedFromStoreCategory(String storeCategory, int days) {
        return receiptItemRepository.findItemStatsByStoreCategoryAndRecentDays(storeCategory, days);
    }

    @Override
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByItemNameWithinDays(String itemName, int days) {
        return receiptItemRepository.findStoresAndDatesByItemNameWithinDays(itemName, days);
    }

    @Override
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(String itemCategory, int days) {
        return receiptItemRepository.findStoresAndDatesByItemCategoryWithinDays(itemCategory, days);
    }


}
