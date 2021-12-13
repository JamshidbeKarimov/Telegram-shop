package service;

import enums.RoleUser;
import jsonFile.CollectionsTypeFactory;
import jsonFile.FileUrls;
import jsonFile.FileUtils;
import jsonFile.Json;
import lombok.SneakyThrows;
import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService extends UserRepository {
    @Override
    public User get(UUID userId) {
        for (User user : getUserListFromFile()) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getList() {
        return getUserListFromFile();
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
        List<User> userList = getUserListFromFile();
        userList.add(user);
        setUserListToFile(userList);
        return SUCCESS;
    }

    @Override
    public String editById(UUID userId, User editedUser) {
        List<User> userList = getUserListFromFile();
        for (User user : userList) {
            if (user.getId().equals(userId)) {
                user.setUsername(editedUser.getUsername());
                user.setBalance(editedUser.getBalance());
                user.setRole(editedUser.getRole());
                user.setPhoneNumber(editedUser.getPhoneNumber());
                user.setActive(editedUser.isActive());
                user.setName(editedUser.getName());
                user.setCreatedBy(editedUser.getCreatedBy());
                user.setUpdatedBy(editedUser.getUpdatedBy());
                user.setCreatedDate(editedUser.getCreatedDate());
                user.setUpdatedDate(editedUser.getUpdatedDate());

                setUserListToFile(userList);
                return SUCCESS;
            }
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    public String toggleActivation(UUID userId) {   // user id boyicha activate qilish
        List<User> userList = getUserListFromFile();
        for (User user : userList) {
            if (user.getId().equals(userId)) {
                user.setActive(!user.isActive());

                setUserListToFile(userList);
                return SUCCESS;
            }
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    protected List<User> getUsers(RoleUser roleUser) {
        List<User> users = new ArrayList<>();
        for (User user : getUserListFromFile()) {
            if (user.getRole().equals(roleUser)) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public String toggleActivation(String phoneNumber) {  // phone boyicha activate qilish
        List<User> userList = getUserListFromFile();
        for (User user : userList) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                user.setActive(!user.isActive());
                setUserListToFile(userList);
                return SUCCESS;
            }
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    public User login(String username) {
        for (User user : getUserListFromFile()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean isUsernameExist(String username) {
        for (User user : getUserListFromFile()) {
         if (user.getUsername().equals(username))
             return true;
        }
        return false;
    }

    public boolean isPhoneNumberExist(String phoneNumber) {
        for (User user : getUserListFromFile()) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUserListFromFile() {
        String userJsonStringFromFile = FileUtils.readFromFile(FileUrls.userUrl);
        List<User> userList;
        try {
            userList = Json.objectMapper.readValue(userJsonStringFromFile, CollectionsTypeFactory.listOf(User.class));
        } catch (Exception e) {
            System.out.println(e);
            userList = new ArrayList<>();
        }
        return userList;
    }

    @SneakyThrows
    public void setUserListToFile(List<User> userList) {
        String newUserJsonFromObject = Json.prettyPrint(userList);
        FileUtils.writeToFile(FileUrls.userUrl, newUserJsonFromObject);
    }
}
