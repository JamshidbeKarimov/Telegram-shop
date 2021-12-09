package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class User extends BaseModel{

    private String username;
    private String password;
    private String phoneNumber;
    private int smsCode;
    private Enum role;
    private double balance;

}
