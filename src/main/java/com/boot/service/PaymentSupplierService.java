package com.boot.service;

import com.boot.DTO.PaymentSupplierDTO;
import com.boot.mapstruct.PaymentSupplierMapper;
import com.boot.repository.PaymentSupplierRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentSupplierService implements ServiceMag<PaymentSupplierDTO> {
    PaymentSupplierRepository paymentSupplierRepository;
    SuppliersService suppliersService;

    PaymentSupplierMapper paymentSupplierMapper;

    @Autowired
    public PaymentSupplierService(PaymentSupplierRepository paymentSupplierRepository, SuppliersService suppliersService,
                                  PaymentSupplierMapper paymentSupplierMapper) {
        this.paymentSupplierRepository = paymentSupplierRepository;
        this.suppliersService = suppliersService;
        this.paymentSupplierMapper = paymentSupplierMapper;
    }

    @Override
    public PaymentSupplierDTO get(long id) {
        return paymentSupplierMapper.toDTO(paymentSupplierRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<PaymentSupplierDTO> getAll() {
        return paymentSupplierRepository.findAll().stream()
                .map(value->paymentSupplierMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(PaymentSupplierDTO entity) {
        paymentSupplierRepository.save(paymentSupplierMapper.toEntity(entity));
    }

    public void update(PaymentSupplierDTO entity) {
       paymentSupplierRepository
               .save(paymentSupplierMapper.toEntity(entity));
    }

    @Override
    public void delete(long id) {
        paymentSupplierRepository.deleteById(id);
    }

    public Set<PaymentSupplierDTO> getAllByDate(Date dateStartPay, Date dateEndPay, Long supplierId){
        return suppliersService.get(supplierId).getPaymentSuppliersDTO().stream()
                .filter(PaymentSupplierDTO::isStatus)
                .filter(value1->value1.getDatePay().after(dateStartPay))
                .filter(value1->value1.getDatePay().before(dateEndPay))
                .filter(PaymentSupplierDTO::isStatus)
                .collect(Collectors.toSet());
    }
    @Synchronized
    public List<PaymentSupplierDTO> getAllActive(){
        return paymentSupplierRepository.findByStatusTrue().stream()
                .map(value->paymentSupplierMapper.toDTO(value))
                .collect(Collectors.toList());
    }

}
