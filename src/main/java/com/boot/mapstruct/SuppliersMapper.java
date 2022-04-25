package com.boot.mapstruct;

import com.boot.DTO.PaymentSupplierDTO;
import com.boot.DTO.SupplierPositionsDTO;
import com.boot.DTO.SuppliersDTO;
import com.boot.DTO.UnitDeliveriesPositionDTO;
import com.boot.entity.PaymentSupplier;
import com.boot.entity.SupplierPositions;
import com.boot.entity.Suppliers;
import com.boot.entity.UnitDeliveriesPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SuppliersMapper {

    @Mapping(source = "suppliers.supplyPositions", target = "supplyPositionsDTO")
    @Mapping(source = "suppliers.paymentSuppliers", target = "paymentSuppliersDTO")
    @Mapping(source = "suppliers.unitDeliveriesPositions", target = "unitDeliveriesPositionsDTO")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    SuppliersDTO toDTO (Suppliers suppliers);
    @Mapping(source = "suppliersDTO.supplyPositionsDTO", target = "supplyPositions")
    @Mapping(source = "suppliersDTO.paymentSuppliersDTO", target = "paymentSuppliers")
    @Mapping(source = "suppliersDTO.unitDeliveriesPositionsDTO", target = "unitDeliveriesPositions")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Suppliers toEntity (SuppliersDTO suppliersDTO);

    Set<SupplierPositionsDTO> toAllSupplierPositionsDTO (Set<SupplierPositions> supplierPositions);

    Set<SupplierPositions> toAllSupplierPositions(Set<SupplierPositionsDTO> supplierPositionsDTOS);

    Set<PaymentSupplierDTO> toAllPaymentSupplierDTO(Set<PaymentSupplier> paymentSuppliers);

    Set<PaymentSupplier> toAllPaymentSupplier(Set<PaymentSupplierDTO> paymentSupplierDTOS);

    Set<UnitDeliveriesPositionDTO> toAllUnitDeliveriesPositionDTO(Set<UnitDeliveriesPosition> unitDeliveriesPositions);

    Set<UnitDeliveriesPosition> toAllUnitDeliveriesPosition(Set<UnitDeliveriesPositionDTO> unitDeliveriesPositionDTOS);
}
