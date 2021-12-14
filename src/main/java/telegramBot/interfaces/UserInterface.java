package telegramBot.interfaces;

public interface UserInterface {
    default boolean isStart(String text){
        return text.equals("/start");
    }
}
