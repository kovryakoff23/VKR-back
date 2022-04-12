package com.boot.entity;

import javax.persistence.*;

@Entity
@Table(name = "Documentation")
public class Documentation {
    @Id
    @Column(name = "pricing_worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @ManyToOne(optional=false, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;

    public Documentation(){}

    public Documentation(Long id, String name, Unit unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Documentation(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
