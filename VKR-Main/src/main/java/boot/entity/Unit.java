package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Unit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    @Column(name = "unit_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date dateStart;
    private Date dateFinish;
    private String address;
    private String nameOrganizationCustomer;
    private String contactPhoneNumber;
    private String responsibleForWork;
    private  boolean type;

    @OneToMany (mappedBy="unit", orphanRemoval = true)
    private Set<UnitProductions> unitProductions = new HashSet<>();

    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitDeliveries> unitDeliveries = new HashSet<>();

    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<Documentation> documentations = new HashSet<>();

    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitEquipmentRental> unitEquipmentRentals = new HashSet<>();

    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitUpkeep> unitUpkeeps = new HashSet<>();


}
