package telegramBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface TelegramBotUtils {
    String BOT_USERNAME = "https://t.me/b1TokhirBot";
    String BOT_TOKEN = "5077576061:AAG5yjqwNk7P5jf2B58H3lXkVtWaEuhPFis";

    default boolean isStart(String text) {
        return text.equals("/start");
    }

    default boolean isMenu(String text) {
        return text.equals("Menu");
    }

    default boolean isCallBack(String text) {
        return text.equals("1");
    }

    List<String> cityList = new ArrayList<>(Arrays.asList(
            "Tashkent",
            "Fergana",
            "Andijan",
            "Namangan",
            "Sirdarya",
            "Samarqand",
            "Bukhara",
            "Navoi",
            "Jizzax",
            "Qashqadaryo",
            "Surxondaryo",
            "Xorazm",
            "Qoraqalpogiston"
    ));
    default boolean getLocation(String text){
        return cityList.contains(text);
    }


    default boolean checkSignUp(String text){
        return text.equals("seller") || text.equals("user");
    }

}
