package boot.mapstruct;

import boot.DTO.UnitProductionPositionDTO;
import boot.entity.UnitProductionPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {UnitProductionsMapper.class, SalaryWorkerMapper.class, WorkerMapper.class})
public interface UnitProductionPositionMapper {

    @Mapping(source = "unitProductionPosition.unitProductions", target = "unitProductionsDTO")
    @Mapping(source = "unitProductionPosition.worker", target = "workerDTO")
    @Mapping(source = "unitProductionPosition.salaryWorker", target = "salaryWorkerDTO", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductionPositionDTO toDTO (UnitProductionPosition unitProductionPosition);

    @Mapping(source = "unitProductionPositionDTO.unitProductionsDTO", target = "unitProductions")
    @Mapping(source = "unitProductionPositionDTO.workerDTO", target = "worker")
    @Mapping(source = "unitProductionPositionDTO.salaryWorkerDTO", target = "salaryWorker", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitProductionPosition toEntity (UnitProductionPositionDTO unitProductionPositionDTO);

}
