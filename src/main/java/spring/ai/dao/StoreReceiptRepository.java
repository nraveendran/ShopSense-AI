package spring.ai.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.ai.entity.StoreReceipt;

import java.util.List;

public interface StoreReceiptRepository extends JpaRepository<StoreReceipt, Long> {

    @Query("SELECT DISTINCT sr.genericStoreName FROM StoreReceipt sr WHERE sr.genericStoreName IS NOT NULL")
    List<String> findAllDistinctGenericStoreNames();


}

