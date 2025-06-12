package spring.ai.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.entity.StoreReceipt;
import spring.ai.model.StoreReceiptDTO;
import spring.ai.service.ReceiptService;


@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public StoreReceipt createReceipt(@RequestBody StoreReceiptDTO dto) {
        return receiptService.saveReceipt(dto);
    }
}
