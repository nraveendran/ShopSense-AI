package spring.ai.service;


import spring.ai.entity.StoreReceipt;
import spring.ai.model.StoreReceiptDTO;

public interface ReceiptService {
    StoreReceipt saveReceipt(StoreReceiptDTO dto);
}
