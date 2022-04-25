package com.boot.service;

import com.boot.DTO.UnitProductionPositionDTO;
import com.boot.DTO.UnitProductionsDTO;
import com.boot.DTO.WorkerDTO;
import com.boot.entity.SalaryWorker;
import com.boot.entity.UnitProductionPosition;
import com.boot.mapstruct.SalaryWorkerMapper;
import com.boot.mapstruct.UnitProductionPositionMapper;
import com.boot.repository.UnitProductionPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnitProductionPositionService  implements ServiceMag<UnitProductionPositionDTO>{

    UnitProductionPositionRepository unitProductionPositionRepository;
    UnitService unitService;
    SalaryWorkerService salaryWorkerService;

    UnitProductionPositionMapper unitProductionPositionMapper;

    SalaryWorkerMapper salaryWorkerMapper;

    @Autowired
    public UnitProductionPositionService(UnitProductionPositionRepository unitProductionPositionRepository,
                                         UnitService unitService, SalaryWorkerService salaryWorkerService,
                                         UnitProductionPositionMapper unitProductionPositionMapper,
                                         SalaryWorkerMapper salaryWorkerMapper) {
        this.unitProductionPositionRepository = unitProductionPositionRepository;
        this.unitService = unitService;
        this.salaryWorkerService = salaryWorkerService;
        this.unitProductionPositionMapper = unitProductionPositionMapper;
        this.salaryWorkerMapper = salaryWorkerMapper;
    }

    @Override
    public UnitProductionPositionDTO get(long id) {
        return unitProductionPositionMapper.toDTO(unitProductionPositionRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<UnitProductionPositionDTO> getAll() {
        return unitProductionPositionRepository.findAll()
                .stream().map(value->unitProductionPositionMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UnitProductionPositionDTO unitProductionPositionDTO) {
        SalaryWorker salaryWorker;
        UnitProductionPosition entity = unitProductionPositionMapper.toEntity(unitProductionPositionDTO);
        if (entity.getId()==null) {
            salaryWorker = new SalaryWorker(
                    entity.getUnitProductions().getDateEndWork(),
                    null,
                    entity.getUnitProductions().getUnit().getName(),
                    entity.getNamePosition(),
                    false,
                    false,
                    entity.getPrice(),
                    entity.getWorker(),
                    entity);
            entity.setSalaryWorker(salaryWorker);
        }
        else {
            salaryWorker = salaryWorkerMapper.toEntity(this.get(entity.getId()).getSalaryWorkerDTO());
            salaryWorker.setWorker(entity.getWorker());
            salaryWorker.setUnitProductionPosition(entity);
            entity.setSalaryWorker(salaryWorker);
        }
        unitProductionPositionRepository.save(entity);
    }

    public void update(UnitProductionPositionDTO unitProductionPositionDTO) {
        UnitProductionPosition entity = unitProductionPositionMapper.toEntity(unitProductionPositionDTO);
        SalaryWorker salaryWorker = salaryWorkerMapper.toEntity(this.get(entity.getId()).getSalaryWorkerDTO());
        salaryWorker.setStatus(!entity.isStatus());
        salaryWorker.setUnitProductionPosition(entity);
        entity.setSalaryWorker(salaryWorker);
        unitProductionPositionRepository.save(entity);
    }
    @Override
    public void delete(long id) {
        unitProductionPositionRepository.deleteById(id);
    }

    public Set<WorkerDTO> getWorker(Long unitId){
        Set<WorkerDTO> workers = new HashSet<>();
        Set<UnitProductionsDTO> unitProductionWorks = unitService.get(unitId).getUnitProductionsDTO();
        for (UnitProductionsDTO unitProductionWork: unitProductionWorks){
          for(UnitProductionPositionDTO item:  unitProductionWork.getUnitProductionPositionsDTO()) {
                workers.add(item.getWorkerDTO());
          }
        }
        return workers;
    }
}
