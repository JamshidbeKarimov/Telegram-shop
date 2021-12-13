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

    protected String buy(List<MyCart> myCartList) {
        return null;
    }
}
