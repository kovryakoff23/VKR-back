package com.boot.service;

import com.boot.entity.Suppliers;
import com.boot.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SuppliersService implements ServiceMag<Suppliers>{
    SuppliersRepository suppliersRepository;

    @Autowired
    public SuppliersService(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public Suppliers get(long id) {
        return suppliersRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Suppliers> getAll() {
        return suppliersRepository.findAll();
    }

    @Override
    public void save(Suppliers entity) {
        suppliersRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        suppliersRepository.deleteById(id);
    }

    public List<Suppliers> getAllSearch(String search) {
        search=search+"%";
        return suppliersRepository.findByName(search);
    }
}
