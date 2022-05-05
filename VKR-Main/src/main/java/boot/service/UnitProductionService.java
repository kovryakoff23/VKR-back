package boot.service;

import boot.DTO.UnitProductionsDTO;
import boot.entity.UnitProductions;
import boot.mapstruct.UnitProductionsMapper;
import boot.repository.UnitProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitProductionService implements ServiceMag<UnitProductionsDTO>{

    UnitProductionRepository unitProductionRepository;

    UnitProductionPositionService unitProductionPositionService;

    UnitProductionsMapper unitProductionsMapper;
    @Autowired
    public UnitProductionService(UnitProductionRepository unitProductionRepository,
                                 UnitProductionPositionService unitProductionPositionService, UnitProductionsMapper unitProductionsMapper) {
        this.unitProductionRepository = unitProductionRepository;
        this.unitProductionPositionService = unitProductionPositionService;
        this.unitProductionsMapper=unitProductionsMapper;
    }

    @Override
    public UnitProductionsDTO get(long id) {
        return unitProductionsMapper.toDTO(unitProductionRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<UnitProductionsDTO> getAll() {
        return unitProductionRepository.findAll().stream()
                .map(value->unitProductionsMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UnitProductionsDTO unitProductionsDTO) {
        UnitProductions entity =unitProductionsMapper.toEntity(unitProductionsDTO);
        if(entity.getId()!=null && !entity.isStatus()){
            this.get(entity.getId()).getUnitProductionPositionsDTO().forEach(value->{
                value.setStatus(false);
                unitProductionPositionService.save(value);
            });
        }
        unitProductionRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        unitProductionRepository.deleteById(id);
    }

    public List<UnitProductionsDTO> getAllActive(){
        return unitProductionRepository.findByStatusTrue().stream()
                .map(value->unitProductionsMapper.toDTO(value))
                .collect(Collectors.toList());
    }

}
