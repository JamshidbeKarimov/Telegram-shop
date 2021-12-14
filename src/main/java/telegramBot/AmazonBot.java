package telegramBot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AmazonBot extends TelegramLongPollingBot implements TelegramBotUtils{
    UserService userService = new UserService();
    CategoryService categoryService = new CategoryService();
    HistoryService historyService = new HistoryService();
    MyCartService myCartService = new MyCartService();
    NotificationService notificationService = new NotificationService();
    ProductService productService = new ProductService();


    private String chatId;
    private String messageFromBot;
    private String messageFromUser;
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            this.chatId = update.getMessage().getChatId().toString();
            this.messageFromUser = update.getMessage().getText();
            this.messageFromBot = "Welcome :smile: \uD83D\uDE0A";
            SendMessage sendMessage = new SendMessage(chatId, messageFromBot);

            switch (messageFromUser) {
                case "/start" : {
//                    isStart(messageFromUser);
                    break;
                }
            }

            execute(sendMessage);
        }
        else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            this.chatId = update.getCallbackQuery().getMessage().getChatId().toString();

            switch (callData) {
                case "acceptSeller" : {

                    break;
                }
            }
        }
    }

    private void log(String name, String chatId, String messageFromUser, String messageFromBot) {
        System.out.println("\n-------------------------\n");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("User : " + name + "( chat id: " + chatId + " )");
        System.out.println("message from user : " + messageFromUser);
        System.out.println("message from bot: " + messageFromBot);
    }
}
