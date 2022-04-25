package com.boot.service;

import com.boot.DTO.UnitDeliveriesPositionDTO;
import com.boot.entity.PaymentSupplier;
import com.boot.entity.UnitDeliveriesPosition;
import com.boot.mapstruct.PaymentSupplierMapper;
import com.boot.mapstruct.UnitDeliveriesPositionMapper;
import com.boot.repository.UnitDeliveriesPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitDeliveriesPositionService implements ServiceMag<UnitDeliveriesPositionDTO>{

    UnitDeliveriesPositionRepository unitDeliveriesPositionRepository;
    UnitDeliveriesPositionMapper unitDeliveriesPositionMapper;

    PaymentSupplierMapper paymentSupplierMapper;
    @Autowired
    public UnitDeliveriesPositionService(UnitDeliveriesPositionRepository unitDeliveriesPositionRepository,
                                         UnitDeliveriesPositionMapper unitDeliveriesPositionMapper,
                                         PaymentSupplierMapper paymentSupplierMapper) {
        this.unitDeliveriesPositionRepository = unitDeliveriesPositionRepository;
        this.unitDeliveriesPositionMapper = unitDeliveriesPositionMapper;
        this.paymentSupplierMapper=paymentSupplierMapper;
    }

    @Override
    public UnitDeliveriesPositionDTO get(long id) {
        return unitDeliveriesPositionMapper.toDTO(
                unitDeliveriesPositionRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<UnitDeliveriesPositionDTO> getAll() {

        return unitDeliveriesPositionRepository.findAll().stream()
                .map(value->unitDeliveriesPositionMapper.toDTO(value))
                .collect(Collectors.toList());
    }


    @Override
    public void save(UnitDeliveriesPositionDTO unitDeliveriesPositionDTO) {
       PaymentSupplier paymentSupplier;
       UnitDeliveriesPosition entity = unitDeliveriesPositionMapper.toEntity(unitDeliveriesPositionDTO);
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
            paymentSupplier = paymentSupplierMapper.toEntity(this.get(entity.getId()).getPaymentSupplierDTO());
            paymentSupplier.setUnitDeliveriesPosition(entity);
            paymentSupplier.setSuppliers(entity.getSuppliers());
            entity.setPaymentSupplier(paymentSupplier);
        }
        unitDeliveriesPositionRepository.save(entity);
    }

    public void update(UnitDeliveriesPositionDTO unitDeliveriesPositionDTO) {
        UnitDeliveriesPosition entity = unitDeliveriesPositionMapper.toEntity(unitDeliveriesPositionDTO);
        PaymentSupplier paymentSupplier = paymentSupplierMapper.toEntity(this.get(entity.getId()).getPaymentSupplierDTO());
        paymentSupplier.setStatus(!entity.isStatus());
        paymentSupplier.setUnitDeliveriesPosition(entity);
        entity.setPaymentSupplier(paymentSupplier);
        unitDeliveriesPositionRepository.save(entity);
    }
    @Override
    public void delete(long id) {
       unitDeliveriesPositionRepository.deleteById(id);
    }

}
