package boot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitProductionPositionDTO {
    Long id;
    String namePosition;
    Long priceOne;
    Long quantity;
    Long price;
    String measure;
    boolean status;

    private UnitProductionsDTO unitProductionsDTO;

    WorkerDTO workerDTO;
    @JsonIgnore
    SalaryWorkerDTO salaryWorkerDTO;
}
