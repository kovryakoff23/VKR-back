package boot.service;

import boot.DTO.UnitUpkeepDTO;
import boot.mapstruct.UnitUpkeepMapper;
import boot.repository.UnitUpkeepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitUpkeepService implements ServiceMag<UnitUpkeepDTO>{
    UnitUpkeepRepository unitUpkeepRepository;

    UnitUpkeepMapper unitUpkeepMapper;

    @Autowired
    public UnitUpkeepService(UnitUpkeepRepository unitUpkeepRepository, UnitUpkeepMapper unitUpkeepMapper) {
        this.unitUpkeepRepository = unitUpkeepRepository;
        this.unitUpkeepMapper = unitUpkeepMapper;
    }

    @Override
    public UnitUpkeepDTO get(long id) {
        return unitUpkeepMapper.toDTO(unitUpkeepRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<UnitUpkeepDTO> getAll() {
        return unitUpkeepRepository.findAll().stream()
                .map(value->unitUpkeepMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UnitUpkeepDTO unitUpkeepDTO) {
        unitUpkeepRepository.save(unitUpkeepMapper.toEntity(unitUpkeepDTO));
    }

    @Override
    public void delete(long id) {
        unitUpkeepRepository.deleteById(id);
    }
}
