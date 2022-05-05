package boot.mapstruct;

import boot.DTO.SupplierPositionsDTO;
import boot.entity.SupplierPositions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SuppliersMapper.class})
public interface SupplierPositionsMapper {

    @Mapping(source = "supplierPositions.suppliers", target = "suppliersDTO")
    SupplierPositionsDTO toDTO (SupplierPositions supplierPositions);
    @Mapping(source = "supplierPositionsDTO.suppliersDTO", target = "suppliers")
    SupplierPositions toEntity (SupplierPositionsDTO supplierPositionsDTO);

}
