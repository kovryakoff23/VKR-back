package com.boot.service;

import com.boot.entity.Suppliers;
import com.boot.entity.Unit;
import com.boot.entity.Worker;
import com.boot.repository.UnitRepository;
import com.boot.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WorkerService implements ServiceMag<Worker> {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    WorkerRepository workerRepository;

    @Override
    public Worker get(long id) {
        return workerRepository.findById(id).get();
    }

    @Override
    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    @Override
    public void save(Worker entity) {
        workerRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        workerRepository.deleteById(id);
    }

    public List<Worker> getAllSearch(String search) {
        search=search+"%";
        return em.createQuery("SELECT W FROM Worker W WHERE UPPER(W.name) LIKE UPPER(:paramId)", Worker.class)
                .setParameter("paramId", search).getResultList();
    }
}