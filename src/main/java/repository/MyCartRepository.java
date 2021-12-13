package repository;

import model.MyCart;

import java.util.List;

public abstract class MyCartRepository implements BaseRepository<MyCart, String, List<MyCart>> {

    protected abstract String buy(List<MyCart> myCartList);
}
