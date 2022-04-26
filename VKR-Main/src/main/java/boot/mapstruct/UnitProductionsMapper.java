package boot.mapstruct;

import boot.DTO.UnitProductionPositionDTO;
import boot.DTO.UnitProductionsDTO;
import boot.entity.UnitProductionPosition;
import boot.entity.UnitProductions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring", uses = { UnitMapper.class})
public interface UnitProductionsMapper {
    @Mapping(source = "unitProductions.unit", target = "unitDTO")
    @Mapping(source = "unitProductions.unitProductionPositions", target = "unitProductionPositionsDTO")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductionsDTO toDTO (UnitProductions unitProductions);
    @Mapping(source = "unitProductionsDTO.unitDTO", target = "unit")
    @Mapping(source = "unitProductionsDTO.unitProductionPositionsDTO", target = "unitProductionPositions")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductions toEntity (UnitProductionsDTO unitProductionsDTO);

    Set<UnitProductionPositionDTO> toAllUnitProductionPositionDTO(Set<UnitProductionPosition> unitProductionPositions);

    Set<UnitProductionPosition> toAllUnitProductionPosition(Set<UnitProductionPositionDTO> unitProductionPositionDTOS);

    @Mapping(source = "unitProductionPosition.worker", target = "workerDTO")
    @Mapping(source = "unitProductionPosition.salaryWorker", target = "salaryWorkerDTO", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductionPositionDTO toDTO (UnitProductionPosition unitProductionPosition);

    @Mapping(source = "unitProductionPositionDTO.workerDTO", target = "worker")
    @Mapping(source = "unitProductionPositionDTO.salaryWorkerDTO", target = "salaryWorker", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductionPosition toEntity (UnitProductionPositionDTO unitProductionPositionDTO);
}
