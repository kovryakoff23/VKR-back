package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "unit_production_position")
public class UnitProductionPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String namePosition;
    Long priceOne;
    Long quantity;
    Long price;
    String measure;
    boolean status;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    private UnitProductions unitProductionWorks;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="worker_id")
    Worker worker;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name = "salaryWorker_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    SalaryWorker salaryWorker;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SalaryWorker getSalaryWorker() {
        return salaryWorker;
    }

    public void setSalaryWorker(SalaryWorker salaryWorker) {
        this.salaryWorker = salaryWorker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    public Long getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Long priceOne) {
        this.priceOne = priceOne;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public UnitProductions getUnitProductionWorks() {
        return unitProductionWorks;
    }

    public void setUnitProductionWorks(UnitProductions unitProductionWorks) {
        this.unitProductionWorks = unitProductionWorks;
    }

    public UnitProductionPosition(Long id, String namePosition, Long priceOne, Long quantity, Long price, String measure, UnitProductions unitProductionWorks) {
        this.id = id;
        this.namePosition = namePosition;
        this.priceOne = priceOne;
        this.quantity = quantity;
        this.price = price;
        this.measure = measure;
        this.unitProductionWorks = unitProductionWorks;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public UnitProductionPosition(){}

}
