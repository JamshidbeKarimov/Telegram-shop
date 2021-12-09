package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCard extends BaseModel{
    private UUID userId;
    private UUID productId;
    private boolean isSold;
    private double price;
}
