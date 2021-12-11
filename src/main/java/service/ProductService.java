package service;

import model.MyCart;
import model.Product;
import repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Product> getList(UUID id) { // NOT USED
        return null;
    }

    @Override
    public String add(Product newProduct) {//Agar maxsulot mavjud bo'lsa, sonini oshiritb qoyadi va narxi yangi maxsulot kabi bo'lib qoladi;
        newProduct.setActive(true);
        int index = 0;
        for (Product product : productList) {
            if (product.getName().equals(newProduct.getName())
                    && product.getCategoryId() == newProduct.getCategoryId()
                    && product.getSellerId() == newProduct.getSellerId()) {

                productList.set(index, editProduct(product, newProduct));
                return SUCCESS;
            }
            index++;
        }
        productList.add(newProduct);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, Product product) { // NOT USED
        return null;
    }

    @Override
    public String toggleActivation(UUID productId) {
        int index = 0;
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                product.setActive(!product.isActive());
                productList.set(index, product);
                return SUCCESS;
            }
            index++;
        }
        return ERROR_ID_NOT_FOUND;
    }



        MyCart myCart = new MyCart(userId, product.getId(), false, product.getPrice(), amount, product.getName());
        myCart.setCreatedDate(new Date());
        myCartList.add(myCart);
        return SUCCESS;
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

    private Product editProduct(Product oldProduct, Product newProduct) {
        oldProduct.setActive(newProduct.isActive());
        if (newProduct.getPrice() > 0) {
            oldProduct.setPrice(newProduct.getPrice());
        }
        if (newProduct.getAmount() > 0) {
            if (oldProduct.isActive()) {
                oldProduct.setAmount(oldProduct.getAmount() + newProduct.getAmount());
            } else {
                oldProduct.setAmount(newProduct.getAmount());
            }
        }
        if (newProduct.getProductInfo() != null) {
            oldProduct.setProductInfo(newProduct.getProductInfo());
        }
        return oldProduct;
    }
}
