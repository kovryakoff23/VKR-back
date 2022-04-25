package com.boot.mapstruct;

import com.boot.DTO.SalaryWorkerDTO;
import com.boot.entity.SalaryWorker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WorkerMapper.class})
public interface SalaryWorkerMapper {
    @Mapping(source = "salaryWorker.worker", target = "workerDTO")
    @Mapping(source = "salaryWorker.unitProductionPosition", target = "unitProductionPositionDTO")
    SalaryWorkerDTO toDTO (SalaryWorker salaryWorker);
    @Mapping(source = "salaryWorkerDTO.workerDTO", target = "worker")
    @Mapping(source = "salaryWorkerDTO.unitProductionPositionDTO", target = "unitProductionPosition")
    SalaryWorker toEntity (SalaryWorkerDTO salaryWorkerDTO);

//    WorkerDTO toWorkerDTO (Worker worker);
//
//    Worker toWorker (WorkerDTO workerDTO);
//
//    UnitProductionPositionDTO toUnitProductionPositionDTO (UnitProductionPosition unitProductionPosition);
//
//    UnitProductionPosition toUnitProductionPositionEntity (UnitProductionPositionDTO unitProductionPositionDTO);
}
