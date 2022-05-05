package boot.service;

import boot.DTO.PaymentSupplierDTO;
import boot.DTO.UnitDeliveriesPositionDTO;
import boot.entity.PaymentSupplier;
import boot.mapstruct.PaymentSupplierMapper;
import boot.mapstruct.SuppliersMapper;
import boot.repository.PaymentSupplierRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentSupplierService implements ServiceMag<PaymentSupplierDTO> {
    PaymentSupplierRepository paymentSupplierRepository;
    SuppliersService suppliersService;

    PaymentSupplierMapper paymentSupplierMapper;


    SuppliersMapper suppliersMapper;

    @Autowired
    public PaymentSupplierService(PaymentSupplierRepository paymentSupplierRepository, SuppliersService suppliersService,
                                  PaymentSupplierMapper paymentSupplierMapper, SuppliersMapper suppliersMapper) {
        this.paymentSupplierRepository = paymentSupplierRepository;
        this.suppliersService = suppliersService;
        this.paymentSupplierMapper = paymentSupplierMapper;
        this.suppliersMapper=suppliersMapper;
    }

    @Override
    public PaymentSupplierDTO get(long id) {
        return paymentSupplierMapper.toDTO(paymentSupplierRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<PaymentSupplierDTO> getAll() {
        return paymentSupplierRepository.findAll().stream()
                .map(value->paymentSupplierMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(PaymentSupplierDTO entity) {
        paymentSupplierRepository.save(paymentSupplierMapper.toEntity(entity));
    }

    public void update(PaymentSupplierDTO entity) {
       paymentSupplierRepository
               .save(paymentSupplierMapper.toEntity(entity));
    }

    @Override
    public void delete(long id) {
        paymentSupplierRepository.deleteById(id);
    }

    public Set<PaymentSupplierDTO> getAllByDate(Date dateStartPay, Date dateEndPay, Long supplierId){
        return suppliersService.get(supplierId).getPaymentSuppliersDTO().stream()
                .filter(PaymentSupplierDTO::isStatus)
                .filter(value1->value1.getDatePay().after(dateStartPay))
                .filter(value1->value1.getDatePay().before(dateEndPay))
                .filter(PaymentSupplierDTO::isStatus)
                .collect(Collectors.toSet());
    }
    @Synchronized
    public List<PaymentSupplierDTO> getAllActive(){
        return paymentSupplierRepository.findByStatusTrue().stream()
                .map(value->paymentSupplierMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    public void createPaymentSupplier(UnitDeliveriesPositionDTO unitDeliveriesPositionDTO){
        PaymentSupplier paymentSupplier;
            paymentSupplier = new PaymentSupplier(
                    unitDeliveriesPositionDTO.getId(),
                    unitDeliveriesPositionDTO.getUnitDeliveriesDTO().getDateComplete(),
                    unitDeliveriesPositionDTO.getNamePosition(),
                    false,
                    unitDeliveriesPositionDTO.getPrice(),
                    suppliersMapper.toEntity(unitDeliveriesPositionDTO.getSuppliersDTO())
                    );
        paymentSupplierRepository.save(paymentSupplier);
    }

    public void updatePaymentSupplier(UnitDeliveriesPositionDTO unitDeliveriesPositionDTO) {
        PaymentSupplier paymentSupplier = paymentSupplierRepository.findById(unitDeliveriesPositionDTO.getId()).orElseThrow(IllegalArgumentException::new);
        paymentSupplier.setStatus(!unitDeliveriesPositionDTO.isStatus());
        paymentSupplierRepository.save(paymentSupplier);
    }
}
