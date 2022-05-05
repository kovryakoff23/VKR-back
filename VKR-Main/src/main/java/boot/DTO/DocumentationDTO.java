package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocumentationDTO {
    Long id;
    String name;
    private UnitDTO unitDTO;

    public DocumentationDTO(String name, UnitDTO unitDTO) {
        this.name = name;
        this.unitDTO = unitDTO;
    }
}
