package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Suppliers")
public class Suppliers {
    @Id
    @Column(name = "suppliers_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String numberAccount;
    private String bic;
    private String kpp;
    private String inn;
    private String address;
    private String contactPhoneNumber;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy="suppliers", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<SupplierPositions> supplyPositions = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="suppliers", cascade = CascadeType.DETACH, fetch=FetchType.LAZY)
    private Set<PaymentSupplier> paymentSuppliers = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="suppliers", cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<UnitDeliveriesPosition> unitDeliveriesPositions = new HashSet<>();

    public Set<UnitDeliveriesPosition> getUnitDeliveriesPositions() {
        return unitDeliveriesPositions;
    }

    public void setUnitDeliveriesPositions(Set<UnitDeliveriesPosition> unitDeliveriesPositions) {
        this.unitDeliveriesPositions = unitDeliveriesPositions;
    }

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

    public Set<PaymentSupplier> getPaymentSuppliers() {
        return paymentSuppliers;
    }

    public void setPaymentSuppliers(Set<PaymentSupplier> paymentSuppliers) {
        this.paymentSuppliers = paymentSuppliers;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<SupplierPositions> getSupplyPositions() {
        return supplyPositions;
    }

    public void setSupplyPositions(Set<SupplierPositions> supplyPositions) {
        this.supplyPositions = supplyPositions;
    }
    public Suppliers(){

    }
    public Suppliers(Long id, String name, String address, String contactPhoneNumber, String email, Set<SupplierPositions> supplyPositions) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactPhoneNumber = contactPhoneNumber;
        this.email = email;
        this.supplyPositions = supplyPositions;
    }
}
