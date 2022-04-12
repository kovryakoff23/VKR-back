package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PaymentSupplier")
public class PaymentSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    Date datePay;
    Date dateExecution;
    String unitName;
    String namePosition;
    boolean status;
    boolean statusExecution;
    Long sumPay;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "suppliers_id")
    Suppliers suppliers;
    @JsonIgnore
    @OneToOne(mappedBy = "paymentSupplier",cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    UnitDeliveriesPosition unitDeliveriesPosition;

    public PaymentSupplier(){};

    public PaymentSupplier(Long id, Date datePay, Date dateExecution, String unitName, String namePosition, boolean status,
                           boolean statusExecution, Long sumPay, Suppliers suppliers,
                           UnitDeliveriesPosition unitDeliveriesPosition) {
        this.id = id;
        this.datePay = datePay;
        this.dateExecution = dateExecution;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.status = status;
        this.statusExecution = statusExecution;
        this.sumPay = sumPay;
        this.suppliers = suppliers;
        this.unitDeliveriesPosition = unitDeliveriesPosition;
    }

    public Date getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }

    public boolean isStatusExecution() {
        return statusExecution;
    }

    public void setStatusExecution(boolean statusExecution) {
        this.statusExecution = statusExecution;
    }

    public UnitDeliveriesPosition getUnitDeliveriesPosition() {
        return unitDeliveriesPosition;
    }

    public void setUnitDeliveriesPosition(UnitDeliveriesPosition unitDeliveriesPosition) {
        this.unitDeliveriesPosition = unitDeliveriesPosition;
    }

    public PaymentSupplier(Date datePay, String unitName, String namePosition,
                           boolean status, Long sumPay, Suppliers suppliers) {
        this.datePay = datePay;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.status = status;
        this.sumPay = sumPay;
        this.suppliers = suppliers;
        this.unitDeliveriesPosition = new UnitDeliveriesPosition();
    }

    public PaymentSupplier(Long id, Date datePay, String unitName,
                           String namePosition, boolean status, Long sumPay, Suppliers suppliers) {
        this.id = id;
        this.datePay = datePay;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.status = status;
        this.sumPay = sumPay;
        this.suppliers = suppliers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getSumPay() {
        return sumPay;
    }

    public void setSumPay(Long sumPay) {
        this.sumPay = sumPay;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }
}
