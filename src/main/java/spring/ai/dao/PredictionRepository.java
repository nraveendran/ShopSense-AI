package spring.ai.dao;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import spring.ai.model.ItemPredictionDto;

import java.util.List;

public interface PredictionRepository extends Repository<spring.ai.entity.StoreReceipt, Long> {

    @Query(value = """
            SELECT
              ri.item_name AS itemName,
              ri.item_category AS itemCategory,
              MAX(sr.purchase_date)::date AS lastPurchase,
              ROUND(
                CASE
                  WHEN COUNT(DISTINCT sr.purchase_date) > 1
                  THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400
                       / (COUNT(DISTINCT sr.purchase_date) - 1)
                  ELSE NULL
                END,
                2
              ) AS avgDaysBetweenPurchases,
              (
  MAX(sr.purchase_date) +
  (
    ROUND(
      CASE
        WHEN COUNT(DISTINCT sr.purchase_date) > 1
        THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400
             / (COUNT(DISTINCT sr.purchase_date) - 1)
        ELSE NULL
      END
    ) || ' days'
  )::interval
)::date AS predictedNextPurchase
            FROM
              receiptschema.receipt_item ri
            JOIN
              receiptschema.store_receipt sr
                ON sr.id = ri.receipt_id
            GROUP BY
              ri.item_name,
              ri.item_category
            HAVING
              (
                MAX(sr.purchase_date) +
                (
                  ROUND(
                    CASE
                      WHEN COUNT(DISTINCT sr.purchase_date) > 1
                      THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400
                           / (COUNT(DISTINCT sr.purchase_date) - 1)
                      ELSE NULL
                    END
                  ) || ' days'
                )::interval
              ) > CURRENT_DATE
              AND
              (
                MAX(sr.purchase_date) +
                (
                  ROUND(
                    CASE
                      WHEN COUNT(DISTINCT sr.purchase_date) > 1
                      THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400
                           / (COUNT(DISTINCT sr.purchase_date) - 1)
                      ELSE NULL
                    END
                  ) || ' days'
                )::interval
              ) < CURRENT_DATE + make_interval(days := :days)
        """,
            nativeQuery = true)
    List<ItemPredictionDto> getPredictedPurchases(@Param("days") int days);



    @Query(value = """
            SELECT
                                       ri.item_name AS itemName,
                                       ri.item_category AS itemCategory,
                                       MAX(sr.purchase_date)::date AS lastPurchase,
                                       ROUND(
                                         CASE
                                           WHEN COUNT(DISTINCT sr.purchase_date) > 1
                                           THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400\s
                                                / (COUNT(DISTINCT sr.purchase_date) - 1)
                                           ELSE NULL
                                         END,
                                         2
                                       ) AS avgDaysBetweenPurchases,
                                       (
                                         MAX(sr.purchase_date) +
                                         (
                                           ROUND(
                                             CASE
                                               WHEN COUNT(DISTINCT sr.purchase_date) > 1
                                               THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400\s
                                                    / (COUNT(DISTINCT sr.purchase_date) - 1)
                                               ELSE NULL
                                             END
                                           ) || ' days'
                                         )::interval
                                       )::date AS predictedNextPurchase
                                     FROM
                                       receiptschema.receipt_item ri
                                     JOIN
                                       receiptschema.store_receipt sr
                                         ON sr.id = ri.receipt_id
                                     WHERE
                                       ri.item_category = :itemCategory
                                     GROUP BY
                                       ri.item_name,
                                       ri.item_category
                                     HAVING
                                       (
                                         MAX(sr.purchase_date) +
                                         (
                                           ROUND(
                                             CASE
                                               WHEN COUNT(DISTINCT sr.purchase_date) > 1
                                               THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400\s
                                                    / (COUNT(DISTINCT sr.purchase_date) - 1)
                                               ELSE NULL
                                             END
                                           ) || ' days'
                                         )::interval
                                       )::date > CURRENT_DATE
                                       AND
                                       (
                                         MAX(sr.purchase_date) +
                                         (
                                           ROUND(
                                             CASE
                                               WHEN COUNT(DISTINCT sr.purchase_date) > 1
                                               THEN EXTRACT(EPOCH FROM MAX(sr.purchase_date) - MIN(sr.purchase_date)) / 86400\s
                                                    / (COUNT(DISTINCT sr.purchase_date) - 1)
                                               ELSE NULL
                                             END
                                           ) || ' days'
                                         )::interval
                                       )::date < CURRENT_DATE + make_interval(days := :days)
""", nativeQuery = true)
    List<ItemPredictionDto> findUpcomingItemsByCategoryWithinDays(
            @Param("itemCategory") String itemCategory,
            @Param("days") int days);



}

