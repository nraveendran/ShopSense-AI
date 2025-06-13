package spring.ai.controller;



import spring.ai.model.ItemPredictionDto;
import spring.ai.service.PredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {
    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/upcoming/items")
    public List<ItemPredictionDto> getUpcomingPurchases(
            @RequestParam(name = "days", defaultValue = "7") int days) {
        return predictionService.getUpcomingPredictions(days);
    }


    @GetMapping("/upcoming/category")
    public List<ItemPredictionDto> getUpcomingPredictions(
            @RequestParam String itemCategory,
            @RequestParam(defaultValue = "7") int days) {
        return predictionService.getPredictedItems(itemCategory, days);
    }

}

