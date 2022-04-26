package boot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
public class UnitDTO {
    private Long id;
    private String name;
    private Date dateStart;
    private Date dateFinish;
    private String address;
    private String nameOrganizationCustomer;
    private String contactPhoneNumber;
    private String responsibleForWork;
    private  boolean type;
    @JsonIgnore
    private Set<UnitProductionsDTO> unitProductionsDTO = new HashSet<>();
    @JsonIgnore
    private Set<UnitDeliveriesDTO> unitDeliveriesDTO = new HashSet<>();
    @JsonIgnore
    private Set<DocumentationDTO> documentationsDTO = new HashSet<>();
    @JsonIgnore
    private Set<UnitEquipmentRentalDTO> unitEquipmentRentalsDTO = new HashSet<>();
    @JsonIgnore
    private Set<UnitUpkeepDTO> unitUpkeepsDTO = new HashSet<>();

}
