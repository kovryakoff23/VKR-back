package boot.service;

import boot.DTO.SupplierPositionsDTO;
import boot.mapstruct.SupplierPositionsMapper;
import boot.repository.SuppliersPositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierPositionsService implements ServiceMag<SupplierPositionsDTO>{
    SuppliersPositionsRepository supplierPositionsRepository;

    SupplierPositionsMapper supplierPositionsMapper;

    @Autowired
    public SupplierPositionsService(SuppliersPositionsRepository supplierPositionsRepository,
                                    SupplierPositionsMapper supplierPositionsMapper) {
        this.supplierPositionsRepository = supplierPositionsRepository;
        this.supplierPositionsMapper = supplierPositionsMapper;
    }

    @Override
    public SupplierPositionsDTO get(long id) {
        return supplierPositionsMapper.toDTO(supplierPositionsRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<SupplierPositionsDTO> getAll() {
        return supplierPositionsRepository.findAll().stream()
                .map(value->supplierPositionsMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SupplierPositionsDTO entity) {
        supplierPositionsRepository.save(supplierPositionsMapper.toEntity(entity));
    }

    @Override
    public void delete(long id) {
        supplierPositionsRepository.deleteById(id);
    }
}
