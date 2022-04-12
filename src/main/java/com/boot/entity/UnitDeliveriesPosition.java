package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "UnitDeliveriesPosition")
public class UnitDeliveriesPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String namePosition;
    String measure;
    Long quantity;
    Long priceOne;
    Long price;
    boolean status;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_deliveries_id")
    private UnitDeliveries unitDeliveries;
    @ManyToOne (optional = true, cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn (name="suppliers_id")
    Suppliers suppliers;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name = "paymentSupplier_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    PaymentSupplier paymentSupplier;

    public PaymentSupplier getPaymentSupplier() {
        return paymentSupplier;
    }

    public void setPaymentSupplier(PaymentSupplier paymentSupplier) {
        this.paymentSupplier = paymentSupplier;
    }

    public UnitDeliveriesPosition(Long id, String namePosition, String measure, Long quantity, Long priceOne, Long price) {
        this.id = id;
        this.namePosition = namePosition;
        this.measure = measure;
        this.quantity = quantity;
        this.priceOne = priceOne;
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Long priceOne) {
        this.priceOne = priceOne;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public UnitDeliveriesPosition(){}

    public UnitDeliveriesPosition(Long id, String namePosition, String measure, Long quantity, Long priceOne, Long price, UnitDeliveries unitDeliveries, Suppliers suppliers) {
        this.id = id;
        this.namePosition = namePosition;
        this.measure = measure;
        this.quantity = quantity;
        this.priceOne = priceOne;
        this.price = price;
        this.unitDeliveries = unitDeliveries;
        this.suppliers = suppliers;
    }

    public UnitDeliveries getUnitDeliveries() {
        return unitDeliveries;
    }

    public void setUnitDeliveries(UnitDeliveries unitDeliveries) {
        this.unitDeliveries = unitDeliveries;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }
}
