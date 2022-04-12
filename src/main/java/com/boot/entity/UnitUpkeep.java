package com.boot.entity;

import javax.persistence.*;

@Entity
@Table(name = "UnitUpkeep")
public class UnitUpkeep {
    @Id
    @Column(name = "unit_unitUpkeep_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    Long price;
    String note;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;
    public UnitUpkeep(){

    }
    public UnitUpkeep(Long id, String name, Long price, String note, Unit unit) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
