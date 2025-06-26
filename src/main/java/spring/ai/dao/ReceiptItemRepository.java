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
                        ri.generic_item_name AS genericItemName,
                        COUNT(*) AS timesPurchased,
                        SUM(ri.subtotal) AS totalSpent,
                        ROUND(AVG(ri.subtotal)::numeric, 2) AS averagePrice,
                        sr.generic_store_name AS genericStoreName,
                        sr.store_category AS storeCategory
                    FROM
                        receiptschema.receipt_item ri
                    JOIN
                        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
                    WHERE
                        sr.generic_store_name = :genericStoreName
                        AND sr.purchase_date >= CURRENT_DATE - make_interval(days := :days)
                    GROUP BY
                        ri.generic_item_name, sr.generic_store_name, sr.store_category
                    ORDER BY
                        timesPurchased DESC
            """, nativeQuery = true)
    List<ItemPurchaseStatsDto> findItemStatsByStoreNameAndRecentDays(
            @Param("genericStoreName") String genericStoreName,
            @Param("days") int days
    );

    @Query(value = """
                    SELECT
                        ri.generic_item_name AS genericItemName,
                        COUNT(*) AS timesPurchased,
                        SUM(ri.subtotal) AS totalSpent,
                        ROUND(AVG(ri.subtotal)::numeric, 2) AS averagePrice,
                        sr.generic_store_name AS genericStoreName,
                        sr.store_category AS storeCategory
                    FROM
                        receiptschema.receipt_item ri
                    JOIN
                        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
                    WHERE
                        sr.store_category IN (:storeCategories)
                        AND sr.purchase_date >= CURRENT_DATE - make_interval(days := :days)
                    GROUP BY
                        ri.generic_item_name,sr.generic_store_name, sr.store_category
                    ORDER BY
                        timesPurchased DESC
            """, nativeQuery = true)
    List<ItemPurchaseStatsDto> findItemStatsByStoreCategoryAndRecentDays(
            @Param("storeCategories") List<String> storeCategories,
            @Param("days") int days
    );

    @Query(value = """
                    SELECT
                        ri.generic_item_name AS genericItemName,
                        ri.item_category AS itemCategory,
                        SUM(ri.subtotal) AS totalSpent,
                        ROUND(AVG(ri.subtotal)::numeric, 2) AS averagePrice,
                        sr.generic_store_name AS genericStoreName,
                        sr.store_category AS storeCategory,
                        sr.purchase_date AS purchaseDate
                    FROM
                        receiptschema.receipt_item ri
                    JOIN
                        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
                    WHERE
                        ri.generic_item_name in (:genericItemNames)
                        AND sr.purchase_date >= CURRENT_DATE - make_interval(days := :days)
                    GROUP BY
                        ri.generic_item_name,
                        ri.item_category,
                        sr.generic_store_name,
                        sr.store_category,
                        sr.purchase_date
                    ORDER BY
                        sr.purchase_date DESC;
            """, nativeQuery = true)
    List<ItemStorePurchaseDto> findStoresAndDatesByItemNameWithinDays(
            @Param("genericItemNames") List<String> genericItemNames,
            @Param("days") int days
    );


    @Query(value = """
                    SELECT
                        ri.generic_item_name AS genericItemName,
                        ri.item_category AS itemCategory,
                        SUM(ri.subtotal) AS totalSpent,
                        ROUND(AVG(ri.subtotal)::numeric, 2) AS averagePrice,
                        sr.generic_store_name AS genericStoreName,
                        sr.store_category AS storeCategory,
                        sr.purchase_date AS purchaseDate
                    FROM
                        receiptschema.receipt_item ri
                    JOIN
                        receiptschema.store_receipt sr ON sr.id = ri.receipt_id
                    WHERE
                        ri.item_category in (:itemCategories)
                        AND sr.purchase_date >= CURRENT_DATE - make_interval(days := :days)
                    GROUP BY
                        ri.generic_item_name,
                        ri.item_category,
                        sr.generic_store_name,
                        sr.store_category,
                        sr.purchase_date
                    ORDER BY
                        sr.purchase_date DESC;
            """, nativeQuery = true)
    List<ItemStorePurchaseDto> findStoresAndDatesByItemCategoryWithinDays(
            @Param("itemCategories") List<String> itemCategories,
            @Param("days") int days
    );


    @Query(value = """
            SELECT DISTINCT ri.item_category 
            FROM receiptschema.receipt_item ri
            WHERE ri.item_category IS NOT NULL
            ORDER BY ri.item_category
            """, nativeQuery = true)
    List<String> findAllDistinctItemCategories();

    @Query(value = """
            SELECT DISTINCT ri.generic_item_name 
            FROM receiptschema.receipt_item ri
            WHERE ri.generic_item_name IS NOT NULL
            ORDER BY ri.generic_item_name
            """, nativeQuery = true)
    List<String> findAllDistinctGenericItemNames();
}


