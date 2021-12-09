package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel {

    protected UUID id;
    protected String name;
    protected Date createdDate;
    protected Date updatedDate;
    protected UUID createdBy;
    protected UUID updatedBy;
    protected boolean isActive;

    {
        this.id = UUID.randomUUID();
        this.createdDate = new Date();
    }
}
