package service;

import model.MyCart;
import model.Product;
import repository.MyCartRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyCartService extends MyCartRepository {
    @Override
    public MyCart get(UUID cartId) {
        return null;
    }

    @Override
    public List<MyCart> getList() {
        return myCartList;
    }

    @Override
    public List<MyCart> getList(UUID userId) {
        List<MyCart> myCarts = new ArrayList<>();

        for (MyCart m:myCartList) {
            if(m.getUserId().equals(userId))
                myCarts.add(m);
        }

        return myCarts;
    }

    @Override
    public String add(MyCart myCart) {

        return null;
    }

    @Override
    protected String addToCart(Product product, UUID userId, int amount) {
        if (product.getAmount() < amount) return ERROR_PRODUCT_AMOUNT_NOT_ENOUGH;
        int index = 0;
        for (MyCart myCard : myCartList) {
            if (myCard.getUserId() == userId && myCard.getProductId() == product.getId() && myCard.isActive()) {
                myCard.setAmount(myCard.getAmount() + amount);
                myCard.setCreatedDate(LocalDateTime.now());
                myCartList.set(index, myCard);
                return SUCCESS;
            }
            index++;
        }

        MyCart myCart = new MyCart(userId, product.getId(), false, product.getPrice(), amount);
        myCart.setCreatedDate(LocalDateTime.now());
        myCartList.add(myCart);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, MyCart myCart) {
        return null;
    }

    @Override
    public String toggleActivation(UUID id) {
        return null;
    }

    @Override
    protected String buy(List<MyCart> cartsToBuy) {
        return null;
    }
}
