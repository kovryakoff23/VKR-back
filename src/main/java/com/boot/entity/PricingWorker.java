package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
@Table(name = "PricingWorker")
public class PricingWorker {
    @Id
    @Column(name = "pricing_worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String namePosition;
    String measure;
    Long priceOne;
    String typeWork;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="worker_id")
    Worker worker;

    public PricingWorker(){}
    public PricingWorker(Long id, String namePosition, String measure, Long priceOne, Worker worker, String typeWork) {
        this.id = id;
        this.namePosition = namePosition;
        this.measure = measure;
        this.priceOne = priceOne;
        this.worker = worker;
        this.typeWork = typeWork;
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

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Long getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Long priceOne) {
        this.priceOne = priceOne;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(String typeWork) {
        this.typeWork = typeWork;
    }
}
