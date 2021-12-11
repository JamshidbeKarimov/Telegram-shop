package repository;

import model.MyCart;
import model.Product;

import java.util.List;
import java.util.UUID;

public abstract class MyCartRepository implements BaseRepository<MyCart, String, List<MyCart>> {

    protected abstract String buy(List<MyCart> myCartList);

    protected abstract String addToCart(Product product, UUID userId, int amount);

}
