package boot.service;

import boot.DTO.UnitDeliveriesDTO;
import boot.entity.UnitDeliveries;
import boot.mapstruct.UnitDeliveriesMapper;
import boot.repository.UnitDeliveriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitDeliveriesService implements ServiceMag<UnitDeliveriesDTO>{

    UnitDeliveriesRepository unitDeliveriesRepository;

    UnitDeliveriesPositionService unitDeliveriesPositionService;

    UnitDeliveriesMapper unitDeliveriesMapper;

    @Autowired
    public UnitDeliveriesService(UnitDeliveriesRepository unitDeliveriesRepository,
                                 UnitDeliveriesPositionService unitDeliveriesPositionService, UnitDeliveriesMapper unitDeliveriesMapper) {
        this.unitDeliveriesRepository = unitDeliveriesRepository;
        this.unitDeliveriesPositionService = unitDeliveriesPositionService;
        this.unitDeliveriesMapper = unitDeliveriesMapper;
    }

    @Override
    public UnitDeliveriesDTO get(long id) {
        return unitDeliveriesMapper.toDTO(unitDeliveriesRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<UnitDeliveriesDTO> getAll() {
        return unitDeliveriesRepository.findAll().stream()
                .map(value->unitDeliveriesMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UnitDeliveriesDTO unitDeliveriesDTO) {
        UnitDeliveries entity = unitDeliveriesMapper.toEntity(unitDeliveriesDTO);
        if(entity.getId()!=null && !entity.isStatus()){
            this.get(entity.getId()).getUnitDeliveriesPositionsDTO().forEach(value->{
                value.setStatus(false);
                unitDeliveriesPositionService.save(value);
            });
        }
        unitDeliveriesRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        unitDeliveriesRepository.deleteById(id);
    }

    public List<UnitDeliveriesDTO> getAllActive(){
        return unitDeliveriesRepository.findByStatusTrue().stream()
                .map(value->unitDeliveriesMapper.toDTO(value))
                .collect(Collectors.toList());
    }
}
