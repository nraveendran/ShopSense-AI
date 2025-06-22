package spring.ai.service;

import spring.ai.controller.ShopSenseController;

public interface ShopSenseService {

   String getPromptResponse(String model, String userInput);

   ShopSenseController.StructuredJoke generateJoke(String userInput);

   String answerQuestions(String userInput);
}
