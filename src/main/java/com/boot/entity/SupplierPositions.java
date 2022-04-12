package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "SupplyPositions")
public class SupplierPositions {
    @Id
    @Column(name = "suppliers_positions_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String namePosition;
    String measure;
    Long priceOne;
    @ManyToOne(optional=true, cascade= CascadeType.DETACH, fetch= FetchType.EAGER)
    @JoinColumn(name="supply_id")
    private Suppliers suppliers;


    public SupplierPositions(){

    }
    public SupplierPositions(Long id, String namePosition, String measure, Long priceOne, Suppliers suppliers) {
        this.id = id;
        this.namePosition = namePosition;
        this.measure = measure;
        this.priceOne = priceOne;
        this.suppliers = suppliers;
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

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }
}
