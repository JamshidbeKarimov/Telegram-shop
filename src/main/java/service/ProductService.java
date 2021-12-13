package service;

import model.MyCart;
import model.Product;
import repository.ProductRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductService extends ProductRepository {
    @Override
    public Product get(UUID productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getList() {
        return productList;
    }

    @Override
    public List<Product> getList(UUID categoryOrSellerId) { // ishlatma bu function ni
        return null;
    }

    @Override
    public String add(Product newProduct) {//Agar maxsulot mavjud bo'lsa, sonini oshiritb qoyadi va narxi yangi maxsulot kabi bo'lib qoladi;
        newProduct.setActive(true);
        for (Product product : productList) {
            if (product.getName().equals(newProduct.getName())
                    && product.getCategoryId() == newProduct.getCategoryId()
                    && product.getSellerId() == newProduct.getSellerId()) {
                editById(product.getId(), newProduct);
                return SUCCESS;
            }
        }
        productList.add(newProduct);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, Product editingProduct) { // NOT USED

        for (Product product : productList) {
            if (product.getId().equals(id)) {
                if (editingProduct.getProductInfo() != null) {
                    product.setProductInfo(editingProduct.getProductInfo());
                }
                if (editingProduct.getAmount() != 0) {
                    product.setAmount(editingProduct.getAmount());
                }
                if (editingProduct.getPrice() != 0) {
                    product.setAmount(editingProduct.getAmount());
                }
                if (editingProduct.getName() != null) {
                    product.setName(editingProduct.getName());
                }
                product.setUpdatedDate(editingProduct.getCreatedDate());
                return SUCCESS;
            }
        }
        return ERROR_PRODUCT_NOT_FOUND;
    }

    @Override
    public String toggleActivation(UUID productId) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                product.setActive(!product.isActive());
                return SUCCESS;
            }
        }
        return ERROR_ID_NOT_FOUND;
    }

    @Override
    protected List<Product> getListByCategoryId(UUID categoryId) {
        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategoryId() == categoryId && product.isActive()) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    protected List<Product> getListBySellerId(UUID sellerId) {
        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            if (product.getSellerId() == sellerId && product.isActive()) {
                products.add(product);
            }
        }
        return products;
    }
}
