package boot.mapstruct;

import boot.DTO.UnitDeliveriesPositionDTO;
import boot.entity.UnitDeliveriesPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {UnitDeliveriesMapper.class})
public interface UnitDeliveriesPositionMapper {
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "unitDeliveriesPosition.unitDeliveries", target = "unitDeliveriesDTO")
    UnitDeliveriesPositionDTO toDTO (UnitDeliveriesPosition unitDeliveriesPosition);
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "unitDeliveriesPositionDTO.unitDeliveriesDTO", target = "unitDeliveries")
    @Mapping(source = "unitDeliveriesPositionDTO.suppliersDTO.name", target = "nameSupplier")
    UnitDeliveriesPosition toEntity (UnitDeliveriesPositionDTO unitDeliveriesPositionDTO);

}
