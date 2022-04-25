package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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
    @JsonIgnore
    @OneToMany (mappedBy="unit", orphanRemoval = true)
    private Set<UnitProductions> unitProductions = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitDeliveries> unitDeliveries = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<Documentation> documentations = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitEquipmentRental> unitEquipmentRentals = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="unit", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitUpkeep> unitUpkeeps = new HashSet<>();


}
