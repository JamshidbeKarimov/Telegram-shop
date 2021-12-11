package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product extends BaseModel {
    private double price;
    private int amount;
    private UUID sellerId;
    private UUID categoryId;
    private String productInfo;

    @Override
    public String toString() {

        String str = "| NAME='" + name +
                "\t|\tCREATED DATE: " + createdDate;
        if (updatedDate != null)
            str += "\t|\tUPDATED DATE: " + updatedDate;
        str += "\t|\tIS ACTIVE: " + isActive +
                "\t\tPRICE: " + price +
                "\t\tAMOUNT: " + amount +
                "\t\tPRODUCT INFO: " + productInfo + " |";
        return str;
    }
}
