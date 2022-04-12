package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "UnitDeliveries")
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

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Set<UnitDeliveriesPosition> getUnitDeliveriesPositions() {
        return unitDeliveriesPositions;
    }

    public void setUnitDeliveriesPositions(Set<UnitDeliveriesPosition> unitDeliveriesPositions) {
        this.unitDeliveriesPositions = unitDeliveriesPositions;
    }
    public UnitDeliveries(){

    }
    public UnitDeliveries(Long id, String name, Date dateCreate, Date dateComplete, String description, boolean status, Unit unit, Set<UnitDeliveriesPosition> unitDeliveriesPositions) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.dateComplete = dateComplete;
        this.description = description;
        this.status = status;
        this.unit = unit;
        this.unitDeliveriesPositions = unitDeliveriesPositions;
    }
}
