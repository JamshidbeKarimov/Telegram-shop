package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private double price;
    private int amount;
    private int sellerId;
    private int categoryId;
    private String productInfo;
}
