package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification extends BaseModel {
    String message;
    // isActive  ->  have been read;
    // isNotActive -> have not been read


    @Override
    public String toString() {
        return "| CREATED DATE: " + createdDate +
                "\t\tMESSAGE: " + message + " |";
    }
}
