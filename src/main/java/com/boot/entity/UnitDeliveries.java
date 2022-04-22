package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "UnitDeliveries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDeliveries {
    @Id
    @Column(name = "unit_deliveries_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    Date dateCreate;
    Date dateComplete;
    String description;
    boolean status;
    @ManyToOne(optional=false, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;
    @JsonIgnore
    @OneToMany (mappedBy="unitDeliveries", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitDeliveriesPosition> unitDeliveriesPositions = new HashSet<>();

}
