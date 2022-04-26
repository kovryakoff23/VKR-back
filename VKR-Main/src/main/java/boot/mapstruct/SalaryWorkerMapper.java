package boot.mapstruct;

import boot.DTO.SalaryWorkerDTO;
import boot.entity.SalaryWorker;
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

}
