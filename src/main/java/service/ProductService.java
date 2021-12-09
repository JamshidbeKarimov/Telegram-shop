package service;

import model.Product;
import repository.ProductRepository;

import java.util.List;
import java.util.UUID;

public class ProductService extends ProductRepository {
    @Override
    public Product get(UUID id) {
        return null;
    }

    @Override
    public List<Product> getList() {
        return null;
    }

    @Override
    public List<Product> getList(UUID id) {
        return null;
    }

    @Override
    public String add(Product product) {
        return null;
    }

    @Override
    public String editById(UUID id, Product product) {
        return null;
    }

    @Override
    public String toggleActivation(UUID id) {
        return null;
    }

    @Override
    protected String addToCart(Product product) {
        return null;
    }
}
