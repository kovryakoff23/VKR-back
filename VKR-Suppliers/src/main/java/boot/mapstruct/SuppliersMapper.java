package boot.mapstruct;

import boot.DTO.PaymentSupplierDTO;
import boot.DTO.SupplierPositionsDTO;
import boot.DTO.SuppliersDTO;
import boot.entity.PaymentSupplier;
import boot.entity.SupplierPositions;
import boot.entity.Suppliers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SuppliersMapper {

    @Mapping(source = "suppliers.supplyPositions", target = "supplyPositionsDTO")
    @Mapping(source = "suppliers.paymentSuppliers", target = "paymentSuppliersDTO")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    SuppliersDTO toDTO (Suppliers suppliers);
    @Mapping(source = "suppliersDTO.supplyPositionsDTO", target = "supplyPositions")
    @Mapping(source = "suppliersDTO.paymentSuppliersDTO", target = "paymentSuppliers")
    @Mapping(source = "id", target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Suppliers toEntity (SuppliersDTO suppliersDTO);

    Set<SupplierPositionsDTO> toAllSupplierPositionsDTO (Set<SupplierPositions> supplierPositions);

    Set<SupplierPositions> toAllSupplierPositions(Set<SupplierPositionsDTO> supplierPositionsDTOS);

    Set<PaymentSupplierDTO> toAllPaymentSupplierDTO(Set<PaymentSupplier> paymentSuppliers);

    Set<PaymentSupplier> toAllPaymentSupplier(Set<PaymentSupplierDTO> paymentSupplierDTOS);

}
