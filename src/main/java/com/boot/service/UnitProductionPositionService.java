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
    @Autowired
    UnitProductionPositionRepository unitProductionPositionRepository;
    @Autowired
    UnitService unitService;
    @Autowired
    SalaryWorkerService salaryWorkerService;
    @Autowired
    SessionFactory factory;

    @Override
    public UnitProductionPosition get(long id) {
        return unitProductionPositionRepository.findById(id).get();
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
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        }
        else {
            UnitProductionPosition unitProductionPosition =this.get(entity.getId());
            salaryWorker = unitProductionPosition.getSalaryWorker();
            salaryWorker.setWorker(entity.getWorker());
            entity.setSalaryWorker(salaryWorker);
            Session session = factory.openSession();
            session.beginTransaction();
            session.update(salaryWorker);
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void update(UnitProductionPosition entity) {
        SalaryWorker salaryWorker = this.get(entity.getId()).getSalaryWorker();
        salaryWorker.setStatus(!entity.isStatus());
        entity.setSalaryWorker(salaryWorker);
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
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
