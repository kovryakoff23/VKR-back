package boot.service;

import boot.DTO.UnitDTO;
import boot.mapstruct.UnitMapper;
import boot.repository.UnitRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitService implements ServiceMag<UnitDTO> {

    UnitRepository unitRepository;

    UnitMapper unitMapper;
    @Autowired
    public UnitService(UnitRepository unitRepository, UnitMapper unitMapper) {
        this.unitRepository = unitRepository;
        this.unitMapper = unitMapper;
    }

    @Override
    public UnitDTO get(long id) {
       return unitMapper.unitToUnitDTO(unitRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    @Synchronized
    public List<UnitDTO> getAll() {
        return unitRepository.findAll().stream()
                .map(value->unitMapper.unitToUnitDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UnitDTO unitDTO) {
        unitRepository.save(unitMapper.unitDTOToUnit(unitDTO));
    }

    @Override
    public void delete(long id) {
        unitRepository.deleteById(id);
    }
    public List<UnitDTO> getAllSearch(String search) {
        search=search+"%";
        return  unitRepository.findByName(search).stream()
                .map(value->unitMapper.unitToUnitDTO(value))
                .collect(Collectors.toList());
    }
    @Synchronized
    public List<UnitDTO> getAllActive(){
        return unitRepository.findByTypeTrue().stream()
                .map(value->unitMapper.unitToUnitDTO(value))
                .collect(Collectors.toList());
    }
}