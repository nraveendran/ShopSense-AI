package spring.ai.service;


import spring.ai.entity.StoreReceipt;
import spring.ai.dto.StoreReceiptDTO;

public interface ReceiptService {
    StoreReceipt saveReceipt(StoreReceiptDTO dto);
}
