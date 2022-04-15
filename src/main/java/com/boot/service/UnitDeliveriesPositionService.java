package com.boot.service;

import com.boot.entity.*;
import com.boot.repository.UnitDeliveriesPositionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UnitDeliveriesPositionService implements ServiceMag<UnitDeliveriesPosition>{

    UnitDeliveriesPositionRepository unitDeliveriesPositionRepository;

    @Autowired
    public UnitDeliveriesPositionService(UnitDeliveriesPositionRepository unitDeliveriesPositionRepository) {
        this.unitDeliveriesPositionRepository = unitDeliveriesPositionRepository;
    }

    @Override
    public UnitDeliveriesPosition get(long id) {
        return unitDeliveriesPositionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<UnitDeliveriesPosition> getAll() {
        return unitDeliveriesPositionRepository.findAll();
    }


    @Override
    public void save(UnitDeliveriesPosition entity) {
       PaymentSupplier paymentSupplier;
        if (entity.getId()==null) {
            paymentSupplier = new PaymentSupplier(
                    entity.getUnitDeliveries().getDateComplete(),
                    entity.getUnitDeliveries().getUnit().getName(),
                    entity.getNamePosition(),
                    false,
                    entity.getPrice(),
                    entity.getSuppliers());
            paymentSupplier.setUnitDeliveriesPosition(null);
            entity.setPaymentSupplier(paymentSupplier);
        }
        else {
            UnitDeliveriesPosition unitProductionPosition =this.get(entity.getId());
            paymentSupplier = unitProductionPosition.getPaymentSupplier();
            paymentSupplier.setSuppliers(entity.getSuppliers());
            entity.setPaymentSupplier(paymentSupplier);
        }
        unitDeliveriesPositionRepository.save(entity);
    }

    public void update(UnitDeliveriesPosition entity) {
        PaymentSupplier paymentSupplier = this.get(entity.getId()).getPaymentSupplier();
        paymentSupplier.setStatus(!entity.isStatus());
        entity.setPaymentSupplier(paymentSupplier);
        unitDeliveriesPositionRepository.save(entity);
    }
    @Override
    public void delete(long id) {
       unitDeliveriesPositionRepository.deleteById(id);
    }

}
