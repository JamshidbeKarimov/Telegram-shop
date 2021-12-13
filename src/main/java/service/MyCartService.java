package service;

import jsonFile.CollectionsTypeFactory;
import jsonFile.FileUrls;
import jsonFile.FileUtils;
import jsonFile.Json;
import lombok.SneakyThrows;
import model.MyCart;
import repository.MyCartRepository;

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
        return getMyCartListFromFile();
    }

    @Override
    public List<MyCart> getList(UUID userId) {
        List<MyCart> myCarts = new ArrayList<>();

        for (MyCart m : getMyCartListFromFile()) {
            if(m.getUserId().equals(userId))
                myCarts.add(m);
        }

        return myCarts;
    }

    @Override
    public String add(MyCart myCart) {
        List<MyCart> myCartList = getMyCartListFromFile();

        myCartList.add(myCart);

        setMyCartListToFile(myCartList);
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

    public List<MyCart> getMyCartListFromFile() {
        String myCartJsonStringFromFile = FileUtils.readFromFile(FileUrls.myCartUrl);
        List<MyCart> myCartList;
        try {
            myCartList = Json.objectMapper.readValue(myCartJsonStringFromFile, CollectionsTypeFactory.listOf(MyCart.class));
        } catch (Exception e) {
            System.out.println(e);
            myCartList = new ArrayList<>();
        }
        return myCartList;
    }

    @SneakyThrows
    public void setMyCartListToFile(List<MyCart> myCartList) {
        String newMyCartJsonFromObject = Json.prettyPrint(myCartList);
        FileUtils.writeToFile(FileUrls.userUrl, newMyCartJsonFromObject);
    }
}
