package spring.ai.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import spring.ai.dto.ItemPurchaseStatsDto;
import spring.ai.dto.ItemStorePurchaseDto;
import spring.ai.entity.ReceiptItem;

import java.util.List;

public interface ReceiptItemRepository extends CrudRepository<ReceiptItem, Long> {

    @Query(value = """
    SELECT 
        ri.item_name AS itemName,
        COUNT(*) AS timesPurchased,
        sr.store_name AS storeName,
        sr.store_category AS storeCategory
    FROM 
        receiptschema.receipt_item ri
    JOIN 
        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
    WHERE 
        sr.store_name = :storeName
        AND sr.purchase_date >= CURRENT_DATE - make_interval(days := :days)
    GROUP BY 
        ri.item_name, sr.store_name, sr.store_category
    ORDER BY 
        timesPurchased DESC
    """, nativeQuery = true)
    List<ItemPurchaseStatsDto> findItemStatsByStoreNameAndRecentDays(
            @Param("storeName") String storeName,
            @Param("days") int days
    );

    @Query(value = """
    SELECT 
        ri.item_name AS itemName,
        COUNT(*) AS timesPurchased,
        sr.store_name AS storeName,
        sr.store_category AS storeCategory
    FROM 
        receiptschema.receipt_item ri
    JOIN 
        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
    WHERE 
        sr.store_category = :storeCategory
        AND sr.purchase_date >= CURRENT_DATE  - make_interval(days := :days)
    GROUP BY 
        ri.item_name, sr.store_name, sr.store_category
    ORDER BY 
        timesPurchased DESC
    """, nativeQuery = true)
    List<ItemPurchaseStatsDto> findItemStatsByStoreCategoryAndRecentDays(
            @Param("storeCategory") String storeCategory,
            @Param("days") int days
    );

    @Query(value = """
    SELECT 
        ri.item_name AS itemName,
        ri.item_category AS itemCategory,
        sr.store_name AS storeName,
        sr.store_category AS storeCategory,
        sr.purchase_date AS purchaseDate
    FROM 
        receiptschema.receipt_item ri
    JOIN 
        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
    WHERE 
        ri.item_name = :itemName
        AND sr.purchase_date >= CURRENT_DATE - make_interval(days := :days)
    ORDER BY 
        sr.purchase_date DESC
    """, nativeQuery = true)
    List<ItemStorePurchaseDto> findStoresAndDatesByItemNameWithinDays(
            @Param("itemName") String itemName,
            @Param("days") int days
    );


    @Query(value = """
    SELECT 
        ri.item_name AS itemName,
        ri.item_category AS itemCategory,
        sr.store_name AS storeName,
        sr.store_category AS storeCategory,
        sr.purchase_date AS purchaseDate
    FROM 
        receiptschema.receipt_item ri
    JOIN 
        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
    WHERE 
        ri.item_category = :itemCategory
        AND sr.purchase_date >= CURRENT_DATE - make_interval(days := :days)
    ORDER BY 
        sr.purchase_date DESC
    """, nativeQuery = true)
    List<ItemStorePurchaseDto> findStoresAndDatesByItemCategoryWithinDays(
            @Param("itemCategory") String itemCategory,
            @Param("days") int days
    );

}
