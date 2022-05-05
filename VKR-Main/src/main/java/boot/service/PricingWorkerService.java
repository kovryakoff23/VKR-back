package boot.service;

import boot.DTO.PricingWorkerDTO;
import boot.mapstruct.PricingWorkerMapper;
import boot.repository.PricingWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PricingWorkerService implements ServiceMag<PricingWorkerDTO> {
    PricingWorkerRepository pricingWorkerRepository;
    PricingWorkerMapper pricingWorkerMapper;

    @Autowired
    public PricingWorkerService(PricingWorkerRepository pricingWorkerRepository,
                                PricingWorkerMapper pricingWorkerMapper) {
        this.pricingWorkerRepository = pricingWorkerRepository;
        this.pricingWorkerMapper = pricingWorkerMapper;
    }

    @Override
    public PricingWorkerDTO get(long id) {
        return pricingWorkerMapper.toDTO(pricingWorkerRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<PricingWorkerDTO> getAll() {
        return pricingWorkerRepository.findAll().stream()
                .map(value->pricingWorkerMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(PricingWorkerDTO entity) {
        pricingWorkerRepository.save(pricingWorkerMapper.toEntity(entity));
    }

    @Override
    public void delete(long id) {
        pricingWorkerRepository.deleteById(id);
    }
}
