package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO {
    Long id;
    Date dateCreate;
    int type;
    String message;

    public NotificationDTO(Date dateCreate, int type, String message) {
        this.dateCreate = dateCreate;
        this.type = type;
        this.message = message;
    }
}
