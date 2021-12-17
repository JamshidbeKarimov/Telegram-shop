package telegramBot.userBot;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor

public enum UserState {
    START_NEW_USER,
    START_CONTACT_SHARED,
    START_LOCATION,
    MAIN_MENU,
    CATEGORIES,
    PRODUCTS,
    PRODUCT_INFO,
    ADD_TO_CART;

    public UserState forward(UserState userState){


        int state = userState.ordinal();
        UserState[] values = UserState.values();
        if(values.length - 1 != state){
            return values[state + 1];
        }
        return null;
    }

    public UserState back(UserState userState){

        int state = userState.ordinal();
        UserState[] values = UserState.values();
        if(state != 1 ){
            return values[state - 1];
        }

        return null;
    }

}
