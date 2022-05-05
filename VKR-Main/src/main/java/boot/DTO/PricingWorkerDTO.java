package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PricingWorkerDTO {
    Long id;
    String namePosition;
    String measure;
    Long priceOne;
    String typeWork;
    WorkerDTO workerDTO;

}
