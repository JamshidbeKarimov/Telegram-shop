package service;

import enums.RoleUser;
import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService extends UserRepository {
    @Override
    public User get(UUID id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getList() {
        return userList;
    }

    @Override
    public List<User> getList(UUID id) {
        return null;
    }

    @Override
    public String add(User user) {
        if (isPhoneNumberExist(user.getPhoneNumber())) {
            return ERROR_USER_ALREADY_EXIST;
        }
        userList.add(user);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, User editedUser) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                user.setUsername(editedUser.getUsername());
                user.setBalance(editedUser.getBalance());
                user.setPassword(editedUser.getPassword());
                user.setRole(editedUser.getRole());
                user.setPhoneNumber(editedUser.getPhoneNumber());
                user.setSmsCode(editedUser.getSmsCode());
                user.setActive(editedUser.isActive());
                user.setName(editedUser.getName());
                user.setCreatedBy(editedUser.getCreatedBy());
                user.setUpdatedBy(editedUser.getUpdatedBy());
                user.setCreatedDate(editedUser.getCreatedDate());
                user.setUpdatedDate(editedUser.getUpdatedDate());

                return SUCCESS;
            }
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    public String toggleActivation(UUID id) {
        return null;
    }

    @Override
    protected List<User> getUsers(RoleUser roleUser) {
        List<User> users = new ArrayList<>();
        for (User user : userList) {
            if (user.getRole().equals(roleUser)) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public String toggleActivation(String phoneNumber) {
        for (User user : userList) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                user.setActive(!user.isActive());
                return SUCCESS;
            }
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    public User login(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean isUsernameExist(String username) {
        for (User user : userList) {
         if (user.getUsername().equals(username))
             return true;
        }
        return false;
    }

    public boolean isPhoneNumberExist(String phoneNumber) {
        for (User user : userList) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
