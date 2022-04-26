package boot.mapstruct;

import boot.DTO.NotificationDTO;
import boot.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDTO (Notification notification);

    Notification toEntity (NotificationDTO notificationDTO);
}
