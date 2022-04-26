package boot.service;

import boot.DTO.UnitEquipmentRentalDTO;
import boot.mapstruct.UnitEquipmentRentalMapper;
import boot.repository.UnitEquipmentRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitEquipmentRentalService implements ServiceMag<UnitEquipmentRentalDTO>{
    UnitEquipmentRentalRepository unitEquipmentRentalRepository;

    UnitEquipmentRentalMapper unitEquipmentRentalMapper;

    @Autowired
    public UnitEquipmentRentalService(UnitEquipmentRentalRepository unitEquipmentRentalRepository,
                                      UnitEquipmentRentalMapper unitEquipmentRentalMapper) {
        this.unitEquipmentRentalRepository = unitEquipmentRentalRepository;
        this.unitEquipmentRentalMapper = unitEquipmentRentalMapper;
    }

    @Override
    public UnitEquipmentRentalDTO get(long id) {
        return unitEquipmentRentalMapper.toDTO(unitEquipmentRentalRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<UnitEquipmentRentalDTO> getAll() {
        return unitEquipmentRentalRepository.findAll().stream()
                .map(value->unitEquipmentRentalMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UnitEquipmentRentalDTO unitEquipmentRentalDTO) {
        unitEquipmentRentalRepository.save(unitEquipmentRentalMapper.toEntity(unitEquipmentRentalDTO));
    }

    @Override
    public void delete(long id) {
        unitEquipmentRentalRepository.deleteById(id);
    }
}
