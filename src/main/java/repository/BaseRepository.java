package repository;

import model.*;
import response.BaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BaseRepository<T, R, RL> extends BaseResponse {
    List<User> userList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<Notification> notificationList = new ArrayList<>();
    List<MyCart> myCartList = new ArrayList<>();
    List<History> historyList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();

    T get(UUID id);
    RL getList();
    RL getList(UUID id);
    R add(T t);
    R editById(UUID id, T t);
    R toggleActivation(UUID id);
}
