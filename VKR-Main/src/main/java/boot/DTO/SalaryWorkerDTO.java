package boot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class SalaryWorkerDTO {
    Long id;
    Date dateSalary;
    Date dateExecution;
    String unitName;
    String namePosition;
    boolean statusExecution;
    boolean status;
    Long sumSalary;
    WorkerDTO workerDTO;
    @JsonIgnore
    UnitProductionPositionDTO unitProductionPositionDTO;

}
