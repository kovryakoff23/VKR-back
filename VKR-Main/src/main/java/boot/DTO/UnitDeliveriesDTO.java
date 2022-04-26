package boot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class UnitDeliveriesDTO {
    Long id;
    String name;
    Date dateCreate;
    Date dateComplete;
    String description;
    boolean status;
    private UnitDTO unitDTO;
    @JsonIgnore
    private Set<UnitDeliveriesPositionDTO> unitDeliveriesPositionsDTO = new HashSet<>();
}
