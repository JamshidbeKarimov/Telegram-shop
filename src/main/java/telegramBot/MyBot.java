package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;


public class MyBot extends TelegramLongPollingBot implements TelegramBotUtils {
    private String chatId;
    private String message;
    private int number;

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
//       update.getMessage().getContact();

        if (update.hasMessage()) {
            this.chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();

            if (isStart(text)) {
                this.message = "Sign up as a ";
                executeMain(1);
            }
            if (checkSignUp(text)){
                this.message = "Welcome back " + text;
                executeMain(2);
            }

            if (getLocation(text)){
                this.message = "your location registered";
                executeMain(2);
            }

        }
        else if (update.hasCallbackQuery()) {
            this.chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String data = update.getCallbackQuery().getData();
            if (isCallBack(data)) {
                this.message = "what are you doing";
                executeMain(3);
            }
        }
    }

    private void executeMain(int isMenu) {
        SendMessage sendMessage = new SendMessage(this.chatId, this.message);
        switch (isMenu) {
            case 1 -> sendMessage.setReplyMarkup(signUp());
            case 2 -> sendMessage.setReplyMarkup(location());
            case 3 -> sendMessage.setReplyMarkup(getButtons());
        }
        try {
            execute(sendMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private ReplyKeyboardMarkup location() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Tashkent");
        keyboardRow1.add("Fergana");

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("Andijan");
        keyboardRow2.add("Namangan");

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Sirdarya");
        keyboardRow3.add("Samarqand");

        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add("Bukhara");
        keyboardRow4.add("Navoi");

        KeyboardRow keyboardRow5 = new KeyboardRow();
        keyboardRow5.add("Jizzax");
        keyboardRow5.add("Qashqadaryo");

        KeyboardRow keyboardRow6 = new KeyboardRow();
        keyboardRow6.add("Surxondaryo");
        keyboardRow6.add("Xorazm");

        KeyboardRow keyboardRow7 = new KeyboardRow();
        keyboardRow7.add("Qoraqalpogiston");


        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);
        keyboardRowList.add(keyboardRow3);
        keyboardRowList.add(keyboardRow4);
        keyboardRowList.add(keyboardRow5);
        keyboardRowList.add(keyboardRow6);
        keyboardRowList.add(keyboardRow7);

        return replyKeyboardMarkup;
    }

    private ReplyKeyboardMarkup signUp() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("seller");

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("user");

        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);

        return replyKeyboardMarkup;
    }

    private ReplyKeyboardMarkup phoneNumber() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Enter your phone number: ");

        keyboardRowList.add(keyboardRow1);

        return replyKeyboardMarkup;
    }




    private InlineKeyboardMarkup getButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtonList = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtonList);

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton((i + 1) + "");
            inlineKeyboardButton.setCallbackData(String.valueOf(i + 1));
            inlineKeyboardButtons.add(inlineKeyboardButton);
            if ((i + 1) % 3 == 0) {
                inlineKeyboardButtonList.add(inlineKeyboardButtons);
                inlineKeyboardButtons = new ArrayList<>();
            }
        }
        return inlineKeyboardMarkup;
    }
}
