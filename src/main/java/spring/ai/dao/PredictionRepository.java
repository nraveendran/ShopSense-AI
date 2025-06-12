package spring.ai.dao;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import spring.ai.model.ItemPredictionDto;

import java.util.List;

public interface PredictionRepository extends Repository<spring.ai.entity.StoreReceipt, Long> {

    @Query(value = """
        SELECT 
          ri.item_name as itemName,
          ri.item_category as itemCategory,
          MAX(sr.purchase_date) as lastPurchase,
          ROUND(
            CASE 
              WHEN COUNT(DISTINCT sr.purchase_date) > 1 
              THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400 
                   / (COUNT(DISTINCT sr.purchase_date) - 1)
              ELSE NULL 
            END, 
            2
          ) AS avgDaysBetweenPurchases,
          (MAX(sr.purchase_date) + 
            (ROUND(
              CASE 
                WHEN COUNT(DISTINCT sr.purchase_date) > 1 
                THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400 
                     / (COUNT(DISTINCT sr.purchase_date) - 1)
                ELSE NULL 
              END
            ) || ' days')::interval) AS predictedNextPurchase
        FROM 
          receiptschema.receipt_item ri 
        JOIN 
          receiptschema.store_receipt sr 
          ON sr.id = ri.receipt_id 
        GROUP BY 
          ri.item_name,
          ri.item_category
        HAVING 
          (MAX(sr.purchase_date) + 
            (ROUND(
              CASE 
                WHEN COUNT(DISTINCT sr.purchase_date) > 1 
                THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400 
                     / (COUNT(DISTINCT sr.purchase_date) - 1)
                ELSE NULL 
              END
            ) || ' days')::interval) > CURRENT_DATE
          AND 
          (MAX(sr.purchase_date) + 
            (ROUND(
              CASE 
                WHEN COUNT(DISTINCT sr.purchase_date) > 1 
                THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400 
                     / (COUNT(DISTINCT sr.purchase_date) - 1)
                ELSE NULL 
              END
            ) || ' days')::interval) < CURRENT_DATE + interval '3 days'
        """,
            nativeQuery = true)
    List<ItemPredictionDto> getPredictedPurchases();
}

