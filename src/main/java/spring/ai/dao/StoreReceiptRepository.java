package spring.ai.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.ai.entity.StoreReceipt;

import java.util.List;

public interface StoreReceiptRepository extends JpaRepository<StoreReceipt, Long> {

    @Query("SELECT DISTINCT sr.genericStoreName FROM StoreReceipt sr WHERE sr.genericStoreName IS NOT NULL")
    List<String> findAllDistinctGenericStoreNames();

    @Query(value = """
    SELECT DISTINCT sr.store_category
    FROM  receiptschema.store_receipt sr 
    WHERE sr.store_category IS NOT NULL
    ORDER BY sr.store_category
    """, nativeQuery = true)
    List<String> findAllDistinctStoreCategories();

}

