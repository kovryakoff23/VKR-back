package com.boot.controller;

import com.boot.entity.Notification;
import com.boot.entity.UnitProductionPosition;
import com.boot.entity.UnitProductions;
import com.boot.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

    NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("notification")
    public List<Notification> getNotification() {
        return notificationService.getAll();
    }

    @DeleteMapping("notification")
    public void deleteAll() {
        notificationService.deleteAll();
    }
}
