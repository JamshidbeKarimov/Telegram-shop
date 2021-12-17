package telegramBot.userBot;

import enums.RoleUser;
import lombok.SneakyThrows;
import model.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramBot.admin.AdminBot;

public class UserBot extends TelegramLongPollingBot implements UserInterface {


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

        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            User currentUser = userService.login(chatId);
            UserState userState = UserState.MAIN_MENU;


            if (message.hasText() && !message.getText().equals("/start")){
                String text = message.getText();

                if(text.equals("📋 Categories"))
                    userState = UserState.CATEGORIES;

                switch (userState){
                    case CATEGORIES: {
                        currentUser.setUserState(UserState.PRODUCTS);
                        userService.editByChatId(chatId, currentUser);
                        execute(categories(chatId));
                    }
                    break;
                }


            } else if (message.hasContact()) {
                userState = UserState.START_CONTACT_SHARED;
            } else if (message.hasLocation()) {
                userState = UserState.START_LOCATION;
            } else if (currentUser == null) {
                userState = UserState.START_NEW_USER;
            } else if (currentUser.getLocation() == null) {
                execute(shareLocation(chatId));
                return;
            } else if(message.getText().equals("/start")){
                userState = UserState.MAIN_MENU;
            }
            else
                userState = currentUser.getUserState();

            switch (userState) {
                case START_NEW_USER: {
                    execute (sharePhoneNumber(chatId));
                }
                break;
                case START_CONTACT_SHARED: {
                    String phoneNumber = message.getContact().getPhoneNumber();
                    User user = new User();
                    user.setRole(RoleUser.USER);
                    user.setPhoneNumber(phoneNumber);
                    user.setChatId(chatId);
                    if (message.getChat().getUserName() != null)
                        user.setUsername(message.getChat().getUserName());

                    user.setName(message.getContact().getFirstName());
                    user.setUserState(UserState.START_LOCATION);
                    userService.add(user);

                    execute(shareLocation(chatId));
                }
                break;
                case START_LOCATION: {
                    Location location = message.getLocation();
                    currentUser.setLocation(location);
                    currentUser.setUserState(UserState.MAIN_MENU);
                    userService.editByChatId(chatId, currentUser);

                    execute(userMainMenu(chatId));
                }
                break;
                case MAIN_MENU: {
                        execute(userMainMenu(chatId));
                }
                break;
                default:

            }
        }
        else if (update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();

            String callData = callbackQuery.getData();
            Integer messageId = callbackQuery.getMessage().getMessageId();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            User currentUser = userService.login(chatId);

            UserState userState = currentUser.getUserState();

            if (callData.equals("backToCategories"))
                userState = UserState.CATEGORIES;
            else if (callData.equals("backToProductList"))
                userState = UserState.PRODUCTS;


            switch (userState){
                case PRODUCTS: {
                    currentUser.setUserState(UserState.PRODUCT_INFO);
                    userService.editByChatId(chatId, currentUser);
                    execute(products(callData, messageId, chatId));
                }break;
                case CATEGORIES: {
                    currentUser.setUserState(UserState.PRODUCTS);
                    userService.editByChatId(chatId, currentUser);
                    execute(editToCategories(messageId, chatId));
                }
                case PRODUCT_INFO:{
                    currentUser.setUserState(UserState.ADD_TO_CART);
                    userService.editByChatId(chatId, currentUser);
                    execute(productInfo(chatId, messageId, callData));
                }
                break;
            }

        }

    }
}
