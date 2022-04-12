package com.boot.service;

import com.boot.entity.Notification;
import com.boot.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements ServiceMag<Notification> {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification get(long id) {
        return notificationRepository.findById(id).get();
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public void save(Notification entity) {
        notificationRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        notificationRepository.deleteById(id);
    }

    public void deleteAll(){
        notificationRepository.deleteAll();
    }
}
