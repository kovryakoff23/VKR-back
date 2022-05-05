package boot.service;

import boot.DTO.PaymentSupplierDTO;
import boot.component.Payment;
import boot.mapstruct.SuppliersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    PaymentSupplierService paymentSupplierService;
    SuppliersService suppliersService;

    SuppliersMapper suppliersMapper;
    @Autowired
    public PaymentService(PaymentSupplierService paymentSupplierService, SuppliersService suppliersService, SuppliersMapper suppliersMapper) {
        this.paymentSupplierService = paymentSupplierService;
        this.suppliersService = suppliersService;
        this.suppliersMapper=suppliersMapper;
    }

    public Set<Payment> getAll(){
        Set<Payment> payments = suppliersService.getAll().stream()
                .map(value->{
                    return new Payment(suppliersMapper.toEntity(value),
                            value
                                    .getPaymentSuppliersDTO()
                                    .stream()
                                    .filter(PaymentSupplierDTO::isStatus)
                                    .map(PaymentSupplierDTO::getSumPay)
                                    .mapToLong(Long::longValue)
                                    .sum());
                })
                .filter(value->value.getSumPay()>0&&value.getSumPay()!=null)
                .collect(Collectors.toSet());
        return payments;
    }
    public Set<Payment> getAllByDate(Date dateStartPay, Date dateEndPay){
        Set<Payment> payments = suppliersService.getAll().stream()
                .map(value->{
                    return new Payment(suppliersMapper.toEntity(value),

                            value
                                    .getPaymentSuppliersDTO()
                                    .stream()
                                    .filter(PaymentSupplierDTO::isStatus)
                                    .filter(value1->value1.getDatePay().after(dateStartPay))
                                    .filter(value1->value1.getDatePay().before(dateEndPay))
                                    .filter(PaymentSupplierDTO::isStatus)
                                    .map(PaymentSupplierDTO::getSumPay)
                                    .mapToLong(Long::longValue)
                                    .sum());
                })
                .filter(value->value.getSumPay()>0&&value.getSumPay()!=null)
                .collect(Collectors.toSet());
        return payments;
    }
}
