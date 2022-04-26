package boot.mapstruct;

import boot.DTO.UnitUpkeepDTO;
import boot.entity.UnitUpkeep;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UnitMapper.class})
public interface UnitUpkeepMapper {
    @Mapping(source = "unitUpkeep.unit", target = "unitDTO")
    UnitUpkeepDTO toDTO (UnitUpkeep unitUpkeep);

    @Mapping(source = "unitUpkeepDTO.unitDTO", target = "unit")
    UnitUpkeep toEntity (UnitUpkeepDTO unitUpkeepDTO);

}
