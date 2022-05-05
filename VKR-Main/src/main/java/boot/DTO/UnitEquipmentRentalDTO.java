package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class UnitEquipmentRentalDTO {
    Long id;
    String nameEquipment;
    Date dateUse;
    Long price;
    String note;
    private UnitDTO unitDTO;
}
