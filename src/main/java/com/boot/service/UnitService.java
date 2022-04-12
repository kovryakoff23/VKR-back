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
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UnitRepository unitRepository;
    @Override
    public Unit get(long id) {
       return unitRepository.findById(id).get();
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
        return em.createQuery("SELECT U FROM Unit U WHERE UPPER(U.name) LIKE UPPER(:paramId)", Unit.class)
                .setParameter("paramId", search).getResultList();
    }
    @Synchronized
    public List<Unit> getAllActive(){
        return em.createQuery("SELECT U FROM Unit U WHERE type = true", Unit.class).getResultList();
    }
}