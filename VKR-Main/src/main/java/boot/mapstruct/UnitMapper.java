package boot.mapstruct;

import boot.DTO.*;
import boot.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;


@Mapper(componentModel = "spring")
public interface UnitMapper {

    @Mapping(source = "unit.unitProductions", target = "unitProductionsDTO")
    @Mapping(source = "unit.unitDeliveries", target = "unitDeliveriesDTO")
    @Mapping(source = "unit.documentations", target = "documentationsDTO")
    @Mapping(source = "unit.unitEquipmentRentals", target = "unitEquipmentRentalsDTO")
    @Mapping(source = "unit.unitUpkeeps", target = "unitUpkeepsDTO")
    UnitDTO unitToUnitDTO(Unit unit);

    @Mapping(source = "unitDTO.unitProductionsDTO", target = "unitProductions")
    @Mapping(source = "unitDTO.unitDeliveriesDTO", target = "unitDeliveries")
    @Mapping(source = "unitDTO.documentationsDTO", target = "documentations")
    @Mapping(source = "unitDTO.unitEquipmentRentalsDTO", target = "unitEquipmentRentals")
    @Mapping(source = "unitDTO.unitUpkeepsDTO", target = "unitUpkeeps")
    Unit unitDTOToUnit(UnitDTO unitDTO);

    Set<UnitProductionsDTO> toAllUnitProductionsDTO(Set<UnitProductions> unitProductions);

    Set<UnitProductions> toAllUnitProductions(Set<UnitProductionsDTO> unitProductionsDTOS);

    Set<DocumentationDTO> toAllDocumentationDTO(Set<Documentation> documentations);

    Set<Documentation> toAllDocumentation(Set<DocumentationDTO> documentationDTOS);

    Set<UnitDeliveriesDTO> toAllUnitDeliveriesDTO(Set<UnitDeliveries> unitDeliveries);

    Set<UnitDeliveries> toAllUnitDeliveries(Set<UnitDeliveriesDTO> unitDeliveriesDTOS);

    Set<UnitEquipmentRentalDTO> toAllUnitEquipmentRentalDTO(Set<UnitEquipmentRental> unitEquipmentRentals);

    Set<UnitEquipmentRental> toAllUnitEquipmentRental(Set<UnitEquipmentRentalDTO> unitEquipmentRentalDTOS);

    Set<UnitUpkeepDTO> toAllUnitUpkeepDTO(Set<UnitUpkeep> unitUpkeeps);

    Set<UnitUpkeep> toAllUnitUpkeep(Set<UnitUpkeepDTO> unitUpkeepDTOS);

    @Mapping(source = "unitProductions.unitProductionPositions", target = "unitProductionPositionsDTO")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductionsDTO toDTO (UnitProductions unitProductions);

    @Mapping(source = "unitProductionsDTO.unitProductionPositionsDTO", target = "unitProductionPositions")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductions toEntity (UnitProductionsDTO unitProductionsDTO);

    @Mapping(source = "unitDeliveries.unitDeliveriesPositions", target = "unitDeliveriesPositionsDTO")
    UnitDeliveriesDTO toDTO (UnitDeliveries unitDeliveries);

    @Mapping(source = "unitDeliveriesDTO.unitDeliveriesPositionsDTO", target = "unitDeliveriesPositions")
    UnitDeliveries toEntity (UnitDeliveriesDTO unitDeliveriesDTO);
}
