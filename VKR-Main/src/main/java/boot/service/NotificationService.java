package boot.service;

import boot.DTO.NotificationDTO;
import boot.mapstruct.NotificationMapper;
import boot.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService implements ServiceMag<NotificationDTO> {

    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationDTO get(long id) {
        return notificationMapper.toDTO(notificationRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<NotificationDTO> getAll() {
        return notificationRepository.findAll().stream()
                .map(value -> notificationMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(NotificationDTO entity) {
        notificationRepository.save(notificationMapper.toEntity(entity));
    }

    @Override
    public void delete(long id) {
        notificationRepository.deleteById(id);
    }

    public void deleteAll(){
        notificationRepository.deleteAll();
    }
}
