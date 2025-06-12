package spring.ai.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.ai.entity.StoreReceipt;
import spring.ai.model.StoreReceiptDTO;

public interface StoreReceiptRepository extends JpaRepository<StoreReceipt, Long> {}

