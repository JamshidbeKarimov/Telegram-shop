package service;

import model.MyCart;
import repository.MyCartRepository;

import java.util.List;
import java.util.UUID;

public class MyCartService extends MyCartRepository {
    @Override
    public MyCart get(UUID id) {
        for (MyCart mycart:myCartList) {

        }
        return null;
    }

    @Override
    public List<MyCart> getList() {
        return null;
    }

    @Override
    public List<MyCart> getList(UUID id) {
        return null;
    }

    @Override
    public String add(MyCart myCart) {
        return null;
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
