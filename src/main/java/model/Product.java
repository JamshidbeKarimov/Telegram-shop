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

        return " NAME =   " + name +
                "\nCREATED DATE :   " + createdDate +
                "\nIS ACTIVE :   " + isActive +
                "\nPRICE :   " + price +
                "\nAMOUNT :   " + amount +
                "\nPRODUCT INFO :  " + productInfo + " |";
    }
}
