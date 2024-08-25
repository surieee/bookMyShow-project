package com.wg.bookmyshow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wg.bookmyshow.config.DBConnection;
import com.wg.bookmyshow.model.NotificationModel;

public class NotificationDao {

    // Method to insert a new notification
    public boolean insertNotification(NotificationModel notification) {
        String query = "INSERT INTO Notification (notification_id, message, priority, account_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, notification.getNotificationId());
            stmt.setString(2, notification.getMessage());
            stmt.setString(3, notification.getPriority());
            stmt.setString(4, notification.getUserId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve notifications for an account
    public List<NotificationModel> getNotificationsByAccountId(String accountId) {
        String query = "SELECT * FROM Notification WHERE account_id = ?";
        List<NotificationModel> notifications = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    NotificationModel notification = new NotificationModel();
                    notification.setNotificationId(rs.getString("notification_id"));
                    notification.setMessage(rs.getString("message"));
                    notification.setPriority(rs.getString("priority"));
                    notification.setUserId(rs.getString("account_id"));

                    notifications.add(notification);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
