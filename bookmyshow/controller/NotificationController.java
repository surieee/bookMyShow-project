package com.wg.bookmyshow.controller;

import com.wg.bookmyshow.model.NotificationModel;
import com.wg.bookmyshow.service.NotificationService;

import java.util.List;

public class NotificationController {
    private NotificationService notificationService;

    public NotificationController() {
        this.notificationService = new NotificationService();
    }

    public boolean addNotification(NotificationModel notification) {
        return notificationService.addNotification(notification);
    }

//    public List<NotificationModel> getNotificationsByAccountId(String accountId) {
//        return notificationService.getNotification(accountId);
//    }
}
