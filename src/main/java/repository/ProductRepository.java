package repository;

import model.Product;

import java.util.List;

public abstract class ProductRepository implements BaseRepository<Product,String, List<Product>>{

    protected abstract String addToCart(Product product);
}
