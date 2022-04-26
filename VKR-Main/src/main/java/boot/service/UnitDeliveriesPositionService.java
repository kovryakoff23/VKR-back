package boot.service;

import boot.DTO.UnitDeliveriesPositionDTO;
import boot.entity.UnitDeliveriesPosition;
import boot.mapstruct.UnitDeliveriesPositionMapper;
import boot.repository.UnitDeliveriesPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitDeliveriesPositionService implements ServiceMag<UnitDeliveriesPositionDTO>{

    UnitDeliveriesPositionRepository unitDeliveriesPositionRepository;
    UnitDeliveriesPositionMapper unitDeliveriesPositionMapper;

    RestTemplate restTemplate;

    String urlRequest = "http://localhost:8091/suppliersPositions/unitDeliveriesPosition";
    @Autowired
    public UnitDeliveriesPositionService(UnitDeliveriesPositionRepository unitDeliveriesPositionRepository,
                                         UnitDeliveriesPositionMapper unitDeliveriesPositionMapper,
                                         RestTemplate restTemplate) {
        this.unitDeliveriesPositionRepository = unitDeliveriesPositionRepository;
        this.unitDeliveriesPositionMapper = unitDeliveriesPositionMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public UnitDeliveriesPositionDTO get(long id) {
        return unitDeliveriesPositionMapper.toDTO(
                unitDeliveriesPositionRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<UnitDeliveriesPositionDTO> getAll() {

        return unitDeliveriesPositionRepository.findAll().stream()
                .map(value->unitDeliveriesPositionMapper.toDTO(value))
                .collect(Collectors.toList());
    }


    @Override
    public void save(UnitDeliveriesPositionDTO unitDeliveriesPositionDTO) {
//       PaymentSupplier paymentSupplier;
       UnitDeliveriesPosition entity = unitDeliveriesPositionMapper.toEntity(unitDeliveriesPositionDTO);
//        if (entity.getId()==null) {
//            paymentSupplier = new PaymentSupplier(
//                    entity.getUnitDeliveries().getDateComplete(),
//                    entity.getUnitDeliveries().getUnit().getName(),
//                    entity.getNamePosition(),
//                    false,
//                    entity.getPrice(),
//                    entity.getSuppliers());
//            paymentSupplier.setUnitDeliveriesPosition(null);
//            entity.setPaymentSupplier(paymentSupplier);
//        }
//        else {
//            paymentSupplier = paymentSupplierMapper.toEntity(this.get(entity.getId()).getPaymentSupplierDTO());
//            paymentSupplier.setUnitDeliveriesPosition(entity);
//            paymentSupplier.setSuppliers(entity.getSuppliers());
//            entity.setPaymentSupplier(paymentSupplier);
//        }
        UnitDeliveriesPosition unitDeliveriesPosition = unitDeliveriesPositionRepository.save(entity);
        unitDeliveriesPositionDTO.setId(unitDeliveriesPosition.getId());
        this.restTemplate.postForEntity(this.urlRequest, unitDeliveriesPositionDTO, UnitDeliveriesPositionDTO.class);
    }

    public void update(UnitDeliveriesPositionDTO unitDeliveriesPositionDTO) {
        UnitDeliveriesPosition entity = unitDeliveriesPositionMapper.toEntity(unitDeliveriesPositionDTO);
        this.restTemplate.put(this.urlRequest, unitDeliveriesPositionDTO, UnitDeliveriesPositionDTO.class);
//        PaymentSupplier paymentSupplier = paymentSupplierMapper.toEntity(this.get(entity.getId()).getPaymentSupplierDTO());
//        paymentSupplier.setStatus(!entity.isStatus());
//        paymentSupplier.setUnitDeliveriesPosition(entity);
//        entity.setPaymentSupplier(paymentSupplier);
        unitDeliveriesPositionRepository.save(entity);
    }
    @Override
    public void delete(long id) {
       unitDeliveriesPositionRepository.deleteById(id);
    }

}
