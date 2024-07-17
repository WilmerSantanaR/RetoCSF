package com.example.empresa.services;

import com.example.empresa.model.Notification;
import com.example.empresa.model.User;
import com.example.empresa.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService; // Suponiendo que tienes un UserService para manejar usuarios

    @Transactional
    public Notification createNotification(Long userId, String message) {
        User user = userService.getUserById(userId); // Obtener el usuario por su ID
        Notification notification = new Notification(user, message);
        return notificationRepository.save(notification);
    }

    @Transactional
    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found with id: " + notificationId));
        notification.markAsRead();
        notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<Notification> getNotificationsByUser(Long userId) {
        User user = userService.getUserById(userId); // Obtener el usuario por su ID
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }
}
