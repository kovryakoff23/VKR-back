package com.boot.service;

import com.boot.component.Payment;
import com.boot.component.Salary;
import com.boot.entity.PaymentSupplier;
import com.boot.entity.SalaryWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    PaymentSupplierService paymentSupplierService;
    SuppliersService suppliersService;

    @Autowired
    public PaymentService(PaymentSupplierService paymentSupplierService, SuppliersService suppliersService) {
        this.paymentSupplierService = paymentSupplierService;
        this.suppliersService = suppliersService;
    }

    public Set<Payment> getAll(){
        Set<Payment> payments = suppliersService.getAll().stream()
                .map(value->{
                    return new Payment(value,
                            value
                                    .getPaymentSuppliers()
                                    .stream()
                                    .filter(PaymentSupplier::isStatus)
                                    .map(PaymentSupplier::getSumPay)
                                    .mapToLong(Long::longValue)
                                    .sum());
                })
                .filter(value->value.getSumPay()>0&&value.getSumPay()!=null)
                .collect(Collectors.toSet());
        return payments;
    }
    public Set<Payment> getAllByDate(Date dateStartPay, Date dateEndPay){
        Set<Payment> payments = suppliersService.getAll().stream()
                .map(value->{
                    return new Payment(value,

                            value
                                    .getPaymentSuppliers()
                                    .stream()
                                    .filter(PaymentSupplier::isStatus)
                                    .filter(value1->value1.getDatePay().after(dateStartPay))
                                    .filter(value1->value1.getDatePay().before(dateEndPay))
                                    .filter(PaymentSupplier::isStatus)
                                    .map(PaymentSupplier::getSumPay)
                                    .mapToLong(Long::longValue)
                                    .sum());
                })
                .filter(value->value.getSumPay()>0&&value.getSumPay()!=null)
                .collect(Collectors.toSet());
        return payments;
    }
}
