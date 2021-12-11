package model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class BaseModel {

    protected UUID id;
    protected String name;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected UUID createdBy; //
    protected UUID updatedBy;
    protected boolean isActive;

    {
        this.id = UUID.randomUUID();
        this.createdDate = LocalDateTime.now();
    }


}
