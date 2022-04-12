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
    @Autowired
    UnitDeliveriesPositionRepository unitDeliveriesPositionRepository;
    @Autowired
    PaymentSupplierService paymentSupplierService;
    @Autowired
    SessionFactory  factory;

    @Override
    public UnitDeliveriesPosition get(long id) {
        return unitDeliveriesPositionRepository.findById(id).get();
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
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        }
        else {
            UnitDeliveriesPosition unitProductionPosition =this.get(entity.getId());
            paymentSupplier = unitProductionPosition.getPaymentSupplier();
            paymentSupplier.setSuppliers(entity.getSuppliers());
            entity.setPaymentSupplier(paymentSupplier);
            Session session = factory.openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void update(UnitDeliveriesPosition entity) {
        PaymentSupplier paymentSupplier = this.get(entity.getId()).getPaymentSupplier();
        paymentSupplier.setStatus(!entity.isStatus());
        entity.setPaymentSupplier(paymentSupplier);
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void delete(long id) {
       unitDeliveriesPositionRepository.deleteById(id);
    }

}
