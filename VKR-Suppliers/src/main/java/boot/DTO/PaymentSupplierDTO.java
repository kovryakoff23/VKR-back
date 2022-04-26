package boot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class PaymentSupplierDTO {
    Long id;
    Date datePay;
    Date dateExecution;
    String unitName;
    String namePosition;
    boolean status;
    boolean statusExecution;
    Long sumPay;
    SuppliersDTO suppliersDTO;
    @JsonIgnore
    UnitDeliveriesPositionDTO unitDeliveriesPositionDTO;

}
