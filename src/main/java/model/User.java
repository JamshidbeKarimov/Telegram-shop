package model;


import enums.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Data
public class User extends BaseModel {

    private String username;
    private String location;
    private String phoneNumber;
    private RoleUser role;
    private double balance;
    private String chatId;

    @Override
    public String toString() {
        String str = "| NAME='" + name +
                "\t|\tCREATED DATE: " + createdDate;
        if (updatedDate != null) str += "\t|\tUPDATED DATE: " + updatedDate;
        str += "\t|\tIS ACTIVE: " + isActive +
                "\t|\tUSERNAME: " + username +
                "\t|\tPHONE NUMBER: " + phoneNumber +
                "\t|\tLOCATION: " + location +
                "\t|\tBALANCE: " + balance + " |";
        return str;
    }
}
