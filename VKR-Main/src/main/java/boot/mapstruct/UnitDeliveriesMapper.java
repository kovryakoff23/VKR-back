package boot.mapstruct;

import boot.DTO.UnitDeliveriesDTO;
import boot.DTO.UnitDeliveriesPositionDTO;
import boot.entity.UnitDeliveries;
import boot.entity.UnitDeliveriesPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {UnitMapper.class})
public interface UnitDeliveriesMapper {
    @Mapping(source = "unitDeliveries.unit", target = "unitDTO")
    @Mapping(source = "unitDeliveries.unitDeliveriesPositions", target = "unitDeliveriesPositionsDTO")
    UnitDeliveriesDTO toDTO (UnitDeliveries unitDeliveries);
    @Mapping(source = "unitDeliveriesDTO.unitDTO", target = "unit")
    @Mapping(source = "unitDeliveriesDTO.unitDeliveriesPositionsDTO", target = "unitDeliveriesPositions")
    UnitDeliveries toEntity (UnitDeliveriesDTO unitDeliveriesDTO);

    Set<UnitDeliveriesPositionDTO> toAllUnitDeliveriesPositionDTO(Set<UnitDeliveriesPosition> unitDeliveriesPositions);

    Set<UnitDeliveriesPosition> toAllUnitDeliveriesPosition(Set<UnitDeliveriesPositionDTO> unitDeliveriesPositionDTOS);

    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitDeliveriesPositionDTO toDTO (UnitDeliveriesPosition unitDeliveriesPosition);

    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitDeliveriesPosition toEntity (UnitDeliveriesPositionDTO unitDeliveriesPositionDTO);

}
