package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Worker")
public class Worker {
    @Id
    @Column(name = "worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String phoneNumber;
    String numberAccount;
    String bic;
    String kpp;
    String inn;
    boolean status;
    @JsonIgnore
    @OneToMany (mappedBy="worker",  cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<UnitProductionPosition> unitProductionPositions =new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="worker", cascade = CascadeType.DETACH, fetch=FetchType.LAZY)
    private Set<PricingWorker> pricingWorkers = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="worker", cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
    private Set<SalaryWorker> salaryWorkers = new HashSet<>();

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Set<SalaryWorker> getSalaryWorkers() {
        return salaryWorkers;
    }

    public void setSalaryWorkers(Set<SalaryWorker> salaryWorkers) {
        this.salaryWorkers = salaryWorkers;
    }

    public Worker(){
    }

    public Worker(Long id, String name, String phoneNumber, boolean status, List<UnitProductionPosition> unitProductionPositions, Set<PricingWorker> pricingWorkers) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.pricingWorkers = pricingWorkers;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<UnitProductionPosition> getUnitProductionPositions() {
        return unitProductionPositions;
    }

    public void setUnitProductionPositions(Set<UnitProductionPosition> unitProductionPositions) {
        this.unitProductionPositions = unitProductionPositions;
    }

    public Set<PricingWorker> getPricingWorkers() {
        return pricingWorkers;
    }

    public void setPricingWorkers(Set<PricingWorker> pricingWorkers) {
        this.pricingWorkers = pricingWorkers;
    }

}
