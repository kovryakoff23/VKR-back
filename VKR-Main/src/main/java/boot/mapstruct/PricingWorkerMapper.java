package boot.mapstruct;

import boot.DTO.PricingWorkerDTO;
import boot.entity.PricingWorker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {WorkerMapper.class})
public interface PricingWorkerMapper {
    @Mapping(source = "pricingWorker.worker", target = "workerDTO")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PricingWorkerDTO toDTO (PricingWorker pricingWorker);
    @Mapping(source = "pricingWorkerDTO.workerDTO", target = "worker")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PricingWorker toEntity (PricingWorkerDTO pricingWorkerDTO);


}
