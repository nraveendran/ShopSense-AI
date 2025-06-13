package spring.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.ai.dto.StoreReceiptDTO;
import spring.ai.service.OpenAIImageProcessService;
import spring.ai.service.PoetryService;

@RestController
public class PoetryController {

    private final PoetryService poetryService;

    @Autowired
    private OpenAIImageProcessService openAIImageProcessService;

    public PoetryController(PoetryService poetryService) {
        this.poetryService = poetryService;
    }

    @GetMapping("/hello")
    String hello() {
        return "hello world";
    }

   public record StructuredJoke(String subject, String setup, String punchLine, String explanation, int rating) {}

    @GetMapping("/{dto}/{userInput}")
   String generation(@PathVariable String model, @PathVariable String userInput) {
      return poetryService.getPromptResponse(model, userInput);
    }

    @GetMapping("/joke/{userInput}")
    StructuredJoke generatedJoke(@PathVariable String userInput) {
        return poetryService.generateJoke(userInput);
    }

//    @GetMapping("/relatedjokes/{userInput}")
//    List<String> relatedjokes(@PathVariable String userInput) {
//        return poetryService.relatedJokes(userInput);
//    }
    // constructor

    @GetMapping("/relatedjokes/{userInput}")
    String relatedjokes(@PathVariable String userInput) {
        return poetryService.relatedJokes(userInput);
    }

    @GetMapping("/extractText/{userInput}")
    StoreReceiptDTO extractText(@PathVariable String userInput) {
        return openAIImageProcessService.extractTextFromImage(userInput);
    }
}