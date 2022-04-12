package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@Entity
@Table(name = "Unit")
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
    @OneToMany (mappedBy="unitProduction", orphanRemoval = true)
    private List<UnitProductions> unitProductionWorks = new ArrayList<>();
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

    public Set<UnitEquipmentRental> getUnitEquipmentRentals() {
        return unitEquipmentRentals;
    }

    public void setUnitEquipmentRentals(Set<UnitEquipmentRental> unitEquipmentRentals) {
        this.unitEquipmentRentals = unitEquipmentRentals;
    }

    public Set<UnitUpkeep> getUnitUpkeeps() {
        return unitUpkeeps;
    }

    public void setUnitUpkeeps(Set<UnitUpkeep> unitUpkeeps) {
        this.unitUpkeeps = unitUpkeeps;
    }

    public Set<Documentation> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(Set<Documentation> documentations) {
        this.documentations = documentations;
    }

    public Unit() {
    }

    public Set<UnitDeliveries> getUnitDeliveries() {
        return unitDeliveries;
    }

    public void setUnitDeliveries(Set<UnitDeliveries> unitDeliveries) {
        this.unitDeliveries = unitDeliveries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOrganizationCustomer() {
        return nameOrganizationCustomer;
    }

    public void setNameOrganizationCustomer(String nameOrganizationCustomer) {
        this.nameOrganizationCustomer = nameOrganizationCustomer;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getResponsibleForWork() {
        return responsibleForWork;
    }

    public void setResponsibleForWork(String responsibleForWork) {
        this.responsibleForWork = responsibleForWork;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public List<UnitProductions> getUnitProductionWorks() {
        return unitProductionWorks;
    }

    public void setUnitProductionWorks(List<UnitProductions> unitProductionWorks) {
        this.unitProductionWorks = unitProductionWorks;
    }

    public Unit(Long id, String name, Date dateStart, Date dateFinish, String address, String nameOrganizationCustomer, String contactPhoneNumber, String responsibleForWork, boolean type) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.address = address;
        this.nameOrganizationCustomer = nameOrganizationCustomer;
        this.contactPhoneNumber = contactPhoneNumber;
        this.responsibleForWork = responsibleForWork;
        this.type = type;
    }


}
