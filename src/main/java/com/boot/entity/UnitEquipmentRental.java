package com.boot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UnitEquipmentRental")
public class UnitEquipmentRental {
    @Id
    @Column(name = "unit_equipment_rental_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nameEquipment;
    Date dateUse;
    Long price;
    String note;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;
    public UnitEquipmentRental(){
    }
    public UnitEquipmentRental(Long id, String nameEquipment, Date dateUse, Long price, String note, Unit unit) {
        this.id = id;
        this.nameEquipment = nameEquipment;
        this.dateUse = dateUse;
        this.price = price;
        this.note = note;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEquipment() {
        return nameEquipment;
    }

    public void setNameEquipment(String nameEquipment) {
        this.nameEquipment = nameEquipment;
    }

    public Date getDateUse() {
        return dateUse;
    }

    public void setDateUse(Date dateUse) {
        this.dateUse = dateUse;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
