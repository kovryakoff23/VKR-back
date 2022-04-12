package com.boot.service;

import com.boot.entity.UnitDeliveries;
import com.boot.entity.UnitDeliveriesPosition;
import com.boot.entity.UnitProductions;
import com.boot.repository.UnitDeliveriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UnitDeliveriesService implements ServiceMag<UnitDeliveries>{
    @Autowired
    UnitDeliveriesRepository unitDeliveriesRepository;
    @Autowired
    UnitDeliveriesPositionService unitDeliveriesPositionService;
    @PersistenceContext
    private EntityManager em;

    @Override
    public UnitDeliveries get(long id) {
        return unitDeliveriesRepository.findById(id).get();
    }

    @Override
    public List<UnitDeliveries> getAll() {
        return unitDeliveriesRepository.findAll();
    }

    @Override
    public void save(UnitDeliveries entity) {
        if(entity.getId()!=null && !entity.isStatus()){
            this.get(entity.getId()).getUnitDeliveriesPositions().forEach(value->{
                value.setStatus(false);
                unitDeliveriesPositionService.save(value);
            });
        }
        unitDeliveriesRepository.save(entity);
    }

    @Override
    public void delete(long id) {

        unitDeliveriesRepository.deleteById(id);
    }

    public List<UnitDeliveries> getAllActive(){
        return em.createQuery("SELECT U FROM UnitDeliveries U WHERE status = true", UnitDeliveries.class).getResultList();
    }
}
