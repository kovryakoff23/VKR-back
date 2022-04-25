package com.boot.mapstruct;

import com.boot.DTO.PricingWorkerDTO;
import com.boot.DTO.SalaryWorkerDTO;
import com.boot.DTO.UnitProductionPositionDTO;
import com.boot.DTO.WorkerDTO;
import com.boot.entity.PricingWorker;
import com.boot.entity.SalaryWorker;
import com.boot.entity.UnitProductionPosition;
import com.boot.entity.Worker;
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
