package com.boot.mapstruct;

import com.boot.DTO.NotificationDTO;
import com.boot.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDTO (Notification notification);

    Notification toEntity (NotificationDTO notificationDTO);
}
