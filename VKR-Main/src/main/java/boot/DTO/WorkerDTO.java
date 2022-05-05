package boot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class WorkerDTO {
    Long id;
    String name;
    String phoneNumber;
    String numberAccount;
    String bic;
    String kpp;
    String inn;
    boolean status;
    @JsonIgnore
    private Set<UnitProductionPositionDTO> unitProductionPositionsDTO =new HashSet<>();
    @JsonIgnore
    private Set<PricingWorkerDTO> pricingWorkersDTO = new HashSet<>();
    @JsonIgnore
    private Set<SalaryWorkerDTO> salaryWorkersDTO = new HashSet<>();
}
