package spring.ai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.ai.dto.ItemStorePurchaseDto;
import spring.ai.service.StoreAnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemAnalyticsController {

    @Autowired
    private StoreAnalyticsService storeAnalyticsService;

    @GetMapping("/items-by-generic-item-names")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryWithinDays(
            @RequestParam List<String> genericItemNames,
            @RequestParam(defaultValue = "30") int days) {
        return storeAnalyticsService.getItemPurchaseHistoryByItemNameWithinDays(genericItemNames, days);
    }

    @GetMapping("/category/items-by-item-categories")
    public List<ItemStorePurchaseDto> getItemPurchaseHistoryByCategoryWithinDays(
            @RequestParam List<String> itemCategories,
            @RequestParam(defaultValue = "30") int days) {
        return storeAnalyticsService.getItemPurchaseHistoryByCategoryWithinDays(itemCategories, days);
    }

    @GetMapping("/category/list")
    public List<String> getUniqueItemCategories() {
        return storeAnalyticsService.getUniqueItemCategories();
    }

    @GetMapping("/list")
    public List<String> getUniqueItemNames() {
        return storeAnalyticsService.getUniqueGenericItemNames();
    }


}
