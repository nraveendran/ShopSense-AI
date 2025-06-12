package spring.ai.service;




import org.springframework.stereotype.Service;
import spring.ai.dao.PredictionRepository;
import spring.ai.model.ItemPredictionDto;

import java.util.List;

@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;

    public PredictionService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    public List<ItemPredictionDto> getUpcomingPredictions() {
        return predictionRepository.getPredictedPurchases();
    }
}

