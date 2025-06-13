package spring.ai.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.ai.entity.StoreReceipt;

public interface StoreReceiptRepository extends JpaRepository<StoreReceipt, Long> {}

