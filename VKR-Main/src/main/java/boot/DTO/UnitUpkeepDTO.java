package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitUpkeepDTO {
    Long id;
    String name;
    Long price;
    String note;
    private UnitDTO unitDTO;
}
