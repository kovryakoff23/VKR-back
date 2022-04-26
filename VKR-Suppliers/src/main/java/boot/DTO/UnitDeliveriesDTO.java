package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class UnitDeliveriesDTO {
    Long id;
    String name;
    Date dateCreate;
    Date dateComplete;
}
