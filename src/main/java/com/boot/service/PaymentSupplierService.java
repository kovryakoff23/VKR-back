package com.boot.service;

import com.boot.entity.PaymentSupplier;
import com.boot.entity.SalaryWorker;
import com.boot.entity.Unit;
import com.boot.repository.PaymentSupplierRepository;
import lombok.Synchronized;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentSupplierService implements ServiceMag<PaymentSupplier> {
    @Autowired
    PaymentSupplierRepository paymentSupplierRepository;
    @Autowired
    SuppliersService suppliersService;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EntityManagerFactory emf;
    @Autowired
    SessionFactory factory;

    @Override
    public PaymentSupplier get(long id) {
        return paymentSupplierRepository.findById(id).get();
    }

    @Override
    public List<PaymentSupplier> getAll() {
        return paymentSupplierRepository.findAll();
    }

    @Override
    public void save(PaymentSupplier entity) {
        paymentSupplierRepository.save(entity);
    }

    public void update(PaymentSupplier entity) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @Transactional
    public void delete(long id) {
        PaymentSupplier paymentSupplier = em.find(PaymentSupplier.class, id);
        em.remove(paymentSupplier);
        em.flush();
//        paymentSupplierRepository.deleteById(id);
    }

    public Set<PaymentSupplier> getAllByDate(Date dateStartPay, Date dateEndPay, Long supplierId){
        return suppliersService.get(supplierId).getPaymentSuppliers().stream()
                .filter(PaymentSupplier::isStatus)
                .filter(value1->value1.getDatePay().after(dateStartPay))
                .filter(value1->value1.getDatePay().before(dateEndPay))
                .filter(PaymentSupplier::isStatus)
                .collect(Collectors.toSet());
    }
    @Synchronized
    public List<PaymentSupplier> getAllActive(){
        return em.createQuery("SELECT U FROM PaymentSupplier U WHERE status = true", PaymentSupplier.class).getResultList();
    }

}
