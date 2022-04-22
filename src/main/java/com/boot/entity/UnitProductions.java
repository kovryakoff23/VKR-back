package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "UnitProductions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitProductions {
    @Id
    @Column(name = "unit_production_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date dateStartWork;
    Date dateEndWork;
    String nameWork;
    String description;
    boolean status;
    @ManyToOne(optional=false, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;
    @JsonIgnore
    @OneToMany (mappedBy="unitProductionWorks", orphanRemoval = true)
    private Set<UnitProductionPosition> unitProductionPositions = new HashSet<>();

}
