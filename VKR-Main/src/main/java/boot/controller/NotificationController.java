package boot.controller;

import boot.DTO.NotificationDTO;
import boot.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class NotificationController {

    NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("notification")
    @PreAuthorize("hasRole('USER')")
    public List<NotificationDTO> getNotification() {
        return notificationService.getAll();
    }

    @DeleteMapping("notification")
    @PreAuthorize("hasRole('USER')")
    public void deleteAll() {
        notificationService.deleteAll();
    }
}
