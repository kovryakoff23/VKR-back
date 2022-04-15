package com.boot.service;

import com.boot.entity.Unit;
import com.boot.repository.UnitRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UnitService implements ServiceMag<Unit> {

    UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Unit get(long id) {
       return unitRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Synchronized
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    @Override
    public void save(Unit entity) {
        unitRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        unitRepository.deleteById(id);
    }
    public List<Unit> getAllSearch(String search) {
        search=search+"%";
        return unitRepository.findByName(search);
    }
    @Synchronized
    public List<Unit> getAllActive(){
        return unitRepository.findByTypeTrue();
    }
}