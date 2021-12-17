package model;


import enums.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Location;
import telegramBot.admin.AdminState;
import telegramBot.userBot.UserState;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Data
public class User extends BaseModel {

    private String username;
    private Location location;
    private String phoneNumber;
    private RoleUser role;
    private double balance;
    private String chatId;
    private AdminState botState;
    private UserState userState;

    @Override
    public String toString() {
        return " NAME =   " + name +
                "\nIS ACTIVE :    " + isActive +
                "\nUSERNAME :    " + username +
                "\nPHONE NUMBER :    " + phoneNumber +
                "\nCREATED DATE :    " + createdDate +
                "\nUPDATED DATE :    " + updatedDate +
                "\nCHAT ID :   " + chatId +
                "\nBALANCE :   " + balance + " |";
    }
}
