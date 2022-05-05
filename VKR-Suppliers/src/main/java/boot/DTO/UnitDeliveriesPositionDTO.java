package boot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitDeliveriesPositionDTO {
    Long id;
    String namePosition;
    String measure;
    Long quantity;
    Long priceOne;
    Long price;
    boolean status;

    private UnitDeliveriesDTO unitDeliveriesDTO;

    SuppliersDTO suppliersDTO;

    @JsonIgnore
    PaymentSupplierDTO paymentSupplierDTO;

}
