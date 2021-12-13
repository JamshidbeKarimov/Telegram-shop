package model;


import enums.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class User extends BaseModel {

    private String username;
    private String password;
    private String phoneNumber;
    private int smsCode;
    private RoleUser role;
    private double balance;
    private long chatId;

    @Override
    public String toString() {
        String str = "| NAME='" + name +
                "\t|\tCREATED DATE: " + createdDate;
        if (updatedDate != null) str += "\t|\tUPDATED DATE: " + updatedDate;
        str += "\t|\tIS ACTIVE: " + isActive +
                "\t|\tUSERNAME: " + username +
                "\t|\tPHONE NUMBER: " + phoneNumber +
                "\t|\tBALANCE: " + balance + " |";
        return str;
    }
}
