package com.boot.controller;

import com.boot.DTO.NotificationDTO;
import com.boot.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

    NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("notification")
    public List<NotificationDTO> getNotification() {
        return notificationService.getAll();
    }

    @DeleteMapping("notification")
    public void deleteAll() {
        notificationService.deleteAll();
    }
}
