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
public class UnitProductionsDTO {
    Long id;
    Date dateStartWork;
    Date dateEndWork;
    String nameWork;
    String description;
    boolean status;
    private UnitDTO unitDTO;
    @JsonIgnore
    private Set<UnitProductionPositionDTO> unitProductionPositionsDTO = new HashSet<>();

}
