package com.boot.service;

import com.boot.entity.UnitUpkeep;
import com.boot.repository.UnitUpkeepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitUpkeepService implements ServiceMag<UnitUpkeep>{
    UnitUpkeepRepository unitUpkeepRepository;

    @Autowired
    public UnitUpkeepService(UnitUpkeepRepository unitUpkeepRepository) {
        this.unitUpkeepRepository = unitUpkeepRepository;
    }

    @Override
    public UnitUpkeep get(long id) {
        return unitUpkeepRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<UnitUpkeep> getAll() {
        return unitUpkeepRepository.findAll();
    }

    @Override
    public void save(UnitUpkeep entity) {
        unitUpkeepRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        unitUpkeepRepository.deleteById(id);
    }
}
