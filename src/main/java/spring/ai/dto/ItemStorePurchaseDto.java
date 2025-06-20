package spring.ai.dto;

import java.util.Date;

public interface ItemStorePurchaseDto {
    String getItemName();
    String getItemCategory();
    String getGenericStoreName();
    String getStoreCategory();
    Date getPurchaseDate();
}
