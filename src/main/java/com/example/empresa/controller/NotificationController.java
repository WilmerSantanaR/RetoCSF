package com.example.empresa.controller;

import com.example.empresa.model.Notification;
import com.example.empresa.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestParam Long userId, @RequestParam String message) {
        Notification notification = notificationService.createNotification(userId, message);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/markAsRead")
    public ResponseEntity<?> markNotificationAsRead(@PathVariable("id") Long id) {
        notificationService.markNotificationAsRead(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable("userId") Long userId) {
        List<Notification> notifications = notificationService.getNotificationsByUser(userId);
        return ResponseEntity.ok(notifications);
    }
}
