package service;

import model.Notification;
import repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationService extends NotificationRepository {
    @Override
    public Notification get(UUID id) {
        for (Notification n: notificationList) {
            if(n.getId().equals(id))
                return n;
        }
        return null;
    }

    @Override
    public List<Notification> getList() {
        List<Notification> notifications = new ArrayList<>();
        for (Notification n: notificationList) {
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
        notificationList.add(notification);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, Notification notification) {
        int ind = 0;
        for (Notification n: notificationList) {
            if (n.getId().equals(id)){
                notification.setActive(true);
                notificationList.set(ind, notification);
                return SUCCESS;
            }
        }
        return ERROR_NOTIFICATION_NOT_FOUND;
    }


    // isActive --> true
    @Override
    public String toggleActivation(UUID id) {
        for (Notification n: notificationList) {
            if (n.getId().equals(id)){
                n.setActive(!n.isActive());
                return SUCCESS;
            }
        }
        return ERROR_NOTIFICATION_NOT_FOUND;
    }

    public boolean acceptReject(int action){
        //
        return action == 1; // accepted
    }
}
