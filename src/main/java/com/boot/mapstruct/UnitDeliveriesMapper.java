package com.boot.mapstruct;

import com.boot.DTO.UnitDeliveriesDTO;
import com.boot.DTO.UnitDeliveriesPositionDTO;
import com.boot.entity.UnitDeliveries;
import com.boot.entity.UnitDeliveriesPosition;
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
//

    @Mapping(source = "unitDeliveriesPosition.suppliers", target = "suppliersDTO")
    @Mapping(source = "unitDeliveriesPosition.paymentSupplier", target = "paymentSupplierDTO")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitDeliveriesPositionDTO toDTO (UnitDeliveriesPosition unitDeliveriesPosition);

    @Mapping(source = "unitDeliveriesPositionDTO.suppliersDTO", target = "suppliers")
    @Mapping(source = "unitDeliveriesPositionDTO.paymentSupplierDTO", target = "paymentSupplier")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitDeliveriesPosition toEntity (UnitDeliveriesPositionDTO unitDeliveriesPositionDTO);
//    UnitDTO toUnitDTO (Unit unit);
//
//    Unit toUnit (UnitDTO unitDTO);
}
