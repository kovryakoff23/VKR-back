package boot.DTO;

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
    String nameSupplier;
    private UnitDeliveriesDTO unitDeliveriesDTO;

    SuppliersDTO suppliersDTO;


}
