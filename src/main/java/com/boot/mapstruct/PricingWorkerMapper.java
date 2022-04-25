package com.boot.mapstruct;

import com.boot.DTO.PricingWorkerDTO;
import com.boot.entity.PricingWorker;
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

//    WorkerDTO toWorkerDTO (Worker worker);
//
//    Worker toWorker (WorkerDTO workerDTO);
}
