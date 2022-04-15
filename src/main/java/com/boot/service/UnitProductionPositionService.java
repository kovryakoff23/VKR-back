package com.boot.service;

import com.boot.entity.*;
import com.boot.repository.UnitProductionPositionRepository;
import com.boot.repository.UnitRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UnitProductionPositionService  implements ServiceMag<UnitProductionPosition>{

    UnitProductionPositionRepository unitProductionPositionRepository;
    UnitService unitService;
    SalaryWorkerService salaryWorkerService;

    @Autowired
    public UnitProductionPositionService(UnitProductionPositionRepository unitProductionPositionRepository, UnitService unitService, SalaryWorkerService salaryWorkerService) {
        this.unitProductionPositionRepository = unitProductionPositionRepository;
        this.unitService = unitService;
        this.salaryWorkerService = salaryWorkerService;
    }

    @Override
    public UnitProductionPosition get(long id) {
        return unitProductionPositionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<UnitProductionPosition> getAll() {
        return unitProductionPositionRepository.findAll();
    }

    @Override
    public void save(UnitProductionPosition entity) {
        SalaryWorker salaryWorker;
        if (entity.getId()==null) {
            salaryWorker = new SalaryWorker(
                    entity.getUnitProductionWorks().getDateEndWork(),
                    null,
                    entity.getUnitProductionWorks().getUnit().getName(),
                    entity.getNamePosition(),
                    false,
                    false,
                    entity.getPrice(),
                    entity.getWorker(),
                    entity);
            entity.setSalaryWorker(salaryWorker);
        }
        else {
            UnitProductionPosition unitProductionPosition =this.get(entity.getId());
            salaryWorker = unitProductionPosition.getSalaryWorker();
            salaryWorker.setWorker(entity.getWorker());
            entity.setSalaryWorker(salaryWorker);
        }
        unitProductionPositionRepository.save(entity);
    }

    public void update(UnitProductionPosition entity) {
        SalaryWorker salaryWorker = this.get(entity.getId()).getSalaryWorker();
        salaryWorker.setStatus(!entity.isStatus());
        entity.setSalaryWorker(salaryWorker);

        salaryWorkerService.save(salaryWorker);
        unitProductionPositionRepository.save(entity);
    }
    @Override
    public void delete(long id) {
        unitProductionPositionRepository.deleteById(id);
    }

    public Set<Worker> getWorker(Long unitId){
        Set<Worker> workers = new HashSet<>();
        List<UnitProductions> unitProductionWorks = unitService.get(unitId).getUnitProductionWorks();
        for (UnitProductions unitProductionWork: unitProductionWorks){
          for(UnitProductionPosition item:  unitProductionWork.getUnitProductionPositions()) {
                workers.add(item.getWorker());
          }
        }
        return workers;
    }
}
