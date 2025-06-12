package spring.ai.service;

import spring.ai.controller.PoetryController;

public interface PoetryService {

   String getPromptResponse(String model, String userInput);

   PoetryController.StructuredJoke generateJoke(String userInput);

   String relatedJokes(String userInput);
}
