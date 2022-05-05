package boot.mapstruct;

import boot.DTO.PricingWorkerDTO;
import boot.DTO.SalaryWorkerDTO;
import boot.DTO.UnitProductionPositionDTO;
import boot.DTO.WorkerDTO;
import boot.entity.PricingWorker;
import boot.entity.SalaryWorker;
import boot.entity.UnitProductionPosition;
import boot.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkerMapper {
    @Mapping(source = "worker.unitProductionPositions", target = "unitProductionPositionsDTO")
    @Mapping(source = "worker.pricingWorkers", target = "pricingWorkersDTO")
    @Mapping(source = "worker.salaryWorkers", target = "salaryWorkersDTO")
    WorkerDTO toDTO (Worker worker);
    @Mapping(source = "workerDTO.unitProductionPositionsDTO", target = "unitProductionPositions")
    @Mapping(source = "workerDTO.pricingWorkersDTO", target = "pricingWorkers")
    @Mapping(source = "workerDTO.salaryWorkersDTO", target = "salaryWorkers")
    Worker toEntity (WorkerDTO workerDTO);

    Set<UnitProductionPositionDTO> toAllUnitProductionPositionDTO(Set<UnitProductionPosition> unitProductionPositions);

    Set<UnitProductionPosition> toAllUnitProductionPosition(Set<UnitProductionPositionDTO> unitProductionPositionDTOS);

    Set<PricingWorkerDTO> toAllPricingWorkerDTO(Set<PricingWorker> pricingWorkers);

    Set<PricingWorker> toAllPricingWorker(Set<PricingWorkerDTO> pricingWorkerDTOS);

    Set<SalaryWorkerDTO> toAllSalaryWorkerDTO(Set<SalaryWorker> salaryWorkers);

    Set<SalaryWorker> toAllSalaryWorker(Set<SalaryWorkerDTO> salaryWorkerDTOS);
}
