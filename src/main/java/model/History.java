package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor

@Data

public class History extends BaseModel{

    private UUID userId;
    private UUID sellerId;
    private UUID productId;
    private int amount;
}
