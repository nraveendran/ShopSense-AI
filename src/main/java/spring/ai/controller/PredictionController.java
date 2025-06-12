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

    @GetMapping("/upcoming")
    public List<ItemPredictionDto> getUpcomingPurchases() {
        return predictionService.getUpcomingPredictions();
    }
}

