package service;

import enums.RoleUser;
import model.User;
import repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService extends UserRepository {
    @Override
    public User get(UUID id) {
        return null;
    }

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public List<User> getList(UUID id) {
        return null;
    }

    @Override
    public String add(User user) {
        return null;
    }

    @Override
    public String editById(UUID id, User user) {
        return null;
    }

    @Override
    public String toggleActivation(UUID id) {
        return null;
    }

    @Override
    protected List<User> getUsers(RoleUser roleUser) {
        return null;
    }
}
