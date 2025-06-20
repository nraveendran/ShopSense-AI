package spring.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.ai.dto.StoreReceiptDTO;
import spring.ai.service.OpenAIImageProcessService;

@RestController
public class ReceiptExtractionController {


    @Autowired
    private OpenAIImageProcessService openAIImageProcessService;


    @GetMapping("/hello")
    String hello() {
        return "hello world";
    }


    @GetMapping("/extractText/{userInput}")
    StoreReceiptDTO extractText(@PathVariable String userInput) {
        return openAIImageProcessService.extractTextFromImage(userInput);
    }


}