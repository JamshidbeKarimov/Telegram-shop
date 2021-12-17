package service;

import jsonFile.CollectionsTypeFactory;
import jsonFile.FileUrls;
import jsonFile.FileUtils;
import jsonFile.Json;
import lombok.SneakyThrows;
import model.Notification;
import repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationService extends NotificationRepository {
    @Override
    public Notification get(UUID id) {
        for (Notification n: getNotificationListFromFile()) {
            if(n.getId().equals(id))
                return n;
        }
        return null;
    }

    @Override
    public List<Notification> getList() {
        List<Notification> notifications = new ArrayList<>();
        for (Notification n: getNotificationListFromFile()) {
            if (!n.isActive())
                notifications.add(n);
        }
        return notifications;
    }

    @Override
    public List<Notification> getList(UUID id) {
        return null;
    }

    @Override
    public String add(Notification notification) {
        List<Notification> notificationList = getNotificationListFromFile();
        notificationList.add(notification);
        setNotificationListToFile(notificationList);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, Notification notification) {
        List<Notification> notificationList = getNotificationListFromFile();
        for (Notification n: notificationList) {
            if (n.getId().equals(id)){
                notification.setActive(true);

                setNotificationListToFile(notificationList);
                return SUCCESS;
            }
        }
        return ERROR_NOTIFICATION_NOT_FOUND;
    }

    public boolean acceptReject(int action){

        return action == 1; // accepted
    }

    public List<Notification> getNotificationListFromFile() {
        String notificationJsonStringFromFile = FileUtils.readFromFile(FileUrls.notificationUrl);
        List<Notification> notificationList;
        try {
            notificationList = Json.objectMapper.readValue(notificationJsonStringFromFile, CollectionsTypeFactory.listOf(Notification.class));
        } catch (Exception e) {
            System.out.println(e);
            notificationList = new ArrayList<>();
        }
        return notificationList;
    }

    @SneakyThrows
    public void setNotificationListToFile(List<Notification> notificationList) {
        String newNotificationJsonFromObject = Json.prettyPrint(notificationList);
        FileUtils.writeToFile(FileUrls.notificationUrl, newNotificationJsonFromObject);
    }
}
