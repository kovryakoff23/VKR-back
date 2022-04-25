package com.boot.mapstruct;

import com.boot.DTO.PaymentSupplierDTO;
import com.boot.entity.PaymentSupplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SuppliersMapper.class})
public interface PaymentSupplierMapper {
    @Mapping(source = "paymentSupplier.suppliers", target = "suppliersDTO")
    @Mapping(source = "paymentSupplier.unitDeliveriesPosition", target = "unitDeliveriesPositionDTO")
    PaymentSupplierDTO toDTO (PaymentSupplier paymentSupplier);
    @Mapping(source = "paymentSupplierDTO.suppliersDTO", target = "suppliers")
    @Mapping(source = "paymentSupplierDTO.unitDeliveriesPositionDTO", target = "unitDeliveriesPosition")
    PaymentSupplier toEntity (PaymentSupplierDTO paymentSupplierDTO);

//    SuppliersDTO toSuppliersDTO (Suppliers suppliers);
//
//    Suppliers toSuppliers (SuppliersDTO suppliersDTO);
//
//    UnitDeliveriesPositionDTO toUnitDeliveriesPositionDTO (UnitDeliveriesPosition unitDeliveriesPosition);
//
//    UnitDeliveriesPosition toUnitDeliveriesPosition (UnitDeliveriesPositionDTO unitDeliveriesPositionDTO);
}
