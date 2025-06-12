package spring.ai.service;

import org.springframework.stereotype.Service;
import spring.ai.dao.StoreReceiptRepository;
import spring.ai.entity.ReceiptItem;
import spring.ai.entity.StoreReceipt;
import spring.ai.model.StoreReceiptDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final StoreReceiptRepository storeReceiptRepository;

    public ReceiptServiceImpl(StoreReceiptRepository storeReceiptRepository) {
        this.storeReceiptRepository = storeReceiptRepository;
    }

    @Override
    public StoreReceipt saveReceipt(StoreReceiptDTO dto) {
        StoreReceipt receipt = new StoreReceipt();
        receipt.setStoreName(dto.getStoreName());
        receipt.setStoreAddress(dto.getStoreAddress());
        receipt.setStoreCategory(dto.getStoreCategory());
        receipt.setTotal(dto.getTotal());
        receipt.setPurchaseDate(dto.getPurchaseDate());

        List<ReceiptItem> items = dto.getItems().stream().map(itemDto -> {
            ReceiptItem item = new ReceiptItem();
            item.setItemName(itemDto.getItemName());
            item.setQuantity(itemDto.getQuantity());
            item.setSubtotal(itemDto.getSubtotal());
            item.setItemCategory(itemDto.getItemCategory());
            return item;
        }).collect(Collectors.toList());

        receipt.setReceiptItemEntities(items);

        return storeReceiptRepository.save(receipt);
    }
}
