package com.wg.bookmyshow.service;

import com.wg.bookmyshow.dao.NotificationDao;
import com.wg.bookmyshow.model.NotificationModel;

import java.util.List;

public class NotificationService {

    private NotificationDao notificationDao;

    public NotificationService() {
        this.notificationDao = new NotificationDao();
    }

    // Send a notification
    public void sendNotification(NotificationModel notification) {
        boolean success = notificationDao.insertNotification(notification);
        if (success) {
            System.out.println("Notification sent successfully.");
        } else {
            System.out.println("Failed to send notification.");
        }
    }

    // Get notifications for an account
    public List<NotificationModel> getNotification(String accountId) {
        List<NotificationModel> notifications = notificationDao.getNotificationsByAccountId(accountId);
        if (notifications.isEmpty()) {
            System.out.println("No notifications found for this account.");
        } else {
            for (NotificationModel notification : notifications) {
                System.out.println(notification);
            }
        }
		return notifications;
    }

	public boolean addNotification(NotificationModel notification) {
		// TODO Auto-generated method stub
		return false;
	}
}
