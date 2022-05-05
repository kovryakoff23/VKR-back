package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplierPositionsDTO {
    private Long id;
    String namePosition;
    String measure;
    Long priceOne;
    private SuppliersDTO suppliersDTO;
}
