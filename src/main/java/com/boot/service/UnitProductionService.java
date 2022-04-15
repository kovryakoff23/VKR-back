package com.boot.service;

import com.boot.entity.Unit;
import com.boot.entity.UnitProductionPosition;
import com.boot.entity.UnitProductions;
import com.boot.repository.UnitProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UnitProductionService implements ServiceMag<UnitProductions>{

    UnitProductionRepository unitProductionRepository;

    UnitProductionPositionService unitProductionPositionService;

    @Autowired
    public UnitProductionService(UnitProductionRepository unitProductionRepository, UnitProductionPositionService unitProductionPositionService) {
        this.unitProductionRepository = unitProductionRepository;
        this.unitProductionPositionService = unitProductionPositionService;
    }

    @Override
    public UnitProductions get(long id) {
        return unitProductionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<UnitProductions> getAll() {
        return unitProductionRepository.findAll();
    }

    @Override
    public void save(UnitProductions entity) {
        if(entity.getId()!=null && !entity.isStatus()){
            this.get(entity.getId()).getUnitProductionPositions().forEach(value->{
                value.setStatus(false);
                unitProductionPositionService.save(value);
            });
        }
        unitProductionRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        unitProductionRepository.deleteById(id);
    }

    public List<UnitProductions> getAllActive(){
        return unitProductionRepository.findByStatusTrue();
    }

}
