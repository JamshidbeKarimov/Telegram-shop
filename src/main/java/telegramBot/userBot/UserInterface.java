package telegramBot.userBot;

import lombok.SneakyThrows;
import model.Category;
import model.Product;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public interface UserInterface {

    String BOT_USERNAME = "My_Secret_Tester_Bot";
    String BOT_TOKEN = "5042642340:AAHkp3vmhU9LJrr1x4CerG0Fj5sGGoK2VNo";

    UserService userService = new UserService();
    CategoryService categoryService = new CategoryService();
    HistoryService historyService = new HistoryService();
    MyCartService myCartService = new MyCartService();

    ProductService productService = new ProductService();

    default SendMessage userMainMenu(String chatId){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("\uD83D\uDCCB Categories");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("\uD83D\uDCD1 History");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("\uD83D\uDED2 My Cart");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBB\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB EDIT PROFILE");
        keyboardRows.add(row);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        SendMessage sendMessage = new SendMessage(chatId, "MAIN MENU");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    default SendMessage categories(String chatId){
        SendMessage sendMessage = new SendMessage(chatId, "\uD83D\uDCCB Categories");

        if (categoriesInlineMarkup() == null){
            sendMessage.setText("❗️ NO CATEGORIES AVAILABLE ❗️");
            return sendMessage;
        }
        else {
            sendMessage.setReplyMarkup(categoriesInlineMarkup());
        }
        return sendMessage;
    }

    default EditMessageText products(String categoryName, Integer messageId, String chatId){
        EditMessageText editCategoryToProduct = new EditMessageText();
        editCategoryToProduct.setText("==== PRODUCTS ====");
        editCategoryToProduct.setChatId(chatId);
        editCategoryToProduct.setMessageId(messageId);


        UUID categoryId = UUID.randomUUID();

        for(Category category:categoryService.getList()){
            if(category.getName().equals(categoryName))
                categoryId = category.getId();
        }


        InlineKeyboardMarkup productMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        int i = 2;

        List<Product> products = productService.getListByCategoryId(categoryId);

        if(products.size() == 0){
            editCategoryToProduct.setText("❗️ NO PRODUCTS AVAILABLE IN THIS CATEGORY ❗️");
        } else {
            for (Product product : products) {
                if (product.getCategoryId().equals(categoryId)) {
                    i--;

                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(product.getName());
                    button.setCallbackData(String.valueOf(product.getId()));

                    row.add(button);

                    if (i == 0) {
                        rows.add(row);
                        i = 2;
                        row = new ArrayList<>();
                    }
                }
            }

            if (row.size() != 0) {
                rows.add(row);
            }
        }

        row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("⬅️ back");
        button.setCallbackData("backToCategories");

        row.add(button);

        rows.add(row);

        productMarkup.setKeyboard(rows);
        editCategoryToProduct.setReplyMarkup(productMarkup);

        return editCategoryToProduct;
    }

    default EditMessageText editToCategories(Integer messageId, String chatId){
        EditMessageText backToCategories = new EditMessageText();
        backToCategories.setText("\uD83D\uDCCB Categories");
        backToCategories.setReplyMarkup(categoriesInlineMarkup());
        backToCategories.setMessageId(messageId);
        backToCategories.setChatId(chatId);

        return backToCategories;
    }

    @SneakyThrows
    default SendMessage sharePhoneNumber(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Welcome \uD83D\uDE0A\nSend your phone number");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText("\uD83D\uDCDE Share your phone number >");
        button.setRequestContact(true);

        keyboardRow.add(button);
        keyboard.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return sendMessage;
    }
    @SneakyThrows
    default SendMessage shareLocation(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Share your location >");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText("\uD83D\uDCCD Share location >");
        button.setRequestLocation(true);

        keyboardRow.add(button);
        keyboard.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return sendMessage;
    }

    default InlineKeyboardMarkup categoriesInlineMarkup() {
        InlineKeyboardMarkup categoriesMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineButtonsRows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        List<Category> categories = categoryService.getCategoryListFromFile();

        if(categories.size() == 0){
            return null;
        }
        for (Category category: categories) {
            if (category.isActive()) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(category.getName());
                button.setCallbackData(category.getName());

                row.add(button);

                if (row.size() == 2) {
                    inlineButtonsRows.add(row);
                    row = new ArrayList<>();
                }
            }
        }

        if (row.size() != 0) {
            inlineButtonsRows.add(row);
        }

        categoriesMarkup.setKeyboard(inlineButtonsRows);

        return categoriesMarkup;
    }

    default EditMessageText productInfo(String chatId, Integer messageId, String productIdStr){
        EditMessageText info = new EditMessageText();
        info.setChatId(chatId);
        info.setMessageId(messageId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton("ADD TO CART \uD83D\uDED2");
        button.setCallbackData("cart");
        row.add(button);

        button = new InlineKeyboardButton("⬅️ back");
        button.setCallbackData("backToProductList");
        row.add(button);

        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        info.setReplyMarkup(inlineKeyboardMarkup);

        Product product = new Product();

        for (Product p: productService.getList()) {
            if (p.getId().toString().equals(productIdStr)){
                info.setText(p.getProductInfo());
                return info;
            }
        }

        return info;
    }

}
