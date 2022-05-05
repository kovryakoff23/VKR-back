package boot.mapstruct;

import boot.DTO.DocumentationDTO;
import boot.entity.Documentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UnitMapper.class})
public interface DocumentationMapper {
    @Mapping(source = "documentation.unit", target = "unitDTO")
    DocumentationDTO documentationToDocumentationDto (Documentation documentation);
    @Mapping(source = "documentationDTO.unitDTO", target = "unit")
    Documentation documentationDtoToDocumentation (DocumentationDTO documentationDTO);

}
