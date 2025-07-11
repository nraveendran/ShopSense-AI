package spring.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/extractText", consumes = MediaType.IMAGE_JPEG_VALUE)
    public StoreReceiptDTO extractDataFromImage(@RequestBody byte[] imageBytes) {
        // parse imageBytes
        return openAIImageProcessService.extractTextFromImage(imageBytes);
    }


    @GetMapping("/extractTextAndStore/{userInput}")
    String extractTextAndStore(@PathVariable String userInput) {
        StoreReceiptDTO storeReceiptDTO=  openAIImageProcessService.extractTextFromImage(userInput);
       StoreReceipt storeReceipt =  receiptService.saveReceipt(storeReceiptDTO);
        return storeReceipt.toString();
    }

    @PostMapping(value = "/extractTextAndStore", consumes = MediaType.IMAGE_JPEG_VALUE)
    String extractTextAndStore(@RequestBody byte[] imageBytes) {
        StoreReceiptDTO storeReceiptDTO=  openAIImageProcessService.extractTextFromImage(imageBytes);
        StoreReceipt storeReceipt =  receiptService.saveReceipt(storeReceiptDTO);
        return storeReceipt.toString();
    }

}