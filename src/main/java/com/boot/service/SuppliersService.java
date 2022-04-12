package com.boot.service;

import com.boot.entity.Suppliers;
import com.boot.entity.Unit;
import com.boot.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SuppliersService implements ServiceMag<Suppliers>{
    @PersistenceContext
    private EntityManager em;
    @Autowired
    SuppliersRepository suppliers;
    @Override
    public Suppliers get(long id) {
        return suppliers.findById(id).get();
    }

    @Override
    public List<Suppliers> getAll() {
        return suppliers.findAll();
    }

    @Override
    public void save(Suppliers entity) {
        suppliers.save(entity);
    }

    @Override
    public void delete(long id) {
        suppliers.deleteById(id);
    }

    public List<Suppliers> getAllSearch(String search) {
        search=search+"%";
        return em.createQuery("SELECT S FROM Suppliers S WHERE UPPER(S.name) LIKE UPPER(:paramId)", Suppliers.class)
                .setParameter("paramId", search).getResultList();
    }
}
