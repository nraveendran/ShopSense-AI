package spring.ai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.service.StoreAnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreAnalyticsController {

    @Autowired
    private StoreAnalyticsService storeAnalyticsService;

    @GetMapping("/{genericStoreName}/items")
    public List<ItemPurchaseStatsDto> getItemsPurchasedFromStore(
            @PathVariable String genericStoreName,
            @RequestParam(defaultValue = "30") int days
    ) {
        return storeAnalyticsService.getItemsPurchasedFromStore(genericStoreName, days);
    }

    @GetMapping("/category/{storeCategory}/items")
    public List<ItemPurchaseStatsDto> getItemsPurchasedByStoreCategory(
            @PathVariable String storeCategory,
            @RequestParam(defaultValue = "30") int days
    ) {
        return storeAnalyticsService.getItemsPurchasedFromStoreCategory(storeCategory, days);
    }



}
