import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegramBot.admin.AdminBot;
import telegramBot.userBot.UserBot;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new UserBot());
        System.out.println("User bot is running... ");

        botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new AdminBot());
        System.out.println("Admin bot is running... \n\n");

    }
}