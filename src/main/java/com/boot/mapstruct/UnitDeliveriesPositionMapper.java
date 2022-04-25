package com.boot.mapstruct;

import com.boot.DTO.UnitDeliveriesPositionDTO;
import com.boot.entity.UnitDeliveriesPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {UnitDeliveriesMapper.class, PaymentSupplierMapper.class, SuppliersMapper.class})
public interface UnitDeliveriesPositionMapper {
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "unitDeliveriesPosition.unitDeliveries", target = "unitDeliveriesDTO")
    @Mapping(source = "unitDeliveriesPosition.suppliers", target = "suppliersDTO")
    @Mapping(source = "unitDeliveriesPosition.paymentSupplier", target = "paymentSupplierDTO", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitDeliveriesPositionDTO toDTO (UnitDeliveriesPosition unitDeliveriesPosition);
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "unitDeliveriesPositionDTO.unitDeliveriesDTO", target = "unitDeliveries")
    @Mapping(source = "unitDeliveriesPositionDTO.suppliersDTO", target = "suppliers")
    @Mapping(source = "unitDeliveriesPositionDTO.paymentSupplierDTO", target = "paymentSupplier", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UnitDeliveriesPosition toEntity (UnitDeliveriesPositionDTO unitDeliveriesPositionDTO);

//    UnitDeliveriesDTO toUnitDeliveriesDTO (UnitDeliveries unitDeliveries);
//
//    UnitDeliveries toUnitDeliveries (UnitDeliveriesDTO unitDeliveriesDTO);
//
//    PaymentSupplierDTO toPaymentSupplierDTO (PaymentSupplier paymentSupplier);
//
//    PaymentSupplier toPaymentSupplier (PaymentSupplierDTO paymentSupplierDTO);
}
