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
public class SuppliersDTO {
    private Long id;
    private String name;
    private String numberAccount;
    private String bic;
    private String kpp;
    private String inn;
    private String address;
    private String contactPhoneNumber;
    private String email;
    @JsonIgnore
    private Set<SupplierPositionsDTO> supplyPositionsDTO = new HashSet<>();
    @JsonIgnore
    private Set<PaymentSupplierDTO> paymentSuppliersDTO = new HashSet<>();
    @JsonIgnore
    private Set<UnitDeliveriesPositionDTO> unitDeliveriesPositionsDTO = new HashSet<>();

}
