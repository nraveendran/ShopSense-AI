package spring.ai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.dto.ItemStorePurchaseDto;
import spring.ai.service.StoreAnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemAnalyticsController {

    @Autowired
    private StoreAnalyticsService storeAnalyticsService;

    @GetMapping("/{itemName}")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryWithinDays(
            @PathVariable String itemName,
            @RequestParam(defaultValue = "30") int days) {
        return storeAnalyticsService.getItemPurchaseHistoryByItemNameWithinDays(itemName, days);
    }

    @GetMapping("/category/{itemCategory}")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(
            @PathVariable String itemCategory,
            @RequestParam(defaultValue = "30") int days) {
        return storeAnalyticsService.getItemPurchaseHistoryByCategoryWithinDays(itemCategory, days);
    }


//    @GetMapping("/category/{itemCategory}")
//    public List<ItemPurchaseStatsDto> getItemsPurchasedByStoreCategory(
//            @PathVariable String storeCategory,
//            @RequestParam(defaultValue = "30") int days
//    ) {
//        return storeAnalyticsService.getItemsPurchasedFromStoreCategory(storeCategory, days);
//    }



}
