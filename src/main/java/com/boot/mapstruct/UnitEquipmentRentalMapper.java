package com.boot.mapstruct;

import com.boot.DTO.UnitEquipmentRentalDTO;
import com.boot.entity.UnitEquipmentRental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {UnitMapper.class})
public interface UnitEquipmentRentalMapper {
    @Mapping(source = "unitEquipmentRental.unit", target = "unitDTO")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitEquipmentRentalDTO toDTO (UnitEquipmentRental unitEquipmentRental);
    @Mapping(source = "unitEquipmentRentalDTO.unitDTO", target = "unit")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitEquipmentRental toEntity (UnitEquipmentRentalDTO unitEquipmentRentalDTO);

//    UnitDTO toUnitDTO (Unit unit);
//
//    Unit toUnit (UnitDTO unitDTO);
}
