package spring.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.ai.dto.StoreReceiptDTO;
import spring.ai.entity.StoreReceipt;
import spring.ai.service.OpenAIImageProcessService;
import spring.ai.service.ReceiptService;

@RestController
public class ReceiptExtractionController {


    @Autowired
    private OpenAIImageProcessService openAIImageProcessService;

    @Autowired
    private ReceiptService receiptService;


    @GetMapping("/hello")
    String hello() {
        return "hello world";
    }


    @GetMapping("/extractText/{userInput}")
    StoreReceiptDTO extractText(@PathVariable String userInput) {
        return openAIImageProcessService.extractTextFromImage(userInput);
    }

    @GetMapping("/extractTextAndStore/{userInput}")
    String extractTextAndStore(@PathVariable String userInput) {
        StoreReceiptDTO storeReceiptDTO=  openAIImageProcessService.extractTextFromImage(userInput);
       StoreReceipt storeReceipt =  receiptService.saveReceipt(storeReceiptDTO);
        return storeReceipt.toString();
    }

}