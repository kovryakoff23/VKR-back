package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "UnitProductions")
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
    private Unit unitProduction;
    @JsonIgnore
    @OneToMany (mappedBy="unitProductionWorks", orphanRemoval = true)
    private Set<UnitProductionPosition> unitProductionPositions = new HashSet<>();

    public UnitProductions(){

    }

    public UnitProductions(Long id, Date dateStartWork, Date dateEndWork, String nameWork, String description, boolean status, Unit unitProduction, Set<UnitProductionPosition> unitProductionPositions) {
        this.id = id;
        this.dateStartWork = dateStartWork;
        this.dateEndWork = dateEndWork;
        this.nameWork = nameWork;
        this.description = description;
        this.status = status;
        this.unitProduction = unitProduction;
        this.unitProductionPositions = unitProductionPositions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateStartWork() {
        return dateStartWork;
    }

    public void setDateStartWork(Date dateStartWork) {
        this.dateStartWork = dateStartWork;
    }

    public Date getDateEndWork() {
        return dateEndWork;
    }

    public void setDateEndWork(Date dateEndWork) {
        this.dateEndWork = dateEndWork;
    }

    public Unit getUnit() {
        return unitProduction;
    }

    public void setUnit(Unit unit) {
        this.unitProduction = unit;
    }

    public String getNameWork() {
        return nameWork;
    }

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status ;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Unit getUnitProduction() {
        return unitProduction;
    }

    public void setUnitProduction(Unit unitProduction) {
        this.unitProduction = unitProduction;
    }

    public Set<UnitProductionPosition> getUnitProductionPositions() {
        return unitProductionPositions;
    }

    public void setUnitProductionPositions(Set<UnitProductionPosition> unitProductionPositions) {
        this.unitProductionPositions = unitProductionPositions;
    }
}
